package clientCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Order;
import model.User;

@WebServlet(urlPatterns = {"/all-orders"})
public class AllOrdersServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		User userSession = (User) session.getAttribute("userLoginClient"); // session luu o Login va Header
		if(userSession == null) {
			resp.sendRedirect("login-client");
		} else {
			OrderDAO orderDAO = new OrderDAO();
			List<Order> orders = new ArrayList<>();
			
			orders = orderDAO.clientAllOrders(userSession.getUserId());
			 
			req.setAttribute("orders", orders);
			req.getRequestDispatcher("/views/client/all_orders.jsp").forward(req, resp);
		}
		
		
	}
}
