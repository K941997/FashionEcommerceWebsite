package adminProductController;

import java.io.IOException;

import dao.ProductColorSizeDAO;
import dao.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProductColorSize;

@WebServlet(urlPatterns = {"/delete-product-color-size"})
public class DeleteProductColorSizeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sProductColorSizeId = req.getParameter("idPCS"); //idPCS được đặt từ JSP get_product_color_size
		String previousPageUrl = req.getHeader("referer"); //go back về trang trước đó
		try {
			int productColorSizeId = Integer.parseInt(sProductColorSizeId);
			ProductColorSizeDAO productColorSizeDAO = new ProductColorSizeDAO();
			productColorSizeDAO.delete(productColorSizeId);
			resp.sendRedirect(previousPageUrl); //go back về trang trước đó get_product_color_size (để có product_id)
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
