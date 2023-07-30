package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import model.Color;
import model.Payment;
import model.Product;
import model.ProductColorSize;
import model.Shipment;
import model.Size;

public class PaymentDAO extends DBConnect {
	
	//Create:
	public void insert(Payment payment) {
		String sql = "INSERT INTO sales_payment (name, sales_order_id, created_at, modified_at) "
				+ " VALUES(?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, payment.getName());
			pst.setInt(2, payment.getOrderId());
			pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
		
		
		

}
