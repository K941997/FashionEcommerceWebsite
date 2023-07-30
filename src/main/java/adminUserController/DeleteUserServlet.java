package adminUserController;

import java.io.IOException;

import dao.CategoryDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/delete-user"})
public class DeleteUserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sUserId = req.getParameter("id"); //id được đặt từ JSP list_user
		try {
			int userId = Integer.parseInt(sUserId);
			UserDAO userDAO = new UserDAO();
			userDAO.delete(userId);
			resp.sendRedirect("list-user");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
