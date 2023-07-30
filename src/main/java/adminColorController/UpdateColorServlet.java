package adminColorController;

import java.io.IOException;

import dao.ColorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Color;

@WebServlet(urlPatterns = {"/update-color"})
public class UpdateColorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sColorId = req.getParameter("id"); //id lấy từ parameter "/update-color?id=..." from JSP list_color
		ColorDAO colorDAO = new ColorDAO();
		try {
			int colorId = Integer.parseInt(sColorId);
			Color color = colorDAO.getById(colorId);
			req.setAttribute("colorUpdateServlet", color);	
			req.getRequestDispatcher("/views/admin/update_color.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sColorId = req.getParameter("colorId"); //hidden id PK auto_incre
		String colorName = req.getParameter("colorName");
		ColorDAO colorDAO = new ColorDAO();
		try {
			//Ép kiểu:
			int colorId = Integer.parseInt(sColorId); //hidden id PK auto_incre
			
			Color colorNow = colorDAO.getById(colorId);
			
			//Kiểm tra:
			if(colorName == "" || colorName.equals("")) {
				//Nếu chưa nhập Name:
				System.out.println("Name is null !");
				req.setAttribute("errorUpdateColor", "Name is null !");
				req.setAttribute("colorUpdateServlet", colorNow);
				req.getRequestDispatcher("/views/admin/update_color.jsp").forward(req, resp);
			} else { 
				//Nếu đã nhập Name:
				System.out.println("Da nhap het !");
				
				//Nếu Name nhập == Name hiện tại:
				if(colorName.equals(colorNow.getColorName())) {
					System.out.println("Name new == Name now -> Update keep Name !");
					colorNow.setColorName(colorName);
					colorDAO.update(colorNow);
					resp.sendRedirect("list-color");
					
				} else { 
					//Nếu Name nhập != Name hiện tại:
					System.out.println("Name new != Name now -> Check ...");
					
					//Kiểm tra tồn tại:
					System.out.println("Check Exist ...");
					Color colorExist = colorDAO.getByName(colorName);
					if(colorExist == null) {
						//Nếu chưa tồn tại Name:
						System.out.println("Name doesn't exist !");
						colorNow.setColorName(colorName);
						colorDAO.update(colorNow);
						resp.sendRedirect("list-color");
					} else {
						//Nếu đã tồn tại Name:
						System.out.println("Name already exists !");
						req.setAttribute("errorUpdateColor", "Name " +colorName+ " already exists !");
						req.setAttribute("colorUpdateServlet", colorNow);
						req.getRequestDispatcher("/views/admin/update_color.jsp").forward(req, resp);
					}
				}
			}
			
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorUpdateColor", "Null Number When Update Color !");
			req.getRequestDispatcher("/views/admin/update_color.jsp").forward(req, resp);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorUpdateColor", "Error Update Color !");
			req.getRequestDispatcher("/views/admin/update_color.jsp").forward(req, resp);
		}
	}
}
