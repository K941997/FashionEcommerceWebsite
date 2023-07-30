package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.CartItem;
import model.Category;
import model.Color;
import model.Product;
import model.ProductColorSize;
import model.Size;

//Không cần thiết:
public class ProductColorSizeDAO extends DBConnect {
	ProductDAO productDAO = new ProductDAO();
	ColorDAO colorDAO = new ColorDAO();
	SizeDAO sizeDAO = new SizeDAO();
	
	
	//Get BẢNG TRUNG GIAN:
	//Get All:
	public List<ProductColorSize> getAll(){
		List<ProductColorSize> productColorSizes = new ArrayList<ProductColorSize>();
		String sql = "SELECT * FROM product_color_size ORDER BY product_id";
		
		try { 
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int pcsId = rs.getInt("pcs_id");
				String pcsImage = rs.getString("image");
				int productId = rs.getInt("product_id");
				int colorId = rs.getInt("color_id");
				int sizeId = rs.getInt("size_id");
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				
				Product product = (new ProductDAO().getById(productId));
				Color color = (new ColorDAO().getById(colorId));
				Size size = (new SizeDAO().getById(sizeId));
				
				
				ProductColorSize productColorSize = new ProductColorSize(
						pcsId, pcsImage, product, color, size, quantity, price
				);
				
				productColorSizes.add(productColorSize);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return productColorSizes;
	}
	
//	//WithoutBatch Create BẢNG TRUNG GIAN ProductColorSize:
//	public void insert(int productId, int sizeId, int colorId, int quantity, 
//			Timestamp createdAt, Timestamp modified_at) {
//		
//		String sqlRelation = "insert into product_color_size (product_id, size_id, color_id, quantity, "
//				+ "created_at, modified_at) values (?,?,?,?,?,?)";
//		
//		try {
//			PreparedStatement pstRelation = connection.prepareStatement(sqlRelation);
//			pstRelation.setInt(1, productId); //Relation 1-1, 1-N from ManyToMany
//			pstRelation.setInt(2, sizeId); //Relation 1-1, 1-N from ManyToMany
//			pstRelation.setInt(3, colorId); //Relation 1-1, 1-N from ManyToMany
//			pstRelation.setInt(4, quantity);
//			pstRelation.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
//			pstRelation.setTimestamp(6, new Timestamp(System.currentTimeMillis()));	
//			pstRelation.executeUpdate();
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
	
	//Create BẢNG TRUNG GIAN ProductColorSize (Dùng Batch để tạo ra với nhiều color_id, nhiều size_id cùng 1 lúc):
	public void insert(List<ProductColorSize> productColorSizes) {
		
		String sqlRelation = "insert into product_color_size (image, product_id, color_id, size_id, quantity, price, "
				+ "created_at, modified_at) values (?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement pstRelation = connection.prepareStatement(sqlRelation);

			for (ProductColorSize productColorSize: productColorSizes) {
				pstRelation.setString(1, productColorSize.getPcsImage());
				pstRelation.setInt(2, productColorSize.getProduct().getProductId()); //Relation 1-1, 1-N from ManyToMany
				pstRelation.setInt(3, productColorSize.getColor().getColorId()); //Relation 1-1, 1-N from ManyToMany
				pstRelation.setInt(4, productColorSize.getSize().getSizeId()); //Relation 1-1, 1-N from ManyToMany
				pstRelation.setInt(5, productColorSize.getQuantity());
				pstRelation.setDouble(6, productColorSize.getPrice());
				pstRelation.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
				pstRelation.setTimestamp(8, new Timestamp(System.currentTimeMillis()));	
				
				pstRelation.addBatch(); //insert many rows 1 time
			

			}
			pstRelation.executeBatch();
	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error Insert ProductColorSize !");
		}
	}
	
	
	//Find and  Remove Duplicate rows based on Multiple Columns (Bổ sung cho Add New ProductColorSize sau khi Add Product):
	//(Sau khi Insert 1 Product -> có 1 product_id):
	//(Tìm và  Xóa toàn bộ nếu tạo duplicate cùng lúc size_id & color_id):
	public ProductColorSize findAndRemoveDuplicateMultipleRows(int prodId) {
		try {
			String sqlFindDuplicate = "select product_id, color_id, size_id,"
					+ " COUNT(*) FROM product_color_size WHERE product_id=?"
					+ " GROUP BY product_id, color_id, size_id"
					+ " HAVING COUNT(*) > 1";
			PreparedStatement pstFindDuplicate = connection.prepareStatement(sqlFindDuplicate);
			pstFindDuplicate.setInt(1, prodId);
			ResultSet rs = pstFindDuplicate.executeQuery();
			while(rs.next()) {
				int productId = rs.getInt("product_id");
				int colorId = rs.getInt("color_id");
				int sizeId = rs.getInt("size_id");
				Product product = productDAO.getById(rs.getInt("product_id"));
				Color color = colorDAO.getById(rs.getInt("color_id"));
				Size size = sizeDAO.getById(rs.getInt("size_id"));
		
				ProductColorSize pcs = new ProductColorSize(
						product,
						color,
						size	
				);
				
				//Khi create Nếu có Duplicate phải xóa hết ko để thừa cái nào trong ProductColorSize:
				String sqlRemoveDuplicate = "DELETE FROM product_color_size "
						+ " WHERE product_id=?";
				PreparedStatement pstRemoveDuplicate = connection.prepareStatement(sqlRemoveDuplicate);
				pstRemoveDuplicate.setInt(1, productId);
				pstRemoveDuplicate.executeUpdate();
				pstRemoveDuplicate.close();
				
				return pcs;
			
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error findAndRemoveDuplicateMultipleRows ProductColorSize !");
		}
		return null;
	}
	
	
	//Find and Remove Duplicate and Save Lastest (Dành cho Add More ProductColorSize):
	public ProductColorSize removeDuplicateAndSaveLatest(int prodId) {
		try {			
			String sqlFindDuplicate = "select product_id, color_id, size_id,"
					+ " COUNT(*) FROM product_color_size WHERE product_id=?"
					+ " GROUP BY product_id, color_id, size_id"
					+ " HAVING COUNT(*) > 1";
			PreparedStatement pstFindDuplicate = connection.prepareStatement(sqlFindDuplicate);
			pstFindDuplicate.setInt(1, prodId);
			ResultSet rs = pstFindDuplicate.executeQuery();
			while(rs.next()) {
				int productId = rs.getInt("product_id");
				int colorId = rs.getInt("color_id");
				int sizeId = rs.getInt("size_id");
				
				Product product = productDAO.getById(rs.getInt("product_id"));
				Color color = colorDAO.getById(rs.getInt("color_id"));
				Size size = sizeDAO.getById(rs.getInt("size_id"));
				
				ProductColorSize pcs = new ProductColorSize(
						product,
						color,
						size
				);

				//Nếu có Duplicate phải xóa hết ko để thừa cái nào trong ProductColorSize:
				String sqlRemoveDuplicate = "DELETE t1 FROM product_color_size t1 "
						+ "INNER JOIN product_color_size t2 "
						+ "WHERE t1.color_id = t2.color_id "
						+ "AND t1.size_id = t2.size_id "
						+ "AND t1.pcs_id < t2.pcs_id;";
				PreparedStatement pstRemoveDuplicate = connection.prepareStatement(sqlRemoveDuplicate);
				pstRemoveDuplicate.executeUpdate();
				pstRemoveDuplicate.close();
				
				return pcs;
			
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error removeDuplicateAndSaveLatest ProductColorSize !");
		}
		return null;
	}
	

	//Get List ProductColorSize by product_id (Giống getProductsByCategoryId): (Đang làm)
	public List<ProductColorSize> getListPCSByProductId(int prodId) {
		List<ProductColorSize> listProductColorSize = new ArrayList<ProductColorSize>();
		String sql = "SELECT * FROM product_color_size "
				+ " INNER JOIN product ON product_color_size.product_id = product.product_id"
				+ " INNER JOIN color ON product_color_size.color_id = color.color_id"
				+ " INNER JOIN size ON product_color_size.size_id = size.size_id"
				+ " WHERE product_color_size.product_id=?"
				+ " ORDER BY product_color_size.color_id";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, prodId);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int pcsId = rs.getInt("pcs_id");
				String pcsImage = rs.getString("image");
				int productId = rs.getInt("product_id");
				int colorId = rs.getInt("color_id");
				int sizeId = rs.getInt("size_id");
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				
				Product product = (new ProductDAO().getById(productId));
				Color color = (new ColorDAO().getById(colorId));
				Size size = (new SizeDAO().getById(sizeId));
			
				ProductColorSize productColorSize = new ProductColorSize(
						pcsId, pcsImage, product, color, size, quantity, price
				);
				
				listProductColorSize.add(productColorSize);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return listProductColorSize;
	}
	
	//Get PCS By Product, Color, Size:
	public ProductColorSize getPCSByProductAndColorAndSize(int productId, int colorId, int sizeId) {
		String sql = "SELECT * FROM product_color_size WHERE product_id=? "
				+ "	AND color_id=? AND size_id =? ";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, productId);
			pst.setInt(2, colorId);
			pst.setInt(3, sizeId);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Product product = productDAO.getById(rs.getInt("product_id"));
				Color color = colorDAO.getById(rs.getInt("color_id"));
				Size size = sizeDAO.getById(rs.getInt("size_id"));
				
				ProductColorSize pcs = new ProductColorSize(
						rs.getInt("pcs_id"),
						rs.getString("image"),
						product, //1-1 product
						color,
						size,
						rs.getInt("quantity"),
						rs.getDouble("price")
				);	
				return pcs;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//Get By Id:
	public ProductColorSize getById(int pcsId) {
		String sql = "SELECT * FROM product_color_size WHERE pcs_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, pcsId);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Product product = productDAO.getById(rs.getInt("product_id"));
				Color color = colorDAO.getById(rs.getInt("color_id"));
				Size size = sizeDAO.getById(rs.getInt("size_id"));
				
				ProductColorSize pcs = new ProductColorSize(
						rs.getInt("pcs_id"),
						rs.getString("image"),
						product, //1-1 product
						color,
						size,
						rs.getInt("quantity"),
						rs.getDouble("price")
				);	
				return pcs;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//Update:
	public void update(ProductColorSize pcs) {
		String sql = "UPDATE product_color_size SET image=?, quantity=?, price=?, modified_at=? WHERE pcs_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, pcs.getPcsImage());
			pst.setInt(2, pcs.getQuantity());
			pst.setDouble(3, pcs.getPrice());
			pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pst.setInt(5, pcs.getPcsId());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Delete:
	public void delete(int pcsId) {
		String sql = "DELETE FROM product_color_size WHERE pcs_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, pcsId);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//(Client) Lấy Price Max theo ProductId: (Hiển thị giá ở index)
		public double getMaxPricePCS(int productId) {
			String sql = "SELECT MAX(price) AS max_price FROM product_color_size WHERE product_id = ?";
			 double maxPrice = 0.0;
			try {
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setInt(1, productId);
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					maxPrice = rs.getDouble("max_price");
					
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return maxPrice;
		}
		
		
		//(Client) Lấy  Price Min theo ProductId: (Hiển thị giá ở index):
		public double getMinPricePCS(int productId) {
			String sql = "SELECT MIN(price) AS min_price FROM product_color_size WHERE product_id = ?";
			 double minPrice = 0.0;
			try {
				PreparedStatement pst = connection.prepareStatement(sql);
				pst.setInt(1, productId);
				
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					minPrice = rs.getDouble("min_price");
						
					
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return minPrice;
		}
	
	
	//(Client) AddToCart ListProductColorSize:  (Làm trong JSP Giỏ Hàng cart_products)
	public List<CartItem> getCartProducts(ArrayList<CartItem> cartList){
		List<CartItem> products = new ArrayList<CartItem>();
		
		try {
			if(cartList.size()>0) {
				//Hiển thị thông tin CartItem trong Cart:
				for(CartItem cartItem: cartList) {
					String sql = "SELECT * FROM product_color_size WHERE pcs_id = ?";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setInt(1, cartItem.getPcsId());
					ResultSet rs = pst.executeQuery();
					while(rs.next()) {
						Product product = productDAO.getById(rs.getInt("product_id"));
						Color color = colorDAO.getById(rs.getInt("color_id"));
						Size size = sizeDAO.getById(rs.getInt("size_id"));
						
						//Hiển thị thông tin CartItem trong Cart:
						CartItem row = new CartItem();
						row.setPcsId(rs.getInt("pcs_id"));
						row.setPcsImage(rs.getString("image"));
						row.setProduct(product);
						row.setColor(color);
						row.setSize(size);
						row.setQuantityCartItem(cartItem.getQuantityCartItem());
						row.setPrice(rs.getDouble("price")*cartItem.getQuantityCartItem());
				

						products.add(row);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return products;
	}
	
	
	//(Client) Get TotalPrice của Cart: (Làm trong JSP Giỏ Hàng cart_products)
	public double getTotalCartPrice(ArrayList<CartItem> cartList) {
		double sum = 0;
		
		try {
			if(cartList.size()>0) {
				for(CartItem cartItem: cartList) {
					String sql = "SELECT price FROM product_color_size WHERE pcs_id = ?";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.setInt(1, cartItem.getPcsId());
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()) {
						sum+=rs.getDouble("price")*cartItem.getQuantityCartItem();
					}
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return sum;
	}
	
	
	//(Client) (Phần Servlet Checkout cho JSP Cart) Sau khi Order Checkout thành công -> trừ Quantity của ProductColorSize đi 1:
	public void subtractQuantity(int pcsId, int quantity) {
        String sql = "UPDATE product_color_size SET quantity=quantity-? WHERE pcs_id=?";
    	
    	try {
        	PreparedStatement pst = connection.prepareStatement(sql);
        	pst.setInt(1, quantity);
        	pst.setInt(2, pcsId);
        	pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }   
    }
}
	
	
	

