<%@page import="model.CartItem"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Add thư mục assets CSS của Admin Page -->
<c:url value="views/admin/assets" var="urlAssetsAdmin"></c:url>
<c:url value="views/client/assets" var="urlAssets"></c:url>

<!-- Cart -->
<%
	ArrayList<CartItem> cart_list = (ArrayList<CartItem>) session.getAttribute("cart-list");
	if(cart_list != null){
		request.setAttribute("cart_list", cart_list);
	};

%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Profile</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	 <!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="${urlAssets}/images/icons/favicon.png"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/fonts/linearicons-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/slick/slick.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/MagnificPopup/magnific-popup.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${urlAssets}/css/util.css">
	<link rel="stylesheet" type="text/css" href="${urlAssets}/css/main.css">
<!--===============================================================================================-->

	<!-- Favicons -->
  <link href="${urlAssetsAdmin}/img/favicon.png" rel="icon">
  <link href="${urlAssetsAdmin}/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->

  <link href="${urlAssetsAdmin}/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="${urlAssetsAdmin}/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="${urlAssetsAdmin}/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="${urlAssetsAdmin}/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="${urlAssetsAdmin}/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="${urlAssetsAdmin}/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="${urlAssetsAdmin}/css/style.css" rel="stylesheet">


  
 

</head>
<body class="animsition">
	<!-- Header -->
	<%@include file="layout/header.jsp" %>
	<!-- End Header -->
	
	<main id="main"  class="main bg0 m-t-23 p-b-140">
    <div class="pagetitle">
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index">Home</a></li>
          <li class="breadcrumb-item">My Account</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section profile">
      <div class="row">
        <div class="col-xl-4">

          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">

              <img src='images/${sessionScope.userLoginClient.avatar}' enctype="multipart/form-data" alt="Profile" class="rounded-circle">
              <br>
              <h2>${sessionScope.userLoginClient.fullname}</h2>
              <br>
              <h3>${sessionScope.userLoginClient.email}</h3>
              <br>
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
                    	<div class="col-lg-9 col-md-8" >${sessionScope.userLoginClient.userId}</div>
                  	</div>
                  
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label ">Full Name</div>
                    <div class="col-lg-9 col-md-8">${sessionScope.userLoginClient.fullname}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Email</div>
                    <div class="col-lg-9 col-md-8">${sessionScope.userLoginClient.email}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Phone</div>
                    <div class="col-lg-9 col-md-8">${sessionScope.userLoginClient.phone}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Address</div>
                    <div class="col-lg-9 col-md-8">${sessionScope.userLoginClient.address}</div>
                  </div>

                             
                </div>

                <div class="tab-pane fade profile-edit pt-3" id="profile-edit">

                  <!-- Profile Edit Form -->
                  	<!-- Hiển thị Error -->
					
					<p class="text-danger " >${requestScope.messageUpdateProfile}</p>
                  <form action="update-profile-user-client" method="post" enctype="multipart/form-data">
                    <div class="row mb-3">
                      <label for="profileImage" class="col-md-4 col-lg-3 col-form-label">Profile Image</label>
                      <div class="col-md-8 col-lg-9">
                      
                      	<c:if test="${sessionScope.userLoginClient.avatar != null}">
                      		 <img id="preview" alt="Image Preview" src='images/${sessionScope.userLoginClient.avatar}' alt="Profile" name="userAvatar">
                      	</c:if>
                        <div class="pt-2">
                         
                         <input type="file" id="userAvatar" name="userAvatar" value="${sessionScope.userLoginClient.avatar}" size="60" onchange="previewImage(event)"/>
                        </div>
                      </div>
                    </div>

					<div hidden="" class="row mb-3">
                      <label for="fullName" class="col-md-4 col-lg-3 col-form-label">ID</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="userId" type="hidden" class="form-control"  value="${sessionScope.userLoginClient.userId}">      
                      </div>
                    </div>
                    
                    <div class="row mb-3">
                      <label for="fullName" class="col-md-4 col-lg-3 col-form-label">Full Name</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="userFullname" type="text" class="form-control" value="${sessionScope.userLoginClient.fullname}">
                      </div>
                    </div>
                               
                    <div class="row mb-3">
                      <label for="Job" class="col-md-4 col-lg-3 col-form-label">Email</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="userEmail" type="email" class="form-control" value="${sessionScope.userLoginClient.email}">
                      </div>
                    </div>
                    
                         
                    <div class="row mb-3">
                      <label for="Job" class="col-md-4 col-lg-3 col-form-label">Phone</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="userPhone" type="text" class="form-control" value="${sessionScope.userLoginClient.phone}">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="Address" class="col-md-4 col-lg-3 col-form-label">Address</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="userAddress" type="text" class="form-control" value="${sessionScope.userLoginClient.address}">
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
         
                  <form action="change-password-profile-client">
                    <div class="row mb-3">
                      <label for="currentPassword" class="col-md-4 col-lg-3 col-form-label">Current Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="currentPassword" type="password" class="form-control" id="currentPassword">
                        <input name="userCurrentEmail" type="hidden" class="form-control" id="userCurrentEmail" value="${sessionScope.userLoginClient.email}">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="newPassword" class="col-md-4 col-lg-3 col-form-label">New Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="newPassword" type="password" class="form-control" id="newPassword">
                      </div>
                    </div>

                    <div class="row mb-3">
                      <label for="renewPassword" class="col-md-4 col-lg-3 col-form-label">Re-enter New Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="renewPassword" type="password" class="form-control" id="renewPassword">
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
  
  <!-- Header -->
	<%@include file="layout/footer.jsp" %>
	<!-- End Header -->
  
  
  
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="${urlAssetsAdmin}/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="${urlAssetsAdmin}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="${urlAssetsAdmin}/vendor/chart.js/chart.umd.js"></script>
  <script src="${urlAssetsAdmin}/vendor/echarts/echarts.min.js"></script>
  <script src="${urlAssetsAdmin}/vendor/quill/quill.min.js"></script>
  <script src="${urlAssetsAdmin}/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="${urlAssetsAdmin}/vendor/tinymce/tinymce.min.js"></script>
  <script src="${urlAssetsAdmin}/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="${urlAssetsAdmin}/js/main.js"></script>
  
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
  
  <!--===============================================================================================-->	
	<script src="${urlAssets}/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/bootstrap/js/popper.js"></script>
	<script src="${urlAssets}/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/select2/select2.min.js"></script>
	<script>
		$(".js-select2").each(function(){
			$(this).select2({
				minimumResultsForSearch: 20,
				dropdownParent: $(this).next('.dropDownSelect2')
			});
		})
	</script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/daterangepicker/moment.min.js"></script>
	<script src="${urlAssets}/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/slick/slick.min.js"></script>
	<script src="${urlAssets}/js/slick-custom.js"></script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/parallax100/parallax100.js"></script>
	<script>
        $('.parallax100').parallax100();
	</script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
	<script>
		$('.gallery-lb').each(function() { // the containers for all your galleries
			$(this).magnificPopup({
		        delegate: 'a', // the selector for gallery item
		        type: 'image',
		        gallery: {
		        	enabled:true
		        },
		        mainClass: 'mfp-fade'
		    });
		});
	</script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/isotope/isotope.pkgd.min.js"></script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/sweetalert/sweetalert.min.js"></script>
	<script>
		$('.js-addwish-b2, .js-addwish-detail').on('click', function(e){
			e.preventDefault();
		});

		$('.js-addwish-b2').each(function(){
			var nameProduct = $(this).parent().parent().find('.js-name-b2').html();
			$(this).on('click', function(){
				swal(nameProduct, "is added to wishlist !", "success");

				$(this).addClass('js-addedwish-b2');
				$(this).off('click');
			});
		});

		$('.js-addwish-detail').each(function(){
			var nameProduct = $(this).parent().parent().parent().find('.js-name-detail').html();

			$(this).on('click', function(){
				swal(nameProduct, "is added to wishlist !", "success");

				$(this).addClass('js-addedwish-detail');
				$(this).off('click');
			});
		});

		/*---------------------------------------------*/

		$('.js-addcart-detail').each(function(){
			var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
			$(this).on('click', function(){
				swal(nameProduct, "is added to cart !", "success");
			});
		});
	
	</script>
<!--===============================================================================================-->
	<script src="${urlAssets}/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script>
		$('.js-pscroll').each(function(){
			$(this).css('position','relative');
			$(this).css('overflow','hidden');
			var ps = new PerfectScrollbar(this, {
				wheelSpeed: 1,
				scrollingThreshold: 1000,
				wheelPropagation: false,
			});

			$(window).on('resize', function(){
				ps.update();
			})
		});
	</script>
<!--===============================================================================================-->
	<script src="${urlAssets}/js/main.js"></script>
</body>
</html>