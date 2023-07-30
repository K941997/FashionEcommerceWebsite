package adminUserController;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(urlPatterns = {"/login-admin"})
public class LoginAdminServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/admin/login_admin.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("userEmail"); //parameter from JSP
		String password = req.getParameter("userPassword"); //parameter from JSP
		UserDAO userDAO = new UserDAO();
		
		//Check account:
		User user = userDAO.checkLogin(email, password);
		if(user != null) {
			if(user.getRole() == 1 || user.getRole() == 2) {
				HttpSession session  = req.getSession();
				session.setAttribute("userLoginServlet", user); //Lưu account vào Session (tắt là mất)
				session.setMaxInactiveInterval(6000); //Session ton tai 6000s
				resp.sendRedirect("profile-user-admin"); //After Login, go to JSP profile-user-admin, nên go to JSP Home
			} else {
				req.setAttribute("errorLoginAdminServlet", "This account is Not Admin !");
				req.getRequestDispatcher("/views/admin/login_admin.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("errorLoginAdminServlet", "Email or Password is wrong !");
			req.getRequestDispatcher("/views/admin/login_admin.jsp").forward(req, resp);
		}
	}  
}
