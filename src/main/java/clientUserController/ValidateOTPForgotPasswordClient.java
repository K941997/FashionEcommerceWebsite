package clientUserController;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/validate-otp-forgot-password-client"})
public class ValidateOTPForgotPasswordClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int value = Integer.parseInt(req.getParameter("otp")); //from JSP enter_otp_forgot_password_client
		HttpSession session = req.getSession();
		int otp = (int) session.getAttribute("otp");
		
		RequestDispatcher dispatcher=null;
		
		if(value==otp) {
			req.setAttribute("email", req.getParameter("email")); //get from Session Servlet ForgotPasswordClient
			req.setAttribute("status", "success");
			dispatcher = req.getRequestDispatcher("views/client/new_password_forgot_password_client.jsp");
			dispatcher.forward(req, resp);
		} else {
			req.setAttribute("message","Wrong OTP");
			dispatcher = req.getRequestDispatcher("views/client/enter_otp_forgot_password_client.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
