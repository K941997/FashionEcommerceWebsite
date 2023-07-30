package adminCategoryController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;

@WebServlet(urlPatterns = {"/list-category"})
public class ListCategoryServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CategoryDAO categoryDAO = new CategoryDAO();
		List<Category> categories = new ArrayList<Category>();
		categories = categoryDAO.getAll();
		
		//Paginate (Phân trang):
		int page, numberPage = 6;
		int size = categories.size();
		int num = (size%6==0?(size/6):((size/6))+1);
		String xPage = req.getParameter("page");
		if(xPage == null) {
			page = 1;
		} else {
			page = Integer.parseInt(xPage);
		}
		int start, end;
		start = (page-1)*numberPage;
		end = Math.min(page*numberPage, size);
		List<Category> list = new ArrayList<Category>();
		list = categoryDAO.getListByPage(categories, start, end);		
		req.setAttribute("categoriesListServlet", list);
		req.setAttribute("page", page);
		req.setAttribute("num", num);
		req.getRequestDispatcher("/views/admin/list_category.jsp").forward(req, resp);
	
		//Chuan:
//		categories = categoryDAO.getAll();
//		req.setAttribute("categoriesListServlet", categories);
//		req.getRequestDispatcher("/views/admin/list_category.jsp").forward(req, resp);
	}
}
