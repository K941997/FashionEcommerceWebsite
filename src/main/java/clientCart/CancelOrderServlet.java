package clientCart;

import java.io.IOException;

import dao.OrderDAO;
import dao.SizeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/cancel-order"})
public class CancelOrderServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sOrderId = req.getParameter("orderId"); //orderId từ JSP all_orders
		try {
			int orderId = Integer.parseInt(sOrderId);
			OrderDAO orderDAO = new OrderDAO();
			orderDAO.cancelOrder(orderId);
			resp.sendRedirect("all-orders");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
