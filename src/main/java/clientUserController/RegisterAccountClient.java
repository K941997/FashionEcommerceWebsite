package clientUserController;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(urlPatterns = {"/register-account-client"})
public class RegisterAccountClient extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/client/register_account_client.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String emailUser = req.getParameter("emailUser");
		String passwordUser = req.getParameter("passwordUser");
		String repeatPassword = req.getParameter("repeatPassword");
		if(emailUser.equals("")) {
			req.setAttribute("alertRegisterClientServlet", "Email is null !");
			req.getRequestDispatcher("/views/client/register_account_client.jsp").forward(req, resp);
		} else if(passwordUser.equals("")) {
			req.setAttribute("alertRegisterClientServlet", "Password is null !");
			req.getRequestDispatcher("/views/client/register_account_client.jsp").forward(req, resp);
		} else {
			if(!passwordUser.equals(repeatPassword)) {
				req.setAttribute("alertRegisterClientServlet", "Repeat Password is Wrong !");
				req.getRequestDispatcher("/views/client/register_account_client.jsp").forward(req, resp);
			}else {
				UserDAO userDAO = new UserDAO();
				//Kiem tra ton tai:
				User userExistEmail = userDAO.getByEmail(emailUser);
				if(userExistEmail == null) {
					//Neu chua ton tai -> Dang ky:
					userDAO.register(emailUser, passwordUser,null,null);
					req.setAttribute("alertRegisterClientServlet", "Sign Up Success, Please Login !");
					req.getRequestDispatcher("/views/client/register_account_client.jsp").forward(req, resp);
				} else {
					req.setAttribute("alertRegisterClientServlet", "Email already exists !");
					req.getRequestDispatcher("/views/client/register_account_client.jsp").forward(req, resp);
				}
			}
		}
		
	}
}
