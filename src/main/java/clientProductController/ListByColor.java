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

@WebServlet(urlPatterns = {"/color-list-product"})
public class ListByColor extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Get List Product By ColorId:
		String sColorId = req.getParameter("colorId"); //JSP list_product_cliennt color-list-product?colorId=
		int colorId = Integer.parseInt(sColorId);
		ProductDAO productDAO = new ProductDAO();
		List<Product> listProductByColorId = productDAO.getProductsByColorId(colorId); //Relation ManyToMany
		req.setAttribute("listProductClient", listProductByColorId);
		
		//Lấy lại Color tránh lỗi:
		List<Color> colors = new ArrayList<Color>();
		ColorDAO colorDAO = new ColorDAO();
		colors = colorDAO.getAll();
		req.setAttribute("listColorClient", colors);
		
		//Lấy lại Category tránh lỗi:
		List<Category> categories = new ArrayList<Category>();
		CategoryDAO categoryDAO = new CategoryDAO();
		categories = categoryDAO.getAll();
		req.setAttribute("listCategoryClient", categories);
		
		//Đánh dấu Category Chọn:
		req.setAttribute("tag1", colorId);
		
		req.getRequestDispatcher("/views/client/list_product_by_color.jsp").forward(req, resp);
	}
}
