<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="views/admin/assets" var="url"></c:url>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Add Product Admin</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="${url}/img/favicon.png" rel="icon">
<link href="${url}/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="${url}/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${url}/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="${url}/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="${url}/vendor/quill/quill.snow.css" rel="stylesheet">
<link href="${url}/vendor/quill/quill.bubble.css" rel="stylesheet">
<link href="${url}/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="${url}/vendor/simple-datatables/style.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="${url}/css/style.css" rel="stylesheet">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">

<!-- CKEDITOR cho Description -->
<script src="https://cdn.ckeditor.com/4.21.0/standard/ckeditor.js"></script>
</head>

<body>
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	
	<!-- ======= Header ======= -->
	 <%@include file="layout/header.jsp" %>
	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
	<%@include file="layout/sidebar.jsp" %>
	<!-- End Sidebar-->

	<main id="main" class="main">

		<div class="pagetitle">
			<h1>Add Product</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="list-product">Home</a></li>
					<li class="breadcrumb-item active">Add Product</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<section class="section dashboard">
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h5 class="card-title">Add Product</h5>
							
							<!-- Hiển thị Error -->
							<p class="text-danger ">${requestScope.errorAddProductServlet}</p>
							
							<form action="add-product" method="post" enctype="multipart/form-data">
								<div hidden="" class="row mb-3">
									<label for="" class="col-sm-2 col-form-label">ID</label>
									<div class="col-sm-10">
										<input readonly="readonly" type="number" name="productId" class="form-control">
									</div>
								</div>
								<div class="row mb-3">
									<label for="" class="col-sm-2 col-form-label">Custom ID</label>
									<div class="col-sm-10">
										<input required="required" type="text" name="productCustomId" class="form-control">
									</div>
								</div>
								<div class="row mb-3">
									<label for="" class="col-sm-2 col-form-label">Name</label>
									<div class="col-sm-10">
										<input required="required" type="text" name="productName" class="form-control">
									</div>
								</div>
								<div class="row mb-3">
									<label for="" class="col-sm-2 col-form-label">Image</label>
									<div class="col-sm-10">
										<input required="required" type="file" id="productImage" name="productImage" size="60" onchange="previewImage(event)"/>
										<img id="preview"  width="200" height="200"/>
									</div>
									
									<div class="gallery"></div>
								</div>
								
								<div class="row mb-3">
									<label for="" class="col-sm-2 col-form-label">Price</label>
									<div class="col-sm-10">
										<input required="required" type="number" value="0" name="productPrice" class="form-control">
									</div>
								</div>
								<div class="row mb-3">
									<label for="" class="col-sm-2 col-form-label">Original Price</label>
									<div class="col-sm-10">
										<input required="required" type="number" value="0" name="productOriginalPrice" class="form-control">
									</div>
								</div>
								
								<div class="row mb-3">
									<label for="" class="col-sm-2 col-form-label">Description</label>
									<div class="col-sm-10">
										<textarea name="productDescription"></textarea>																		
									</div>
									<script>
										CKEDITOR.replace('productDescription');
									</script>
								</div>

								<div class="row mb-3">
									<label for="" class="col-sm-2 col-form-label">Status</label>
									<div class="col-sm-10">
										<select class="form-control" name="productStatus" required>
                                            <option value="1">Active</option> <!-- status = 1 = Active -->
                                            <option value="0">In-Active</option> <!-- status = 0 = In-Active -->
                                        </select>
									</div>
								</div>
								
								<div class="row mb-3">
									<label for="" class="col-sm-2 col-form-label">Category</label>
									<div class="col-sm-10">
										<select name="categoryId" class="form-control">
    										<c:forEach items="${requestScope.categories}" var="category">
        										<option value="${category.categoryId}">${category.categoryName}</option>
    										</c:forEach>
										</select>
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
			</div>
		</section>

	</main>
	<!-- End #main -->

	<!-- ======= Footer ======= -->
	<footer id="footer" class="footer">
		<div class="copyright">
			&copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights
			Reserved
		</div>
		<div class="credits">
			<!-- All the links in the footer should remain intact. -->
			<!-- You can delete the links only if you purchased the pro version. -->
			<!-- Licensing information: https://bootstrapmade.com/license/ -->
			<!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
			Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
		</div>
	</footer>
	<!-- End Footer -->

	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Vendor JS Files -->
	<script src="${url}/vendor/apexcharts/apexcharts.min.js"></script>
	<script src="${url}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="${url}/vendor/chart.js/chart.min.js"></script>
	<script src="${url}/vendor/echarts/echarts.min.js"></script>
	<script src="${url}/vendor/quill/quill.min.js"></script>
	<script src="${url}/vendor/simple-datatables/simple-datatables.js"></script>
	<script src="${url}/vendor/tinymce/tinymce.min.js"></script>
	<script src="${url}/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="${url}/js/main.js"></script>
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