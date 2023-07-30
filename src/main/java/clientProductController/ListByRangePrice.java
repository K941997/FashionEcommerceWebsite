package clientProductController;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

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



@WebServlet(urlPatterns = {"/list-product-by-range-price"})
public class ListByRangePrice extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		
		//double minPrice = Double.parseDouble(req.getParameter("minPrice"));
		//double maxPrice = Double.parseDouble(req.getParameter("maxPrice"));

		//Get List Products By Price Range:
		// Retrieve the selected price range value from the request parameter
		  String priceRange = req.getParameter("priceRange");
		  int minPrice = 0;
		  int maxPrice = 0;
		  // Parse the price range into minimum and maximum price values
		  if (priceRange.equals("0-100000")) {
		    minPrice = 0;
		    maxPrice = 100000;
		  } else if (priceRange.equals("100000-300000")) {
		    minPrice = 100000;
		    maxPrice = 300000;
		  } else if (priceRange.equals("300000-500000")) {
		    minPrice = 300000;
		    maxPrice = 500000;
		  } else if (priceRange.equals("500000-1000000")) {
		    minPrice = 500000;
		    maxPrice = 1000000;
		  } else if (priceRange.equals("1000000-1000000000")) {
			    minPrice = 1000000;
			    maxPrice = 1000000000;
			  }
		  // Query the database for matching products
		  ProductDAO productDAO = new ProductDAO();
		  List<Product> productsByRangePrice = productDAO.getProductsByPrice(minPrice, maxPrice);
		  req.setAttribute("listProductClient", productsByRangePrice);
		
//		Gson gson = new Gson();
//		String productListJson = gson.toJson(productList);

		req.getRequestDispatcher("/views/client/list_product_by_range_price.jsp").forward(req, resp);

	}
}
