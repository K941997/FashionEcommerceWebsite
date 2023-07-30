package clientCart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartItem;

@WebServlet(urlPatterns = {"/add-to-cart"})
public class AddToCartServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = resp.getWriter()) {
			//Giỏ hàng:
			ArrayList<CartItem> cartList = new ArrayList<CartItem>();
			
			//Tạo 1 sản phẩm vào Giỏ là sản phẩm detail-product:
			int pcsId = Integer.parseInt(req.getParameter("pcsId")); //ở detail_product khi lấy được pcs
			CartItem cartItem = new CartItem();
			cartItem.setPcsId(pcsId);
			cartItem.setQuantityCartItem(1);
			
			//Đặt danh sách sản phẩm vào Session:
			HttpSession session = req.getSession();
			ArrayList<CartItem> cart_list = (ArrayList<CartItem>) session.getAttribute("cart-list");
			
			
			if(cart_list == null) {
				//Nếu mà Giỏ chưa có danh sách sản phẩm -> thêm 1 sản phẩm vào giỏ:
				cartList.add(cartItem);
				session.setAttribute("cart-list", cartList);
				resp.sendRedirect("index");
			} else {
				//Nếu mà Giỏ có danh sách sản phẩm:
				cartList = cart_list;
				
				//Các sản phẩm trong giỏ:
				boolean exist = false;
				
				for(CartItem cI: cart_list) {
					if(cI.getPcsId() == pcsId) {
						//Nếu sản phẩm đã có trong giỏ -> Tăng sản phẩm trong giỏ:
						exist = true;
						cI.setQuantityCartItem(cI.getQuantityCartItem()+1);
						int quantity = cartItem.getQuantityCartItem();
						quantity++;
						cartItem.setQuantityCartItem(quantity);
						
						resp.sendRedirect("index");
					}	
				}
				if(!exist) {
					//Nếu sản phẩm ko tồn tại trong giỏ -> thêm vào giỏ:
					cartList.add(cartItem);
					resp.sendRedirect("index");
				}
				
				
				//Phần này làm trong JSP Giỏ Hàng cart_products:
//				if(cart_list != null){
//					ProductColorSizeDAO pcsDAO = new ProductColorSizeDAO();
//					cartProducts = pcsDAO.getCartProducts(cart_list);
//					double total = pcsDAO.getTotalCartPrice(cart_list);
//					request.setAttribute("cart_list", cart_list);
//					request.setAttribute("total", total);
//				}
			}
			
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
