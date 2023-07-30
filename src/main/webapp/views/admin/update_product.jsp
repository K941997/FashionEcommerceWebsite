<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="views/admin/assets" var="urlAssets"></c:url>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Update Product</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="${urlAssets}/img/favicon.png" rel="icon">
<link href="${urlAssets}/img/apple-touch-icon.png"
	rel="apple-touch-icon">

<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="${urlAssets}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${urlAssets}/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="${urlAssets}/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="${urlAssets}/vendor/quill/quill.snow.css" rel="stylesheet">
<link href="${urlAssets}/vendor/quill/quill.bubble.css" rel="stylesheet">
<link href="${urlAssets}/vendor/remixicon/remixicon.css"
	rel="stylesheet">
<link href="${urlAssets}/vendor/simple-datatables/style.css"
	rel="stylesheet">

<!-- Template Main CSS File -->
<link href="${urlAssets}/css/style.css" rel="stylesheet">

<!-- CDN cho fontawesome cho icon Sửa Xóa -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
	
<!-- CKEDITOR cho Description -->
<script src="https://cdn.ckeditor.com/4.21.0/standard/ckeditor.js"></script>

<!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Mar 09 2023 with Bootstrap v5.2.3
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>
	<!-- ======= Header ======= -->
	<%@include file="layout/header.jsp"%>
	<!-- ======= End Header ======= -->

	<!-- ======= Sidebar ======= -->
	<%@include file="layout/sidebar.jsp"%>
	<!-- ======= End Sidebar ======= -->

	<!-- ======= Main ======= -->
	<main id="main" class="main">
		<div class="pagetitle">
			<h1>Dashboard</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="list-product">Home</a></li>
					<li class="breadcrumb-item active">Dashboard</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<section class="section dashboard">
			<div class="row">
				<div class="card">
					<div class="card-body">
					
						
						
						<c:set var="product" value="${requestScope.productUpdateServlet}"></c:set>
						<h5 class="card-title">Update Product</h5>
						
						<!-- Hiển thị Error -->
						<p class="text-danger ">${requestScope.errorUpdateProductServlet}</p>
						
						<!-- General Form Elements -->
						<form action="update-product" method="post" enctype="multipart/form-data">
							<div hidden="" class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">ID</label>
								<div class="col-sm-10">
									<input readonly="readonly" type="number" value="${product.productId}"
										name="productId" class="form-control">
								</div>
							</div>
							<div class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">Custom ID</label>
								<div class="col-sm-10">
									<input required="required" type="text" value="${product.productCustomId}"
										name="productCustomId" class="form-control">
								</div>
							</div>
							<div class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">Name</label>
								<div class="col-sm-10">
									<input required="required" type="text" value="${product.productName}"
										name="productName" class="form-control">
								</div>
							</div>
							
							<div class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">Image</label>
								<div class="col-sm-10">
									<c:if test="${product.productImage != null}">
                      		 			<img id="preview" alt="Image Preview" width="200" height="200" src='images/${product.productImage}' alt="Profile" name="productImage">
                      				</c:if>
									<input  type="file" id="productImage" name="productImage" value="${product.productImage}" size="60" onchange="previewImage(event)"/>
								</div>
							</div>
							
							<div class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">Price</label>
								<div class="col-sm-10">
									<input required="required" type="number" value="${product.productPrice}"
										name="productPrice" class="form-control">
								</div>
							</div>
							<div class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">Import Price</label>
								<div class="col-sm-10">
									<input required="required" type="number" value="${product.productOriginalPrice}"
										name="productOriginalPrice" class="form-control">
								</div>
							</div>
							
							<div class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">Description</label>
								<div class="col-sm-10">
									<textarea name="productDescription"> ${product.productDescription}</textarea>																		
								</div>
								<script>
									CKEDITOR.replace('productDescription');
								</script>
							</div>
							<div class="row mb-3">
								<legend class="col-form-label col-sm-2 pt-0">Status</legend>
								<div class="col-sm-10">

									<div class="form-check">
										<input class="form-check-input" name="productStatus" value="1" type="checkbox"> 
										<label class="form-check-label" for="gridCheck1"> Active </label>
									</div>
								</div>
							</div>
							<div class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">Category</label>
								<div class="col-sm-10">
										<select name="categoryId" class="form-control">
    										<c:forEach items="${categories}" var="category">
        										<option value="${category.categoryId}">${category.categoryName}</option>
    										</c:forEach>
										</select>
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-sm-10 offset-sm-2">
									<button type="submit" class="btn btn-primary">Update</button>
								</div>
							</div>
						</form>
						<!-- End General Form Elements -->
					</div>
				</div>
			</div>
		</section>
	</main>
	<!-- ======= End #main -->

	<!-- ======= Footer ======= -->
	<%@include file="layout/footer.jsp"%>>
	<!-- End Footer -->

	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

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