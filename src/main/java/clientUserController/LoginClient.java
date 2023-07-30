package clientUserController;

import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(urlPatterns = {"/login-client"})
public class LoginClient extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/client/login_client.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("userEmail");
		String password = req.getParameter("userPassword");
		UserDAO userDAO = new UserDAO();
		
		//Check account:
		User user = userDAO.checkLogin(email, password);
		if(user != null) {
			//Remember Me Login: (Lưu Account vào Cookie -> nhiều ngày)
			if(req.getParameter("rememberLogin")!= null) {
				String remember = req.getParameter("rememberLogin");
				System.out.println("Remember Login Client: " +remember);
				Cookie cookieEmailUser = new Cookie("cookieEmailUser", email.trim()); //take in JSP login_client
				Cookie cookiePasswordUser = new Cookie("cookiePasswordUser", password.trim());	//take in JSP login_client
				Cookie cookieRememberUser = new Cookie("cookieRememberUser", remember.trim()); //take in JSP login_client
				cookieEmailUser.setMaxAge(60*60*24*15); //Cookie ton tai 15 days
				cookiePasswordUser.setMaxAge(60*60*24*15); //Cookie ton tai 15 days
				cookieRememberUser.setMaxAge(60*60*24*15); //Cookie ton tai 15 days
				resp.addCookie(cookieEmailUser);
				resp.addCookie(cookiePasswordUser);
				resp.addCookie(cookieRememberUser);
				
			}
			
			//Lưu Account vào Session -> Tắt là mất:
			HttpSession session = req.getSession();
			session.setAttribute("userLoginClient", user); //Không đặt giống Session Admin
			session.setMaxInactiveInterval(6000); //Session ton tai 6000s
			resp.sendRedirect("index"); //After Login -> Go to Page Index
		} else {
			req.setAttribute("errorLoginClientServlet", "Email or Password is wrong !");
			req.getRequestDispatcher("/views/client/login_client.jsp").forward(req, resp);
		}
	}
}
