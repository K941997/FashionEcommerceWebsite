<%@page import="dao.ProductColorSizeDAO"%>
<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@page import="model.CartItem"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Format Money VND -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />
<fmt:setBundle basename="com.example.message" />

<!-- Add thư mục assets CSS của Shop Template -->
<c:url value="views/client/assets" var="urlAssets"></c:url>

<!-- Cart -->
<%
	ArrayList<CartItem> cart_list = (ArrayList<CartItem>) session.getAttribute("cart-list");
	if(cart_list != null){
		request.setAttribute("cart_list", cart_list);
	};

%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>List Products</title>
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
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/slick/slick.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/MagnificPopup/magnific-popup.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/css/util.css">
	<link rel="stylesheet" type="text/css" href="${urlAssets}/css/main.css">
<!--===============================================================================================-->
</head>
<body class="animsition">
	<!-- Header -->
	<%@include file="layout/header.jsp" %>
	<!-- End Header -->
	
	<!-- Product -->
	<div class="bg0 m-t-23 p-b-140">
		<div class="container">
			<div class="flex-w flex-sb-m p-b-52">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">
					<a href="list-product-client">
						<button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 ${tag == c.categoryId ? "how-active1":""}" data-filter="*">
							All Products
						</button>
					</a>

					<c:forEach items="${listCategoryClient}" var="c">
						<a href="category-list-product?cid=${c.categoryId}">
							<button class=" stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 ${tag == c.categoryId ? "how-active1":""}" data-filter=".women">
								${c.categoryName}
							</button>
						</a>
						
					</c:forEach>
				</div>

				<div class="flex-w flex-c-m m-tb-10">
					<div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4 js-show-filter">
						<i class="icon-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-filter-list"></i>
						<i class="icon-close-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
						 Filter
					</div>

					<div class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-tb-4 js-show-search">
						<i class="icon-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-search"></i>
						<i class="icon-close-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
						Search
					</div>
				</div>
				
				<!-- Search product -->
				<div class="dis-none panel-search w-full p-t-10 p-b-15">
					<form action="search-ajax" method="get" >
						<div class="bor8 dis-flex p-l-15">
								<button class="size-113 flex-c-m fs-16 cl2 hov-cl1 trans-04">
									<i class="zmdi zmdi-search"></i>
								</button>
		
								<input oninput="searchAjaxByName(this)" name="txtSearchAjax" class="mtext-107 cl2 size-114 plh2 p-r-15" type="text"  placeholder="Search">
							
						</div>	
					</form>
				</div>

				<!-- Filter -->
				<div class="dis-none panel-filter w-full p-t-10">
					<div class="wrap-filter flex-w bg6 w-full p-lr-40 p-t-27 p-lr-15-sm">
						<div class="filter-col1 p-r-15 p-b-27">
							<div class="mtext-102 cl2 p-b-15">
								Sort By
							</div>

							<ul>
								<li class="p-b-6">
									<a href="list-product-client" class="filter-link stext-106 trans-04">
										Default
									</a>
								</li>

							

								<li class="p-b-6">
									<a href="sort-newness" class="filter-link stext-106 trans-04 ">
										Newness
									</a>
								</li>

								<li class="p-b-6">
									<a href="sort-price-asc" class="filter-link stext-106 trans-04">
										Price: Low to High
									</a>
								</li>

								<li class="p-b-6">
									<a href="sort-price-desc" class="filter-link stext-106 trans-04">
										Price: High to Low
									</a>
								</li>
							</ul>
						</div>


						<!-- Price Range -->
						<div class="filter-col2 p-r-15 p-b-27">
								<div class="mtext-102 cl2 p-b-15">
									Price
								</div>
							
							<ul>
								
								<li class="p-b-6">
									<a href="index" class="filter-link stext-106 trans-04">
										All Products
									</a>
									
								</li>
								
								<li class="p-b-6">
									<!-- Test Price Range -->
									<form method="GET" action="list-product-by-range-price" >
									  <label for="priceRange" ><input type="submit" value="Search Price Range" class="filter-link stext-106 trans-04"></label>
									  <select name="priceRange" id="priceRange" >
									    <option value="0-100000">Under <fmt:formatNumber value="100000" type="currency" currencyCode="VND" /></option>
									    <option value="100000-300000">
									    	<fmt:formatNumber value="100000" type="currency" currencyCode="VND" />
									    	-
									    	<fmt:formatNumber value="300000" type="currency" currencyCode="VND" />
									    </option>
									    <option value="300000-500000">
											<fmt:formatNumber value="300000" type="currency" currencyCode="VND" />
											-
											<fmt:formatNumber value="500000" type="currency" currencyCode="VND" />
									    </option>
									    <option value="500000-1000000">
									    	<fmt:formatNumber value="500000" type="currency" currencyCode="VND" />
											-
											<fmt:formatNumber value="1000000" type="currency" currencyCode="VND" />
										</option>
									    <option value="1000000-1000000000">
											>
											<fmt:formatNumber value="1000000" type="currency" currencyCode="VND" />
										</option>
									    <!-- Add more price range options as needed -->
									  </select>
									 
									</form>
								</li>
								
							</ul>
						</div>

						<div class="filter-col3 p-r-15 p-b-27">
							<div class="mtext-102 cl2 p-b-15">
								Color
							</div>
							
							<div class="flex-w p-t-4 m-r--5">
								<c:forEach items="${listColorClient}" var="color">
									<c:if test="${color.colorId == 1}">
										<a style=" background-color: black;" href="color-list-product?colorId=${color.colorId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5 ${tag1 == color.colorId ? "how-active1":""}">									
													
										</a>
									</c:if> 
									<c:if test="${color.colorId == 2}">
										<a style=" background-color: white;" href="color-list-product?colorId=${color.colorId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5 ${tag1 == color.colorId ? "how-active1":""}">									
													
										</a>
									</c:if> 
									<c:if test="${color.colorId == 3}">
										<a style=" background-color: #FFCC99;" href="color-list-product?colorId=${color.colorId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5 ${tag1 == color.colorId ? "how-active1":""}">									
													
										</a>
									</c:if> 
									<c:if test="${color.colorId == 4}">
										<a style=" background-color: #CCCCCC;" href="color-list-product?colorId=${color.colorId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5 ${tag1 == color.colorId ? "how-active1":""}">									
													
										</a>
									</c:if> 
									<c:if test="${color.colorId == 5}">
										<a style=" background-color: #3399FF;" href="color-list-product?colorId=${color.colorId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5 ${tag1 == color.colorId ? "how-active1":""}">									
													
										</a>
									</c:if> 
									<c:if test="${color.colorId == 6}">
										<a style=" background-color: #3366CC;" href="color-list-product?colorId=${color.colorId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5 ${tag1 == color.colorId ? "how-active1":""}">									
													
										</a>
									</c:if> 
									<c:if test="${color.colorId == 7}">
										<a style=" background-color: #00FF00;" href="color-list-product?colorId=${color.colorId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5 ${tag1 == color.colorId ? "how-active1":""}">									
													
										</a>
									</c:if> 
									<c:if test="${color.colorId == 8}">
										<a style=" background-color: #339900;" href="color-list-product?colorId=${color.colorId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5 ${tag1 == color.colorId ? "how-active1":""}">									
													
										</a>
									</c:if> 
									<c:if test="${color.colorId == 9}">
										<a style=" background-color: #996600;" href="color-list-product?colorId=${color.colorId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5 ${tag1 == color.colorId ? "how-active1":""}">									
													
										</a>
									</c:if> 
									<c:if test="${color.colorId == 10}">
										<a style=" background-color: #FF0000;" href="color-list-product?colorId=${color.colorId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5 ${tag1 == color.colorId ? "how-active1":""}">									
													
										</a>
									</c:if>
									<c:if test="${color.colorId == 11}">
										<a style=" background-color: #FFCCFF;" href="color-list-product?colorId=${color.colorId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5 ${tag1 == color.colorId ? "how-active1":""}">									
													
										</a>
									</c:if>
									<c:if test="${color.colorId == 12}">
										<a style=" background-color: #FF9900;" href="color-list-product?colorId=${color.colorId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5 ${tag1 == color.colorId ? "how-active1":""}">									
													
										</a>
									</c:if>
									<c:if test="${color.colorId == 13}">
										<a style=" background-color: #FFFF33;" href="color-list-product?colorId=${color.colorId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5 ${tag1 == color.colorId ? "how-active1":""}">									
													
										</a>
									</c:if>
									<c:if test="${color.colorId == 14}">
										<a style=" background-color: #9966FF;" href="color-list-product?colorId=${color.colorId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5 ${tag1 == color.colorId ? "how-active1":""}">									
													
										</a>
									</c:if>
								</c:forEach>
							</div>
						</div>

						<div class="filter-col4 p-b-27">
							<div class="mtext-102 cl2 p-b-15">
								Categories
							</div>

							<div class="flex-w p-t-4 m-r--5">
								<c:forEach items="${listCategoryClient}" var="c">
									<a href="category-list-product?cid=${c.categoryId}" class="flex-c-m stext-107 cl6 size-301 bor7 p-lr-15 hov-tag1 trans-04 m-r-5 m-b-5 ${tag == c.categoryId ? "how-active1":""}">
										${c.categoryName }
									</a>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Products -->
			
			<!-- Tìm cách chọn 1 trong 2 để hiển thị tất cả Products -->
			<!-- Products in Price Range -->
			<div id="productList" class="row">
			<!-- Products Load More -->
			<div id="contentLoadMore" class="row">
				<% 
					List<Product> products = (List<Product>) request.getAttribute("listProductClient");
				
					for(Product o: products){
						ProductColorSizeDAO productColorSizeDAO = new ProductColorSizeDAO();
					
						
						%>
							<c:if test="<%= o.getProductStatus() == 1 %>">
					
							<!-- Product -->
							<div class="productLoadMore col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item women">
								<!-- Block2 -->
								<div class="block2">
									<div class="block2-pic hov-img0">
										<img src="images/<%= o.getProductImage()%>" alt="IMG-PRODUCT">
									</div>
			
									<div class="block2-txt flex-w flex-t p-t-14">
										<div class="block2-txt-child1 flex-col-l ">
											<a href="detail-product?pid=<%= o.getProductId()%>" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
												<%= o.getProductName()%>
											</a>
			
											<span class="stext-105 cl3">
												<% int productId = o.getProductId(); %>
												<fmt:formatNumber value="<%= productColorSizeDAO.getMinPricePCS(productId)%>" type="currency" currencyCode="VND" />
												-
												<fmt:formatNumber value="<%= productColorSizeDAO.getMaxPricePCS(productId)%>" type="currency" currencyCode="VND" />
											</span>
										</div>
			
									</div>
								</div>
							</div>
						</c:if>
					
						
						<% 
					}
				
				%>
			</div>
			</div>
				

			<!-- Load more -->
			<div class="flex-c-m flex-w w-full p-t-45">
				<button id="loadMoreBtn" class="flex-c-m stext-101 cl5 size-103 bg2 bor1 hov-btn1 p-lr-15 trans-04">
					Load More
				</button>
			</div>
		</div>
	</div>
		

	<!-- Footer -->
	<%@include file="layout/footer.jsp" %>
	<!-- End Footer -->


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
	<script src="${urlAssets}/vendor/daterangepicker/moment.min.js"></script>
	<script src="${urlAssets}/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/slick/slick.min.js"></script>
	<script src="${urlAssets}/js/slick-custom.js"></script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/parallax100/parallax100.js"></script>
	<script>
        $('.parallax100').parallax100();
	</script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
	<script>
		$('.gallery-lb').each(function() { // the containers for all your galleries
			$(this).magnificPopup({
		        delegate: 'a', // the selector for gallery item
		        type: 'image',
		        gallery: {
		        	enabled:true
		        },
		        mainClass: 'mfp-fade'
		    });
		});
	</script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/isotope/isotope.pkgd.min.js"></script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/sweetalert/sweetalert.min.js"></script>
	<script>
		$('.js-addwish-b2, .js-addwish-detail').on('click', function(e){
			e.preventDefault();
		});

		$('.js-addwish-b2').each(function(){
			var nameProduct = $(this).parent().parent().find('.js-name-b2').html();
			$(this).on('click', function(){
				swal(nameProduct, "is added to wishlist !", "success");

				$(this).addClass('js-addedwish-b2');
				$(this).off('click');
			});
		});

		$('.js-addwish-detail').each(function(){
			var nameProduct = $(this).parent().parent().parent().find('.js-name-detail').html();

			$(this).on('click', function(){
				swal(nameProduct, "is added to wishlist !", "success");

				$(this).addClass('js-addedwish-detail');
				$(this).off('click');
			});
		});

		/*---------------------------------------------*/

		$('.js-addcart-detail').each(function(){
			var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
			$(this).on('click', function(){
				swal(nameProduct, "is added to cart !", "success");
			});
		});
	
	</script>
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
	
	<!-- Nút Load More Sử dụng AJAX -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	
	<script>
		//Load More by AJAX:
	    $(document).ready(function() {
		   $("#loadMoreBtn").click(function() {
				var amount = document.getElementsByClassName("productLoadMore").length;
				$.ajax({
					  url: "/ProjectEShopOnWeb/load-more-product-client",
					  type: "get", //send it through get method
					  data:{
						  exits: amount
					  },
					 
					  success: function(data) {
					    //Do Something
					    var row = document.getElementById("contentLoadMore");
					   
					    row.innerHTML += data;
					  },
					  error: function(xhr) {
					    //Do Something to handle error
					  }
					});
			})
			
			var txtSearch = "<%=request.getParameter("txtSearch")%>";
	        if (txtSearch != null && !txtSearch.isEmpty()) {
	        	 $("#loadMoreBtn").remove();
	            
	        }
	        
	        var txtSearchAjax = "<%=request.getParameter("txtSearchAjax")%>";
	        if (txtSearchAjax != null && !txtSearchAjax.isEmpty()) {
	            $("#loadMoreBtn").hide();
	        }
	    });
		
		
	</script>
	
	<script type="text/javascript">
		function searchAjaxByName(param) {
			var txt = param.value;
			$.ajax({
				  url: "/ProjectEShopOnWeb/search-ajax",
				  type: "get", //send it through get method
				  data:{
					  txtSearchAjax: txt,
				  },
				 
				  success: function(data) {
				    //Do Something
				    var row = document.getElementById("contentLoadMore");
				    row.innerHTML = data;
				    $("#loadMoreBtn").remove();
				  },
				  error: function(xhr) {
				    //Do Something to handle error
				  }
			});
		}
	</script>

</body>
</html>