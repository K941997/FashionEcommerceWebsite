package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import model.Category;
import model.Color;

public class CategoryDAO extends DBConnect {
	//Get All:
	public List<Category> getAll(){
		List<Category> categories = new ArrayList<Category>();
		String sql = "SELECT * FROM category";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Category category = new Category(
						rs.getInt("category_id"),
						rs.getString("name")
				);
				categories.add(category);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return categories;
	}
	
	//Get By Id:
	public Category getById(int categoryId) {
		String sql = "SELECT * FROM category WHERE category_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, categoryId);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Category category = new Category(
						rs.getInt("category_id"),
					
						rs.getString("name")
				);
				return category;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	

	
	//Get By Name:
	public Category getByName(String name) {
		String sql = "SELECT * FROM category WHERE name = ?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Category category = new Category(
						rs.getInt("category_id"),
			
						rs.getString("name")
				);
				return category;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
//	//Create 1: (OK)
//	public void insert(Category category) {
//		String sql = "INSERT INTO category VALUES(?,?,?,?,?)";
//		try {
//			PreparedStatement pst = connection.prepareStatement(sql);
//			pst.setInt(1, category.getCategoryId());
//			pst.setString(2, category.getCategoryCustomId());
//			pst.setString(3, category.getCategoryName());
//			pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
//			pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
//			pst.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//	}
	
	//Create 2: (OK):
	public void insert(Category category) {
		String sql = "INSERT INTO category (category_id, name, created_at, modified_at) VALUES(?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, category.getCategoryId());
			pst.setString(2, category.getCategoryName());
			pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//Update:
	public void update(Category category) {
		String sql = "UPDATE category SET name=?, modified_at=? WHERE category_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, category.getCategoryName());
			pst.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pst.setInt(3, category.getCategoryId());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Delete:
	public void delete(int categoryId) {
		String sql = "DELETE FROM category WHERE category_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, categoryId);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//Paginate:
	public List<Category> getListByPage(List<Category> categories, int start, int end){
		ArrayList<Category> arr = new ArrayList<>();
		for(int i = start; i<end; i++) {
			arr.add(categories.get(i));
		}
		return arr;
		
	}
	
	
	
	
}
