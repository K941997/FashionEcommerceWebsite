<%@page import="model.CartItem"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Add thư mục assets CSS của Shop Template -->
<c:url value="views/client/assets" var="urlAssets"></c:url>


	<!-- Header -->
	<header class="header-v4">
		<!-- Header desktop -->
		<div class="container-menu-desktop">
			<!-- Topbar -->
			<div class="top-bar">
				<div class="content-topbar flex-sb-m h-full container">
					<div class="left-top-bar">
						Welcome to K Shop
					</div>
					
					<!-- Button Login Logout -->
					<div class="right-top-bar flex-w h-full">
						<c:if test="${sessionScope.userLoginClient != null}">
								<c:if test="${sessionScope.userLoginClient.role == 1 || sessionScope.userLoginClient.role == 2}">
									<a href="list-product" class="flex-c-m trans-04 p-lr-25">
										Manage Shop
									</a>
								</c:if>
								
								
								<a href="profile-user-client" class="flex-c-m trans-04 p-lr-25">
									My Account ${sessionScope.userLoginClient.email}
								</a>
		
								<a href="logout-client" class="flex-c-m trans-04 p-lr-25">
									Logout
								</a>
						</c:if>
						
						<c:if test="${sessionScope.userLoginClient == null}">
						   <a href="login-client" class="flex-c-m trans-04 p-lr-25">
									Login
								</a>
						</c:if>
					</div>
					
				</div>
			</div>

			<div class="wrap-menu-desktop">
				<nav class="limiter-menu-desktop container">
					
					<!-- Logo desktop -->		
					<a href="index" class="logo">
						<img src="https://cf.shopee.vn/file/b91c164d5ef9f6627aba6b6213211e85" alt="IMG-LOGO">
					</a>

					<!-- Menu desktop -->
					<div class="menu-desktop">
						<ul class="main-menu">
							<li >
								<a href="index">Home</a>
								
							</li>

							<li>
								<a href="list-product-client">Shop</a>
							</li>


							<li>
								<a href="about">About</a>
							</li>

							<li>
								<a href="contact">Contact</a>
							</li>
						</ul>
					</div>	

					<!-- Icon header -->
					<div class="wrap-icon-header flex-w flex-r-m">
						<div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 js-show-modal-search">
							<i class="zmdi zmdi-search"></i>
						</div>
						
						<!-- Cart -->
						<c:if test="${cart_list.size() != null }" >
							<div data-notify="${cart_list.size()}" class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart" >
								<a href="cart-product"><i class="zmdi zmdi-shopping-cart"></i></a>
							</div>
						</c:if>
						
						<c:if test="${cart_list.size() == null }" >
							<div data-notify="0" class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart" >
								<a href="cart-product"><i class="zmdi zmdi-shopping-cart"></i></a>
							</div>
						</c:if>
							
						
						<!-- All Orders -->
						<div  class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11  js-show-cart" >
							<a href="all-orders"><i class="zmdi zmdi-shopping-basket"></i></a>
						</div>
						
						
					</div>
				</nav>
			</div>	
		</div>

		<!-- Header Mobile -->
		<div class="wrap-header-mobile">
			<!-- Logo moblie -->		
			<div class="logo-mobile">
				<a href="index"><img src="https://cf.shopee.vn/file/b91c164d5ef9f6627aba6b6213211e85" alt="IMG-LOGO"></a>
			</div>

			<!-- Icon header -->
			<div class="wrap-icon-header flex-w flex-r-m m-r-15">
				<div class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 js-show-modal-search">
					<i class="zmdi zmdi-search"></i>
				</div>

				<c:if test="${cart_list.size() != null }" >
					<div data-notify="${cart_list.size()}" class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti js-show-cart" >
						<a href="cart-product"><i class="zmdi zmdi-shopping-cart"></i></a>
					</div>
				</c:if>
				
				<c:if test="${cart_list.size() == null }" >
					<div data-notify="0" class="icon-header-item cl2 hov-cl1 trans-04 p-r-11 p-l-10 icon-header-noti js-show-cart" >
						<a href="cart-product"><i class="zmdi zmdi-shopping-cart"></i></a>
					</div>
				</c:if>
			
				<!-- All Orders -->
				<div  class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11  js-show-cart" >
					<a href="all-orders"><i class="zmdi zmdi-shopping-basket"></i></a>
				</div>

				
			</div>

			<!-- Button show menu -->
			<div class="btn-show-menu-mobile hamburger hamburger--squeeze">
				<span class="hamburger-box">
					<span class="hamburger-inner"></span>
				</span>
			</div>
		</div>


		<!-- Menu Mobile -->
		<div class="menu-mobile">
			<ul class="topbar-mobile">
				<li>
					<div class="left-top-bar">
							Welcome to K Shop
					</div>
				</li>

				<li>
					<!-- Button Login Logout -->
					<div class="right-top-bar flex-w h-full">
						<c:if test="${sessionScope.userLoginClient != null}">
								<c:if test="${sessionScope.userLoginClient.role == 1 || sessionScope.userLoginClient.role == 2}">
									<a href="list-product" class="flex-c-m trans-04 p-lr-10">
										Manage Shop
									</a>
								</c:if>
								
								
								<a href="profile-user-client" class="flex-c-m trans-04 p-lr-10">
									My Account ${sessionScope.userLoginClient.email}
								</a>
		
								<a href="logout-client" class="flex-c-m trans-04 p-lr-10">
									Logout
								</a>
						</c:if>
						
						<c:if test="${sessionScope.userLoginClient == null}">
						   <a href="login-client" class="flex-c-m trans-04 p-lr-10">
									Login
								</a>
						</c:if>
					</div>
				</li>
			</ul>

			<ul class="main-menu-m">
				<li >
				<a href="index">Home</a>
					
				</li>

				<li>
					<a href="list-product-client">Shop</a>
				</li>


				<li>
					<a href="about">About</a>
				</li>

				<li>
					<a href="contact">Contact</a>
				</li>
			</ul>
		</div>

		<!-- Modal Search -->
		<div class="modal-search-header flex-c-m trans-04 js-hide-modal-search">
			<div class="container-search-header">
				<button class="flex-c-m btn-hide-modal-search trans-04 js-hide-modal-search">
					<img src="${urlAssets}/images/icons/icon-close2.png" alt="CLOSE">
				</button>

				<div  class="wrap-search-header flex-w p-l-15">
					<button type="submit" class="flex-c-m trans-04">
						<i class="zmdi zmdi-search"></i>
					</button>
					<input oninput="searchAjaxByName(this)" name="txtSearchAjax" class="plh3" type="text"  placeholder="Search...">
				</div>
			</div>
		</div>
	</header>
	
	
	
	<!-- End Header -->
	
	