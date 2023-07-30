package adminCategoryController;

import java.io.IOException;

import dao.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;

@WebServlet(urlPatterns = {"/update-category"})
public class UpdateCategoryServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sCategoryId = req.getParameter("id"); //id from parameter "/update-category?id=..." from JSP list_category
		CategoryDAO categoryDAO = new CategoryDAO();
		try {
			//Ép kiểu:
			int categoryId = Integer.parseInt(sCategoryId);
			Category category = categoryDAO.getById(categoryId);
			req.setAttribute("categoryUpdateServlet", category);
			req.getRequestDispatcher("/views/admin/update_category.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sCategoryId = req.getParameter("categoryId"); //hidden id PK auto_incre
		String categoryName = req.getParameter("categoryName");
		//boolean bStatusCheck = req.getParameter("categoryStatus")!=null;
		
		CategoryDAO categoryDAO = new CategoryDAO();
		try {
			//Ép kiểu:
			int categoryId = Integer.parseInt(sCategoryId); //hidden id PK auto_incre
			
			//int status = 0;
			//if(bStatusCheck)
				//status = 1;		
			
			Category categoryNow = categoryDAO.getById(categoryId);
			
			if(categoryName == "" || categoryName.equals("")) {
				//Nếu không nhập Name:
				System.out.println("Name is null !");
				req.setAttribute("errorUpdateCategory", "Name is null ! ");
				req.setAttribute("categoryUpdateServlet", categoryNow); //lấy lại giá trị category khi lỗi
				req.getRequestDispatcher("/views/admin/update_category.jsp").forward(req, resp);
			} else {
				//Nếu đã nhập hết:
				System.out.println("Da nhap het tat ca !");
				
//				//Kiểm tra Name:
				System.out.println("Check Name ...");
				//Nếu Name nhập == Name hiện tại:
				if(categoryName.equals(categoryNow.getCategoryName())) {
					System.out.println("Name new == Name now ->  Update keep Name !");
					categoryNow.setCategoryName(categoryName);
					categoryDAO.update(categoryNow);
					resp.sendRedirect("list-category");
				} else {
					//Nếu Name nhập != Name hiện tại:
					System.out.println("Name new != Name now -> Check ...");
					
					//Kiểm tra tồn tại hay chưa:
					System.out.println("Check Exist ...");
					Category categoryExistName = categoryDAO.getByName(categoryName);
					if(categoryExistName == null) {
						//Nếu chưa tồn tại Name:
						System.out.println("Name doesn't exist -> You can Update !");
						categoryNow.setCategoryName(categoryName);
						categoryDAO.update(categoryNow);
						resp.sendRedirect("list-category");
					} else {
						//Nếu đã tồn tại Name:
						System.out.println("Name already exists !");
						req.setAttribute("errorUpdateCategory", "Name " +categoryName+ " already exists !");
						req.setAttribute("categoryUpdateServlet", categoryNow);
						req.getRequestDispatcher("/views/admin/update_category.jsp").forward(req, resp);
					}	
				}
				
			}
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorUpdateCategory", "Null Number When Update Category !");
			req.getRequestDispatcher("/views/admin/update_category.jsp").forward(req, resp);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorUpdateCategory", "Error Update Category !");
			req.getRequestDispatcher("/views/admin/update_category.jsp").forward(req, resp);

		}
	}
}
