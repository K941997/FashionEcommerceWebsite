package clientProductController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.CategoryDAO;
import dao.ColorDAO;
import dao.ProductColorSizeDAO;
import dao.ProductDAO;
import dao.SizeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Color;
import model.Product;
import model.ProductColorSize;
import model.Size;

@WebServlet(urlPatterns = {"/detail-product"})
public class DetailProduct extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductColorSizeDAO productColorSizeDAO = new ProductColorSizeDAO();
		
		//Detail Product (liên quan đến ManyToMany nên dùng PCSDAO):
		String sProductId = req.getParameter("pid"); //pid from JSP Client index
		int productId = Integer.parseInt(sProductId);
		ProductDAO productDAO = new ProductDAO();
		Product product = productDAO.getById(productId);
		req.setAttribute("product", product);
		
		//Get List PCSs By ProductID: (Chức năng Hiển thị các ảnh PCS của Product)
		List<ProductColorSize> listPCSByProductId = productColorSizeDAO.getListPCSByProductId(productId);
		req.setAttribute("listPCSByProductId", listPCSByProductId);
		
		
		//Get List Colors By ProductID:
		List<Color> colors = new ArrayList<Color>();
		ColorDAO colorDAO = new ColorDAO();
		colors = colorDAO.getAllColorsByProductId(productId);
		req.setAttribute("colors", colors);
	
		//Get List Sizes By ProductID:
		List<Size> sizes = new ArrayList<Size>();
		SizeDAO sizeDAO = new SizeDAO();
		sizes = sizeDAO.getAllSizesByProductID(productId);
		req.setAttribute("sizes", sizes);
		
		
		req.getRequestDispatcher("/views/client/detail_product.jsp").forward(req, resp);
	}
}
