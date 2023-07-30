package adminColorController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.ColorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Color;


@WebServlet(urlPatterns = {"/list-color"})
public class ListColorServlet extends HttpServlet {
	private ColorDAO colorDAO;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		colorDAO = new ColorDAO();
		List<Color> colors = new ArrayList<Color>();
		colors = colorDAO.getAll();
		
		//Paginate (Phân trang):
		int page, numberPage = 6;
		int size = colors.size();
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
		List<Color> list = new ArrayList<Color>();
		list = colorDAO.getListByPage(colors, start, end);		
		req.setAttribute("colorsListColorServlet", list);
		req.setAttribute("page", page);
		req.setAttribute("num", num);
		req.getRequestDispatcher("/views/admin/list_color.jsp").forward(req, resp);
				
	}
}
