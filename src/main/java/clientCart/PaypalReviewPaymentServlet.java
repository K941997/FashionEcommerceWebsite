package clientCart;

import java.io.IOException;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/paypal-review-payment"})
public class PaypalReviewPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public PaypalReviewPaymentServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String paymentId = req.getParameter("paymentId");
		String payerId = req.getParameter("PayerID");
		
		try {
			PaypalServlet paymentServices = new PaypalServlet();
			Payment payment = paymentServices.getPaymentDetails(paymentId);
			
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			Transaction transaction = payment.getTransactions().get(0);
	
			ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
			
			req.setAttribute("payer", payerInfo);
			req.setAttribute("transaction", transaction);
			req.setAttribute("shippingAddress", shippingAddress);
		
			
			String url = "/views/client/paypal_review_payment.jsp?paymentId=" + paymentId + "&PayerID=" + payerId;
			req.getRequestDispatcher(url).forward(req, resp);
		}catch (PayPalRESTException e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "Could not get payment details");
			req.getRequestDispatcher("/views/client/paypal_error.jsp").forward(req, resp);
		}
		
	}
}
