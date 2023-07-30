package adminOrderController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDetailDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderDetail;

@WebServlet(urlPatterns = {"/detail-order-admin"})
public class DetailOrderAdminServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sOrderId = req.getParameter("orderId"); //orderId từ JSP list_order_admin
		int orderId = Integer.parseInt(sOrderId);
		OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
		List<OrderDetail> orderDetails = new ArrayList<>();
		
		orderDetails = orderDetailDAO.getOrderDetailsByOrderId(orderId);
		req.setAttribute("orderDetails", orderDetails);
		
		req.getRequestDispatcher("/views/admin/detail_order_admin.jsp").forward(req, resp);
	}
}
