<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="views/admin/assets" var="url"></c:url>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Add Product Size Color</title>
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

<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
 
<style type="text/css">
 .preview {
     display: inline-block;
     width: 70px;
     height: 70px;
     margin: 0 5px;
     vertical-align: middle;
     border: 1px solid #ccc;
     border-radius: 5px;
     overflow: hidden;
   }
   
   .preview > img {
     width: 100%;
     height: 100%;
     object-fit: cover;
   }
</style>

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
			<h1>Add New PCS</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="list-product">Home</a></li>
					<li class="breadcrumb-item active">Add New PCS</li>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->

		<section class="section dashboard">
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							
							<!-- productAfterAddSession từ add-product (send data from add-product) 
							-->
							<h5 class="card-title">Add New PCS</h5>
							
							<c:set var="idSession" value="${sessionScope.productIdFromAddSession}"></c:set>
							<c:set var="nameSession" value="${sessionScope.productNameFromAddSession}"></c:set>
							<h5 hidden="" class="card-title"> Product ID: ${idSession}</h5>
							<h5 hidden="" class="card-title"> Product Name: ${nameSession}</h5>
	
								
							<!-- Hiển thị Error -->
							<p class="text-danger">${requestScope.errorAddProductColorSizeServlet}</p>
							
							<!-- productNow từ add-product-size-color (set data from data sent from add-product) -->					
							<form action="add-product-color-size" method="post" enctype="multipart/form-data">
								<input hidden="" type="text" name="id" value="${sessionScope.productIdFromAddSession}"/>
								<input hidden="" type="text" name="name" value="${sessionScope.productNameFromAddSession}"/>
								<!-- 
									<input name="productAfterAddId" value="${requestScope.productAfterAdd.productId}" />
									<input name="productAfterAddName" value="${requestScope.productAfterAdd.productName}" />
								 -->
								<div class="container">
								    <div class="row clearfix">
										<div class="col-md-12 column">
											<table class="table table-bordered table-hover" id="productColorSizeTable">
											      <thead>
											        <tr>
												         <th class="text-center">Image</th>
												          <th class="text-center">Color</th>
												          <th class="text-center">Size</th>
												          <th class="text-center">Quantity</th>
												          <th class="text-center">Price</th>
												          <th class="text-center">Action</th>
											        </tr>
											      </thead>
											      <tbody>
											        <tr>
											        	 <td>
											        	 	<input required="required" type="file" name="pcsImages" onchange="previewImage(this, '0')" />
											        	 	<div class="preview" ></div>
											        	 	   
											        	 </td>
											          <td>
											          		<select name="colorId" class="form-control">											
					    										<c:forEach items="${requestScope.colors}" var="color">
					        										<option value="${color.colorId}">${color.colorName}</option>
					    										</c:forEach>
															</select>
											          </td>
											          <td>
												          <select name="sizeId" class="form-control">
					    										<c:forEach items="${requestScope.sizes}" var="size">
					        										<option value="${size.sizeId}">${size.sizeName}</option>
					    										</c:forEach>
															</select>
											          </td>
											          <td><input  value="0" placeholder='Nhập Quantity...'  type="number" name="quantity"  class='form-control' /></td>
											          <td><input  value="0" placeholder='Nhập Price...'  type="number" name="price"  class='form-control' /></td>
											         
											          <td><button type="button" onclick="deleteRow(this)">Delete</button></td>
											        </tr>
											      </tbody>
											    </table>
											    <button type="button" onclick="addRow()">Add new row</button>
											    <button type="submit">Save</button>
											   
										</div>
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

	<!-- ======= Header ======= -->
	 <%@include file="layout/footer.jsp" %>
	<!-- End Header -->

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
		//Dynamic Table Row Creation And Deletion:
		//Add Row:
		  function addRow() {
		     var table = document.getElementById("productColorSizeTable");
		     var rowCount = table.rows.length;
		     var row = table.insertRow(rowCount - 1);
		     var col1 = row.insertCell(0);
		     var col2 = row.insertCell(1);
		     var col3 = row.insertCell(2);
		     var col4 = row.insertCell(3);
		     var col5 = row.insertCell(4);
		     var col6 = row.insertCell(5);
		     col1.innerHTML = "<input type='file' name='pcsImages' onchange='previewImage(this, " + rowCount + ")' /> <div class='preview'></div>";
		     col2.innerHTML = "<select name='colorId' class='form-control'><c:forEach items='${requestScope.colors}' var='color'><option value='${color.colorId}''>${color.colorName}</option></c:forEach></select>";
		     col3.innerHTML = "<select name='sizeId' class='form-control'><c:forEach items='${requestScope.sizes}' var='size'><option value='${size.sizeId}''>${size.sizeName}</option></c:forEach></select>";
		     col4.innerHTML = "<input value='0' placeholder='Nhập Quantity...' type='number' name='quantity' class='form-control' />";
		     col5.innerHTML = "<input value='0' placeholder='Nhập Price...' type='number' name='price' class='form-control'/>";
		     col6.innerHTML = "<button type='button'  onclick='deleteRow(this)'>Delete</button>";
		   }
		
		//Delete Row:
			function deleteRow(btn) {
		     var row = btn.parentNode.parentNode;
		     row.parentNode.removeChild(row);
		     resetPreviewOrder();
		   }
		   
		   function resetPreviewOrder() {
		     var rows = document.getElementById("productColorSizeTable").rows;
		     for (var i = 1; i < rows.length - 1; i++) {
		       var preview = rows[i].cells[4].querySelector(".preview");
		       preview.setAttribute("data-order", i - 1);
		     }
		   }
		   
	   function previewImage(input, order) {
		     var preview = input.parentNode.querySelector(".preview");
		     preview.setAttribute("data-order", order - 1);
		     if (input.files && input.files[0]) {
		       var reader = new FileReader();
		       reader.onload = function (e) {
		         preview.innerHTML = "<img src='" + e.target.result + "' alt='Preview' />";
		       };
		       reader.readAsDataURL(input.files[0]);
		     } else {
		       preview.innerHTML = "";
		     }
		   }
	   
	   function changePreview(event) {
		     var select = event.target;
		     var order = select.parentNode.parentNode.querySelector(".preview").getAttribute("data-order");
		     var rows = document.getElementById("productColorSizeTable").rows;
		     var imageInput = rows[order].querySelector("input[type=file]");
		     if (imageInput.files && imageInput.files[0]) {
		       var reader = new FileReader();
		       reader.onload = function (e) {
		         rows[order].querySelector(".preview").innerHTML = "<img src='" + e.target.result + "' alt='Preview' />";
		       };
		       reader.readAsDataURL(imageInput.files[0]);
		     } else {
		       rows[order].querySelector(".preview").innerHTML = "";
		     }
		   }
	 		
	 </script>

</body>

</html>