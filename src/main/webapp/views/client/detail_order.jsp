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

	

	//Dùng cho Cart (Phải ghi ở mọi nơi):
	ArrayList<CartItem> cart_list = (ArrayList<CartItem>) session.getAttribute("cart-list");
	if(cart_list != null){
		request.setAttribute("cart_list", cart_list);
	};


%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Detail Order</title>
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
				Detail Order
			</span>
		</div>
	</div>
		
 
	
								
	<!-- Detail Order -->
	<div class="bg0 p-t-75 p-b-85">
		<div class="container">
			<div class="row">
			
				<div class="col-lg-10 col-xl-10 m-lr-auto m-b-50">
				
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<h4 class="mtext-109 cl2 p-b-30">
							Detail Order
						</h4>
						<div class="wrap-table-shopping-cart">
							 <!-- Hiển thị Error -->
							<p class="text-danger">${requestScope.errorDetailOrderServlet}</p>
	
							<!-- All Orders -->
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th hidden="" class="column-1">Order Detail ID</th>
									<th class="column-1">Order ID</th>
									<th class="column-1">Product</th>
									<th class="column-1"></th>
									<th class="column-1">Color</th>
									<th class="column-1">Size</th>
									<th class="column-1">Quantity</th>
									<th class="column-1">Price</th>
									<th class="column-5">Unit Price</th>
								</tr>
								
								<c:forEach var="orderDetail" items="${orderDetails}">							
									<tr class="table_row">
										<td hidden=""  class="column-1">${orderDetail.orderDetailId}</td>
										<td class="column-1">${orderDetail.orderCustomId}</td>
										<td class="column-1"><img width="150px" height="150px" src="images/${orderDetail.pcsImage}" alt="IMG"></td>
										<td class="column-1">${orderDetail.productName}</td>
										<td class="column-1">${orderDetail.colorName}</td>
										<td class="column-1">${orderDetail.sizeName}</td>
										<td class="column-1">${orderDetail.quantity}</td>
										<td class="column-1"><fmt:formatNumber value="${orderDetail.price}" type="currency" currencyCode="VND" /></td>
										<td class="column-5"><fmt:formatNumber value="${orderDetail.pcsPrice}" type="currency" currencyCode="VND" /></td>					
									</tr>
								</c:forEach>
							</table>
							
						</div>
						<div>
							<br>
							<br>
							<h4 class="mtext-109 cl2 p-b-30">
								Customer Information
							</h4>
			
							<div hidden="" class="bor8 m-b-20 how-pos4-parent">
								ID User:
								<input readonly="readonly" class="stext-111 cl2 plh3 size-116 p-l-62 p-r-30" value="${sessionScope.userLoginClient.userId}" type="text" name="userId" placeholder="Your User ID">				
							</div>
							
							Name:
							<div class="bor8 m-b-20 how-pos4-parent">
								<input readonly="readonly" class="stext-111 cl2 plh3 size-116 p-l-62 p-r-30" value="${sessionScope.userLoginClient.fullname}" type="text" name="fullname" placeholder="Your Full Name">				
							</div>
							
							Email:
							<div class="bor8 m-b-20 how-pos4-parent">
								<input readonly="readonly" class="stext-111 cl2 plh3 size-116 p-l-62 p-r-30" value="${sessionScope.userLoginClient.email}" type="text" name="fullname" placeholder="Your Full Name">				
							</div>
							
							Phone:
							<div class="bor8 m-b-20 how-pos4-parent">						
								<input readonly="readonly"  class="stext-111 cl2 plh3 size-116 p-l-62 p-r-30" value="${sessionScope.userLoginClient.phone}" type="text" name="phone" placeholder="Your Phone">				
							</div>
			
							Address:
							<div class="bor8 m-b-30">
								<input readonly="readonly" class="stext-111 cl2 plh3 size-116 p-l-62 p-r-30" value="${sessionScope.userLoginClient.address}" type="text" name="address" placeholder="Your Address">				
							</div>
						</div>
						<br>
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