<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>Forgot Password Client</title>
<link
	href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'
	rel='stylesheet'>
<link href='' rel='stylesheet'>
<script type='text/javascript'
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<style type="text/css">
	body {
		background-position: center;
		background-color: #eee;
		background-repeat: no-repeat;
		background-size: cover;
		color: #505050;
		font-family: "Rubik", Helvetica, Arial, sans-serif;
		font-size: 14px;
		font-weight: normal;
		line-height: 1.5;
		text-transform: none
	}
	
	.forgot {
		background-color: #fff;
		padding: 12px;
		border: 1px solid #dfdfdf
	}
	
	.padding-bottom-3x {
		padding-bottom: 72px !important
	}
	
	.card-footer {
		background-color: #fff
	}
	
	.btn {
		font-size: 13px
	}
	
	.form-control:focus {
		color: #495057;
		background-color: #fff;
		border-color: #76b7e9;
		outline: 0;
		box-shadow: 0 0 0 0px #28a745
	}
</style>
</head>
<body oncontextmenu='return false' class='snippet-body'>
	<div class="container padding-bottom-3x mb-2 mt-5">
		<div class="row justify-content-center">
			<div class="col-lg-8 col-md-10">
				<div class="forgot">
					<h2>Forgot your password?</h2>
					<p>Change your password in three easy steps. This will help you
						to secure your password!</p>
					<ol class="list-unstyled">
						<li><span class="text-primary text-medium">1. </span>Enter
							your email address below.</li>
						<li><span class="text-primary text-medium">2. </span>Our
							system will send you an OTP to your email</li>
						<li><span class="text-primary text-medium">3. </span>Enter the OTP on the 
						next page</li>
					</ol>
				</div>
				
				<!-- FORM Forgot Password Client -->
				<form class="card mt-4" action="forgot-password-client" method="POST">
					<div class="card-body">
						<div class="form-group">
							<label for="email-for-pass">Enter your email address</label> 
							<input required="required"
								class="form-control" type="text" name="emailForgotPassword" id="email-for-pass"><small
								class="form-text text-muted">Enter the registered email address . Then we'll
								email a OTP to this address.</small>
						</div>
					</div>
					<div class="card-footer">
						<button class="btn btn-success" type="submit">Get New
							Password</button>
						<a href="login-client">
							<button class="btn btn-primary" type="button">Back to
							Login</button>
						</a>
						<a href="index">
							<button class="btn btn-warning" type="button">Back to
							Web Shopping</button>
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type='text/javascript'
		src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>
	<script type='text/javascript' src=''></script>
	<script type='text/javascript' src=''></script>
	<script type='text/Javascript'></script>
</body>
</html>