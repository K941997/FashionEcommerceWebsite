package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Color;

public class ColorDAO extends DBConnect {
	//Get All:
	public List<Color> getAll(){
		List<Color> colors = new ArrayList<Color>();
		String sql = "SELECT * FROM color";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Color color = new Color(
						rs.getInt("color_id"),
						rs.getString("name")
				);
				colors.add(color);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return colors;
	}
	
	//Get All Colors by ProductId (ManyToMany):
	public List<Color> getAllColorsByProductId(int productId){
		List<Color> colors = new ArrayList<Color>();
		String sql = "SELECT DISTINCT color.color_id, color.name FROM color "
				+ " INNER JOIN product_color_size ON color.color_id = product_color_size.color_id "
				+ " WHERE product_color_size.product_id = ?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, productId);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Color color = new Color(
						rs.getInt("color_id"),
						rs.getString("name")
				);
				colors.add(color);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return colors;
	}
		
	//Get By Id:
	public Color getById(int colorId) {
		String sql = "SELECT * FROM color WHERE color_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, colorId);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Color color = new Color(
						rs.getInt("color_id"),
						rs.getString("name")
				);
				return color;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//Get By Name:
	public Color getByName(String colorName) {
		String sql = "SELECT * FROM color WHERE name = ?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, colorName);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Color color = new Color(
						rs.getInt("color_id"),
						rs.getString("name")
				);
				return color;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//Create:
	public void insert(Color color) {
		String sql = "INSERT INTO color VALUES(?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, color.getColorId());
			pst.setString(2, color.getColorName());
			pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//Update:
	public void update(Color color) {
		String sql = "UPDATE color SET name=?, modified_at=?  WHERE color_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, color.getColorName());
			pst.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pst.setInt(3, color.getColorId());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Delete:
	public void delete(int colorId) {
		String sql = "DELETE FROM color WHERE color_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, colorId);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Paginate:
	public List<Color> getListByPage(List<Color> colors, int start, int end){
		ArrayList<Color> arr = new ArrayList<>();
		for(int i = start; i<end; i++) {
			arr.add(colors.get(i));
		}
		return arr;
		
	}
}
