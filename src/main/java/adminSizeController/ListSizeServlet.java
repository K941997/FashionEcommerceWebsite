package adminSizeController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.SizeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Size;

@WebServlet(urlPatterns = {"/list-size"})
public class ListSizeServlet extends HttpServlet {
	private SizeDAO sizeDAO;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sizeDAO = new SizeDAO();
		List<Size> sizes = new ArrayList<Size>();
		sizes = sizeDAO.getAll();
		
		//Paginate (Phân trang):
		int page, numberPage = 6;
		int size = sizes.size();
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
		List<Size> list = new ArrayList<Size>();
		list = sizeDAO.getListByPage(sizes, start, end);		
		req.setAttribute("sizesListSizeServlet", list);
		req.setAttribute("page", page);
		req.setAttribute("num", num);
		req.getRequestDispatcher("/views/admin/list_size.jsp").forward(req, resp);
		
	}
}
