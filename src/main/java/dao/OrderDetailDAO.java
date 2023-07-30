package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.OrderDetail;
import model.Product;

public class OrderDetailDAO extends DBConnect {
	
	//ADD: (When Order Checkout)
	public void add( OrderDetail orderDetail) {
		String sql = "insert into sales_order_item (quantity, price, pcs_id, sales_order_id,"
				+ " created_at, modified_at) values (?,?,?,?,?,?)";
		try {
			PreparedStatement pst = connection.prepareStatement(sql);
		
			pst.setInt(1, orderDetail.getQuantity());
			pst.setDouble(2, orderDetail.getPrice());
			pst.setInt(3, orderDetail.getProductColorSizeId());
			pst.setInt(4, orderDetail.getOrderId()); //Relation 1-1 1-N with User
			pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			pst.setTimestamp(6, new Timestamp(System.currentTimeMillis()));	
			pst.executeUpdate();
			
			//Checkout -> Viết SQL Xóa 1 Quantity ở ProductColorSize đi vì khách đã mua
			///////////
			///////////
			//////////
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	//Get By Order Id: (Order Detail -> Hiển thị thông tin sản phẩm, số lượng, giá, mã, màu, size sau khi order)
	public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
		List<OrderDetail> listOrderDetails = new ArrayList<OrderDetail>();
		
		try {
			String sql = "SELECT "
					+ "	sales_order_item.sales_order_item_id, "
					+ " sales_order.custom_id AS order_custom_id, "
					+ "	product_color_size.image AS pcs_image, "
					+ " product_color_size.price AS pcs_price, "
					+ " product.name AS product_name, "
					+ " color.name AS color_name, "
					+ "	size.name AS size_name, "
					+ " sales_order_item.quantity, "
					+ "	sales_order_item.price, "
					+ " sales_payment.name AS payment_name, sales_shipment.name AS shipment_name "
					+ " FROM sales_order_item "
					+ "	JOIN sales_order ON sales_order_item.sales_order_id = sales_order.sales_order_id "
					+ "	JOIN user ON user.user_id = sales_order.user_id "
					+ "	JOIN product_color_size ON product_color_size.pcs_id = sales_order_item.pcs_id "
					+ " JOIN color ON color.color_id = product_color_size.color_id "
					+ " JOIN size ON size.size_id = product_color_size.size_id "
					+ " JOIN product ON product_color_size.product_id = product.product_id "
					+ " join sales_shipment on sales_order.sales_order_id = sales_shipment.sales_order_id\r\n"
					+ "     join sales_payment on sales_order.sales_order_id = sales_payment.sales_order_id "
					+ " WHERE sales_order_item.sales_order_id = ? "
					+ "	ORDER BY sales_order_item.sales_order_item_id DESC";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, orderId);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				OrderDetail orderDetail = new OrderDetail(
						rs.getInt("sales_order_item_id"), //sales_order_item
						rs.getInt("quantity"), //sales_order_item
						rs.getDouble("price"), //sales_order_item
						rs.getString("order_custom_id"), //sales_order
						rs.getString("pcs_image"), //productcolorsize
						rs.getString("product_name"), //product
						rs.getDouble("pcs_price"), //productcolorsize
						rs.getString("color_name"), //color
						rs.getString("size_name"), //size
						rs.getString("payment_name"), //payment
						rs.getString("shipment_name") //shipment
		
				);	
				listOrderDetails.add(orderDetail);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listOrderDetails;
	}

}
