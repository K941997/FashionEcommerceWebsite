package adminProductController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import dao.CategoryDAO;
import dao.ProductDAO;
import image.ImagePathCustomFolder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Category;
import model.Product;
import model.User;

//Image
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB for Image
maxFileSize = 1024 * 1024 * 50, // 50MB for Image
maxRequestSize = 1024 * 1024 * 50) // 50MB for Image
@WebServlet(urlPatterns = {"/update-product"})
public class UpdateProductServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Get list data Category:
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = categoryDAO.getAll();
		req.setAttribute("categories", categories);
		
		String sProductId = req.getParameter("id"); //id lấy từ parameter "/update-product?id=..." from JSP list_product
		ProductDAO productDAO = new ProductDAO();
		try {
			//Ep kieu:
			int productId = Integer.parseInt(sProductId);
			Product product = new Product();
			product = productDAO.getById(productId);
			req.setAttribute("productUpdateServlet", product);
			req.getRequestDispatcher("/views/admin/update_product.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.getRequestDispatcher("/views/admin/update_product.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sProductId = req.getParameter("productId"); //hidden id PK auto_incre, có thể thêm custom id
		String productCustomId = req.getParameter("productCustomId"); //custom id
		String productName = req.getParameter("productName");
		String sProductPrice = req.getParameter("productPrice");
		String sProductOriginalPrice = req.getParameter("productOriginalPrice");
		String productDescription = req.getParameter("productDescription");
		boolean bStatusCheck = req.getParameter("productStatus")!=null;
		Part productImagePart = req.getPart("productImage"); //image
		String sCategoryId = req.getParameter("categoryId"); //Relation 1-1, 1-N
		
		ProductDAO productDAO = new ProductDAO();
		
		//Get list data Category: (tránh lỗi không hiện)
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = categoryDAO.getAll();
		req.setAttribute("categories", categories);
		
		try {
			//Ép kiểu:
			int productId = Integer.parseInt(sProductId);
			double productPrice = Double.parseDouble(sProductPrice);
			double productOriginalPrice = Double.parseDouble(sProductOriginalPrice);
			int status = 0;
			if(bStatusCheck)
				status = 1;	
			int categoryId = Integer.parseInt(sCategoryId);
			Category category = (new CategoryDAO().getById(categoryId));
			
			Product productNow = productDAO.getById(productId);
			
			//Kiểm tra:
			if(productCustomId == "" || productCustomId.equals("")) {
				//Nếu chưa nhập CustomID:
				System.out.println("CustomID is null !");
				req.setAttribute("errorUpdateProductServlet", "CustomID is null !");
				req.setAttribute("productUpdateServlet", productNow); //lấy lại data Product khi lỗi
				req.getRequestDispatcher("/views/admin/update_product.jsp").forward(req, resp);
			} else if(productName == "" || productName.equals("")) {
				//Nếu chưa nhập Name:
				System.out.println("Name is null !");
				req.setAttribute("errorUpdateProductServlet", "Name is null !");
				req.setAttribute("productUpdateServlet", productNow); //lấy lại data Product khi lỗi
				req.getRequestDispatcher("/views/admin/update_product.jsp").forward(req, resp);
			} else {
				//Nếu đã nhập hết:
				System.out.println("Da nhap CustomID va Name !");
				
				//Các trường khác không cần kiểm tra, có thể null, tự điền: (Cho len tren tranh loi)
				productNow.setProductPrice(productPrice);
				productNow.setProductOriginalPrice(productOriginalPrice);
				productNow.setProductDescription(productDescription);
				productNow.setProductStatus(status);
				productNow.setCategory(category);
				
				// image: Nếu ko thêm file thì giữ nguyên avatar của User Session:
				String imageName = productImagePart.getSubmittedFileName();
				if (imageName == null || imageName.equals("")) {
					System.out.println("Image is null !");
					productNow.setProductImage(productNow.getProductImage());
				} else {
					productNow.setProductImage(imageName);
					
					//Image Path Tomcat Folder (Auto Load Image):
					String pathTomcatFolder = req.getServletContext().getRealPath("/images") + File.separator;
					//Image Path Custom Folder (No Auto Load Image):
					ImagePathCustomFolder file = new ImagePathCustomFolder();
					String pathCustomFolder = file.getImagePathCustomFolder() + File.separator;
					
					//Image Create in Path Tomcat Folder:
					System.out.println("Tomcat Folder: " +pathTomcatFolder);
					if (!Files.exists(Path.of(pathTomcatFolder))) {
						Files.createDirectory(Path.of(pathTomcatFolder));
					}
					productImagePart.write(pathTomcatFolder + "/" + imageName);
					System.out.println("Image in Tomcat Folder: " +productImagePart);
					//Image Create in Path Custom Folder:
					System.out.println("Custom Folder: " +pathCustomFolder);
					if (!Files.exists(Path.of(pathCustomFolder))) {
						Files.createDirectory(Path.of(pathCustomFolder));
					}
					productImagePart.write(pathCustomFolder + "/" + imageName);
					System.out.println("Image in Custom Folder: " +productImagePart);
				}
				
//				//Kiem tra CustomID:
				System.out.println("Check CustomID ...");
				if(productCustomId.equals(productNow.getProductCustomId())) {
					//Neu CustomID nhap == CustomID hien tai:
					System.out.println("CustomID new == CustomID now -> Update keep CustomID !");
					productNow.setProductCustomId(productCustomId);
					//productDAO.update(productNow);
					//resp.sendRedirect("list-product"); //Không ghi nhiều tránh lỗi
				
//					//Kiem tra Name:
					System.out.println("Check Name ...");
					if(productName.equals(productNow.getProductName())) {
						//Neu Name nhap == Name hien tai:
						System.out.println("Name new == Name now -> Update keep Name !");
						productNow.setProductName(productName);
						productDAO.update(productNow);
						resp.sendRedirect("list-product");
					} else {
						//Neu Name nhap != Name hien tai:
						System.out.println("Name new != Name now -> Check Exist ...");
						
						//Kiểm tra tồn tại:
						Product productExistName = productDAO.getByName(productName);
						if(productExistName == null) {
							//Nếu chưa tồn tại Name:
							System.out.println("Name doesn't exist -> You can Update !");
							productNow.setProductName(productName);
							productDAO.update(productNow);
							resp.sendRedirect("list-product");
						} else { 
							//Nếu đã tồn tại Name:
							System.out.println("Name already exists !");
							req.setAttribute("errorUpdateProductServlet", "Name " +productName+ " already exists !");
							req.setAttribute("productUpdateServlet", productNow); //lấy lại data Product khi lỗi
							req.getRequestDispatcher("/views/admin/update_product.jsp").forward(req, resp);
						}
					}
				} else {
					//Neu CustomID nhap != CustomID hien tai:
					System.out.println("CustomID new != CustomID now -> Check Exist ...");
					
					//Kiểm tra tồn tại:
					Product productExistCustomId = productDAO.getByCustomId(productCustomId);
					if(productExistCustomId == null) {
						//Nếu chưa tồn tại CustomID:
						System.out.println("CustomID doesn't exist -> You can Update !");
						productNow.setProductCustomId(productCustomId);
						//productDAO.update(productNow);
						//resp.sendRedirect("list-product"); //Không ghi nhiều tránh lỗi 

//						//Kiem tra Name:
						System.out.println("Check Name ...");
						//Neu Name nhap == Name hien tai:
						if(productName.equals(productNow.getProductName())) {
							System.out.println("Name new == Name now-> Update keep Name !");
							productNow.setProductName(productName);
							productDAO.update(productNow);
							resp.sendRedirect("list-product");
						} else {
							//Neu Name nhap != Name hien tai:
							System.out.println("Name new != Name now -> Check Exist ...");
							
							//Kiểm tra tồn tại:
							Product productExistName = productDAO.getByName(productName);
							if(productExistName == null) {
								//Nếu chưa tồn tại Name:
								System.out.println("Name doesn't exist -> You can Update !");
								productNow.setProductName(productName);
								productDAO.update(productNow);
								resp.sendRedirect("list-product");
							} else { 
								//Nếu đã tồn tại Name:
								System.out.println("Name already exists !");
								req.setAttribute("errorUpdateProductServlet", "Name " +productName+ " already exists !");
								req.setAttribute("productUpdateServlet", productNow); //lấy lại data Product khi lỗi
								req.getRequestDispatcher("/views/admin/update_product.jsp").forward(req, resp);
							}
						}
					} else {
						//Nếu đã tồn tại CustomID:
						System.out.println("CustomID already exist !");
						req.setAttribute("errorUpdateProductServlet", "CustomID " +productCustomId+ " already exist !");
						req.setAttribute("productUpdateServlet", productNow); //lấy lại data Product khi lỗi
						req.getRequestDispatcher("/views/admin/update_product.jsp").forward(req, resp);
					}
				}
			}
	
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();

			//lấy lại data Product khi lỗi:
			int productId = Integer.parseInt(sProductId);
			Product product = new Product();
			product = productDAO.getById(productId);
			req.setAttribute("productUpdateServlet", product);
			
			req.setAttribute("errorUpdateProductServlet", "Null Number Update Product !");
			req.getRequestDispatcher("/views/admin/update_product.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			//lấy lại data Product khi lỗi:
			int productId = Integer.parseInt(sProductId);
			Product product = new Product();
			product = productDAO.getById(productId);
			req.setAttribute("productUpdateServlet", product); 
	
			req.setAttribute("errorUpdateProductServlet", "Error Update Product !");
			req.getRequestDispatcher("/views/admin/update_product.jsp").forward(req, resp);
		}
	}
}
