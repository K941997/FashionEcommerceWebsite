package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Size;


public class SizeDAO extends DBConnect {
	//Get All:
	public List<Size> getAll(){
		List<Size> sizes = new ArrayList<Size>();
		String sql = "SELECT * FROM size";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Size size = new Size(
						rs.getInt("size_id"),
						rs.getString("name")
				);
				sizes.add(size);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sizes;
	}
	
	//Get All Sizes By Product ID (ManyToMany):
	public List<Size> getAllSizesByProductID(int productId){
		List<Size> sizes = new ArrayList<Size>();
		String sql = "SELECT DISTINCT size.size_id, size.name FROM size "
				+ " INNER JOIN product_color_size ON size.size_id = product_color_size.size_id "
				+ " WHERE product_color_size.product_id = ? ";
		
//		//Test lấy theo Product và Color:
//		String sql = "SELECT DISTINCT size.size_id, size.name FROM size "
//				+ " INNER JOIN product_color_size ON size.size_id = product_color_size.size_id "
//				+ " WHERE product_color_size.product_id = ? "
//				+ " AND product_color_size.color_id = ? ";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, productId);
//			pst.setInt(2, colorId);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Size size = new Size(
						rs.getInt("size_id"),
						rs.getString("name")
				);
				sizes.add(size);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return sizes;
	}
	
	//Get By Id:
	public Size getById(int sizeId) {
		String sql = "SELECT * FROM size WHERE size_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, sizeId);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Size size = new Size(
						rs.getInt("size_id"),
						rs.getString("name")
				);
				return size;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//Get By Name:
	public Size getByName(String name) {
		String sql = "SELECT * FROM size WHERE name=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				Size size = new Size(
						rs.getInt("size_id"),
						rs.getString("name")
				);
				return size;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//Create:
	public void insert(Size size) {
		String sql = "INSERT INTO size VALUES(?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, size.getSizeId());
			pst.setString(2, size.getSizeName());
			pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//Update:
	public void update(Size size) {
		String sql = "UPDATE size SET name=?, modified_at=?  WHERE size_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, size.getSizeName());
			pst.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pst.setInt(3, size.getSizeId());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Delete:
	public void delete(int sizeId) {
		String sql = "DELETE FROM size WHERE size_id=?";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, sizeId);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//Paginate:
	public List<Size> getListByPage(List<Size> sizes, int start, int end){
		ArrayList<Size> arr = new ArrayList<>();
		for(int i = start; i<end; i++) {
			arr.add(sizes.get(i));
		}
		return arr;
	}
}
