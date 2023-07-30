package adminProductController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/delete-product"})
public class DeleteProductServlet extends HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sProductId = req.getParameter("id"); //id được đặt từ JSP list_product

		try {
			int productId = Integer.parseInt(sProductId);
			ProductDAO productDAO = new ProductDAO();
			productDAO.delete(productId);
			
			resp.sendRedirect("list-product");
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Delete Error Code: " +e.getErrorCode());
			req.setAttribute("errorDeleteProductMessage", "Error deleting product: " + e.getMessage());
	        req.getRequestDispatcher("/views/admin/list_product.jsp").forward(req, resp);

	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorDeleteProductMessage", "Error deleting product: " + e.getMessage());
			req.getRequestDispatcher("/views/admin/list_product.jsp").forward(req, resp);
		} 
	}
}
