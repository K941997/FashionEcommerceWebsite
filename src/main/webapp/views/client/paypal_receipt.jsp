<%@page import="java.sql.PreparedStatement"%>
<%@page import="dao.ProductColorSizeDAO"%>
<%@page import="model.ProductColorSize"%>
<%@page import="model.CartItem"%>
<%@page import="dao.ProductDAO"%>

<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Format Money VND -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />
<fmt:setBundle basename="com.example.message" />

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Add thư mục assets CSS của Shop Template -->
<c:url value="views/client/assets" var="urlAssets"></c:url>


<!-- Cart -->
<%
	//Tất cả Có thể viết trong Servlet:

	//(Đã viết trong Servlet) Phải Login mới xem được Giỏ Hàng và Checkout -> có thông tin User:
	if(session.getAttribute("userLoginClient") == null){
		response.sendRedirect(request.getContextPath() + "/login-client");
	}

	//Dùng cho Cart (Phải ghi ở mọi nơi):
	ArrayList<CartItem> cart_list = (ArrayList<CartItem>) session.getAttribute("cart-list");
	if(cart_list != null){
		request.setAttribute("cart_list", cart_list);
	};


%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Receipt Paypal</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="${urlAssets}/images/icons/favicon.png"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/fonts/linearicons-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/css/util.css">
	<link rel="stylesheet" type="text/css" href="${urlAssets}/css/main.css">
<!--===============================================================================================-->


<!-- Test -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>


</head>
<body >
	
	<!-- Header -->
	<%@include file="layout/header.jsp" %>
	<!-- End Header -->

	<!-- breadcrumb -->
	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="index" class="stext-109  hov-cl1 trans-04">
				Home
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a>

			<span class="stext-109 cl4">
				Receipt Paypal
			</span>
		</div>
	</div>
		
 
	
								
	<!-- Shoping Cart -->
	<div class="bg0 p-t-75 p-b-85">
		<div class="container">
			<div class="row">
			
				<div class="col-lg-10 col-xl-10 m-lr-auto m-b-50">
				
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						
						<div align="center">
							<h1>Payment DONE ! Thank you for purchasing our products</h1>
							
							<br>
							<br>
							
							<h4 class="mtext-109 cl2 p-b-30">
								Receipt Details:
							</h4>
							
								<table>
									<!-- 
									<tr>
										<td>Description:</td> //Tên sản phẩm
										<td>${transaction.description}</td>
									</tr> 
									-->
									
									<!-- 
									<tr>
										<td>Shipping:</td>
										<td>${transaction.amount.details.shipping}</td>
									</tr>
									 -->
									<!-- 
									<tr>
										<td>Tax:</td>
										<td>${transaction.amount.details.tax}</td>
									</tr>
									 -->
									<tr>
										<td>Total:</td>
										<td>${transaction.amount.total} USD</td>
									</tr>
									
									
									<tr><td><br></td></tr>
									
									
									<tr> 
										<td colspan="2">
											<h4 class="mtext-109 cl2 p-b-30">
												Payer Information: <!-- Dữ liệu ở thanh toán của Paypal -->
											</h4>
										</td> 
									
									</tr>
									<tr>
										<td>Payer Name:</td>
										<td>${payer.firstName} ${payer.lastName}</td>
									</tr>
									
									<tr>
										<td>Email:</td>
										<td>${payer.email}</td>
									</tr>
									
									<tr><td><br></td></tr>
									
									<tr> 
										
										<td colspan="2">
											<h4 class="mtext-109 cl2 p-b-30">
												Shipping Address: <!-- Dữ liệu ở thanh toán của Paypal -->
											</h4>
										</td> 
									</tr>
									<tr>
										<td>Recipient Name:</td>
										<td>${shippingAddress.recipientName}</td>
									</tr>
									<tr>
										<td>Line 1:</td>
										<td>${shippingAddress.line1}</td>
									</tr>
									<tr>
										<td>City:</td>
										<td>${shippingAddress.city}</td>
									</tr>
									<tr>
										<td>State:</td>
										<td>${shippingAddress.state}</td>
									</tr>
									<tr>
										<td>Country Code:</td>
										<td>${shippingAddress.countryCode}</td>
									</tr>
									<tr>
										<td>Postal Code:</td>
										<td>${shippingAddress.postalCode}</td>
									</tr>
								</table>
							
						</div>
					</div>
				</div>
				
				
				
						
			</div>
		</div>
	</div>
		
		
	<!-- Header -->
	<%@include file="layout/footer.jsp" %>
	<!-- End Header -->


	<!-- Back to top -->
	<div class="btn-back-to-top" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="zmdi zmdi-chevron-up"></i>
		</span>
	</div>

<!--===============================================================================================-->	
	<script src="${urlAssets}/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/bootstrap/js/popper.js"></script>
	<script src="${urlAssets}/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/select2/select2.min.js"></script>
	<script>
		$(".js-select2").each(function(){
			$(this).select2({
				minimumResultsForSearch: 20,
				dropdownParent: $(this).next('.dropDownSelect2')
			});
		})
	</script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script>
		$('.js-pscroll').each(function(){
			$(this).css('position','relative');
			$(this).css('overflow','hidden');
			var ps = new PerfectScrollbar(this, {
				wheelSpeed: 1,
				scrollingThreshold: 1000,
				wheelPropagation: false,
			});

			$(window).on('resize', function(){
				ps.update();
			})
		});
	</script>
<!--===============================================================================================-->
	<script src="${urlAssets}/js/main.js"></script>

</body>
</html>