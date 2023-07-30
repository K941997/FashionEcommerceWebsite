<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/views/admin/assets" var="urlAssets"></c:url>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Login Admin</title>
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

<!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: Mar 09 2023 with Bootstrap v5.2.3
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>
	<main>
		<div class="container">
			<section
				class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

							

							<div class="card mb-3">

								<div class="card-body">
									<div class="d-flex justify-content-center py-4">
										<a href="index.html"
											class="logo d-flex align-items-center w-auto"> <img
											src="${urlAssets}/img/logo.png" alt=""> <span
											class="d-none d-lg-block">K Clothes</span>
										</a>
									</div>
									<!-- End Logo -->
									<div class="pt-4 pb-2">
										<h5 class="card-title text-center pb-0 fs-4">Login Admin</h5>
									</div>
									
									<!-- Hiển thị Error -->
									<p class="text-danger ">${requestScope.errorLoginAdminServlet}</p>

									<!-- FORM Admin Login -->
									<form action="login-admin" method="post"
										class="row g-3 needs-validation" novalidate>
										<div class="col-12">
											<label for="userEmail" class="form-label">Email</label>
											<div class="input-group has-validation">
												<span class="input-group-text" id="inputGroupPrepend">@</span>
												<input required="required" type="text" name="userEmail" class="form-control"
													id="userEmail">
												<div class="invalid-feedback">Please enter your email.</div>
											</div>
										</div>

										<div class="col-12">
											<label for="userPassword" class="form-label">Password</label>
											<input type="password" name="userPassword"
												class="form-control" id="userPassword" required>
											<div class="invalid-feedback">Please enter your
												password!</div>
										</div>

										
										<div class="col-12">
											<button class="btn btn-primary w-100" type="submit">Login</button>
										</div>
										
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>

		</div>
	</main>
	<!-- End #main -->

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

</body>

</html>