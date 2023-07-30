package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Image;


public class ImageDAO extends DBConnect {
	public List<Image> getAll(){
		List<Image> images = new ArrayList<Image>();
		String sql = "SELECT * FROM image";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Image image = new Image(
						rs.getInt("imageId"),
						rs.getString("imagePath"),
						null
				);
				images.add(image);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return images;
	}
	
	public Image getById(int imageId) {
		String sql = "SELECT * FROM image WHERE imageId=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, imageId);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Image image = new Image(
						rs.getInt("imageId"),
						rs.getString("imagePath"),
						null
				);
				return image;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public void insert(Image image) {
		String sql = "INSERT INTO image VALUES(?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, image.getImageId());
			pst.setString(2, image.getImage());
			pst.setInt(3, image.getProduct().getProductId());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void update(Image image) {
		String sql = "UPDATE image SET imagePath=?, productId=? WHERE imageId=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, image.getImage());
			pst.setInt(2, image.getProduct().getProductId());
			pst.setInt(3, image.getImageId());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(int imageId) {
		String sql = "DELETE FROM image WHERE imageId=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, imageId);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
