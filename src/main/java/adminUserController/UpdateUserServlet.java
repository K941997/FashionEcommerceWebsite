package adminUserController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import dao.UserDAO;
import image.ImagePathCustomFolder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.User;

//Image
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB for Image
maxFileSize = 1024 * 1024 * 50, // 50MB for Image
maxRequestSize = 1024 * 1024 * 50) // 50MB for Image

@WebServlet(urlPatterns = {"/update-user"})
public class UpdateUserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sUserId = req.getParameter("id"); //id lấy từ parameter "/update-user?id=..." from JSP list_user
		UserDAO userDAO = new UserDAO();
		try {
			int userId = Integer.parseInt(sUserId);
			User user = new User();
			user = userDAO.getById(userId);
			req.setAttribute("userUpdateServlet", user);
			req.getRequestDispatcher("/views/admin/update_user.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.getRequestDispatcher("/views/admin/update_user.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sUserId = req.getParameter("userId"); //hidden id PK auto_incre
		String userEmail = req.getParameter("userEmail");
		String userPassword = req.getParameter("userPassword");
		String userFullname = req.getParameter("userFullname");
		String userPhone = req.getParameter("userPhone");
		String userAddress = req.getParameter("userAddress");
		String sUserRole = req.getParameter("userRole"); //role
		Part userAvatarPart = req.getPart("userAvatar"); //image
		
		UserDAO userDAO = new UserDAO();
		
		try {
			//Ép đúng kiểu dữ liệu:
			int userId = Integer.parseInt(sUserId); //hidden id PK
			int userRole = Integer.parseInt(sUserRole);	
			
			//image:
			String imageName = userAvatarPart.getSubmittedFileName();
			
			User userNow = userDAO.getById(userId);
			
			//Kiểm tra:
			if(userEmail.equals("")) {
				//Nếu chưa nhập Email:
				System.out.println("Email is null !");
				req.setAttribute("errorUpdateUserServlet", "Email is null !");
				
				//Lấy lại data User khi lỗi:
				User user = new User();
				user = userDAO.getById(userId);
				req.setAttribute("userUpdateServlet", user);
				
				req.getRequestDispatcher("/views/admin/update_user.jsp").forward(req, resp);
				
			} else if(userPassword.equals("")) {
				//Nếu chưa nhập Password:
				System.out.println("Password is null !");
				req.setAttribute("errorUpdateUserServlet", "Password is null !");
				
				//Lấy lại data User khi lỗi:
				User user = new User();
				user = userDAO.getById(userId);
				req.setAttribute("userUpdateServlet", user);
				
				req.getRequestDispatcher("/views/admin/update_user.jsp").forward(req, resp);
				
			} else {
				//Đã nhập hết:
				System.out.println("Da nhap het !");
				
				//Các trường khác ko cần kiểm tra vì có thể null:
				userNow.setFullname(userFullname);
				userNow.setPhone(userPhone);
				userNow.setAddress(userAddress);
				userNow.setRole(userRole);
				userNow.setPassword(userPassword);
				
				
//				//Kiểm tra Image:
				System.out.println("Check Image ...");
				if(imageName == null || imageName.equals("")) {
					// image: Nếu ko thêm file thì giữ nguyên avatar của User Session:
					System.out.println("Not Insert New Image -> Keep Old Image !");
					userNow.setAvatar(userNow.getAvatar());
				} else {
					//Nếu đã nhập image:
					System.out.println("Insert New Image -> Update Image !");
					
					//image:
					userNow.setAvatar(imageName);

					//Image Path Tomcat Folder (Auto Load Image):
					String pathTomcatFolder = req.getServletContext().getRealPath("/images") + File.separator;
					//Image Path Custom Folder (No Auto Load Image):
					ImagePathCustomFolder file = new ImagePathCustomFolder();
					String pathCustomFolder = file.getImagePathCustomFolder() + File.separator;
					
					//Image Create in Path Tomcat Folder:
					System.out.println("Tomcat Folder: " +pathTomcatFolder);
					if (!Files.exists(Path.of(pathTomcatFolder))) {
						Files.createDirectory(Path.of(pathTomcatFolder));
					}
					userAvatarPart.write(pathTomcatFolder + "/" + imageName);
					System.out.println("Image in Tomcat Folder: " +userAvatarPart);
					//Image Create in Path Custom Folder:
					System.out.println("Custom Folder: " +pathCustomFolder);
					if (!Files.exists(Path.of(pathCustomFolder))) {
						Files.createDirectory(Path.of(pathCustomFolder));
					}
					userAvatarPart.write(pathCustomFolder + "/" + imageName);
					System.out.println("Image in Custom Folder: " +userAvatarPart);
					
				}
				

//				//Kiểm tra Email:
				System.out.println("Check Email ...");
				if(userEmail.equals(userNow.getEmail())) {
					//Nếu Email new == Email now:
					System.out.println("Email New = Email Now -> Update keep Email !");
					userNow.setEmail(userEmail);
					userDAO.update(userNow);
					resp.sendRedirect("list-user");	
				} else {
					//Nếu Email new != Email now:
					System.out.println("Email New != Email Now -> Check Exist ...");
					
					//Kiểm tra tồn tại:
					User userExistEmail = userDAO.getByEmail(userEmail);
					if(userExistEmail == null) {
						//Nếu chưa tồn tại Email:
						System.out.println("Email doesn't exist -> You can Update !");
						userNow.setEmail(userEmail);
						userDAO.update(userNow);
						resp.sendRedirect("list-user");	
					} else {
						//Nếu đã tồn tại Email:
						System.out.println("Email already exists !");
						req.setAttribute("errorUpdateUserServlet", "Email already exists !");
						
						//Lấy lại data User khi lỗi:
						User user = new User();
						user = userDAO.getById(userId);
						req.setAttribute("userUpdateServlet", user);
						
						req.getRequestDispatcher("/views/admin/update_user.jsp").forward(req, resp);
					}
				}
		
			}
	
		} catch (NumberFormatException e) { //dành cho Role ko nhập số:
			e.printStackTrace();
			req.setAttribute("errorUpdateUserServlet", "Null Number Update User !");
			
			//Lay lai gia tri User khi loi:
			int userId = Integer.parseInt(sUserId);
			User user = new User();
			user = userDAO.getById(userId);
			req.setAttribute("userUpdateServlet", user);
			
			req.getRequestDispatcher("/views/admin/update_user.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorUpdateUserServlet", "Error Update User !");
			
			//Lay lai gia tri User khi loi:
			int userId = Integer.parseInt(sUserId);
			User user = new User();
			user = userDAO.getById(userId);
			req.setAttribute("userUpdateServlet", user);
			
			req.getRequestDispatcher("/views/admin/update_user.jsp").forward(req, resp);
			
		}
	}
}
