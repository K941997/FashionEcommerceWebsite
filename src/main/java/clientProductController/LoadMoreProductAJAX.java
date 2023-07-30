package clientProductController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.ProductColorSizeDAO;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

@WebServlet(urlPatterns = {"/load-more-product-client"})
public class LoadMoreProductAJAX extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//AJAX OFFSET LoadMore:
		String amount = req.getParameter("exits");
		int iAmount = Integer.parseInt(amount);
		
		ProductDAO productDAO = new ProductDAO();
		List<Product> list = productDAO.getNext8Products(iAmount);
		PrintWriter out = resp.getWriter();
		
		for(Product o: list) {
			//Get Min-Max Price của ProductColorSize của 1 Product: 
			ProductColorSizeDAO productColorSizeDAO = new ProductColorSizeDAO();
			out.println("<!-- Product -->\r\n"
					+ "					<div  class=\" productLoadMore col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item women\">\r\n"
					+ "						<!-- Block2 -->\r\n"
					+ "						<div class=\"block2\">\r\n"
					+ "							<div class=\"block2-pic hov-img0\">\r\n"
					+ "								<img src=\"images/"+o.getProductImage()+"\" alt=\"IMG-PRODUCT\">\r\n"
					+ "	\r\n"

					+ "							</div>\r\n"
					+ "	\r\n"
					+ "							<div class=\"block2-txt flex-w flex-t p-t-14\">\r\n"
					+ "								<div class=\"block2-txt-child1 flex-col-l \">\r\n"
					+ "									<a href=\"detail-product?pid="+o.getProductId()+"\" class=\"stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6\">\r\n"
					+ "										"+o.getProductName()+"\r\n"
					+ "									</a>\r\n"
					+ "	\r\n"
					+ "									<span class=\"stext-105 cl3\">\r\n"
					+ "											\r\n"
					+ "\r\n"
					+ "											\r\n"
					+ "												"+ productColorSizeDAO.getMinPricePCS(o.getProductId())+"&nbsp;₫\r\n"
					+ "												-\r\n"
					+ "												"+productColorSizeDAO.getMaxPricePCS(o.getProductId())+"&nbsp;₫\r\n"
					+ "											"
					+ "									</span>\r\n"
			
					+ "								</div>\r\n"
					+ "	\r\n"
					+ "								\r\n"
					+ "							</div>\r\n"
					+ "						</div>\r\n"
					+ "					</div>");
		}
	}
	
}
