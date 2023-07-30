package clientUserController;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/logout-client"})
public class LogoutClient extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Remove Cookie when Remember Me in Login:
		Cookie cookieEmailUser = new Cookie("cookieEmailUser", null);
		Cookie cookiePasswordUser = new Cookie("cookiePasswordUser", null);
		Cookie cookieRememberUser = new Cookie("cookieRememberUser", null);
		cookieEmailUser.setMaxAge(0);
		cookiePasswordUser.setMaxAge(0);
		cookieRememberUser.setMaxAge(0);
		resp.addCookie(cookieEmailUser);
		resp.addCookie(cookiePasswordUser);
		resp.addCookie(cookieRememberUser);
		HttpSession session = req.getSession();
		session.removeAttribute("userLoginClient");
		session.invalidate();
		resp.sendRedirect("index");
	}
}
