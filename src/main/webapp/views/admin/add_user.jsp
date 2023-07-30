<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="views/admin/assets" var="urlAssets"></c:url>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Add User</title>
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
					<li class="breadcrumb-item"><a href="list-user">Home</a></li>
					<li class="breadcrumb-item active">Dashboard</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<section class="section dashboard">
			<div class="row">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Add User</h5>
						
						<!-- Hiển thị Error -->
						<p class="text-danger ">${requestScope.errorAddUserServlet}</p>

						<!-- General Form Elements -->
						<form action="add-user" method="post" enctype="multipart/form-data">
							<div hidden="" class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">ID</label>
								<div class="col-sm-10">
									<input type="number" name="userId" class="form-control">
								</div>
							</div>
							<div class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">Email</label>
								<div class="col-sm-10">
									<input required="required" type="text" name="userEmail" class="form-control">
								</div>
							</div>
							<div class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">Password</label>
								<div class="col-sm-10">
									<input required="required" type="password" name="userPassword" class="form-control">
								</div>
							</div>
							<div class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">Fullname</label>
								<div class="col-sm-10">
									<input required="required" type="text" name="userFullname" class="form-control">
								</div>
							</div>
							<div class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">Avatar</label>
								<div class="col-sm-10">
									<input required="required" type="file" id="userAvatar" name="userAvatar" size="60" onchange="previewImage(event)"/>
									<img id="preview" src="#" alt="Image Preview" width="200" height="200"/>
								</div>
							</div>
							<div class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">Phone</label>
								<div class="col-sm-10">
									<input required="required" type="text" name="userPhone" class="form-control">
								</div>
							</div>
							<div class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">Address</label>
								<div class="col-sm-10">
									<input required="required" type="text" name="userAddress" class="form-control">
								</div>
							</div>
							<div class="row mb-3">
								<label for="" class="col-sm-2 col-form-label">Role</label>
								<div class="col-sm-10">
									<input required="required" type="number" name="userRole" class="form-control">
								</div>
							</div>
							
							
							<div class="row mb-3">
								<div class="col-sm-10 offset-sm-2">
									<button type="submit" class="btn btn-primary">Save</button>
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
	<script
		src="${urlAssets}/vendor/simple-datatables/simple-datatables.js"></script>
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