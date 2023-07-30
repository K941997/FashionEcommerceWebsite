<%@page import="dao.ProductColorSizeDAO"%>
<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Format Money VND -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN" />
<fmt:setBundle basename="com.example.message" />


<!-- Add thư mục assets CSS của Admin Page -->
<c:url value="views/admin/assets" var="urlAssets"></c:url>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>List Product By Tablist Category</title>
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

  <!-- JS cho Boostrap 4 Dropdown -->
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  
  <!-- CSS For Pagination, TabList -->
  <style type="text/css">
  	.pagination a.active{
  		background-color: #blue;
  		color: white;
  	}
  	.center {
 		text-align: center;
	}
	.nav a.active{
		background-color: #blue;
  		color: red;
	}
	.alert-message {
        position: fixed;
        top: 10px;
        right: 10px;
        display: none;
        padding: 10px;
        background-color: red;
        color: white;
        font-size: 16px;
        z-index: 1000;
    }
  </style>
  
  <!-- JS cho FilterCheck Categories and Prices -->
  <script type="text/javascript">
	  	function setCheckCategoriesIds(obj){
	  		var fries = document.getElementsByName('categoriesIds');
	  		if((obj.id == 'c0') && (fries[0].checked==true)){
	  			for(var i = 1; i<fries.length; i++){
	  				fries[i].checked = false;
	  			}
	  		} else{
	  			for(var i = 1; i<fries.length; i++){
	  				if(fries[i].checked == true){
	  					fries[0].checked= false;
	  					break;
	  				}
	  			}
	  		}
	  		document.getElementById('f1').submit();
	  	}
	  	
	  	function setCheckProductPrices(obj){
	  		var fries = document.getElementsByName('productPrices');
	  		if((obj.id == 'g0') && (fries[0].checked == true)){
	  			for (var i=1; i<fries.length; i++){
	  				fries[i].checked = false;
	  			}
	  		} else{
	  			for(var i=1; i<fries.length; i++){
	  				if(fries[i].checked == true){
	  					fries[0].checked=false;
	  					break;
	  				}
	  			}
	  		}
	  		document.getElementById('f2').submit();
	  	}
  </script>
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
		  <h1>Dashboard</h1>
		  <nav>
		    <ol class="breadcrumb">
		      <li class="breadcrumb-item"><a href="list-product">Home</a></li>
		      <li class="breadcrumb-item active">Dashboard</li>
		    </ol>
		  </nav>
		</div><!-- End Page Title -->
		
		<!-- List Product -->
		 <section class="section dashboard">
		   <div class="row">
		   	<div class="card">
		         <div class="card-body">
		           <h5 class="card-title">List Product By Tablist Category</h5>
		           		       
		           <a href="add-product" class="btn btn-primary mb-3">Add New</a>
		         
		           <!-- TabList (Lọc theo 1 CategoryId):-->
		             <div class="dropdown">
						<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
							Sort By Category
						</button>
						<div class="dropdown-menu">
							<c:set var="cat" value="${requestScope.categories}"></c:set>
							<c:set var="cid" value="${requestScope.categoryId}"></c:set>
							<a class="dropdown-item ${cid==0?"active":""}" href="list-product-tablist-category?categoryId=${0}">All</a>
			           		<c:forEach items="${cat}" var="c">		           			
			           				<a class="dropdown-item ${c.categoryId == categoryId?"active":""}" href="list-product-tablist-category?categoryId=${c.categoryId}">${c.categoryName}</a>
			           		</c:forEach> 
						</div>
					</div>
					<br>
					
					<!-- FilterCheckbox Categories (Lọc theo Nhiều CategoryIds): Tách Riêng -->
					 <div class="dropdown">
						<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
							Sort By Categories
						</button>
						<div class="dropdown-menu">
							<c:set var="chid" value="${requestScope.chid}"></c:set>
							<form id="f1" action="list-product-filtercheck-categories">
								<input type="checkbox" id="c0" name="categoriesIds" ${chid[0]?"checked":"" } 
									value="${0}" onclick="setCheckCategoriesIds(this)"/>All<br/>
								<c:forEach begin="0" end="${cat.size()-1 }" var="i">
									<input type="checkbox" id="cm" name="categoriesIds"
										${cat.get(i).getCategoryId()==cid?"checked":""}
										value="${cat.get(i).getCategoryId()}"
										${chid[i+1]?"checked":""} onclick="setCheckCategoriesIds(this)"/>
										${cat.get(i).getCategoryName()}
									<br/>
								
								</c:forEach>
							</form>
						</div>
					</div>
					<br/>
					
					<!-- FilterCheckbox Mức Giá (Lọc theo Nhiều Giá): Tách Riêng -->
					 <div class="dropdown">
						<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
							Sort By Prices
						</button>
						<div class="dropdown-menu">
							<c:set var="pp" value="${requestScope.pp}"></c:set>
							<c:set var="pb" value="${requestScope.pb}"></c:set>
				
							<form id="f2" action="list-product-filtercheck-prices">
								<input type="checkbox" id="g0" name="productPrices" ${pb[0]?"checked":"" } 
									value="0" onclick="setCheckProductPrices(this)"> All <br/>
								<c:forEach begin="0" end="${4}" var="i">
									<input type="checkbox" id="g1" name="productPrices"
										${pb[i+1]?"checked":""}
										value="${(i+1)}" onclick="setCheckProductPrices(this)"/>${pp[i]}<br/>
								</c:forEach>
							</form>
						</div>
					</div>
					
		           <!-- Pagination Phân trang (Làm được với Tablist): -->	
		            <nav aria-label="..." >
	        			<c:set var="page" value="${requestScope.page }"></c:set>
					  	<ul class="pagination d-flex justify-content-center ">
					    	<c:forEach begin="${1}" end="${requestScope.num}" var="i">
					    		<li class="page-item ">
		           					<a  class="page-link ${i==page?"active":""}"  href="list-product-tablist-category?page=${i}&categoryId=${categoryId}">${i}</a>
		           				</li>
		           			</c:forEach>
					  </ul>
					</nav>
		           		       
	        		<!-- Hiển thị Error Delete Product -->
					<p class="text-danger">${requestScope.errorDeleteProductMessage}</p>
					<div id="alert-message" class="alert-message">
					    <%= request.getAttribute("errorDeleteProductMessage") %>
					</div>
		
		           <!-- Default Table -->
		           <table class="table">
		             <thead>
		               <tr>
		                 <th hidden="" scope="col">ID</th>
		                 <th scope="col">ID</th>
		                 <th scope="col">Name</th>
		                 <th scope="col">Image</th>
		                 <th scope="col">Price</th>
		                 <th scope="col">Import Price</th>
		                 <th scope="col">Description</th>
		                 <th scope="col">Status</th>
		                 <th scope="col">Category</th>
		            
		                 <th scope="col">Functions</th>
		               </tr>
		             </thead>
		             <tbody>
		             	<% 
				                List<Product> productList = (List<Product>)request.getAttribute("productsPagination");
				                
				                for (Product product : productList) {
				                	ProductColorSizeDAO productColorSizeDAO = new ProductColorSizeDAO();
				            %>
		             	
		             	  	
		             		<tr>
			                   <th hidden="" scope="row"><%= product.getProductId() %></th>
			                   <th scope="row"><%= product.getProductCustomId() %></th>
			                   <td><%= product.getProductName() %></td>
			                   <td>
			                   		<c:if test="<%= product.getProductImage() != null %>">
                      		 			<img style="width: 100px; height: 100px" src='images/<%= product.getProductImage() %>' alt="Profile" name="productImage">
                      				</c:if>
                				</td>
                			
			                   <td>
			                   		<% int productId = product.getProductId(); %>
									<fmt:formatNumber value="<%= productColorSizeDAO.getMinPricePCS(productId)%>" type="currency" currencyCode="VND" />
									-
									<fmt:formatNumber value="<%= productColorSizeDAO.getMaxPricePCS(productId)%>" type="currency" currencyCode="VND" />
			                  
			                   </td>
			                   <td>
			                   		<fmt:formatNumber value="<%= product.getProductOriginalPrice() %>" type="currency" currencyCode="VND" />
			                   
			                   </td>			     
			                   <td>
			                   		<div style="height:50px; width:500px; overflow:hidden">
										<%= product.getProductDescription() %>
									</div>
								</td>
								
								<c:if test="<%= product.getProductStatus() == 1 %>" >
									<td>Active</td>
								</c:if>
								
								<c:if test="<%= product.getProductStatus() != 1 %>" >
									<td>Inactive</td>
								</c:if>
			                   
			           
			                   <td><%= product.getCategory().getCategoryName() %></td>
			                   
			                   
			                   <!-- Quantity PCS, Update, Delete -->
			                 	<td>
			                 		<a href="get-product-color-size?id=<%= product.getProductId() %>"><i class="bi bi-file-ruled-fill"></i></a>
			                 		<a href="update-product?id=<%= product.getProductId() %>"><i class="fa-solid fa-pen-to-square"></i></a> 
			                 		<a href="#" onclick="deleteProduct(<%= product.getProductId() %>)"><i class="fa-solid fa-trash"></i></a>
			                 	</td>
		                	</tr>
		                	
		                	<% 
		                		} 
		                	%>
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
		//Chức năng Xóa:
		function deleteProduct(id) {
	        var flag = false;
	        if (confirm("Are you sure you want to delete this product?")) {
	            var xhttp = new XMLHttpRequest();
	            xhttp.onreadystatechange = function() {
	                if (this.readyState == 4) {
	                    if (this.status == 200) {
	                        // If deletion succeeds, redirect the user to a success page
	                        window.location.href = "list-product";
	                    } else {
	                        // If deletion fails, set the flag and display the error message as an alert
	                        flag = true;
	                        showAlert(this.responseText);
	                        alert("Cannot Delete This Product !");
	                        window.location.href = "list-product";
	                    }
	                }
	                
	            };
	            xhttp.open("GET", "delete-product?id=" + id, true);
	            xhttp.send();
	        } 
	        return !flag;
	    }
	
	    function showAlert(message) {
	        var alertMessage = document.getElementById("alert-message");
	        alertMessage.innerHTML = message;
	        alertMessage.style.display = "block";
	    }
	</script>
</body>
</html>