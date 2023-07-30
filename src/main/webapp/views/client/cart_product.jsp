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

	//Phải Login mới xem được Giỏ Hàng và Checkout -> có thông tin User:
	if(session.getAttribute("userLoginClient") == null){
		response.sendRedirect(request.getContextPath() + "/login-client");
	}

	//Giỏ Hàng Cart:
	ArrayList<CartItem> cart_list = (ArrayList<CartItem>) session.getAttribute("cart-list");

	List<CartItem> cartProducts = null;
	

	if(cart_list != null){
		ProductColorSizeDAO pcsDAO = new ProductColorSizeDAO();
		
		//List CartItems:
		cartProducts = pcsDAO.getCartProducts(cart_list);
		
		//Total Price:
		double total = pcsDAO.getTotalCartPrice(cart_list);
		
		request.setAttribute("cart_list", cart_list);
		request.setAttribute("total", total);
	}

%>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Shopping Cart</title>
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
				Shopping Cart
			</span>
		</div>
	</div>
		
 
	
								
	<!-- Shoping Cart -->
	
	<div class="bg0 p-t-75 p-b-85">
		<div class="container">
			<div class="row">
			
				<div class="col-lg-10 col-xl-9 m-lr-auto m-b-50">
				
					<div class="m-l-25 m-r--38 m-lr-0-xl">
						<!-- Cart Total Price -->
						<h4 class="mtext-109 cl2 p-b-30">
							Cart Totals
						</h4>
						<div class="flex-w flex-t p-t-27 p-b-33">
							<div class="size-208">
								<span class="mtext-101 cl2">
									Total:
								</span>
							</div>

							<div class="size-209 p-t-1">
								<span class="mtext-110 cl2">
									<fmt:formatNumber value="${(total>0)?total:0}" type="currency" currencyCode="VND" />
									
								</span>
							</div>
						</div>
					
						<div class="wrap-table-shopping-cart">
							 <!-- Hiển thị Error -->
							<p class="text-danger">${requestScope.errorCheckOutServlet}</p>
						
							<!-- Cart ListCartItems -->
							<table class="table-shopping-cart">
								<tr class="table_head">
									<th class="column-1">Product</th>
									<th class="column-2"></th>
									<th class="column-3">Color</th>
									<th class="column-3">Size</th>
									<th class="column-3">Price</th>
									<th class="column-3">Quantity</th>
								
									<th class="column-5">Remove</th>
								</tr>
								
								<% 
									if(cart_list != null){
										for(CartItem cI: cartProducts){ %>
											<tr class="table_row">
												<td class="column-1">
													<div class="how-itemcart1">
														<img src="images/<%= cI.getPcsImage() %>" alt="IMG">
													</div>
												</td>
												<td class="column-2"><%= cI.getProduct().getProductName() %></td>
												<td class="column-3"><%= cI.getColor().getColorName() %></td>
												<td class="column-3"><%= cI.getSize().getSizeName() %></td>
												<td class="column-3"><fmt:formatNumber value="<%= cI.getPrice() %>" type="currency" currencyCode="VND" /></td>
												<td class="column-3">
													<form action="" method="post" class="form-inline">
														<div class="wrap-num-product flex-w m-l-auto m-r-0">
															
															<input type="hidden" name="pcsId" value="<%= cI.getPcsId()%>" class="form-input">
															
															<a href="cart-quantity-inc-dec?action=dec&pcsId=<%= cI.getPcsId()%>">
																<div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
																	<i class="fs-16 zmdi zmdi-minus"></i>
																</div>
															</a>
		
															<input class="mtext-104 cl3 txt-center num-product" type="number" name="quantity" value="<%= cI.getQuantityCartItem() %>" readonly="readonly">
		
															<a href="cart-quantity-inc-dec?action=inc&pcsId=<%= cI.getPcsId()%>">
																<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
																	<i class="fs-16 zmdi zmdi-plus"></i>
																</div>
															</a>
														</div>
													</form>
													
												</td>
												
												<td class="column-5"><a class="btn btn-sm btn-danger" href="cart-remove-item?pcsId=<%=  cI.getPcsId()%>">Remove</a></th>
											</tr>
											
										<% }
									}
								%>
							</table>
							
							
							
						</div>
						<form action="order-checkout" method="POST">
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
							
							Phone:
							<div class="bor8 m-b-20 how-pos4-parent">						
								<input readonly="readonly"  class="stext-111 cl2 plh3 size-116 p-l-62 p-r-30" value="${sessionScope.userLoginClient.phone}" type="text" name="phone" placeholder="Your Phone">				
							</div>
		
							Address:
							<div class="bor8 m-b-30">
								<input class="stext-111 cl2 plh3 size-116 p-l-62 p-r-30" value="${sessionScope.userLoginClient.address}" type="text" name="addressShipment" placeholder="Your Address">				
							</div>
							
							<!-- 
								Select way of Payment:
								<div class=" m-b-30">
									<select class="input-style" name="payment">
										<option value="COD-Payment">Cash On Delivery (COD)</option>
										<option value="Online-Payment">Online Payment</option>
									</select>
								</div>
							 -->
							
							
							Select way of Shipment:
							<div class=" m-b-30">
								<select class="input-style"  name="shipment">	
									<option value="Normal-Shipping">Normal Shipping</option>			
									<option value="Express-Shipping">Express Shipping</option>
									
								</select>
							</div>
							
							<button  value="buttonCOD" name="submitType" type="submit" class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
								Checkout  COD
							
							</button>
							<br>
							<button value="buttonPayOnline" name="submitType" type="submit" class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
								Checkout  Online Pay
							</button>
					
						</form>
						
					
						
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