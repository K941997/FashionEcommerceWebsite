package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import model.Category;
import model.User;

public class UserDAO extends DBConnect{
	//Login (Admin + Client):
	public User checkLogin(String email, String password) {
		String sql = "SELECT * FROM user WHERE email=? AND password=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				User user = new User(
						rs.getInt(1), 
						rs.getString(2), 
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getInt(8)
				);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Check Existed Account (Admin + Client):
	public boolean existedUser(String email) {
		String sql = "SELECT * FROM user WHERE email = ?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//Change Password (Admin + Client):
	public void changePassword(User user) {
		String sql = "UPDATE user SET password=?, modified_at=? WHERE email=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, user.getPassword());
			pst.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pst.setString(3, user.getEmail());
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//Register (Client):
	public void register(String email, String password,Timestamp createdAt, Timestamp modified_at) {
		String sql = "INSERT INTO user (email, password, created_at, modified_at) values (?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			pst.setString(2, password);
			pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pst.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	//Update Profile (Admin + Client):
	public boolean updateProfile(User user) {
		boolean f = false;
		try {
			String sql = "UPDATE user SET email=?, password=?, "
					+ "fullname=?, avatar=?, phone=?, "
					+ "address=?, role=?, modified_at=?  WHERE user_id=?";
			
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, user.getEmail());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
			pst.setString(4, user.getAvatar());
			pst.setString(5, user.getPhone());
			pst.setString(6, user.getAddress());
			pst.setInt(7, user.getRole());
			pst.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			pst.setInt(9, user.getUserId());
			
			pst.executeUpdate();
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	//Get All (Admin):
	public List<User> getAll(){
		List<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM user";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				User user = new User(
						rs.getInt("user_id"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("fullname"),
						rs.getString("avatar"),
						rs.getString("phone"),
						rs.getString("address"),
						rs.getInt("role")
				);
				users.add(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return users;
	}
	
	//Get By Id (Admin + Client) :
	public User getById(int id) {
		String sql = "SELECT * FROM user WHERE user_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				User user = new User(
						rs.getInt("user_id"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("fullname"),
						rs.getString("avatar"),
						rs.getString("phone"),
						rs.getString("address"),
						rs.getInt("role"),
						rs.getTimestamp("created_at"),
						rs.getTimestamp("modified_at")
				);
				return user;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//Get By Email (Admin + Client):
	public User getByEmail(String email) {
		String sql = "SELECT * FROM user WHERE email=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				User user = new User(
						rs.getInt("user_id"),
						rs.getString("email"),
						rs.getString("password"),
						rs.getString("fullname"),
						rs.getString("avatar"),
						rs.getString("phone"),
						rs.getString("address"),
						rs.getInt("role"),
						rs.getTimestamp("created_at"),
						rs.getTimestamp("modified_at")
				);
				return user;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//Create (Admin):
	public void insert(String email, String password, String fullName, String avatar,
			String phone, String address, int role, Timestamp createdAt, Timestamp modified_at) {
		String sql = "INSERT INTO user (email, password, fullname, avatar, phone,"
				+ " address, role, created_at, modified_at) VALUES(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			
			pst.setString(1, email);
			pst.setString(2, password);
			pst.setString(3, fullName);
			pst.setString(4, avatar);
			pst.setString(5, phone);
			pst.setString(6, address);
			pst.setInt(7, role);
			pst.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			pst.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//Update (Admin + Client):
	public void update(User user) {
		String sql = "UPDATE user SET email=?, password=?, fullname=?, avatar=?,"
				+ "phone=?, address=?, role=?, modified_at=? WHERE user_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, user.getEmail());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
			pst.setString(4, user.getAvatar());
			pst.setString(5, user.getPhone());
			pst.setString(6, user.getAddress());
			pst.setInt(7, user.getRole());
			pst.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			pst.setInt(9, user.getUserId());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Delete (Admin):
	public void delete(int id) {
		String sql = "DELETE FROM user WHERE user_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Paginate (Admin):
	public List<User> getListByPage(List<User> users, int start, int end){
		ArrayList<User> arr = new ArrayList<>();
		for(int i = start; i<end; i++) {
			arr.add(users.get(i));
		}
		return arr;
		
	}
}
