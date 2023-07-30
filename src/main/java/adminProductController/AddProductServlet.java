package adminProductController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.CategoryDAO;
import dao.ColorDAO;
import dao.ProductDAO;
import dao.SizeDAO;
import image.ImagePathCustomFolder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Category;
import model.Color;
import model.Product;
import model.ProductColorSize;
import model.Size;

//Image
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB for Image
maxFileSize = 1024 * 1024 * 50, // 50MB for Image
maxRequestSize = 1024 * 1024 * 50) // 50MB for Image

@WebServlet(urlPatterns = {"/add-product"})
public class AddProductServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Goi danh sach Category khi Create Product:
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = categoryDAO.getAll();
		req.setAttribute("categories", categories);

		//Send Data to JSP:
		req.getRequestDispatcher("/views/admin/add_product.jsp").forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String sProductId = req.getParameter("productId"); //hidden id PK auto_incre, có thể thêm custom id
		String productCustomId = req.getParameter("productCustomId"); //custom id
		String productName = req.getParameter("productName");
		String sProductPrice = req.getParameter("productPrice");
		String sProductOriginalPrice = req.getParameter("productOriginalPrice");
		String productDescription = req.getParameter("productDescription");
		//boolean bStatusCheck = req.getParameter("productStatus")!=null; //status with boolean
		String statusCheck = req.getParameter("productStatus");	//status with int
		Part productImagePart = req.getPart("productImage"); //image
		
		String sCategoryId = req.getParameter("categoryId"); //Relation 1-1, 1-N
		
		ProductDAO productDAO = new ProductDAO();
		
		try {
			//Ép kiểu:
			//int productId = Integer.parseInt(sProductId); //hidden id PK auto_incre, có thể thêm custom id
			double productPrice = Double.parseDouble(sProductPrice);
			double productOriginalPrice = Double.parseDouble(sProductOriginalPrice);
			int status = Integer.parseInt(statusCheck);
			
			//Category là Object nên phải lấy Object:
			int categoryId = Integer.parseInt(sCategoryId);
			CategoryDAO categoryDAO = new CategoryDAO();
			Category category = categoryDAO.getById(categoryId);
			
			//Goi danh sách Category để tránh bị lỗi không hiệnr thị:
			CategoryDAO categoryDAO1 = new CategoryDAO();
			List<Category> categories = categoryDAO1.getAll();
			req.setAttribute("categories", categories);
		
			// image:
			String imageName = productImagePart.getSubmittedFileName();
	
			//Kiểm tra:
			if(productCustomId == "" || productCustomId.equals("")) {
				//Nếu chưa nhập CustomID:
				System.out.println("CustomID is null !");
				req.setAttribute("errorAddProductServlet", "CustomID is null !");
				req.getRequestDispatcher("/views/admin/add_product.jsp").forward(req, resp);
			} else if(productName == "" || productName.equals("")) {
				//Nếu chưa nhập Name:
				System.out.println("Name is null !");
				req.setAttribute("errorAddProductServlet", "Name is null !");
				req.getRequestDispatcher("/views/admin/add_product.jsp").forward(req, resp);
			} else if (imageName == null || imageName.equals("")) {
				//Nếu chưa nhâp Image:
				System.out.println("Image is null !");
				req.setAttribute("errorAddProductServlet", "Image is null !");
				req.getRequestDispatcher("/views/admin/add_product.jsp").forward(req, resp);
			} else{ 
				//Nếu đã nhập hết:
				System.out.println("Da nhap het !");
				
				//Kiểm tra tồn tại:
				System.out.println("Check Exist ...");
				Product productExistCustomId = productDAO.getByCustomId(productCustomId);
				Product productExistName = productDAO.getByName(productName);
				
				if(productExistCustomId != null) {
					//Nếu đã tồn tại CustomID:
					System.out.println("CustomID already exists !");
					req.setAttribute("errorAddProductServlet", "CustomID " +productCustomId+ " already exists !");
					req.getRequestDispatcher("/views/admin/add_product.jsp").forward(req, resp);
				} else if(productExistName != null) {
					//Nếu đã tồn tại Name:
					System.out.println("Name already exists !");
					req.setAttribute("errorAddProductServlet", "Name " +productName+ " already exists !");
					req.getRequestDispatcher("/views/admin/add_product.jsp").forward(req, resp);
				
				} else  {
					//Nếu chưa tồn tại:
					System.out.println("CustomID and Name don't exist -> You can Create !");

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
					
					//Create:
					productDAO.insert(productCustomId, productName, imageName,
							productPrice, productOriginalPrice, productDescription, 
							status, categoryId, null, null);

					//Sau khi ấn Button Add:
					System.out.println("Them Product thanh cong !");

					//Lấy id ProductNew cho ManyToMany ProductColorSize:
					System.out.println("Send Id ProductNew to ManyToMany ProductColorSize !");
					Product productAfterAdd = productDAO.getByCustomId(productCustomId);
					System.out.println(productAfterAdd);	
					
					//Dùng req.setAttribute khi sendRedirect sẽ ko có data: (Không dùng)
					req.setAttribute("productFromAdd", productAfterAdd);
					req.setAttribute("productIdFromAdd", productAfterAdd.getProductId());
					req.setAttribute("productNameFromAdd", productAfterAdd.getProductName());
					
					//Dùng Session khi sendRedirect sẽ có data: (Dùng)
					HttpSession session  = req.getSession();
					session.setAttribute("productFromAddSession", productAfterAdd);
					session.setAttribute("productIdFromAddSession", productAfterAdd.getProductId());
					session.setAttribute("productNameFromAddSession", productAfterAdd.getProductName());
					
					System.out.println("Send data den JSP add_product_color_size !");
			
					resp.sendRedirect("add-product-color-size");
				}		
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorAddProductServlet", "Null Number When Add Product ! ");
			req.getRequestDispatcher("/views/admin/add_product.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorAddProductServlet", "Error Add Product ! ");
			req.getRequestDispatcher("/views/admin/add_product.jsp").forward(req, resp);
		}
	}
	
}
