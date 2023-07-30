package clientUserController;

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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.User;



//Test
//location = "D:/imageProject",
//Image
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB for Image
maxFileSize = 1024 * 1024 * 50, // 50MB for Image
maxRequestSize = 1024 * 1024 * 50) // 50MB for Image

@WebServlet(urlPatterns = { "/update-profile-user-client" })
public class UpdateProfileUserClient extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sUserId = req.getParameter("userId");
		String userEmail = req.getParameter("userEmail");

		String userFullname = req.getParameter("userFullname");
		String userPhone = req.getParameter("userPhone");
		String userAddress = req.getParameter("userAddress");
		Part userAvatarPart = req.getPart("userAvatar");

		UserDAO userDAO = new UserDAO();

		try {
			// Ép kiểu:
			int userId = Integer.parseInt(sUserId);

			// Get User From Session:
			HttpSession session = req.getSession(); // session luu o Login va Header
			User userSession = (User) session.getAttribute("userLoginClient"); // session luu o Login va Header
			
			User userNow = userDAO.getById(userId);
			
			//Kiem tra;
			if(userEmail == null || userEmail.equals("") ) {
				req.setAttribute("messageUpdateProfile", "Email is null !");
				req.getRequestDispatcher("views/client/profile_user_client.jsp").forward(req, resp);
			} else {
				
				// image: Nếu ko thêm file thì giữ nguyên avatar của User Session:
				String imageName = userAvatarPart.getSubmittedFileName();
				
				if (imageName == null || imageName.equals("")) {
					System.out.println("Image is null !");
					userNow.setAvatar(userNow.getAvatar());
				} else {
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

				//Update Others no need Check:
				userNow.setUserId(userId);
				userNow.setFullname(userFullname);
				userNow.setPhone(userPhone);
				userNow.setAddress(userAddress);				
				
//				//Kiem tra Email:
				if(userEmail.equals(userNow.getEmail())) {
					//Neu Email nhap == Email hien tai:
					System.out.println("Email new == Email now -> Update keep Email !");
					userNow.setEmail(userEmail);
					userDAO.update(userNow);
					
					//Update Session After Update Success:
					userSession.setAvatar(userNow.getAvatar());
					userSession.setUserId(userId);
					userSession.setEmail(userEmail);
					userSession.setFullname(userFullname);
					userSession.setPhone(userPhone);
					userSession.setAddress(userAddress);
				
					
					req.setAttribute("messageUpdateProfile", "Update Success keep Email !");
					req.getRequestDispatcher("views/client/profile_user_client.jsp").forward(req, resp);
				} else {
					//Neu Email nhap != Email hien tai:
					System.out.println("Email new != Email now -> Check Exist ...");
					//Kiem tra Email ton tai:
					User userExistEmail = userDAO.getByEmail(userEmail);
					if(userExistEmail == null) {
						//Nếu chưa tồn tại Email:
						System.out.println("Email doesn't exist -> You can Update !");
						userNow.setEmail(userEmail);
						userDAO.update(userNow);
						
						//Update Session After Update Success:
						userSession.setAvatar(userNow.getAvatar());
						userSession.setUserId(userId);
						userSession.setEmail(userEmail);
						userSession.setFullname(userFullname);
						userSession.setPhone(userPhone);
						userSession.setAddress(userAddress);
			
						
						req.setAttribute("messageUpdateProfile", "Update Success with New Email !");
						req.getRequestDispatcher("views/client/profile_user_client.jsp").forward(req, resp);
					} else {
						//Nếu tồn tại Email:
						System.out.println("Email already exists -> You cannot Update !");
						req.setAttribute("messageUpdateProfile", "Email already exists !");
						req.getRequestDispatcher("views/client/profile_user_client.jsp").forward(req, resp);
					}
				}
			}
			
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("messageUpdateProfile", "NumberFormatException !");
			req.getRequestDispatcher("views/client/profile_user_client.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			req.setAttribute("messageUpdateProfile", "Update Fail !");
			req.getRequestDispatcher("views/client/profile_user_client.jsp").forward(req, resp);
			
		}
			
		
	}
}
