package clientCart;

import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.exception.PayPalException;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.Order;
import model.User;

public class PaypalServlet {
	private static final String CLIENT_ID = "AeLNZSVDLjWQBXHl-DJF97jROr6RQRZ3fA0oqIpACyZQr_oOY61vS1ObccONzktpXD7Nd8sJYIFDj7uh";
	private static final String CLIENT_SECRET ="ENvBMmPVnVhglXatgqz7c6tolj5PHRz3cxkVwSlSScVTfbOM13_jmakHdZ_20pRdHgw8-xNqlrgfW72p";
	private static final String MODE = "sandbox";
	
	public String authorizePayment(Order order) throws PayPalRESTException {
		Payer payer = getPayerInformation();
		RedirectUrls redirectUrls = getRedirectUrls();
		List<Transaction> listTransaction = getTransactionInformation(order);
		Payment requestPayment = new Payment();
		requestPayment.setTransactions(listTransaction)
					.setRedirectUrls(redirectUrls)
					.setPayer(payer)
					.setIntent("authorize");
		
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
		Payment approvePayment = requestPayment.create(apiContext);
		
		System.out.println(approvePayment);
		return getApprovalLink(approvePayment);
	}
	
	private String getApprovalLink(Payment approvePayment) {
		List<Links> links = approvePayment.getLinks();
		String approvalLink = null;
		for(Links link:links) {
			if(link.getRel().equalsIgnoreCase("approval_url")) {
				approvalLink = link.getHref();
			}
		}
		
		return approvalLink;
	}
	
	private List<Transaction> getTransactionInformation(Order order){
		Details details = new Details();
		details.setSubtotal(order.getStringTotalmoney());
		
		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setTotal(order.getStringTotalmoney());
		amount.setDetails(details);
		
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
//		transaction.setDescription(pcsName); //Viết PCS Name
		
		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<Item>();
		
		Item item = new Item();
		item.setCurrency("USD")
//			.setName(order.getProductName)	//Vieets PCS Name
			.setPrice(order.getStringTotalmoney())
			.setQuantity("1");
		items.add(item);
		itemList.setItems(items);
		transaction.setItemList(itemList);
		List<Transaction> listTransaction = new ArrayList<Transaction>();
		listTransaction.add(transaction);
		
		
		
		return listTransaction;
	}
	
	private RedirectUrls getRedirectUrls() {
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("http://localhost:8080/ProjectEShopOnWeb/views/client/paypal_cancel.jsp");
		redirectUrls.setReturnUrl("http://localhost:8080/ProjectEShopOnWeb/paypal-review-payment");
		return redirectUrls;
	}

	public Payment getPaymentDetails (String paymentId) throws PayPalRESTException {
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
		return Payment.get(apiContext, paymentId);
	}
	
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);
		Payment payment  = new Payment().setId(paymentId);
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
		return payment.execute(apiContext, paymentExecution);
	}
	
//	HttpServletRequest req;
//	HttpSession session = req.getSession();
//	User userSession = (User) session.getAttribute("userLoginClient"); // session luu o Login va Header
	 
	private Payer getPayerInformation() {
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		PayerInfo payerInfo = new PayerInfo();
		payerInfo.setFirstName("Ngoc") //thay doi
				.setLastName("Khanh")
				.setEmail("NgocKhanh@gmail.com");
		return payer;
	}
}
