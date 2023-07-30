package clientProductController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

@WebServlet(urlPatterns = {"/search-ajax"})
public class SearchProductAJAX extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String txtSearchAjax = req.getParameter("txtSearchAjax"); //from JSP index co nut Search Ajax
		ProductDAO productDAO = new ProductDAO();
		List<Product> productsSearch =  productDAO.searchProductsByKey(txtSearchAjax);
		PrintWriter out = resp.getWriter();
		for(Product o: productsSearch) {
			out.println("<!-- Product -->\r\n"
					+ "					<div  class=\" col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item women\">\r\n"
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
					+ "										"+o.getProductPrice()+"\r\n"
					+ "									</span>\r\n"
					+ "								</div>\r\n"
					+ "	\r\n"
					+ "								\r\n"
					+ "							</div>\r\n"
					+ "						</div>\r\n"
					+ "					</div>");
		}
		
//		req.setAttribute("listProductClient", productsSearch);
	}
}
