package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.*;

import com.mysql.cj.xdevapi.Statement;

import model.Category;
import model.Order;
import model.Product;
import model.Size;

public class OrderDAO extends DBConnect {
	
	//Create: Trả về order_id (Phục vụ cho OrderDetail):
	public int add( Order order) {
		String sql = "insert into sales_order (custom_id, totalmoney, status, user_id,"
				+ " created_at, modified_at) values (?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
		
			pst.setString(1, order.getCustomId());
			pst.setDouble(2, order.getTotalmoney());
			pst.setInt(3, order.getStatus());
			pst.setInt(4, order.getUser().getUserId()); //Relation 1-1 1-N with User
			pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			pst.setTimestamp(6, new Timestamp(System.currentTimeMillis()));	
			pst.executeUpdate();
			
			//Trả về order_id sau khi tạo:
			ResultSet rs = pst.getGeneratedKeys();
			if(rs.next()) {
				return rs.getInt(1);
			}
			rs.close();
	

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}	
		return 0;
	}
	
	//Get All Orders: (by user_id)
	public List<Order> clientAllOrders (int userId){
		List<Order> listOrders = new ArrayList<Order>();
		
		try {
			String sql = "SELECT DISTINCT sales_order.sales_order_id, sales_order.custom_id, sales_shipment.address AS shipment_address, "
					+ " sales_payment.name AS payment_name , sales_shipment.name AS shipment_name, "
					+ " sales_order.totalmoney, sales_order.created_at, "
					+ " sales_order.status "
					+ " FROM sales_order"
					+ "	JOIN sales_order_item on sales_order.sales_order_id = sales_order_item.sales_order_id "
					+ " JOIN sales_shipment on sales_order.sales_order_id = sales_shipment.sales_order_id "
					+ " JOIN sales_payment on sales_order.sales_order_id = sales_payment.sales_order_id "
					+ " JOIN user on user.user_id = sales_order.user_id "
					+ "	JOIN product_color_size on product_color_size.pcs_id = sales_order_item.pcs_id "
					+ " WHERE user.user_id = ? "
					+ " ORDER BY sales_order.sales_order_id DESC ";
			
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Order order = new Order(
						rs.getInt("sales_order_id"),
						rs.getString("custom_id"),
						rs.getDouble("totalmoney"),
						rs.getInt("status"),
						rs.getTimestamp("created_at"),
						rs.getString("shipment_address"),
						rs.getString("shipment_name"),
						rs.getString("payment_name")

				);
				listOrders.add(order);

			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listOrders;
	}
	
	
	//Cancel 1 Order: (Xóa hoặc Update status = 3 là Cancelled) (Vướng Foreign Key nên phải delete nhiều)
	public void cancelOrder(int orderId) {
		//Nếu Xóa:
//		try {
//			String sql1 = "DELETE FROM sales_order_item WHERE sales_order_id = ?";
//			PreparedStatement pst1 = connection.prepareStatement(sql1);
//			pst1.setInt(1, orderId);
//			pst1.executeUpdate();
//			String sql2 = "DELETE FROM sales_order WHERE sales_order_id = ?";
//			PreparedStatement pst2 = connection.prepareStatement(sql2);
//			pst2.setInt(1, orderId);
//			pst2.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//Nếu Update status Order:
		try {
			String sql1 = "UPDATE sales_order SET status=3 WHERE sales_order_id=? ";
			PreparedStatement pst1 = connection.prepareStatement(sql1);
			pst1.setInt(1, orderId);
			pst1.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	
	//Complete 1 Order: (Update status = 1 là Completed) (Vướng Foreign Key nên phải delete nhiều)
	public void completeOrderAdmin(int orderId) {
		try {
			String sql1 = "UPDATE sales_order SET status=1 WHERE sales_order_id=? ";
			PreparedStatement pst1 = connection.prepareStatement(sql1);
			pst1.setInt(1, orderId);
			pst1.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	//Process 1 Order: (Update status = 1 là Completed) (Vướng Foreign Key nên phải delete nhiều)
	public void processOrderAdmin(int orderId) {
		try {
			String sql1 = "UPDATE sales_order SET status=2 WHERE sales_order_id=? ";
			PreparedStatement pst1 = connection.prepareStatement(sql1);
			pst1.setInt(1, orderId);
			pst1.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	

	
	
	
	
	//Get All Orders: Manage Tất Cả Orders: (Mã đơn hàng, tên user, sđt, địa chỉ Ship, tổng tiền đơn, trạng thái, ngày kê đơn)
	public List<Order> adminAllOrders (){
		List<Order> listOrders = new ArrayList<Order>();
		
		try {
			String sql = "SELECT DISTINCT \r\n"
					+ "	sales_order.sales_order_id, \r\n"
					+ " sales_order.custom_id, \r\n"
					+ " user.fullname AS user_fullname, \r\n"
					+ " user.phone AS user_phone, \r\n"
					+ "	sales_shipment.address AS address_shipment, \r\n"
					+ " sales_order.totalmoney, \r\n"
					+ " sales_order.status,\r\n"
					+ " sales_order.created_at\r\n"
					+ "\r\n"
					+ "  FROM sales_order \r\n"
					+ "		JOIN sales_order_item ON sales_order.sales_order_id = sales_order_item.sales_order_id \r\n"
					+ "    	JOIN sales_shipment ON sales_order.sales_order_id = sales_shipment.sales_order_id\r\n"
					+ "     JOIN sales_payment ON sales_order.sales_order_id = sales_payment.sales_order_id\r\n"
					+ "     JOIN user ON user.user_id = sales_order.user_id\r\n"
					+ "		JOIN product_color_size ON product_color_size.pcs_id = sales_order_item.pcs_id\r\n"
					+ "\r\n"
					+ "     ORDER BY sales_order.sales_order_id DESC;";
			
			PreparedStatement pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Order order = new Order(
						rs.getInt("sales_order_id"),
						rs.getString("custom_id"),
						rs.getString("user_fullname"), //User
						rs.getString("user_phone"), //User
						rs.getString("address_shipment"), //Shipment
						rs.getDouble("totalmoney"),
						rs.getInt("status"),
						rs.getTimestamp("created_at")

				);
				listOrders.add(order);

			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listOrders;
	}
	
	//Paginate List Orders (Admin):
	public List<Order> getListByPage(List<Order> users, int start, int end){
		ArrayList<Order> arr = new ArrayList<>();
		for(int i = start; i<end; i++) {
			arr.add(users.get(i));
		}
		return arr;
		
	}
	

}
