<%@page import="model.User"%>
<%@page import="dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Add thư mục assets CSS của Admin Page -->
<c:url value="views/admin/assets" var="urlAssets"></c:url>

<!-- 
	Phần Header đã chứa Session nên ko cần cái này:
	<
		if(session.getAttribute("userEmailLoginServlet") == null){
			response.sendRedirect(request.getContextPath() + "/login-admin");
		}
	%>
-->

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Profile</title>
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
  	<%@include file="layout/header.jsp" %>
              
 	<!-- End Header -->

  <!-- ======= Sidebar ======= -->
  <%@include file="layout/sidebar.jsp" %>
  <!-- End Sidebar-->

  <main id="main" class="main">

    <div class="pagetitle">
      <h1>Profile</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="profile-user-admin">Home</a></li>
          <li class="breadcrumb-item">Users</li>
          <li class="breadcrumb-item active">Profile</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section profile">
      <div class="row">
        <div class="col-xl-4">

          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">

              <img src='images/${sessionScope.userLoginServlet.avatar}' enctype="multipart/form-data" alt="Profile" class="rounded-circle">
              <h2>${sessionScope.userLoginServlet.fullname}</h2>
              <h3>${sessionScope.userLoginServlet.email}</h3>
              <div class="social-links mt-2">
                <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
                <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
                <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
                <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
              </div>
            </div>
          </div>

        </div>

        <div class="col-xl-8">

          <div class="card">
            <div class="card-body pt-3">
              <!-- Bordered Tabs -->
              <ul class="nav nav-tabs nav-tabs-bordered">

                <li class="nav-item">
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview">Overview</button>
                </li>

                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit">Edit Profile</button>
                </li>

             

                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-change-password">Change Password</button>
                </li>

              </ul>
              <div class="tab-content pt-2">

                <div class="tab-pane fade show active profile-overview" id="profile-overview">              
                  <h5 class="card-title">Profile Details</h5>

					<div hidden="" class="row">
                    	<div class="col-lg-3 col-md-4 label ">ID</div>
                    	<div class="col-lg-9 col-md-8" >${sessionScope.userLoginServlet.userId}</div>
                  	</div>
                  
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label ">Full Name</div>
                    <div class="col-lg-9 col-md-8">${sessionScope.userLoginServlet.fullname}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Email</div>
                    <div class="col-lg-9 col-md-8">${sessionScope.userLoginServlet.email}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Phone</div>
                    <div class="col-lg-9 col-md-8">${sessionScope.userLoginServlet.phone}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Address</div>
                    <div class="col-lg-9 col-md-8">${sessionScope.userLoginServlet.address}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Role</div>
                    <div class="col-lg-9 col-md-8">
                    	<c:if test="${sessionScope.userLoginServlet.role == 1}">
                    		Super Admin
                    	</c:if>
                    	<c:if test="${sessionScope.userLoginServlet.role == 2}">
                    		Admin
                    	</c:if>
                    	<c:if test="${sessionScope.userLoginServlet.role > 2}">
                    		Customer
                    	</c:if>
                    </div>
                  </div>              
                </div>

                <div class="tab-pane fade profile-edit pt-3" id="profile-edit">

                  <!-- Profile Edit Form -->
                  	<!-- Hiển thị Error -->
					<p class="text-danger ">${requestScope.messageUpdateProfile}</p>
					
                  <form action="update-profile-user-admin" method="post" enctype="multipart/form-data">
                    <div class="row mb-3">
                      <label for="profileImage" class="col-md-4 col-lg-3 col-form-label">Profile Image</label>
                      <div class="col-md-8 col-lg-9">
                      
                      	<c:if test="${sessionScope.userLoginServlet.avatar != null}">
                      		 <img id="preview" alt="Image Preview" src='images/${sessionScope.userLoginServlet.avatar}' alt="Profile" name="userAvatar">
                      	</c:if>
                        <div class="pt-2">
                         
                         <input type="file" id="userAvatar" name="userAvatar" value="${sessionScope.userLoginServlet.avatar}" size="60" onchange="previewImage(event)"/>
                        </div>
                      </div>
                    </div>

					<div hidden="" class="row mb-3">
                      <label for="fullName" class="col-md-4 col-lg-3 col-form-label">ID</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="userId" type="hidden" class="form-control"  value="${sessionScope.userLoginServlet.userId}">      
                      </div>
                    </div>
                    
                    <div class="row mb-3">
                      <label for="fullName" class="col-md-4 col-lg-3 col-form-label">Full Name</label>
                      <div class="col-md-8 col-lg-9">
                        <input required="required" name="userFullname" type="text" class="form-control" value="${sessionScope.userLoginServlet.fullname}">
                      </div>
                    </div>
                               
                    <div class="row mb-3">
                      <label for="Job" class="col-md-4 col-lg-3 col-form-label">Email</label>
                      <div class="col-md-8 col-lg-9">
                        <input required="required" name="userEmail" type="email" class="form-control" value="${sessionScope.userLoginServlet.email}">
                      </div>
                    </div>
                    
             
                         
                    <div class="row mb-3">
                      <label for="Job" class="col-md-4 col-lg-3 col-form-label">Phone</label>
                      <div class="col-md-8 col-lg-9">
                        <input required="required" name="userPhone" type="text" class="form-control" value="${sessionScope.userLoginServlet.phone}">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="Address" class="col-md-4 col-lg-3 col-form-label">Address</label>
                      <div class="col-md-8 col-lg-9">
                        <input required="required" name="userAddress" type="text" class="form-control" value="${sessionScope.userLoginServlet.address}">
                      </div>
                    </div>
                    
                    <div class="row mb-3">
                      <label for="Address" class="col-md-4 col-lg-3 col-form-label">Role</label>
                      <div class="col-md-8 col-lg-9">
                      
                        <input required="required" name="userRole" type="number" class="form-control" value="${sessionScope.userLoginServlet.role}">
                      	
                      </div>
                    </div>
                                  
                    <div class="text-center">
                      <button type="submit" class="btn btn-primary">Save Changes</button>
                    </div>
                  </form><!-- End Profile Edit Form -->

                </div>

                
				
                <div class="tab-pane fade pt-3" id="profile-change-password">
                  <!-- Change Password Form -->
                  <h3 style="color: red">${requestScope.messageChangePassword}</h3>
                  
                  <form action="change-password-profile-admin">
                    <div class="row mb-3">
                      <label for="currentPassword" class="col-md-4 col-lg-3 col-form-label">Current Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input required="required" name="currentPassword" type="password" class="form-control" id="currentPassword">
                        <input name="userCurrentEmail" type="hidden" class="form-control" id="userCurrentEmail" value="${sessionScope.userLoginServlet.email}">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="newPassword" class="col-md-4 col-lg-3 col-form-label">New Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input required="required" name="newPassword" type="password" class="form-control" id="newPassword">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="renewPassword" class="col-md-4 col-lg-3 col-form-label">Re-enter New Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input required="required" name="renewPassword" type="password" class="form-control" id="renewPassword">
                      </div>
                    </div>

                    <div class="text-center">
                      <button type="submit" class="btn btn-primary">Change Password</button>
                    </div>
                  </form><!-- End Change Password Form -->

                </div>

              </div><!-- End Bordered Tabs -->

            </div>
          </div>

        </div>
      </div>
    </section>

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer" class="footer">
    <div class="copyright">
    
    </div>
    <div class="credits">
      <!-- All the links in the footer should remain intact. -->
      <!-- You can delete the links only if you purchased the pro version. -->
      <!-- Licensing information: https://bootstrapmade.com/license/ -->
      <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
    
    </div>
  </footer><!-- End Footer -->

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