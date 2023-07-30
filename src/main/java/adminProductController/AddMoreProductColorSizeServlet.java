package adminProductController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
@WebServlet(urlPatterns = {"/add-more-product-color-size"})
public class AddMoreProductColorSizeServlet extends HttpServlet {
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
		
		String sProductId = req.getParameter("id"); //product_id from "add-more-product-size-color?id=..." from JSP get_product_color_size
		int productId = Integer.parseInt(sProductId);
		ProductDAO productDAO = new  ProductDAO();
		Product productNow = productDAO.getById(productId);
		req.setAttribute("productNow", productNow);
		
		req.getRequestDispatcher("/views/admin/add_more_product_color_size.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sProductId = req.getParameter("id"); //id từ form trong add_more_product_color_size
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
			
			//Chưa có Image:
//			for(int i = 0; i < sSizeId.length; i++) {
//				ProductColorSize pcs = new ProductColorSize();
//				pcs.setProduct(product);
//				
//				ColorDAO colorDAO = new ColorDAO();
//				Color color = colorDAO.getById(Integer.parseInt(sColorId[i]));
//				pcs.setColor(color);
//				
//				SizeDAO sizeDAO = new SizeDAO();
//				Size size = sizeDAO.getById(Integer.parseInt(sSizeId[i]));
//				pcs.setSize(size);
//				
//				pcs.setQuantity(Integer.parseInt(sQuantity[i]));
//				
//				pcsList.add(pcs);
//			}
			
			//Có Image:
			//Multiple Images: (For loop theo fileParts, ko theo sSizeId.length)
			System.out.println("Check Image ...");
			List<Part> fileParts = req.getParts().stream().filter(part -> "pcsImages".equals(part.getName()) && part.getSize() > 0).collect(Collectors.toList());
			if(fileParts == null || fileParts.size() == 0) {
				//Nếu chưa nhập file:
				System.out.println("Image is null !");
				
				//Lấy lại giá trị Product khi lỗi:
				Product productNow = productDAO.getById(productId);
				req.setAttribute("productNow", productNow);
				
				req.setAttribute("errorAddMoreProductColorSizeServlet", "Image is null !");
				req.getRequestDispatcher("/views/admin/add_more_product_color_size.jsp").forward(req, resp);
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
				
				//Lấy lại giá trị Product:
				Product productNow = productDAO.getById(productId);
				req.setAttribute("productNow", productNow);

				//Create lưu vào BẢNG TRUNG GIAN ProductColorSize :
				System.out.println("Them ProductColorSize !");
				productColorSizeDAO.insert(pcsList);
				
				//(Important) Add More nên sẽ lấy giá trị cuối cùng lastest:
				System.out.println("Remove Duplicate and Save Lastest !");
				productColorSizeDAO.removeDuplicateAndSaveLatest(productId);

				//Sau khi thêm xong:
				//Gửi Data + Quay trở lại JSP get_product_color_size:
				List<ProductColorSize> listProductColorSize = new ArrayList<ProductColorSize>();
				listProductColorSize = productColorSizeDAO.getListPCSByProductId(productId);
				req.setAttribute("listProductColorSize", listProductColorSize);
				
				Product productNow1 = productDAO.getById(productId);
				req.setAttribute("productNow", productNow1);
				
				req.getRequestDispatcher("/views/admin/get_product_color_size.jsp").forward(req, resp);
			}
			
			

		} catch (NumberFormatException e) {
			//Lấy lại giá trị Product khi lỗi:
			int productId = Integer.parseInt(sProductId);
			ProductDAO productDAO = new  ProductDAO();
			Product productNow = productDAO.getById(productId);
			req.setAttribute("productNow", productNow);
			
			e.printStackTrace();
			req.setAttribute("errorAddMoreProductColorSizeServlet", "Null Number When Add More ProductColorSize ! ");
			req.getRequestDispatcher("/views/admin/add_more_product_color_size.jsp").forward(req, resp);
			
		} catch (FileNotFoundException e) {
			//Lấy lại giá trị Product khi lỗi:
			int productId = Integer.parseInt(sProductId);
			ProductDAO productDAO = new  ProductDAO();
			Product productNow = productDAO.getById(productId);
			req.setAttribute("productNow", productNow);
			
			e.printStackTrace();
			req.setAttribute("errorAddMoreProductColorSizeServlet", "File Not Found When Add M ProductColorSize ! ");
			req.getRequestDispatcher("/views/admin/add_more_product_color_size.jsp").forward(req, resp); 
		} catch (Exception e) {
			//Lấy lại giá trị Product khi lỗi:
			int productId = Integer.parseInt(sProductId);
			ProductDAO productDAO = new  ProductDAO();
			Product productNow = productDAO.getById(productId);
			req.setAttribute("productNow", productNow);
			
			e.printStackTrace();
			req.setAttribute("errorAddMoreProductColorSizeServlet", "Error Add More ProductColorSize ! ");
			req.getRequestDispatcher("/views/admin/add_more_product_color_size.jsp").forward(req, resp);
		}

	}
	
}
