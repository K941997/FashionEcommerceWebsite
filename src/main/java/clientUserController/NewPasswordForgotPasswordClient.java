package clientUserController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(urlPatterns = {"/new-password-forgot-password-client"})
public class NewPasswordForgotPasswordClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("views/client/new_password_forgot_password_client.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String newPassword = req.getParameter("password");
		String confPassword = req.getParameter("confPassword");
		String email = (String) session.getAttribute("email");
		RequestDispatcher dispatcher = null;
		try {
			if (newPassword != null && confPassword != null && !newPassword.equals("") && !confPassword.equals("") && newPassword.equals(confPassword)) {
				UserDAO userDAO = new UserDAO();
				User user = userDAO.getByEmail(email);
				User userNewPass = new User(
						user.getUserId(), email , newPassword,
						user.getFullname(), user.getAvatar(), user.getPhone(),
						user.getAddress(), user.getRole()
				);
				
				userDAO.changePassword(userNewPass);
				
				req.setAttribute("alertNewPassword", "Change Password Success, Please Login !");
				req.getRequestDispatcher("views/client/new_password_forgot_password_client.jsp").forward(req, resp);
			} else if(newPassword.equals("") || newPassword == null) {
				//Take account if fail:
				session = req.getSession();
				newPassword = req.getParameter("password");
				confPassword = req.getParameter("confPassword");
				email = (String) session.getAttribute("email");
				
				req.setAttribute("alertNewPassword", "New Password or Confirm Password is null !");
				req.getRequestDispatcher("views/client/new_password_forgot_password_client.jsp").forward(req, resp);
			} else if(!newPassword.equals(confPassword)) {
				//Take account if fail:
				session = req.getSession();
				newPassword = req.getParameter("password");
				confPassword = req.getParameter("confPassword");
				email = (String) session.getAttribute("email");
				
				req.setAttribute("alertNewPassword", "Confirm Password doesn't equal New Password !");
				req.getRequestDispatcher("views/client/new_password_forgot_password_client.jsp").forward(req, resp);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			//Take account if fail:
			session = req.getSession();
			newPassword = req.getParameter("password");
			confPassword = req.getParameter("confPassword");
			email = (String) session.getAttribute("email");
			
			req.setAttribute("alertNewPassword", "Change Password Fail !");
			req.getRequestDispatcher("views/client/new_password_forgot_password_client.jsp").forward(req, resp);
		}
		
	}
}
