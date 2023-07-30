package dao;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import model.CartItem;
import model.Category;
import model.Color;
import model.Product;
import model.ProductColorSize;
import model.Size;

public class ProductDAO extends DBConnect {
	CategoryDAO categoryDAO = new CategoryDAO();
	//Get All:
	public List<Product> getAll(){
		List<Product> products = new ArrayList<Product>();
		String sql = "SELECT p.product_id, p.custom_id, p.name, p.image, p.price,"
				+ " p.original_price, p.description, p.status, "
				+ " p.category_id AS category_id, "
				+ " SUM(pcs.quantity) AS sumQuantity"
				+ " FROM product p "
				+ " LEFT JOIN category ON p.category_id = category.category_id"
				+ "	LEFT JOIN product_color_size pcs ON p.product_id = pcs.product_id "
				+ " GROUP BY p.product_id";
		
		try { 
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int productId = rs.getInt("product_id");
				String productCustomId = rs.getString("custom_id");
				String productName = rs.getString("name");
				String productImage = rs.getString("image");
				double productPrice = rs.getDouble("price");
				double productOriginalPrice = rs.getDouble("original_price");
				String productDescription = rs.getString("description");
				int productStatus = rs.getInt("status");
				int categoryId = rs.getInt("category_id");
				Category category = (new CategoryDAO().getById(categoryId));
//				int sumQuantity = rs.getInt("sumQuantity"); //Fail
				
				//Test show Sum Quantity in JSP: (Fail)
//				Product product = new Product(
//						productId, productCustomId, productName, productImage, productPrice,
//						productOriginalPrice, productDescription,
//						productStatus, category, sumQuantity
//				);
				
				Product product = new Product(
						productId, productCustomId, productName, productImage, productPrice,
						productOriginalPrice, productDescription,
						productStatus, category
				);
				
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return products;
	}
	
	//Get By Id:
	public Product getById(int productId) {
		String sql = "SELECT * FROM product WHERE product_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, productId);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Category category = categoryDAO.getById(rs.getInt("category_id"));
				Product product = new Product(
						rs.getInt("product_id"), 
						rs.getString("custom_id"), 
						rs.getString("name"),
						rs.getString("image"),
						rs.getDouble("price"),
						rs.getDouble("original_price"), 
						rs.getString("description"), 
						rs.getInt("status"), 
						category //1-1 category
				);	
				return product;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//Get By Custom Id:
	public Product getByCustomId(String productCustomId) {
		String sql = "SELECT * FROM product WHERE custom_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, productCustomId);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Category category = categoryDAO.getById(rs.getInt("category_id"));
				Product product = new Product(
						rs.getInt("product_id"), 
						rs.getString("custom_id"), 
						rs.getString("name"),
						rs.getString("image"),
						rs.getDouble("price"),
						rs.getDouble("original_price"), 
						rs.getString("description"), 
						rs.getInt("status"), 
						category //1-1 category
				);
				return product;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//Get By Name:
	public Product getByName(String name) {
		String sql = "SELECT * FROM product WHERE name = ?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Category category = categoryDAO.getById(rs.getInt("category_id"));
				Product product = new Product(
						rs.getInt("product_id"), 
						rs.getString("custom_id"), 
						rs.getString("name"),
						rs.getString("image"),
						rs.getDouble("price"),
						rs.getDouble("original_price"), 
						rs.getString("description"), 
						rs.getInt("status"), 
						category //1-1 category
				);
				return product;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
		
	
//	//Create 1:
//	public void insert(Product product) {
//		String sql = "insert into product values (?,?,?,?,?,?,?,?,?,?,?)";
//		try {
//			PreparedStatement pst = connection.prepareStatement(sql);
//			pst.setInt(1, product.getProductId());
//			pst.setString(2, product.getProductCustomId());
//			pst.setString(3, product.getProductName());
//			pst.setString(4, product.getProductImage());
//			pst.setInt(5, product.getProductPrice());
//			pst.setInt(6, product.getProductOriginalPrice());
//			pst.setString(6, product.getProductDescription());
//			pst.setInt(8, product.getProductStatus());
//			pst.setInt(9, product.getCategory().getCategoryId());
//			pst.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
//			pst.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
//	
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//	}
	
	//Create 2:
	public void insert( String customId, String name, String image,
			double price, double originalPrice, String description, int status,
			int categoryId, Timestamp createdAt, Timestamp modified_at) {
		String sql = "insert into product (custom_id, name, image, price,"
				+ "original_price, description, status, category_id, "
				+ "created_at, modified_at) values (?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
		
			pst.setString(1, customId);
			pst.setString(2, name);
			pst.setString(3, image);
			pst.setDouble(4, price);
			pst.setDouble(5, originalPrice);
			pst.setString(6, description);
			pst.setInt(7, status);
			pst.setInt(8, categoryId); //1-1 Category
			pst.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
			pst.setTimestamp(10, new Timestamp(System.currentTimeMillis()));	
			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	//Update:
	public void update(Product product) {
		String sql = "UPDATE product SET custom_id=?, name=?, image=?, "
				+ "price=?, original_price=?, "
				+ " description=?, "
				+ "status=?, category_id=?, modified_at=? WHERE product_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1, product.getProductCustomId());
			pst.setString(2, product.getProductName());
			pst.setString(3, product.getProductImage());
			pst.setDouble(4, product.getProductPrice());
			pst.setDouble(5, product.getProductOriginalPrice());
			pst.setString(6, product.getProductDescription());
			pst.setInt(7, product.getProductStatus());
			pst.setInt(8, product.getCategory().getCategoryId()); //1-1 Category
			pst.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
			pst.setInt(10, product.getProductId());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Delete:
	public void delete(int productId) throws SQLException {
		String sql = "DELETE FROM product WHERE product_id=?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, productId);
			pst.executeUpdate();
	}
	
	//Paginate:
	public List<Product> getProductsByPage(List<Product> products, int start, int end){
		ArrayList<Product> arr = new ArrayList<>();
		for(int i = start; i<end; i++) {
			arr.add(products.get(i));
		}
		return arr;
		
	}
	
	//TabList (Lọc theo Category) (Phần JSP giống index, ko cần hiển thị từng PCS, gộp chung 1 Product):
	public List<Product> getProductsByCategoryId(int cateId){
		List<Product> products = new ArrayList<Product>();
		String sql = "SELECT * FROM product "
				+ "INNER JOIN category ON product.category_id = category.category_id "
				+ "WHERE product.category_id = ?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, cateId);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int productId = rs.getInt("product_id");
				String productCustomId = rs.getString("custom_id");
				String productName = rs.getString("name");
				String productImage = rs.getString("image");
				double productPrice = rs.getDouble("price");
				double productOriginalPrice = rs.getDouble("original_price");
				String productDescription = rs.getString("description");
				int productStatus = rs.getInt("status");
				int categoryId = rs.getInt("category_id");
				
				Category category = (new CategoryDAO().getById(categoryId));
				
				Product product = new Product(
						productId, productCustomId, productName, productImage, productPrice,
						productOriginalPrice, productDescription,
						productStatus, category
				);
				
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return products;
	}
	
	//FilterCheckbox Categories (Lọc theo Nhiều CategoriesIds):
	public List<Product> getProductsByCategoryIds(int[] cateIds){
		List<Product> products = new ArrayList<>();
		String sql = "SELECT * FROM product "
				+ "INNER JOIN category ON product.category_id = category.category_id "
				+ "WHERE 1=1 ";
		
		if(cateIds != null && cateIds[0] != 0) {
			sql += " and product.category_id in (";
			for(int i = 0; i < cateIds.length; i++) {
				sql += cateIds[i] + ",";
			}
			if(sql.endsWith(",")) {
				sql = sql.substring(0, sql.length()-1);
			}
			sql += ")";
		}
		
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int productId = rs.getInt("product_id");
				String productCustomId = rs.getString("custom_id");
				String productName = rs.getString("name");
				String productImage = rs.getString("image");
				double productPrice = rs.getDouble("price");
				double productOriginalPrice = rs.getDouble("original_price");
				String productDescription = rs.getString("description");
				int productStatus = rs.getInt("status");
				int categoryId = rs.getInt("category_id");
				
				Category category = (new CategoryDAO().getById(categoryId));
				
				Product product = new Product(
						productId, productCustomId, productName, productImage, productPrice,
						productOriginalPrice, productDescription,
						productStatus, category
				);
				
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return products;
	}
	
	//Search By Name:
	public List<Product> searchProductsByKey(String keySearch){
		List<Product> products = new ArrayList<>();
		String sql = "SELECT * FROM product "
				+ "INNER JOIN category ON product.category_id = category.category_id "
				+ "WHERE product.name LIKE ?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, "%"+keySearch+"%");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int productId = rs.getInt("product_id");
				String productCustomId = rs.getString("custom_id");
				String productName = rs.getString("name");
				String productImage = rs.getString("image");
				double productPrice = rs.getDouble("price");
				double productOriginalPrice = rs.getDouble("original_price");
				String productDescription = rs.getString("description");
				int productStatus = rs.getInt("status");
				int categoryId = rs.getInt("category_id");
				
				Category category = (new CategoryDAO().getById(categoryId));
				
				Product product = new Product(
						productId, productCustomId, productName, productImage, productPrice,
						productOriginalPrice, productDescription,
						productStatus, category
				);
				
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return products;
	}
	
	//FilterCheckbox Prices:
	public List<Product> getProductsByPrice(double from, double to){
		List<Product> products = new ArrayList<>();
		String sql = "SELECT p.product_id, p.custom_id, p.name, pcs.image, pcs.price, p.original_price, "
				+ " p.description, p.status, p.category_id "
				+ " FROM product p  "
				+ " JOIN product_color_size pcs "
				+ " ON pcs.product_id = p.product_id "
				+ " WHERE pcs.price BETWEEN ? AND ? "
				+ " ORDER BY pcs.price ASC";
		
//		String sql = "SELECT * FROM product "
//				+ "WHERE price BETWEEN ? AND ?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setDouble(1, from);
			pst.setDouble(2, to);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int productId = rs.getInt("product_id");
				String productCustomId = rs.getString("custom_id");
				String productName = rs.getString("name");
				String productImage = rs.getString("image");
				double productPrice = rs.getDouble("price");
				double productOriginalPrice = rs.getDouble("original_price");
				String productDescription = rs.getString("description");
				int productStatus = rs.getInt("status");
				int categoryId = rs.getInt("category_id");
				
				Category category = (new CategoryDAO().getById(categoryId));
				
				Product product = new Product(
						productId, productCustomId, productName, productImage, productPrice,
						productOriginalPrice, productDescription,
						productStatus, category
				);
				
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return products;
	}
	
	//Sort DESC Sắp xếp Product theo Price tăng dần của Price Max của PCS:
	public List<Product> getProductsByDecreasePrice(){
		List<Product> products = new ArrayList<>();
		String sql = "SELECT p.product_id, p.custom_id, p.name, p.image, pcs.price, p.original_price, "
				+ " p.description, p.status, p.category_id "
				+ " FROM product p  "
				+ " JOIN ( SELECT product_id, MAX(price) AS max_price "
				+ " FROM product_color_size GROUP BY product_id "
				+ " ) AS pcsm "
				+ " ON p.product_id = pcsm.product_id "
				+ " JOIN product_color_size pcs "
				+ " ON pcs.product_id = p.product_id AND pcs.price = pcsm.max_price "
				+ " ORDER BY pcs.price DESC";
		
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int productId = rs.getInt("product_id");
				String productCustomId = rs.getString("custom_id");
				String productName = rs.getString("name");
				String productImage = rs.getString("image");
				double productPrice = rs.getDouble("price");
				double productOriginalPrice = rs.getDouble("original_price");
				String productDescription = rs.getString("description");
				int productStatus = rs.getInt("status");
				int categoryId = rs.getInt("category_id");
				
				Category category = (new CategoryDAO().getById(categoryId));
				
				Product product = new Product(
						productId, productCustomId, productName, productImage, productPrice,
						productOriginalPrice, productDescription,
						productStatus, category
				);
				
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return products;
	}
	
	//Sort ASC Sắp xếp Product theo Price tăng dần của Price Max của PCS:
	public List<Product> getProductsByAscendingPrice(){
		List<Product> products = new ArrayList<>();
		String sql = "SELECT p.product_id, p.custom_id, p.name, p.image, pcs.price, p.original_price, "
				+ " p.description, p.status, p.category_id "
				+ " FROM product p  "
				+ " JOIN ( SELECT product_id, MAX(price) AS max_price "
				+ " FROM product_color_size GROUP BY product_id "
				+ " ) AS pcsm "
				+ " ON p.product_id = pcsm.product_id "
				+ " JOIN product_color_size pcs "
				+ " ON pcs.product_id = p.product_id AND pcs.price = pcsm.max_price "
				+ " ORDER BY pcs.price ASC";
		
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int productId = rs.getInt("product_id");
				String productCustomId = rs.getString("custom_id");
				String productName = rs.getString("name");
				String productImage = rs.getString("image");
				double productPrice = rs.getDouble("price");
				double productOriginalPrice = rs.getDouble("original_price");
				String productDescription = rs.getString("description");
				int productStatus = rs.getInt("status");
				int categoryId = rs.getInt("category_id");
				
				Category category = (new CategoryDAO().getById(categoryId));
				
				Product product = new Product(
						productId, productCustomId, productName, productImage, productPrice,
						productOriginalPrice, productDescription,
						productStatus, category
				);
				
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return products;
	}
	
	
//	//Sort By Bán chạy nhất (Đếm số lần status = complete):
//	public List<Product> sortByBestSell(){
//		List<Product> products = new ArrayList<>();
//		String sql = "SELECT *, COUNT(*) as total_complete"
//				+ " FROM product "
//				+ " JOIN ";
//		return products;
//	}
//	
	
	//Sort By Newness:
	public List<Product> sortByNewness(){
		List<Product> products = new ArrayList<>();
		String sql = "SELECT * FROM product ORDER BY created_at DESC";
		
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int productId = rs.getInt("product_id");
				String productCustomId = rs.getString("custom_id");
				String productName = rs.getString("name");
				String productImage = rs.getString("image");
				double productPrice = rs.getDouble("price");
				double productOriginalPrice = rs.getDouble("original_price");
				String productDescription = rs.getString("description");
				int productStatus = rs.getInt("status");
				int categoryId = rs.getInt("category_id");
				
				Category category = (new CategoryDAO().getById(categoryId));
				
				Product product = new Product(
						productId, productCustomId, productName, productImage, productPrice,
						productOriginalPrice, productDescription,
						productStatus, category
				);
				
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return products;
	}
	
	
	//Get Top 4 Products for Client:
	public List<Product> getTop8Products() {
		List<Product> products = new ArrayList<Product>();
		String query = "SELECT * FROM product LIMIT 8";
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int productId = rs.getInt("product_id");
				String productCustomId = rs.getString("custom_id");
				String productName = rs.getString("name");
				String productImage = rs.getString("image");
				double productPrice = rs.getDouble("price");
				double productOriginalPrice = rs.getDouble("original_price");
				String productDescription = rs.getString("description");
				int productStatus = rs.getInt("status");
				int categoryId = rs.getInt("category_id");
				
				Category category = (new CategoryDAO().getById(categoryId));
				
				Product product = new Product(
						productId, productCustomId, productName, productImage, productPrice,
						productOriginalPrice, productDescription,
						productStatus, category
				);
				
				products.add(product);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return products;
	}
	
	
	//Get Next Top 4 Products for Client Load More AJAX + OFFSET:
	public List<Product> getNext8Products(int amount) {
		List<Product> products = new ArrayList<Product>();
		String query = "SELECT * FROM product ORDER BY product_id LIMIT ?, 8; ";
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, amount);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int productId = rs.getInt("product_id");
				String productCustomId = rs.getString("custom_id");
				String productName = rs.getString("name");
				String productImage = rs.getString("image");
				double productPrice = rs.getDouble("price");
				double productOriginalPrice = rs.getDouble("original_price");
				String productDescription = rs.getString("description");
				int productStatus = rs.getInt("status");
				int categoryId = rs.getInt("category_id");
				
				Category category = (new CategoryDAO().getById(categoryId));
				
				Product product = new Product(
						productId, productCustomId, productName, productImage, productPrice,
						productOriginalPrice, productDescription,
						productStatus, category
				);
				
				products.add(product);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return products;
	}
	
	//Get List Product By Color của PCS của Product:
	public List<Product> getProductsByColorId(int colorId){
		List<Product> products = new ArrayList<Product>();
		String sql = "SELECT DISTINCT p.product_id, p.custom_id, p.name, pcs.image, pcs.price, "
				+ " p.original_price, p.description, p.status, p.category_id "
				+ " FROM product p"
				+ " INNER JOIN product_color_size pcs ON p.product_id = pcs.product_id "
				+ " WHERE pcs.color_id = ?";
		
		
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, colorId);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				int productId = rs.getInt("product_id");
				String productCustomId = rs.getString("custom_id");
				String productName = rs.getString("name");
				String productImage = rs.getString("image");
				double productPrice = rs.getDouble("price");
				double productOriginalPrice = rs.getDouble("original_price");
				String productDescription = rs.getString("description");
				int productStatus = rs.getInt("status");
				int categoryId = rs.getInt("category_id");
				
				Category category = (new CategoryDAO().getById(categoryId));
				
				Product product = new Product(
						productId, productCustomId, productName, productImage, productPrice,
						productOriginalPrice, productDescription,
						productStatus, category
				);
				
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return products;
	}
	
	
}
