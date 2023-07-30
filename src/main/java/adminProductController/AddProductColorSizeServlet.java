package adminProductController;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import dao.CategoryDAO;
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
import model.Category;
import model.Color;
import model.Product;
import model.ProductColorSize;
import model.Size;


//Image
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB for Image
maxFileSize = 1024 * 1024 * 50, // 50MB for Image
maxRequestSize = 1024 * 1024 * 50) // 50MB for Image
@WebServlet(urlPatterns = {"/add-product-color-size"})
public class AddProductColorSizeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Send danh sach Color khi Create ProductColorSize:
		ColorDAO colorDAO = new ColorDAO();
		List<Color> colors = colorDAO.getAll();
		req.setAttribute("colors", colors);
		
		//Send danh sach Size khi Create ProductColorSize:
		SizeDAO sizeDAO = new SizeDAO();
		List<Size> sizes = sizeDAO.getAll();
		req.setAttribute("sizes", sizes);

		req.getRequestDispatcher("/views/admin/add_product_color_size.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sProductId = req.getParameter("id"); //id từ form, id từ Product lưu trong Session sau khi add-product
		String name = req.getParameter("name");
		System.out.println("Id Product Now: " +sProductId); 
		System.out.println("Name Product Now: " +name);
		
		ProductColorSizeDAO productColorSizeDAO = new ProductColorSizeDAO();

		try {
			//Gọi danh sách Size, Color để không bị lỗi trang không hiển thị:
			ColorDAO colorDAO1 = new ColorDAO();
			List<Color> colors = colorDAO1.getAll();
			req.setAttribute("colors", colors);
			
			SizeDAO sizeDAO1 = new SizeDAO();
			List<Size> sizes = sizeDAO1.getAll();
			req.setAttribute("sizes", sizes);
			
			//Ép kiểu:
			int productId = Integer.parseInt(sProductId);
	
			//Vì Product, Size, Color là Object nên phải lấy theo Object:
			ProductDAO productDAO = new ProductDAO();
			Product product = productDAO.getById(productId);

			//Insert Many Rows To Database + Batch Insert in DAO:
			String[] sColorId = req.getParameterValues("colorId");
			String[] sSizeId = req.getParameterValues("sizeId");
			String[] sQuantity = req.getParameterValues("quantity");
			String[] sPrice = req.getParameterValues("price");

			//Multiple Images:
			//Image Path Tomcat Folder (Auto Load Image):
			String pathTomcatFolder = req.getServletContext().getRealPath("/images") + File.separator;
			//Image Path Custom Folder (No Auto Load Image):
			ImagePathCustomFolder file = new ImagePathCustomFolder();
			String pathCustomFolder = file.getImagePathCustomFolder() + File.separator;
			
			List<ProductColorSize> pcsList= new ArrayList<ProductColorSize>();
//			for(int i = 0; i < sSizeId.length; i++) {
				
//				ProductColorSize pcs = new ProductColorSize();
//				pcs.setProduct(product);
//				
//				SizeDAO sizeDAO = new SizeDAO();
//				Size size = sizeDAO.getById(Integer.parseInt(sSizeId[i]));
//				pcs.setSize(size);
//				
//				ColorDAO colorDAO = new ColorDAO();
//				Color color = colorDAO.getById(Integer.parseInt(sColorId[i]));
//				pcs.setColor(color);
//			
//				pcs.setQuantity(Integer.parseInt(sQuantity[i]));
				
//				pcs.setPcsImage(imageName);

//				pcsList.add(pcs);
				
				//Multiple Images: (For loop theo fileParts, ko theo sSizeId.length)
				System.out.println("Check Image ...");
				List<Part> fileParts = req.getParts().stream().filter(part -> "pcsImages".equals(part.getName()) && part.getSize() > 0).collect(Collectors.toList());
				if(fileParts == null || fileParts.size() == 0) {
					//Nếu chưa nhập file:
					System.out.println("Image is null !");
					req.setAttribute("errorAddProductColorSizeServlet", "Image is null !");
					req.getRequestDispatcher("/views/admin/add_product_color_size.jsp").forward(req, resp);
				} else {
					//Nếu đã nhập file:
					//Multiple Images: (For loop theo fileParts, ko theo sSizeId.length)
					for (int j = 0; j < fileParts.size(); j++) {
						ProductColorSize pcs = new ProductColorSize();
						pcs.setProduct(product);
						
						ColorDAO colorDAO = new ColorDAO();
						Color color = colorDAO.getById(Integer.parseInt(sColorId[j]));
						pcs.setColor(color);
						
						SizeDAO sizeDAO = new SizeDAO();
						Size size = sizeDAO.getById(Integer.parseInt(sSizeId[j]));
						pcs.setSize(size);
						
						pcs.setQuantity(Integer.parseInt(sQuantity[j]));
						
						pcs.setPrice(Double.parseDouble(sPrice[j]));
						
						//image:
						Part p = fileParts.get(j);
						String imageName = p.getSubmittedFileName();
						
						//Image Create in Path Tomcat Folder:
						System.out.println(pathTomcatFolder);
						if (!Files.exists(Path.of(pathTomcatFolder))) {
							Files.createDirectory(Path.of(pathTomcatFolder));
						}
						p.write(pathTomcatFolder + "/" + imageName);
						//Image Create in Path Custom Folder:
						System.out.println(pathCustomFolder);
						if (!Files.exists(Path.of(pathCustomFolder))) {
							Files.createDirectory(Path.of(pathCustomFolder));
						}
						p.write(pathCustomFolder + "/" + imageName);
						
						pcs.setPcsImage(imageName);
						
						pcsList.add(pcs);

					}
					
					//Create lưu vào BẢNG TRUNG GIAN ProductColorSize :
					System.out.println("Add ProductColorSize ...");
					productColorSizeDAO.insert(pcsList);
					
					//Kiểm tra Duplicate, nếu trùng thì xóa không thêm ProductColorSize nào:
					System.out.println("Check Duplicate ...");
					ProductColorSize pcsDuplicate = productColorSizeDAO.findAndRemoveDuplicateMultipleRows(productId);
					System.out.println(pcsDuplicate);
					if(pcsDuplicate != null) {
						//Nếu có Duplicate:
						System.out.println("Co Duplicate -> Xoa Duplicate !");
						req.setAttribute("errorAddProductColorSizeServlet", "It has Duplicate !");
						req.getRequestDispatcher("/views/admin/add_product_color_size.jsp").forward(req, resp);
					} else {
						//Nếu không có Duplicate:
						System.out.println("Khong co Duplicate -> Co the tao ProductColorSize !");
						System.out.println("Them ProductColorSize thanh cong !");
						resp.sendRedirect("list-product");
					}
				}
				
//			}
			
			
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorAddProductColorSizeServlet", "Null Number When Add ProductColorSize !");
			req.getRequestDispatcher("/views/admin/add_product_color_size.jsp").forward(req, resp);
		}  catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorAddProductColorSizeServlet", "File Not Found When Add ProductColorSize !");
			req.getRequestDispatcher("/views/admin/add_product_color_size.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorAddProductColorSizeServlet", "Error Add ProductColorSize !");
			req.getRequestDispatcher("/views/admin/add_product_color_size.jsp").forward(req, resp);
		}

	}
	
}
