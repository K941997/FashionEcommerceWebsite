package clientCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

import clientCart.CheckoutPaypalAuthorizePaymentServlet.ShortIdGenerator;
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
import model.Shipment;
import model.User;

@WebServlet(urlPatterns = {"/paypal-execute-payment"})
public class PaypalExecutePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public PaypalExecutePaymentServlet() {
		super();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String paymentId = req.getParameter("paymentId"); //data from JSP paypal_review_payment
		String payerId = req.getParameter("payerId");
		
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
		
			//User là Object nên phải lấy Object:
			int userId = Integer.parseInt(sUserId);
			UserDAO userDAO = new UserDAO();
			User user = userDAO.getById(userId);

			//Total Price: (Bổ sung - video Cart)
			ProductColorSizeDAO pcsDAO = new ProductColorSizeDAO();
		
			double total = pcsDAO.getTotalCartPrice(cart_list);

			 //Add vào Database Table Order:
			 OrderDAO orderDAO = new OrderDAO();
			 String customId = ShortIdGenerator.generateShortId(); //Tạo mã hóa đơn ngắn gọn
			 Order order = new Order(customId, total, 2, user, null, null); //status = 1 là completing, 2 là processing
		 
		try {
			PaypalServlet paymentServices = new PaypalServlet();
			Payment paymentPaypal = paymentServices.executePayment(paymentId, payerId);
			
			PayerInfo payerInfo = paymentPaypal.getPayer().getPayerInfo();
			Transaction transaction = paymentPaypal.getTransactions().get(0);
			ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
			
			req.setAttribute("payer", payerInfo);
			req.setAttribute("transaction", transaction);
			req.setAttribute("shippingAddress", shippingAddress);
			
//			//SAU KHI THANH TOÁN PAYPAL:
			//Add vào Database Table Order:
			 int orderId = orderDAO.add(order); //Tạo Order -> trả về OrderId để tạo OrderDetail
			 
			 //Add vào Database Table OrderDetail:
			 List<CartItem> cartProducts = pcsDAO.getCartProducts(cart_list); //(Bổ sung - video Cart)	 
			 for(CartItem productCart: cartProducts) {
				double price = productCart.getPrice();
				OrderDetail orderDetail = new OrderDetail(productCart.getQuantityCartItem(), price, productCart.getPcsId(), orderId, null, null);
				OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
				orderDetailDAO.add(orderDetail);
				
				//Trừ số lượng ProductColorSize đã bán ra:
				pcsDAO.subtractQuantity(productCart.getPcsId(), productCart.getQuantityCartItem());
			 }
			 
			 //Shipment:
			 ShipmentDAO shipmentDAO = new ShipmentDAO();
			 Shipment shipment = new Shipment(shipmentName, userId, orderId,  null, null, addressShipment);
			 shipmentDAO.insert(shipment);
			 
			 //Payment:
			 PaymentDAO paymentDAO = new PaymentDAO();
			 model.Payment payment = new model.Payment(paymentName, orderId, null, null);
			 paymentDAO.insert(payment);
			 
			 //Sau khi Order Checkout xong -> Xoa Gio Hang:
			 session.setAttribute("cart-list", null);
			
			
			req.getRequestDispatcher("/views/client/paypal_receipt.jsp").forward(req, resp);
		}catch (PayPalRESTException e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "Could not execute Payment !");
			req.getRequestDispatcher("/views/client/paypal_error.jsp").forward(req, resp);
		}
		
	}
}
