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

  <title>Update ProductColorSize</title>
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
		  <h1>Update PCS</h1>
		  <nav>
		    <ol class="breadcrumb">
		      <li class="breadcrumb-item"><a href="get-product-size-color?id=${requestScope.pcsUpdateProductColorSizeServlet.product.productId}">Home</a></li>
		      <li class="breadcrumb-item active">Update PCS</li>
		    </ol>
		  </nav>
		</div><!-- End Page Title -->
		
		 <section class="section dashboard">
		   <div class="row">
		   	<div class="card">
		         <div class="card-body">
		         
		         <!-- Hiển thị Error -->
				<p class="text-danger ">${requestScope.errorUpdateProductColorSize}</p>
		         
		        
	           <!-- General Form Elements -->
	           <c:set var="pcs" value="${requestScope.pcsUpdateProductColorSizeServlet}"></c:set>
				<form action="update-product-color-size" method="post" enctype="multipart/form-data">
					 <!-- Default Table -->
			           <table class="table">
						  <thead>	
						   		<tr>
							      	 <th hidden="" scope="col">PCS ID</th>
							      	 <th hidden="" scope="col">Product ID</th>
							      	  <th scope="col">Image</th>
					                 <th scope="col">Color</th>
					                 <th scope="col">Size</th>
					                 <th scope="col">Quantity</th>
					                 <th scope="col">Price</th>
						      	</tr>
						  </thead>
						  <tbody>
							  	<tr>
							      	<th hidden="" scope="row">
							      		<input readonly="readonly" type="number" value="${pcs.pcsId}"
										name="pcsId" class="form-control">
									</th>
							      	<td hidden="">
							      		<input readonly="readonly" type="number" value="${pcs.product.productId}"
										name="productId" class="form-control">
									</td>
									
									<td>
										<div class="col-sm-10">
											<c:if test="${pcs.pcsImage != null}">
		                      		 			<img id="preview" alt="Image Preview" width="100" height="100" src='images/${pcs.pcsImage}' alt="Profile" name="pcsImage">
		                      				</c:if>
											<input type="file" id="pcsImage" name="pcsImage" value="${pcs.pcsImage}" size="" onchange="previewImage(event)"/>
										</div>
									</td>
							   		<td>
							   			${pcs.color.colorName}	
							   			
							   		</td>
							   		<td>
							   			${pcs.size.sizeName}
							   				
							   		</td>
							   		<td>
							   			
							   			<input required="required" type="number" name='quantity' value="${pcs.quantity}" class="form-control"/>
							   		</td>
							   		<td>
							   			<input required="required" type="number" name='price' value="${pcs.price}" class="form-control"/>
							   		</td>
							    </tr>
	
					  	</tbody>
					</table>
					
					
					<button type="submit" class="btn btn-primary">Update</button>
					
				</form>
					
					
					
		        
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
	<script>
		//Preview 1 Image before upload:
	 	function previewImage(event) {
			  var reader = new FileReader();
			  reader.onload = function() {
			    var preview = document.getElementById('preview');
			    preview.src = reader.result;
			  }
	  		reader.readAsDataURL(event.target.files[0]);
		}
	</script>
	
	

</body>

</html>