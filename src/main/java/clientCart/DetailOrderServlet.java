package clientCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.User;

@WebServlet(urlPatterns = {"/detail-order"})
public class DetailOrderServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Tăng tính bảo mật, chỉ có User có account mới xem được:
		HttpSession session = req.getSession();
		User userSession = (User) session.getAttribute("userLoginClient"); // session luu o Login va Header
		if(userSession == null) {
			resp.sendRedirect("login-client");
		} else {
			String sOrderId = req.getParameter("orderId"); //orderId từ JSP all_orders
			int orderId = Integer.parseInt(sOrderId);
			OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
			List<OrderDetail> orderDetails = new ArrayList<>();
			
			orderDetails = orderDetailDAO.getOrderDetailsByOrderId(orderId);
			req.setAttribute("orderDetails", orderDetails);
			req.getRequestDispatcher("/views/client/detail_order.jsp").forward(req, resp);
		}
		
		
	}
}
