package clientCart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CartItem;

@WebServlet(urlPatterns = {"/cart-quantity-inc-dec"})
public class CartQuantityIncreaseDecrease extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = resp.getWriter();) {
			String action = req.getParameter("action"); //ở JSP cart_product nút Quantity IncDec
			int pcsId = Integer.parseInt(req.getParameter("pcsId")); //ở JSP cart_product nút Quantity IncDec
			
			ArrayList<CartItem> cart_list = (ArrayList<CartItem>) req.getSession().getAttribute("cart-list");
		
			//Nếu mà có action và có pcsId:
			if(action != null && pcsId >= 1) {
				//Nếu mà action là "inc":
				if(action.equals("inc")) {
					for(CartItem cartItem: cart_list) {
						if(cartItem.getPcsId() == pcsId) {
							int quantity = cartItem.getQuantityCartItem();
							quantity++;
							cartItem.setQuantityCartItem(quantity);
							resp.sendRedirect("cart-product");
						}
					}
				}
				
				//Nếu mà action là "dec":
				if(action.equals("dec")) {
					for(CartItem cartItem: cart_list) {
						if(cartItem.getPcsId() == pcsId && cartItem.getQuantityCartItem() > 1) {
							int quantity = cartItem.getQuantityCartItem();
							quantity--;
							cartItem.setQuantityCartItem(quantity);
							break;
						}
					}
					resp.sendRedirect("cart-product");
				}
			} else {
				resp.sendRedirect("cart-product");
			}
			
		} 
	}
}
