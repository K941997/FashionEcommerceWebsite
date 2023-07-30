package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import model.Color;
import model.Product;
import model.ProductColorSize;
import model.Shipment;
import model.Size;

public class ShipmentDAO extends DBConnect {
	//Create:
	public void insert(Shipment shipment) {
		String sql = "INSERT INTO sales_shipment (name, user_id, sales_order_id, created_at, modified_at, address) "
				+ " VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setString(1, shipment.getName());
			pst.setInt(2, shipment.getUserId());
			pst.setInt(3, shipment.getOrderId());
			pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			pst.setString(6, shipment.getShipAddress());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	

}
