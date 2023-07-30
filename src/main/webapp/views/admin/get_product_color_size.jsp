<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Format Money VND -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />
<fmt:setBundle basename="com.example.message" />
<fmt:formatNumber value="${price}" type="currency" currencyCode="VND" />

<!-- Add thư mục assets CSS của Admin Page -->
<c:url value="views/admin/assets" var="urlAssets"></c:url>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Get ListPCS By Product</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="${urlAssets}/img/favicon.png" rel="icon">
  <link href="${urlAssets}/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${urlAssets}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${urlAssets}/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="${urlAssets}/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="${urlAssets}/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="${urlAssets}/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="${urlAssets}/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="${urlAssets}/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="${urlAssets}/css/style.css" rel="stylesheet">
  
  <!-- CDN cho fontawesome cho icon Sửa Xóa -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

  <!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Mar 09 2023 with Bootstrap v5.2.3
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
  
  <!-- JS cho Boostrap 4 Dropdown -->
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  
  <!-- CSS For Pagination, TabList -->
  <style type="text/css">
  	.pagination a.active{
  		background-color: #blue;
  		color: white;
  	}
  	
  	.center {
 		text-align: center;
	}
	
	.nav a.active{
		background-color: #blue;
  		color: red;
	}
  </style>
  
  
 
</head>

<body>

	<!-- Header -->
	<%@include file="layout/header.jsp" %>
	<!-- End Header -->
	
	<!-- Sidebar -->
	<%@include file="layout/sidebar.jsp" %>
	<!-- End Sidebar -->
	
	<!-- Main -->
	<main id="main" class="main">
		<div class="pagetitle">
		  <h1>Get ListPCS By Product</h1>
		  <nav>
		    <ol class="breadcrumb">
		      <li class="breadcrumb-item"><a href="list-product">Home</a></li>
		      <li class="breadcrumb-item active">Get ListPCS By Product</li>
		    </ol>
		  </nav>
		</div><!-- End Page Title -->
		
		 <section class="section dashboard">
		   <div class="row">
		   	<div class="card">
		         <div class="card-body">
		         
		        
		           <!-- Default Table -->
		           <table class="table">
					  <thead>	
					   		<tr>
						      	 <th hidden="" scope="col">PCS ID</th>
						      	 <th hidden="" scope ="col">Product ID</th>
						      	 <th scope="col">Image</th>
				                 <th scope="col">Color</th>
				                 <th scope="col">Size</th>
				                 <th scope="col">Quantity</th>
				                 <th scope="col">Price</th>
				                 <th scope="col">Function</th>
					      	</tr>
					  </thead>
					  <tbody>
					  	<c:forEach items="${requestScope.listProductColorSize}" var="pcs">
						  	<tr>
						      	<th hidden=""  scope="row">${pcs.pcsId}</th>
						      	<td hidden="" >${pcs.product.productId}</td>
						      	<td>
						      		<c:if test="${pcs.pcsImage != null}">
                      		 			<img style="width: 100px; height: 100px" src='images/${pcs.pcsImage}' alt="Profile" name="pcsImage">
                      				</c:if>
                      			</td>
						   		<td>${pcs.color.colorName}</td>
						   		<td>${pcs.size.sizeName}</td>
						   		<td>${pcs.quantity}</td>							                   
						   		<td>
						   			<fmt:formatNumber value="${pcs.price}" type="currency" currencyCode="VND" />
						   		</td>
						   		<td>
			                 		<a href="update-product-color-size?idPCS=${pcs.pcsId}"><i class="fa-solid fa-pen-to-square"></i></a> |
			                 		<a href="#" onclick="deleteProductColorSize('${pcs.pcsId}')"><i class="fa-solid fa-trash"></i></a>
			                 	</td>
						    </tr>
						    
					  	</c:forEach>
					  
		
					  </tbody>
					</table>
					
					</br>
					<c:set var="productNow" value="${requestScope.productNow}"></c:set>
		          	<a href="add-more-product-color-size?id=${productNow.productId}" class="btn btn-primary mb-3">Add More</a>
					
		        
		           <!-- End Default Table Example -->
		          </div>
		        </div>
		    </div>
		  </section>
	</main>
	<!-- End #main -->


	<!-- Footer -->
	<%@include file="layout/footer.jsp" %>>
	<!-- End Footer -->
	
	<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
	
	<!-- Vendor JS Files -->
	<script src="${urlAssets}/vendor/apexcharts/apexcharts.min.js"></script>
	<script src="${urlAssets}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${urlAssets}/vendor/chart.js/chart.umd.js"></script>
	<script src="${urlAssets}/vendor/echarts/echarts.min.js"></script>
	<script src="${urlAssets}/vendor/quill/quill.min.js"></script>
	<script src="${urlAssets}/vendor/simple-datatables/simple-datatables.js"></script>
	<script src="${urlAssets}/vendor/tinymce/tinymce.min.js"></script>
	<script src="${urlAssets}/vendor/php-email-form/validate.js"></script>
	
	<!-- Template Main JS File -->
	<script src="${urlAssets}/js/main.js"></script>
	<script type="text/javascript">
		function deleteProductColorSize(pcsId){
			if(confirm("Do you want to Delete ProductColorSize with ID "+pcsId)){
				window.location = "delete-product-color-size?idPCS="+pcsId;
			}
		}
	</script>
	
	

</body>

</html>