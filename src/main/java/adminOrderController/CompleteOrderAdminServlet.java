package adminOrderController;

import java.io.IOException;

import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/complete-order-admin"})
public class CompleteOrderAdminServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String sOrderId = req.getParameter("orderId"); //orderId từ JSP list_orders_admin
			int orderId = Integer.parseInt(sOrderId);
			OrderDAO orderDAO = new OrderDAO();
			orderDAO.completeOrderAdmin(orderId);
			resp.sendRedirect("list-order-admin");
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
