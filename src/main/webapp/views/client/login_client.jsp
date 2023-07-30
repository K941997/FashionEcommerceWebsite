<%@page import="model.CartItem"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/views/admin/assets" var="urlAssets"></c:url>
    

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login</title>
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
<style>
	.divider:after,
	.divider:before {
		content: "";
		flex: 1;
		height: 1px;
		background: #eee;
	}
	.h-custom {
		height: calc(100% - 73px);
	}
	@media (max-width: 450px) {
		.h-custom {
			height: 100%;
		}
	}
</style>

<body>
	 <%
	    Cookie[] cookies=request.getCookies();
	    String emailUser = "", passwordUser = "",rememberVal="";
	    if (cookies != null) {
	         for (Cookie cookie : cookies) {
	           if(cookie.getName().equals("cookieEmailUser")) {
	        	   emailUser = cookie.getValue();
	           }
	           if(cookie.getName().equals("cookiePasswordUser")){
	        	   passwordUser = cookie.getValue();
	           }
	           if(cookie.getName().equals("cookieRememberUser")){
	             rememberVal = cookie.getValue();
	           }
	        }
	    }
	%>
	
	<section class="vh-100">
  <div class="container-fluid h-custom">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-md-9 col-lg-6 col-xl-5">
        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
          class="img-fluid" alt="Sample image">
      </div>
      
      <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
      	<!-- FORM Client Login -->
      	
      	 <!-- Hiển thị Error -->
		<p class="text-danger">${requestScope.errorLoginClientServlet}</p>
		
        <form action="login-client" method="post">
          <!-- Email input -->
          <div class="form-outline mb-4">
          	<label class="form-label" for="userEmail">Email</label>
            <input required="required" type="email" name="userEmail" id="userEmail" class="form-control form-control-lg"
              placeholder="Enter a valid email address" />
          </div>

          <!-- Password input -->
          <div class="form-outline mb-3">
           	<label class="form-label" for="userPassword">Password</label>
            <input required="required" type="password" name="userPassword" id="userPassword" class="form-control form-control-lg"
              placeholder="Enter password" />
           
          </div>

          <div class="d-flex justify-content-between align-items-center">
            <!-- Checkbox -->
            <div class="form-check mb-0">
              <input class="form-check-input me-2" type="checkbox" value="1" name="rememberLogin" id="rememberLogin" <%= "1".equals(rememberVal.trim()) ? "checked=\"checked\"" : "" %> />
              <label class="form-check-label" for="rememberLogin">
                Remember me
              </label>
            </div>
            <a href="forgot-password-client" class="text-body">Forgot password?</a>
          </div>

          <div class="text-center text-lg-start mt-4 pt-2">
            <button type="submit" class="btn btn-primary btn-lg"
              style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
             
            <a href="index">
            	<button type="button" class="btn btn-warning btn-lg"
              	style="padding-left: 2.5rem; padding-right: 2.5rem;">Back to Web Shopping</button>
            </a>
            
            <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? 
            	<a href="register-account-client" class="link-danger">Register</a>
            </p>
          </div>
        </form>
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