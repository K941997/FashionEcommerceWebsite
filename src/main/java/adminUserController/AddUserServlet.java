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

@WebServlet(urlPatterns = { "/add-user" })
public class AddUserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/admin/add_user.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String sUserId = req.getParameter("userId"); //hidden id PK Auto_increment, có thể thêm custom id
		String userEmail = req.getParameter("userEmail");
		String userPassword = req.getParameter("userPassword");
		String userFullname = req.getParameter("userFullname");
		String userPhone = req.getParameter("userPhone");
		String userAddress = req.getParameter("userAddress");
		String sUserRole = req.getParameter("userRole");
		Part userAvatarPart = req.getPart("userAvatar"); // image

		UserDAO userDAO = new UserDAO();

		try {
			// Ép kiểu:
			//int userId = Integer.parseInt(sUserId); //hidden id PK Auto_increment, có thể thêm custom id
			int userRole = Integer.parseInt(sUserRole);
			
			// image:
			String imageName = userAvatarPart.getSubmittedFileName();
			
			//Kiểm tra:
			if(userEmail.equals("")) {
				//Nếu chưa nhập Email:
				System.out.println("Email is null !");
				req.setAttribute("errorAddUserServlet", "Email is null !");
				req.getRequestDispatcher("/views/admin/add_user.jsp").forward(req, resp);
			} else if(userPassword.equals("")) {
				//Nếu chưa nhập Password:
				System.out.println("Password is null !");
				req.setAttribute("errorAddUserServlet", "Password is null !");
				req.getRequestDispatcher("/views/admin/add_user.jsp").forward(req, resp);
			} else if(sUserRole == null) { //role là number -> bắt lỗi NumberFormatException
				System.out.println("Role is null !");
				req.setAttribute("errorAddUserServlet", "Role is null !");
				req.getRequestDispatcher("/views/admin/add_user.jsp").forward(req, resp);
			} else if (imageName == null || imageName.equals("")) {
				//Nếu chưa nhập Image:
				System.out.println("Image is null !");
				req.setAttribute("errorAddUserServlet", "Image is null !");
				req.getRequestDispatcher("/views/admin/add_user.jsp").forward(req, resp);
			} else {
				//Đã nhập hết:
				System.out.println("Da nhap het !");
				
				//Kiểm tra tồn tại:
				System.out.println("Check Exist ...");
				User userExist = userDAO.getByEmail(userEmail);
				if (userExist == null) {
					//Nếu chưa tồn tại Email:
					System.out.println("Email doesn't exist -> You can Create !");
			
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
					
					//Create:
					userDAO.insert(userEmail, userPassword, userFullname, 
							imageName, userPhone, userAddress, userRole, null, null);
					resp.sendRedirect("list-user");
					
				} else {
					//Nếu đã tồn tại Email:
					System.out.println("Email already exist !");
					req.setAttribute("errorAddUserServlet", "Email " + userEmail + " already exist !");
					req.getRequestDispatcher("/views/admin/add_user.jsp").forward(req, resp);
				}
			}

		} catch (NumberFormatException e) { //role là number -> bắt lỗi NumberFormatException
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorAddUserServlet", "Null Number When Add User !");
			req.getRequestDispatcher("/views/admin/add_user.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("errorAddUserServlet", "Error Add User !");
			req.getRequestDispatcher("/views/admin/add_user.jsp").forward(req, resp);
		}
	}
}
