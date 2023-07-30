package clientCart;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import dao.CategoryDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.PaymentDAO;
import dao.ProductColorSizeDAO;
import dao.ShipmentDAO;
import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartItem;
import model.Category;
import model.Order;
import model.OrderDetail;
import model.Payment;
import model.Shipment;
import model.User;

@WebServlet(urlPatterns = {"/order-checkout"})
public class CheckoutServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String submitType = req.getParameter("submitType");
	    if (submitType != null && submitType.equals("buttonCOD")) {
	        // Handle button A click by forwarding to COD
	        RequestDispatcher dispatcher = req.getRequestDispatcher("checkout-cod");
	        dispatcher.forward(req, resp);
	    } else if (submitType != null && submitType.equals("buttonPayOnline")) {
	        // Handle button B click by forwarding to Online Payment
	        RequestDispatcher dispatcher = req.getRequestDispatcher("checkout-paypal-authorize-payment");
	        dispatcher.forward(req, resp);
	    } else {
	    	req.getRequestDispatcher("views/client/cart_product.jsp").forward(req, resp);
	    }
	}
}
