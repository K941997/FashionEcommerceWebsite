<%@page import="model.Product"%>
<%@page import="model.CartItem"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Format Money VND -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />
<fmt:setBundle basename="com.example.message" />



<c:url value="views/client/assets" var="urlAssets"></c:url>

<!-- Cart EveryWhere -->
<%
	ArrayList<CartItem> cart_list = (ArrayList<CartItem>) session.getAttribute("cart-list");
	if(cart_list != null){
		request.setAttribute("cart_list", cart_list);
	}

%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Detail Product</title>
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
<style>
  .fade-out {
    opacity: 0;
    transition: opacity 1s ease-in-out;
  }
</style>

<!-- Test FB -->
<script async defer src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v8.0&appId=843730143978452" nonce="aUC1kYAS"></script>

	
</head>
<body >
	<!-- Header -->
	<%@include file="layout/header.jsp" %>
	<!-- End Header -->
	
	<!-- Test FB -->
	<div id="fb-root"></div>
	<script async defer crossorigin="anonymous" src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v17.0&appId=843730143978452&autoLogAppEvents=1" nonce="uzEVi0on"></script>
	
	
	<!-- breadcrumb -->
	<div class="container">
		<div class="bread-crumb flex-w p-l-25 p-r-15 p-t-30 p-lr-0-lg">
			<a href="index" class="stext-109 cl8 hov-cl1 trans-04">
				Home
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a>

			<a href="product" class="stext-109 cl8 hov-cl1 trans-04">
				Product
				<i class="fa fa-angle-right m-l-9 m-r-10" aria-hidden="true"></i>
			</a>

			<span class="stext-109 cl4">
				${product.productName}
			</span>
		</div>
	</div>

	<!-- Product Detail -->
	<section class="sec-product-detail bg0 p-t-65 p-b-60">
		<div class="container">
		
			
		
			<div class="row">
				<div class="col-md-6 col-lg-7 p-b-30">
					<div class="p-l-25 p-r-30 p-lr-0-lg">
						<div class="wrap-slick3 flex-sb flex-w">
							<div class="wrap-slick3-dots"></div>
							<div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

							
							<!-- Image Product Detail -->
							<div class="slick3 gallery-lb">
								<c:if test="${pcsImage != null}">
									<div class="item-slick3" data-thumb="images/${pcsImage}">
										<div class="wrap-pic-w pos-relative">
											<img src="images/${pcsImage}" alt="IMG-PRODUCT">
	
											<a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="images/${pcsImage}">
												<i class="fa fa-expand"></i>
											</a>
										</div>
									</div>
								</c:if>
								
								<c:if test="${pcsImage == null}">
									<div class="item-slick3" data-thumb="images/${product.productImage}">
										<div class="wrap-pic-w pos-relative">
											<img src="images/${product.productImage}" alt="IMG-PRODUCT">
	
											<a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="images/${pcsImage}">
												<i class="fa fa-expand"></i>
											</a>
										</div>
									</div>
								</c:if>
								
					
								<c:forEach items="${listPCSByProductId}" var="pcs">
									<div class="item-slick3" data-thumb="images/${pcs.pcsImage}">
									<div class="wrap-pic-w pos-relative">
										<img src="images/${pcs.pcsImage}" alt="IMG-PRODUCT">

										<a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="images/${pcs.pcsImage}">
											<i class="fa fa-expand"></i>
										</a>
									</div>
								</div>
								</c:forEach>

								
							</div>
							<!-- End Image -->	
						
						</div>
					</div>
				</div>
					
				<!-- Product Info -->
				
				
				
				<div class="col-md-6 col-lg-5 p-b-30">
					<div class="p-r-50 p-t-5 p-lr-0-lg">
						<!-- Test Form Update Price -->
						<form action="detail-product-update-price-and-quantity" method="post">
							<input type="hidden" name="productId" value="${product.productId}">
		
							<h4 class="mtext-105 cl2 js-name-detail p-b-14">
								${product.productName}
							</h4>
							
							<!-- Hiển thị Error -->
							<p id="errorLog" class="text text-danger ">${requestScope.errorDetailProductServlet}</p>
							
							<!-- Price from PCS -->
							<span class="mtext-106 cl2">
								Price: 
								<span id="price">
									<c:if test="${price == 0}">
										<fmt:formatNumber value="${price}" type="currency" currencyCode="VND" />
									</c:if>
									<c:if test="${price != 0}">
										<fmt:formatNumber value="${price}" type="currency" currencyCode="VND" />
										
									</c:if>
									
								</span>
							</span>	
							
							<br>
							
							<!-- Quantity from PCS -->
							<span class="mtext-106 cl2">
								Quantity: 
								<span id="quantity">
									<c:if test="${quantity == 0}">
										${quantity}
									</c:if>
									<c:if test="${quantity != 0}">
										${quantity}
										
									</c:if>
									
								</span>
							</span>	
							
						
							
							
						
							
							
							<!-- Color -->
							<div class="p-t-33">
								<div class="flex-w flex-r-m p-b-10">
									<div class="size-203 flex-c-m respon6">
										Color
									</div>
		
									<div class="size-204 respon6-next">
										<div class="rs1-select2 bor8 bg0">
											<select class="js-select2" name="colorId" >
												<option value="">Choose an option</option>
												<c:forEach items="${colors}" var="color">
													<option value="${color.colorId}"  
														<c:if test="${colorId == color.colorId}"> 
															selected="selected" 
														</c:if>>
														${color.colorName}
													</option>
												</c:forEach>
											</select>
											<div class="dropDownSelect2"></div>
										</div>
									</div>
								</div>
							</div>
								
							<!-- Size -->
							<div class="p-t-33">
								<div class="flex-w flex-r-m p-b-10">
									<div class="size-203 flex-c-m respon6">
										Size
									</div>
	
									<div class="size-204 respon6-next">
										<div class="rs1-select2 bor8 bg0">
											<select class="js-select2" name="sizeId" onchange='this.form.submit()'  >
												<option value="">Choose an option</option>
												<c:forEach items="${sizes}" var="size">
													<option value="${size.sizeId}"  
														<c:if test="${sizeId == size.sizeId}"> selected="selected" </c:if>>
														${size.sizeName}
													</option>
										
												</c:forEach>
												
											</select>
											<div class="dropDownSelect2"></div>
										</div>
									</div>
								</div>
	
							</div>
	
							
						</form>
						
											
						
							<c:if test="${quantity == 0}">
								<div class="flex-w flex-r-m p-b-10">
									<div class="size-204 flex-w flex-m respon6-next">
										<a class="flex-c-m stext-101 cl0 size-101 bg1 bor1  p-lr-15 trans-04">
										 	<button style="color: white;" id="addToCartButton">Sold Out !</button>
										 	
										</a>
										
									</div>
								</div>
							</c:if>
							
							<!-- Button Add To Cart -->
							<c:if test="${productColorSize != null}">
								<div class="flex-w flex-r-m p-b-10">
									<div class="size-204 flex-w flex-m respon6-next">
										<a href="add-to-cart?pcsId=${productColorSize.pcsId}" class="flex-c-m stext-101 cl0 size-101 bg1 bor1  p-lr-15 trans-04 js-addcart-detail">
										 	<button style="color: white;" id="addToCartButton">ADD TO CART</button>
										 	
										</a>
										
									</div>
								</div>
							</c:if>
					
						
						
						
					</div>
				</div>
				
				
			</div>

			<div class="bor10 m-t-50 p-t-43 p-b-40">
				<!-- Tab01 -->
				<div class="tab01">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li class="nav-item p-b-10">
							<a class="nav-link active" data-toggle="tab" href="#description" role="tab">Description</a>
						</li>

						
					</ul>

					<!-- Tab panes -->
					<div class="tab-content p-t-43">
						<!-- - -->
						<div class="tab-pane fade show active" id="description" role="tabpanel">
							<div class="how-pos2 p-lr-15-md">
								<p class="stext-102 cl6">
										${product.productDescription}
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="bg6 flex-c-m flex-w size-302 m-t-73 p-tb-15">
		   
			<span id="product_id" class="stext-107 cl6 p-lr-25">
				 ID: ${product.productId}
			</span>
			
			<span class="stext-107 cl6 p-lr-25">
				Custom ID: ${product.productCustomId}
			</span>

			<span class="stext-107 cl6 p-lr-25">
				Category: ${product.category.categoryName}
			</span>
		</div>
		
		
		
		
		<!-- Facebook Comment -->
		<div class="container">
			<div class="fb-comments" data-href="https://developers.facebook.com/docs/plugins/comments#configurator" data-width="1000" data-numposts="5"></div>
			<div class="fb-comments" data-href="detail_product.jsp?pid=${product.productId}" data-width="1000" data-numposts="5"></div>
			<div class="fb-comments" data-href="detail-product?pid=${product.productId}" data-width="1000" data-numposts="5"></div>
			<div class="fb-comments" data-href="/views/client/detail_product.jsp?pid=${product.productId}" data-width="1000" data-numposts="5"></div>
		</div>
	

		
	</section>



	
	
	<!-- Back to top -->
	<div class="btn-back-to-top" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="zmdi zmdi-chevron-up"></i>
		</span>
	</div>

	
	<!-- Footer -->
	<%@include file="layout/footer.jsp" %>
	<!-- End Footer -->
	
	
	<!-- Test FB Comment -->
	<div id="fb-root"></div>
	<script async defer crossorigin="anonymous" src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v8.0" nonce="G9bzUizu"></script>


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
		$('.js-addwish-b2').on('click', function(e){
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
	
	
	<!-- Change Price of PCS when click each Color, Size -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
		
		 //Thay Đổi Giá, Số lượng bằng AJAX function to update the price and quantity based on selected color and size
	   $(document).ready(function() {
		  $('#colorId, #sizeId').change(function() {
		    var productId = ${product.productId};
		    var colorId = $('#colorId').val();
		    var sizeId = $('#sizeId').val();
		    
		    console.log(productId);
		    console.log(colorId);
		    console.log(sizeId);
		    
		    
		    $.ajax({
		      url: "/ProjectEShopOnWeb/DetailProductUpdatePriceAndQuantity",
			  type: "post", //send it through get method
		      data: { productId: productId, colorId: colorId, sizeId: sizeId },
		      dataType: 'text',
		      success: function(price) {
		        $('#price').text(price);
		        console.log(price);
		      }
		    });
		  });
		});
	</script>  
	
	<script>
	  //Báo lỗi xong biến mất:
	  setTimeout(function() {
		    var element = document.getElementById('errorLog');
		    element.classList.add('fade-out');
		    setTimeout(function() {
		      element.style.display = 'none';
		    }, 1000); // 1000 indicates 1 second
		  }, 5000); // 5000 indicates 5 seconds
	</script>
	
	
	
</body>
</html>