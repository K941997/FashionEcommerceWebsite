package adminColorController;

import java.io.IOException;

import dao.ColorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Color;
import model.Size;

@WebServlet(urlPatterns = {"/add-color"})
public class AddColorServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/admin/add_color.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String sColorId = req.getParameter("colorId"); //hidden id PK auto_increment
		String colorName = req.getParameter("colorName");
		
		ColorDAO colorDAO = new ColorDAO();
		try {
			//Ép đúng kiểu dữ liệu:
			//int colorId = Integer.parseInt(sColorId); //hidden id PK auto_increment
	
			//Kiểm tra:
			if(colorName == "" || colorName.equals("")) {
				//Nếu không nhập Name:
				System.out.println("Name is null !");
				req.setAttribute("errorAddColorServlet", "Name is null ! ");
				req.getRequestDispatcher("/views/admin/add_color.jsp").forward(req, resp);
			} else {
				//Nếu đã nhập hết:
				System.out.println("Da nhap het !");
				
				//Kiểm tra tồn tại:
				System.out.println("Check Exist ...");
				Color colorExist = colorDAO.getByName(colorName);
				if(colorExist == null) {
					//Nếu chưa tồn tại Name:
					System.out.println("Name doesn't exist -> You can Create !");
					
					//Create:
					Color colorNew = new Color();
					colorNew.setColorName(colorName);
					colorDAO.insert(colorNew);
					resp.sendRedirect("list-color");
				} else {
					//Nếu đã tồn tại Name:
					System.out.println("Name already exists !");
					req.setAttribute("errorAddColorServlet", "Name " +colorName+ " already exists !");
					req.getRequestDispatcher("/views/admin/add_color.jsp").forward(req, resp);
				}
			}
			
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorAddColorServlet", "Null Number When Add Color ! ");
			req.getRequestDispatcher("/views/admin/add_color.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorAddColorServlet", "Error Add Color ! ");
			req.getRequestDispatcher("/views/admin/add_color.jsp").forward(req, resp);
		}
	}
}
