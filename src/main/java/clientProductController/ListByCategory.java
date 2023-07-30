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


@WebServlet(urlPatterns = {"/category-list-product"})
public class ListByCategory extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Get List Product By CategoryId:
		String sCategoryId = req.getParameter("cid"); //take in JSP list_product_client category-list-product?cid=
		int categoryId = Integer.parseInt(sCategoryId);
		ProductDAO productDAO = new ProductDAO();
		List<Product> listProductByCateId = productDAO.getProductsByCategoryId(categoryId);
		req.setAttribute("listProductClient", listProductByCateId);
		
		//Lấy lại Category tránh lỗi:
		List<Category> categories = new ArrayList<Category>();
		CategoryDAO categoryDAO = new CategoryDAO();
		categories = categoryDAO.getAll();
		req.setAttribute("listCategoryClient", categories);
		
		//Lấy lại Color tránh lỗi:
		List<Color> colors = new ArrayList<Color>();
		ColorDAO colorDAO = new ColorDAO();
		colors = colorDAO.getAll();
		req.setAttribute("listColorClient", colors);
		
		//Đánh dấu Category Chọn:
		req.setAttribute("tag", categoryId);
		
		req.getRequestDispatcher("/views/client/list_product_by_category.jsp").forward(req, resp);
	}
}
