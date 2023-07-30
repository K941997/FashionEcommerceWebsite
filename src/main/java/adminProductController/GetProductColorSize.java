package adminProductController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductColorSizeDAO;
import dao.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import model.ProductColorSize;

@WebServlet(urlPatterns = {"/get-product-color-size"})
public class GetProductColorSize extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Hiển thị danh sách ProductColorSize theo product_id:
		String sProductId = req.getParameter("id"); //id_product from parameter "/get-product-size-color?id=..." from JSP list_product
		ProductColorSizeDAO productColorSizeDAO = new ProductColorSizeDAO();
		List<ProductColorSize> listProductColorSize = new ArrayList<ProductColorSize>();
		ProductDAO productDAO = new ProductDAO();
		try {
			int productId = Integer.parseInt(sProductId);
			
			listProductColorSize = productColorSizeDAO.getListPCSByProductId(productId);
			req.setAttribute("listProductColorSize", listProductColorSize);
			
			Product productNow = productDAO.getById(productId);
			System.out.println(productNow);
			req.setAttribute("productNow", productNow);
			
			req.getRequestDispatcher("/views/admin/get_product_color_size.jsp").forward(req, resp);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.getRequestDispatcher("/views/admin/get_product_color_size.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
