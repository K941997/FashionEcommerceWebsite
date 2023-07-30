package adminSizeController;

import java.io.IOException;

import dao.SizeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/delete-size"})
public class DeleteSizeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sSizeId = req.getParameter("id"); //id từ JSP list_size
		try {
			int sizeId = Integer.parseInt(sSizeId);
			SizeDAO sizeDAO = new SizeDAO();
			sizeDAO.delete(sizeId);
			resp.sendRedirect("list-size");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
