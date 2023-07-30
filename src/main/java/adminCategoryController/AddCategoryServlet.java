package adminCategoryController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Color;

@WebServlet(urlPatterns = {"/add-category"})
public class AddCategoryServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/admin/add_category.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String sCategoryId = req.getParameter("categoryId"); //hidden id PK auto_incre, có thể thêm custom id
		String categoryName = req.getParameter("categoryName");
		//boolean bStatusCheck = req.getParameter("categoryStatus") != null;
		
		CategoryDAO categoryDAO = new CategoryDAO();
		try {
			//Ép đúng kiểu dữ liệu:
//			int categoryId = Integer.parseInt(sCategoryId); //hidden id PK auto_incre
			
			//int status = 0;
			//if(bStatusCheck) {
				//status = 1;
			//}
			
			//Kiểm tra:
			if(categoryName == "" || categoryName.equals("")) {
				//Nếu chưa nhập Name:
				System.out.println("Name is null !");
				req.setAttribute("errorAddCategoryServlet", "Name is null !");
				req.getRequestDispatcher("/views/admin/add_category.jsp").forward(req, resp);
			} else {
				//Nếu đã nhập hết:
				System.out.println("Da nhap tat ca !");
				
				//Kiểm tra tồn tại:
				System.out.println("Check Exist ...");
				Category categoryExistName = categoryDAO.getByName(categoryName);
				
				if(categoryExistName != null) {
					//Nếu đã tồn tại Name:
					System.out.println("Name already exists !");
					req.setAttribute("errorAddCategoryServlet", "Name " +categoryName+ " already exists !");
					req.getRequestDispatcher("/views/admin/add_category.jsp").forward(req, resp);
					
				} else {
					//Nếu chưa tồn tại:
					System.out.println("Name doesn't exist  -> You can Create Category !");
					
					//Create:
					Category categoryNew = new Category();
					categoryNew.setCategoryName(categoryName);
					categoryDAO.insert(categoryNew);

					System.out.println("Category New: " +categoryNew);

					resp.sendRedirect("list-category");
				}	
			}	
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorAddCategoryServlet", "Null Number When Add Category ! ");
			req.getRequestDispatcher("/views/admin/add_category.jsp").forward(req, resp);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorAddCategoryServlet", "Error Add Category ! ");
			req.getRequestDispatcher("/views/admin/add_category.jsp").forward(req, resp);
		}
	}
}
