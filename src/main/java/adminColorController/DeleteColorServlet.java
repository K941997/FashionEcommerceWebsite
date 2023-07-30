package adminColorController;

import java.io.IOException;

import dao.ColorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/delete-color"})
public class DeleteColorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sColorId = req.getParameter("id"); //id được đặt từ JSP list_color
		try {
			int colorId = Integer.parseInt(sColorId);
			ColorDAO colorDAO = new ColorDAO();
			colorDAO.delete(colorId);
			resp.sendRedirect("list-color");
		} catch (Exception e) {

		}
	}
}
