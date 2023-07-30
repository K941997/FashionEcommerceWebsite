package clientProductController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.CategoryDAO;
import dao.ColorDAO;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Color;
import model.Product;

@WebServlet(urlPatterns = {"/search-product-client"})
public class SearchProduct extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String txtSearch = req.getParameter("txtSearch"); //from JSP header co nut Search
		ProductDAO productDAO = new ProductDAO();
		List<Product> productsSearch = new ArrayList<Product>();
		productsSearch =  productDAO.searchProductsByKey(txtSearch);
		req.setAttribute("listProductClient", productsSearch);
		
		//Get List Category tranh loi:
		List<Category> categories = new ArrayList<Category>();
		CategoryDAO categoryDAO = new CategoryDAO();
		categories = categoryDAO.getAll();
		req.setAttribute("listCategoryClient", categories);
		
		//Get List Color tranh loi:
		List<Color> colors = new ArrayList<Color>();
		ColorDAO colorDAO = new ColorDAO();
		colors = colorDAO.getAll();
		req.setAttribute("listColorClient", colors);
				
		req.getRequestDispatcher("/views/client/list_product_client.jsp").forward(req, resp);
	}
}
