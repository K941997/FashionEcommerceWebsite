package adminOrderController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Order;
import model.User;

@WebServlet(urlPatterns = {"/list-order-admin"})
public class ListOrdersAdminServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OrderDAO orderDAO = new OrderDAO();
		List<Order> listOrders = new ArrayList<Order>();
		listOrders = orderDAO.adminAllOrders();
		
		
		//Paginate (Phân trang):
		int page, numberPage = 6;
		int size = listOrders.size();
		int num = (size%6==0?(size/6):((size/6))+1);
		String xPage = req.getParameter("page");
		if(xPage == null) {
			page = 1;
		} else {
			page = Integer.parseInt(xPage);
		}
		int start, end;
		start = (page-1)*numberPage;
		end = Math.min(page*numberPage, size);
		List<Order> listOrders1 = new ArrayList<Order>();
		listOrders1 = orderDAO.getListByPage(listOrders, start, end);		
		req.setAttribute("listOrders", listOrders1);
		req.setAttribute("page", page);
		req.setAttribute("num", num);
				
		
		req.getRequestDispatcher("/views/admin/list_orders.jsp").forward(req, resp);
	}
}
