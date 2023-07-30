package adminUserController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.User;


@WebServlet(urlPatterns = {"/list-user"})
public class ListUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO userDAO = new UserDAO();
		List<User> users = new ArrayList<User>();
		users = userDAO.getAll();
		
		//Paginate (Phân trang):
		int page, numberPage = 6;
		int size = users.size();
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
		List<User> listUser = new ArrayList<User>();
		listUser = userDAO.getListByPage(users, start, end);		
		req.setAttribute("usersListServlet", listUser);
		req.setAttribute("page", page);
		req.setAttribute("num", num);
		
		req.getRequestDispatcher("/views/admin/list_user.jsp").forward(req, resp);
				
	}
}
