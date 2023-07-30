package adminSizeController;

import java.io.IOException;

import dao.SizeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Size;


@WebServlet(urlPatterns = {"/add-size"})
public class AddSizeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/admin/add_size.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String sSizeId = req.getParameter("sizeId"); //hidden id PK auto_increment
		String sizeName = req.getParameter("sizeName");
		
		SizeDAO sizeDAO = new SizeDAO();
		try {
			//Ép đúng kiểu dữ liệu:
			//int sizeId = Integer.parseInt(sSizeId); //nên hidden size_id PK, nên thêm custom_size_id ở JSP
			
			//Kiểm tra:
			if(sizeName == "" || sizeName.equals("")) {
				//Nếu không nhập Name:
				System.out.println("Name is null !");
				req.setAttribute("errorAddSizeServlet", "Name is null !");
				req.getRequestDispatcher("/views/admin/add_size.jsp").forward(req, resp);
			} else {
				//Nếu đã nhập hết:
				System.out.println("Da nhap het !");
				
				//Kiểm tra tồn tại:
				System.out.println("Check Exist ...");
				Size sizeExist = sizeDAO.getByName(sizeName);
				if(sizeExist == null) {
					//Nếu chưa tồn tại Name:
					System.out.println("Name doesn't exist -> You can Create !");
					//Create:
					Size sizeNew = new Size();
					sizeNew.setSizeName(sizeName);
					sizeDAO.insert(sizeNew);
					resp.sendRedirect("list-size");
				} else {
					//Nếu đã tồn tại Name:
					System.out.println("Name already exists");
					req.setAttribute("errorAddSizeServlet", "Name " +sizeName+ " already exists !");
					req.getRequestDispatcher("/views/admin/add_size.jsp").forward(req, resp);
				}
			}
			
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorAddSizeServlet", "Null Number When Add Size !");
			req.getRequestDispatcher("/views/admin/add_size.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorAddSizeServlet", "Error Add Size !");
			req.getRequestDispatcher("/views/admin/add_size.jsp").forward(req, resp);
		}
	}
}
