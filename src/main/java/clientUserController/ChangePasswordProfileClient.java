package clientUserController;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(urlPatterns = {"/change-password-profile-client"})
public class ChangePasswordProfileClient extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String currentEmail = req.getParameter("userCurrentEmail");
		String currentPassword = req.getParameter("currentPassword");
		String newPassword = req.getParameter("newPassword");
		String renewPassword = req.getParameter("renewPassword");
		
		UserDAO userDAO = new UserDAO();
		
		try {
			//Kiem tra Password Old:
			User userWithOldPassword = userDAO.checkLogin(currentEmail, currentPassword);
			if(userWithOldPassword == null) {
				//Khong dung Password Old:
				String message = "Old Password is incorrect!";
				req.setAttribute("messageChangePassword", message);
				req.getRequestDispatcher("profile-user-client").forward(req, resp);
			} else {
				//Dung Password Old:
				
				//Kiem tra null:
				if(newPassword.equals("") || newPassword == null || renewPassword.equals("") || renewPassword == null) {
					req.setAttribute("messageChangePassword", "New password or Repeat password is null !");
					req.getRequestDispatcher("profile-user-client").forward(req, resp);
				} else {
					//Neu da nhap day du:
					
					//Kiem tra Password New = Repeat Password New:
					if(!newPassword.equals(renewPassword)) {
						req.setAttribute("messageChangePassword", "Repeat password is incorrect !");
						req.getRequestDispatcher("profile-user-client").forward(req, resp);
					} else {
						//New Pass = Repeat New Pass:
						
						//Update Pass:
						User userNewPassword = new User(
								userWithOldPassword.getUserId(), currentEmail , newPassword,
								userWithOldPassword.getFullname(), userWithOldPassword.getAvatar(), userWithOldPassword.getPhone(),
								userWithOldPassword.getAddress(), userWithOldPassword.getRole()
						);
						
						userDAO.changePassword(userNewPassword);
						
						// User Session After Update Success:
						HttpSession session = req.getSession(); // session luu o Login va Header
						User userSession = (User) session.getAttribute("userLoginClient"); // session luu o Login va Header
						userSession.setPassword(newPassword);
						
						req.setAttribute("messageChangePassword", "Update Success !");
						req.getRequestDispatcher("profile-user-client").forward(req, resp);
					}
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("messageChangePassword", "Change Password Fail !");
			req.getRequestDispatcher("profile-user-client").forward(req, resp);
		}
		
	}
}
