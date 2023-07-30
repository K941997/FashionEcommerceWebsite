<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Add thư mục assets CSS của Admin Page -->
<c:url value="views/admin/assets" var="urlAssets"></c:url>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>List Size Admin</title>
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
  
  <style type="text/css">
  	.pagination a.active{
  		background-color: #blue;
  		color: white;
  	}
  	
  	.center {
 		text-align: center;
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
		<h1>List Size</</h1>
		  <nav>
		    <ol class="breadcrumb">
		      <li class="breadcrumb-item"><a href="list-size">Home</a></li>
		      <li class="breadcrumb-item active">List Size</li>
		    </ol>
		  </nav>
		</div><!-- End Page Title -->
	
		 <section class="section dashboard">
		   <div class="row">
		   	<div class="card">
		         <div class="card-body">
		           <h1 class="card-title">List Size</h1>
		           
		           <a href="add-size" class="btn btn-primary mb-3">Add New</a>
		           
		           <!-- Pagination Phân trang -->			       
	        		<nav aria-label="..." >
	        			<c:set var="page" value="${requestScope.page }"></c:set>
					  	<ul class="pagination d-flex justify-content-center ">
					    	<c:forEach begin="${1}" end="${requestScope.num}" var="i">
					    		<li class="page-item ">
		           					<a  class="page-link ${i==page?"active":""}"  href="list-size?page=${i}">${i}</a>
		           				</li>
		           			</c:forEach>
					  </ul>
					</nav>
		
		           <!-- Default Table -->
		           <table class="table">
		             <thead>
		               <tr>
		                 <th scope="col">ID</th>
		                 <th scope="col">Name</th>		            
		                 <th scope="col">Function</th>
		               </tr>
		             </thead>
		             <tbody>
		             	<c:forEach items="${requestScope.sizesListSizeServlet}" var="size">
		             		<tr>
			                   <th scope="row">${size.sizeId}</th>
			                   <td>${size.sizeName}</td>
			                 	<td>
			                 		<a href="update-size?id=${size.sizeId}"><i class="fa-solid fa-pen-to-square"></i></a> |
			                 		<a href="#" onclick="deleteSize('${size.sizeId}')"><i class="fa-solid fa-trash"></i></a>
			                 	</td>
		                	</tr>
		             	</c:forEach>
		             </tbody>
		           </table>
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
		function deleteSize(sizeId){
			if(confirm("Do you want to Delete Size with ID: "+sizeId)){
				window.location = "delete-size?id="+sizeId;
			}
		}
	</script>
	
	

</body>

</html>