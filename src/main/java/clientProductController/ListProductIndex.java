package clientProductController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.CategoryDAO;
import dao.ColorDAO;
import dao.ProductColorSizeDAO;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Color;
import model.Product;
import model.ProductColorSize;

@WebServlet(urlPatterns = {"/index"})
public class ListProductIndex extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Get List Products + Load More AJAX:
		List<Product> products = new ArrayList<Product>();
		ProductDAO productDAO = new ProductDAO();
		products = productDAO.getTop8Products(); //Get 8 Products đầu tiên
		req.setAttribute("listProductClient", products);
		
	
		//(Dùng trực tiếp trong JSP cũng đc)
//		for(Product p: products) {
//			//Get Min-Max Price của ProductColorSize của 1 Product: 
//			ProductColorSizeDAO productColorSizeDAO = new ProductColorSizeDAO();
//			
//			double pcsMinPrice = productColorSizeDAO.getMinPricePCS(p.getProductId());
//			req.setAttribute("pcsMinPrice", pcsMinPrice);
//			
//			double pcsMaxPrice = productColorSizeDAO.getMaxPricePCS(p.getProductId());
//			req.setAttribute("pcsMaxPrice", pcsMaxPrice);
//
//		}
		
		//Get List Category tránh lỗi:
		List<Category> categories = new ArrayList<Category>();
		CategoryDAO categoryDAO = new CategoryDAO();
		categories = categoryDAO.getAll();
		req.setAttribute("listCategoryClient", categories);
		
		//Get List Color tranh loi:
		List<Color> colors = new ArrayList<Color>();
		ColorDAO colorDAO = new ColorDAO();
		colors = colorDAO.getAll();
		req.setAttribute("listColorClient", colors);
		
		req.getRequestDispatcher("/views/client/index.jsp").forward(req, resp);
	}
}
