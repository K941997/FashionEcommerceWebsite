<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/views/admin/assets" var="urlAssets"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Account</title>
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
</head>
<body>
	<section class="vh-100" style="background-color: #eee;">
		  <div class="container h-100">
		    <div class="row d-flex justify-content-center align-items-center h-100">
		      <div class="col-lg-12 col-xl-11">
		        <div class="card text-black" style="border-radius: 25px;">
		          <div class="card-body p-md-5">
		            <div class="row justify-content-center">
		              <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
		
		                <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Register Account</p>
		
						 <!-- Hiển thị Error -->
						<p class=" text-center text-danger">${requestScope.alertRegisterClientServlet}</p>
		
						<!-- FORM Register Account Client -->
		                <form action="register-account-client" method="post" class="mx-1 mx-md-4">
		                  <div class="d-flex flex-row align-items-center mb-4">
		                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
		                    <div class="form-outline flex-fill mb-0">
		                      <input required="required" name="emailUser" type="email" id="emailUser" class="form-control" />
		                      <label class="form-label" for="emailUser">Your Email</label>
		                    </div>
		                  </div>
		
		                  <div class="d-flex flex-row align-items-center mb-4">
		                    <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
		                    <div class="form-outline flex-fill mb-0">
		                      <input required="required" name="passwordUser" type="password" id="passwordUser" class="form-control" />
		                      <label class="form-label" for="passwordUser">Password</label>
		                    </div>
		                  </div>
		
		                  <div class="d-flex flex-row align-items-center mb-4">
		                    <i class="fas fa-key fa-lg me-3 fa-fw"></i>
		                    <div class="form-outline flex-fill mb-0">
		                      <input required="required" name="repeatPassword" type="password" id="repeatPassword" class="form-control" />
		                      <label class="form-label" for="repeatPassword">Repeat your password</label>
		                    </div>
		                  </div>
		                  <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
		                    <button type="submit" class="btn btn-primary btn-lg">Register</button>
		                  </div>
		                  
		                   <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
			                   <a href="index">
					            	<button type="button" class="btn btn-warning btn-lg"
					              	>Back to Web Shopping</button>
					            </a>
				            </div>
		                </form>
		                
		                <!-- Button Login -->
		                <p class="text-center text-muted mt-5 mb-0">Have already an account? 
		                  	<a href="login-client" class="fw-bold text-body"><u>Login here</u></a>
                    	</p>
		
		              </div>
		              <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
		
		                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
		                  class="img-fluid" alt="Sample image">
		
		              </div>
		            </div>
		          </div>
		        </div>
		      </div>
		    </div>
		  </div>
		</section>
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