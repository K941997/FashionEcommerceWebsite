package clientCart;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.paypal.base.exception.PayPalException;
import com.paypal.base.rest.PayPalRESTException;

import clientCart.CheckoutCODServlet.ShortIdGenerator;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.PaymentDAO;
import dao.ProductColorSizeDAO;
import dao.ShipmentDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartItem;
import model.Order;
import model.OrderDetail;
import model.Payment;
import model.Shipment;
import model.User;

@WebServlet(urlPatterns = {"/checkout-paypal-authorize-payment"})
public class CheckoutPaypalAuthorizePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public CheckoutPaypalAuthorizePaymentServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sUserId = req.getParameter("userId"); //thông tin User điền ở JSP cart_product
		String fullname = req.getParameter("fullname");
		String phone = req.getParameter("phone");
		
		
		String addressShipment = req.getParameter("addressShipment");
		String shipmentName = req.getParameter("shipment");
		//String paymentName = req.getParameter("payment");
		String paymentName = "Online-Payment";
		
		 HttpSession session = req.getSession();
		 ArrayList<CartItem> cart_list = (ArrayList<CartItem>) session.getAttribute("cart-list");
		 User userSession = (User) session.getAttribute("userLoginClient"); // session luu o Login va Header

		 if(cart_list != null) {
			 
			//User là Object nên phải lấy Object:
			int userId = Integer.parseInt(sUserId);
			UserDAO userDAO = new UserDAO();
			User user = userDAO.getById(userId);

			//Total Price: (Bổ sung - video Cart)
			ProductColorSizeDAO pcsDAO = new ProductColorSizeDAO();
		
			double total = pcsDAO.getTotalCartPrice(cart_list);

			 //Create đối tượng Order:
			 OrderDAO orderDAO = new OrderDAO();
			 String customId = ShortIdGenerator.generateShortId(); //Tạo mã hóa đơn ngắn gọn
			 Order order = new Order(customId, total, 2, user, null, null); //status = 1 là completing, 2 là processing
			 
			 try {
				//Paypal Online Payment:
				 PaypalServlet paymentServices = new PaypalServlet();
				 String approvalLink = paymentServices.authorizePayment(order);
				 resp.sendRedirect(approvalLink);

				} catch (PayPalRESTException e) {
					e.printStackTrace();
					req.setAttribute("errorMessage", "Invalid Payment Details");
					req.getRequestDispatcher("/views/client/paypal_error.jsp").forward(req, resp);
				}
				
//				 resp.sendRedirect("all-orders");
			 } else {
				 
				 req.setAttribute("errorCheckOutServlet", "Nothing in Cart !");
				 req.getRequestDispatcher("/views/client/cart_product.jsp").forward(req, resp);
			 }
			 
		
	}
	
	//Tạo mã hóa đơn ngắn gọn:
	public class ShortIdGenerator {
	    public static String generateShortId() {
	        try {
	            MessageDigest digest = MessageDigest.getInstance("SHA-1");
	            byte[] hash = digest.digest(String.valueOf(System.currentTimeMillis()).getBytes());
	            StringBuilder sb = new StringBuilder();
	            for (int i = 0; i < 6; i++) {
	                sb.append(Integer.toHexString((hash[i] & 0xFF) | 0x100).substring(1, 3));
	            }
	            return sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	}
}
