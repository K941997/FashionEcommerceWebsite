package clientProductController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.json.simple.JSONObject;

import dao.ColorDAO;
import dao.ProductColorSizeDAO;
import dao.ProductDAO;
import dao.SizeDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Color;
import model.Product;
import model.ProductColorSize;
import model.Size;

@WebServlet(urlPatterns = {"/detail-product-update-price-and-quantity"})
public class DetailProductUpdatePriceAndQuantityServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			int productId = Integer.parseInt(req.getParameter("productId"));
			String sColorId = req.getParameter("colorId");
			String sSizeId = req.getParameter("sizeId");
		    HttpSession session = req.getSession();
		    ProductColorSizeDAO productColorSizeDAO = new ProductColorSizeDAO();
		    

	        if(sColorId.equals("") || sSizeId.equals("")) {
	        
	        	//Nếu chưa nhập Color hoac Size:
				System.out.println("Color or Size is null !");
	
				 ////Lấy lại giá trị tránh lỗi trang:
	 	        ProductDAO productDAO = new ProductDAO();
	 	       	Product product = productDAO.getById(productId);
	 			req.setAttribute("product", product);
	 			
	 			//Get List PCSs By ProductID: (Hiển thị các ảnh)
	 			List<ProductColorSize> listPCSByProductId = productColorSizeDAO.getListPCSByProductId(productId);
	 			req.setAttribute("listPCSByProductId", listPCSByProductId);
	 			
	 			//Get List Colors By ProductID:
	 			List<Color> colors = new ArrayList<Color>();
	 			ColorDAO colorDAO = new ColorDAO();
	 			colors = colorDAO.getAllColorsByProductId(productId);
	 			req.setAttribute("colors", colors);
	 		
	 			//Get List Sizes By ProductID:
	 			List<Size> sizes = new ArrayList<Size>();
	 			SizeDAO sizeDAO = new SizeDAO();
	 			sizes = sizeDAO.getAllSizesByProductID(productId);
	 			req.setAttribute("sizes", sizes);
	 			
	 			 session.removeAttribute("colorId");
			     session.removeAttribute("sizeId");
	 			
	 			req.setAttribute("errorDetailProductServlet", "Color or Size is null !");
	 			req.getRequestDispatcher("/views/client/detail_product.jsp?pid="+productId).forward(req, resp);
	 			
	        } else {
	        	//Nếu đã nhập Color hoặc Size -> Có ProductColorSize:
	        	int colorId = Integer.parseInt(req.getParameter("colorId"));
	 	        int sizeId = Integer.parseInt(req.getParameter("sizeId"));
	 	        
	 	        ProductColorSize productColorSize = productColorSizeDAO.getPCSByProductAndColorAndSize(productId, colorId, sizeId);
	 	        
	 	        
	        	if(productColorSize == null ) {
	        		//Nếu color, size ko khớp trong Database:
		        	double price = 0;
		        	int quantity = 0; 
		 	        req.setAttribute("price", price);
		 	        req.setAttribute("quantity", quantity);
		 	        
		 	        session.setAttribute("colorId", colorId);
		 	        session.setAttribute("sizeId", sizeId);
	
	
		        } else if (productColorSize.getQuantity() <= 0) {
		        	//Nếu quantity = 0
		        	double price = 0;
		        	int quantity = 0; 
		 	        req.setAttribute("price", price);
		 	        req.setAttribute("quantity", quantity);
		 	        
		 	        session.setAttribute("colorId", colorId);
		 	        session.setAttribute("sizeId", sizeId);

		        } else {
		        	//Nếu Perfect có ProductColorSize trong Database, quantity > 0:
		        	double price = productColorSize.getPrice();
		        	int quantity = productColorSize.getQuantity();
		        	String pcsImage = productColorSize.getPcsImage();
		        	
		 	        System.out.println(price);
		 	        System.out.println(quantity);
			        req.setAttribute("price", price);
			        req.setAttribute("quantity", quantity);
			        req.setAttribute("pcsImage", pcsImage);
			        req.setAttribute("productColorSize", productColorSize);
	
		 	        
		 	        //-> Có ProductID, Có ColorID, Có SizeID, 
		 	        //-> Có ProductColorSize, Có Quantity, Có Price
			        session.setAttribute("colorId", colorId);
		 	        session.setAttribute("sizeId", sizeId);
		        }
		        
	        	 // Lưu giá trị màu sắc và kích thước vào req hoac session để sử dụng trong JSP
	 	    
	 	  
	 	
	       
		        
	 	        ////Lấy lại giá trị tránh lỗi trang:
	 	        ProductDAO productDAO = new ProductDAO();
	 	       	Product product = productDAO.getById(productId);
	 			req.setAttribute("product", product);
	 			
	 			//Get List PCSs By ProductID: (Hiển thị các ảnh)
	 			List<ProductColorSize> listPCSByProductId = productColorSizeDAO.getListPCSByProductId(productId);
	 			req.setAttribute("listPCSByProductId", listPCSByProductId);
	 			
	 			//Get List Colors By ProductID:
	 			List<Color> colors = new ArrayList<Color>();
	 			ColorDAO colorDAO = new ColorDAO();
	 			colors = colorDAO.getAllColorsByProductId(productId);
	 			req.setAttribute("colors", colors);
	 		
	 			//Get List Sizes By ProductID:
	 			List<Size> sizes = new ArrayList<Size>();
	 			SizeDAO sizeDAO = new SizeDAO();
	 			sizes = sizeDAO.getAllSizesByProductID(productId);
	 			req.setAttribute("sizes", sizes);
	 			
		        
		        req.getRequestDispatcher("/views/client/detail_product.jsp?pid="+productId).forward(req, resp);
		       
			   	 session.removeAttribute("colorId");
			     session.removeAttribute("sizeId");
		  
//		        // Gửi giá trả về cho Ajax request (Chưa làm được)
//		        resp.setContentType("text/plain");  
//		        resp.setCharacterEncoding("UTF-8");
//		        resp.getWriter().write(price + "");
		    	
	        }
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }
}
