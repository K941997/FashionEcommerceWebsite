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

@WebServlet(urlPatterns = {"/cart-remove-item"})
public class CartRemoveItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = resp.getWriter();) {
			String pcsId = req.getParameter("pcsId");
			
			if(pcsId != null) {
				ArrayList<CartItem> cart_list = (ArrayList<CartItem>) req.getSession().getAttribute("cart-list");
				if(cart_list != null) {
					for(CartItem cartItem: cart_list) {
						if(cartItem.getPcsId() == Integer.parseInt(pcsId)) {
							cart_list.remove(cart_list.indexOf(cartItem));
							break;
						}
					}
					resp.sendRedirect("cart-product");
				}
			}else {
				resp.sendRedirect("cart-product");
			}
			
		}
	}
}
