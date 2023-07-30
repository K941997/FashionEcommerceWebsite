package adminProductController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import dao.ColorDAO;
import dao.ProductColorSizeDAO;
import dao.ProductDAO;

import dao.SizeDAO;
import image.ImagePathCustomFolder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Color;
import model.Product;
import model.ProductColorSize;
import model.Size;

//Image
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB for Image
maxFileSize = 1024 * 1024 * 50, // 50MB for Image
maxRequestSize = 1024 * 1024 * 50) // 50MB for Image
@WebServlet(urlPatterns = {"/update-product-color-size"})
public class UpdateProductColorSizeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Send danh sach Color khi Create Product ManyToMany:
		ColorDAO colorDAO = new ColorDAO();
		List<Color> colors = colorDAO.getAll();
		req.setAttribute("colors", colors);
		
		//Send danh sach Size khi Create Product: 
		SizeDAO sizeDAO = new SizeDAO();
		List<Size> sizes = sizeDAO.getAll();
		req.setAttribute("sizes", sizes);
				
		String sPCSId = req.getParameter("idPCS"); //id_ProductColorSize lấy từ "update-product-size-color?idPCS=" from JSP get_product_color_size
		ProductColorSizeDAO pcsDAO = new ProductColorSizeDAO();
		try {
			int pcsId = Integer.parseInt(sPCSId);
			ProductColorSize pcs = new ProductColorSize();
			pcs = pcsDAO.getById(pcsId);
			req.setAttribute("pcsUpdateProductColorSizeServlet", pcs);
			req.getRequestDispatcher("/views/admin/update_product_color_size.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.getRequestDispatcher("/views/admin/update_product_color_size.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sPCSId = req.getParameter("pcsId"); //đã hidden vì id PK auto_incre
		String sProductId = req.getParameter("productId"); //đã hidden vì id PK auto_incre
		Part pcsImagePart = req.getPart("pcsImage"); //image
		String sQuantity = req.getParameter("quantity");
		String sPrice = req.getParameter("price");
		ProductColorSizeDAO pcsDAO = new ProductColorSizeDAO();
		try {
			int pcsId = Integer.parseInt(sPCSId);
			int productId = Integer.parseInt(sProductId);
			int quantity = Integer.parseInt(sQuantity);
			double price = Double.parseDouble(sPrice);
			ProductColorSize pcsNow = pcsDAO.getById(pcsId);
			
			
			// image: Nếu ko thêm file thì giữ nguyên avatar của User Session:
			String imageName = pcsImagePart.getSubmittedFileName();
			if (imageName == null || imageName.equals("")) {
				System.out.println("Image is null !");
				pcsNow.setPcsImage(pcsNow.getPcsImage());
			} else {
				pcsNow.setPcsImage(imageName);
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
				pcsImagePart.write(pathTomcatFolder + "/" + imageName);
				System.out.println("Image in Tomcat Folder: " +pcsImagePart);
				//Image Create in Path Custom Folder:
				System.out.println("Custom Folder: " +pathCustomFolder);
				if (!Files.exists(Path.of(pathCustomFolder))) {
					Files.createDirectory(Path.of(pathCustomFolder));
				}
				pcsImagePart.write(pathCustomFolder + "/" + imageName);
				System.out.println("Image in Custom Folder: " +pcsImagePart);
			}
			
			pcsNow.setQuantity(quantity);
			pcsNow.setPrice(price);
			pcsDAO.update(pcsNow);
			
			//Sau khi Update xong:
			//Gửi Data + Quay trở lại JSP get_product_color_size:
			List<ProductColorSize> listProductColorSize = new ArrayList<ProductColorSize>();
			listProductColorSize = pcsDAO.getListPCSByProductId(productId);
			req.setAttribute("listProductColorSize", listProductColorSize);
			ProductDAO productDAO = new ProductDAO();
			Product productNow1 = productDAO.getById(productId);
			System.out.println("Product Hien Tai Update la: " +productNow1);
			req.setAttribute("productNow", productNow1);
			
			req.getRequestDispatcher("/views/admin/get_product_color_size.jsp").forward(req, resp);
			
		}catch (NumberFormatException e) { //Lỗi nếu ko nhập Quantity:
			// TODO: handle exception
			e.printStackTrace();
			
			//Lấy Data sau khi lỗi:
			int pcsId = Integer.parseInt(sPCSId);
			ProductColorSize pcs = new ProductColorSize();
			pcs = pcsDAO.getById(pcsId);
			req.setAttribute("pcsUpdateProductColorSizeServlet", pcs);
			
			req.setAttribute("errorUpdateProductColorSize", "Please input number !");
			req.getRequestDispatcher("/views/admin/update_product_color_size.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			//Lấy Data sau khi lỗi:
			int pcsId = Integer.parseInt(sPCSId);
			ProductColorSize pcs = new ProductColorSize();
			pcs = pcsDAO.getById(pcsId);
			req.setAttribute("pcsUpdateProductColorSizeServlet", pcs);
			
			req.setAttribute("errorUpdateProductColorSize", "Loi Update ProductColorSize");
			req.getRequestDispatcher("/views/admin/update_product_color_size.jsp").forward(req, resp);
		}
	}
}
