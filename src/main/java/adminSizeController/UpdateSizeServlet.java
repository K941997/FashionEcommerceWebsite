package adminSizeController;

import java.io.IOException;

import dao.SizeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Size;

@WebServlet(urlPatterns = {"/update-size"})
public class UpdateSizeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sSizeId = req.getParameter("id"); //id from parameter "/update-size?id=..." from JSP list_size
		SizeDAO sizeDAO = new SizeDAO();
		try {
			//Ép kiểu:
			int sizeId = Integer.parseInt(sSizeId);
			Size size = sizeDAO.getById(sizeId);
			req.setAttribute("sizeUpdateServlet", size);
			req.getRequestDispatcher("/views/admin/update_size.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sSizeId = req.getParameter("sizeId"); //hidden id PK auto_incre
		String sizeName = req.getParameter("sizeName");
		
		SizeDAO sizeDAO = new SizeDAO();
		
		try {
			//Ép kiểu cho id:
			int sizeId = Integer.parseInt(sSizeId);	//hidden id PK auto_incre
			
			Size sizeNow = sizeDAO.getById(sizeId);
		
			//Kiểm tra:
			if(sizeName == "" || sizeName.equals("")) {
				//Nếu không nhập Name:
				System.out.println("Name is null !");
				req.setAttribute("errorUpdateSize", "Name is null ! ");
				req.setAttribute("sizeUpdateServlet", sizeNow); //lấy lại giá trị Size khi lỗi
				req.getRequestDispatcher("/views/admin/update_size.jsp").forward(req, resp);
			} else { 
				//Nếu đã nhập Name:
				System.out.println("Da nhap het !");
				
				//Nếu Name nhập == Name hiện tại:
				if(sizeName.equals(sizeNow.getSizeName())) { //Dùng Equals để so sánh giá trị
					System.out.println("Name new == Name now -> Update keep Name !");
					sizeNow.setSizeName(sizeName);
					sizeDAO.update(sizeNow); //update để có cả modified_at mới
					resp.sendRedirect("list-size");	
				} else { 
					//Nếu Name nhập != Name hiện tại:
					System.out.println("Name new != Name now -> Check ...");
					
					//Kiểm tra tồn tại hay chưa:
					System.out.println("Check Exist ...");
					Size sizeExist = sizeDAO.getByName(sizeName);
					if(sizeExist == null) { 
						//Nếu Name chưa tồn tại:
						System.out.println("Name doesn't exist -> You can Update !");
						sizeNow.setSizeName(sizeName);
						sizeDAO.update(sizeNow);
						resp.sendRedirect("list-size");
					} else { 
						//Nếu Name đã tồn tại:					
						System.out.println("Name already exists !");
						req.setAttribute("errorUpdateSize", "Name " +sizeName+ " already exists !");
						req.setAttribute("sizeUpdateServlet", sizeNow); //lấy lại giá trị Size khi lỗi
						req.getRequestDispatcher("/views/admin/update_size.jsp").forward(req, resp);
					}
				}
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			req.setAttribute("errorUpdateSize", "Null Number When Update Size! ");
			req.getRequestDispatcher("/views/admin/update_size.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorUpdateSize", "Error Update Size! ");
			req.getRequestDispatcher("/views/admin/update_size.jsp").forward(req, resp);
		}
	}
}
