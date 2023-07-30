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

@WebServlet(urlPatterns = {"/checkout-cod"})
public class CheckoutCODServlet extends HttpServlet {
	
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String sUserId = req.getParameter("userId"); //thông tin User điền ở JSP cart_product
			String fullname = req.getParameter("fullname");
			String phone = req.getParameter("phone");
			
			
			String addressShipment = req.getParameter("addressShipment");
			String shipmentName = req.getParameter("shipment");
			//String paymentName = req.getParameter("payment");
			String paymentName = "COD-Payment";
			
			 HttpSession session = req.getSession();
			 ArrayList<CartItem> cart_list = (ArrayList<CartItem>) session.getAttribute("cart-list");
			 User userSession = (User) session.getAttribute("userLoginClient"); // session luu o Login va Header
			 
			 if(cart_list != null) {
				 
				 //User là Object nên phải lấy Object:
				int userId = Integer.parseInt(sUserId);
				UserDAO userDAO = new UserDAO();
				User user = userDAO.getById(userId);
				
				//(Không làm cách này) User điền thông tin đặt hàng -> Cập nhật thông tin User luôn:
//				user.setFullname(fullname);
//				user.setPhone(phone);
//				userDAO.update(user);
//				userSession.setFullname(fullname);
//				userSession.setPhone(phone);
				
				
					
				//Total Price: (ko được - video Checkout)
//				 double total = 0;
//				 for(CartItem cI: cart_list) {
//					total += cI.getQuantityCartItem() * cI.getPrice();
//				 }
				 
				//Total Price: (Bổ sung - video Cart)
				ProductColorSizeDAO pcsDAO = new ProductColorSizeDAO();
			
				double total = pcsDAO.getTotalCartPrice(cart_list);

				 //Add vào Database Table Order:
				 OrderDAO orderDAO = new OrderDAO();
				 String customId = ShortIdGenerator.generateShortId(); //Tạo mã hóa đơn ngắn gọn
				 Order order = new Order(customId, total, 2, user, null, null); //status = 1 là completing, 2 là processing
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
				 Payment payment = new Payment(paymentName, orderId, null, null);
				 paymentDAO.insert(payment);
				 
				 //Sau khi Order Checkout xong -> Xoa Gio Hang:
				 session.setAttribute("cart-list", null);
				 
				 resp.sendRedirect("all-orders");
			 } else {
				 
				 req.setAttribute("errorCheckOutServlet", "Nothing in Cart !");
				 req.getRequestDispatcher("/views/client/cart_product.jsp").forward(req, resp);
			 }
			 
			
			 
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			 resp.sendRedirect("cart-product");
		}
		
		 
	}
}
