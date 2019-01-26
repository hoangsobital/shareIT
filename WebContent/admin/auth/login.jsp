<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>ShareIT</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/templates/admin/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/templates/admin/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/templates/admin/css/matrix-login.css" />
<link href="<%=request.getContextPath()%>/templates/admin/css/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<script src="<%=request.getContextPath()%>/templates/admin/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/templates/admin/js/jquery.validate.min.js"></script>

</head>
<body>
<script type="text/javascript">
	$(document).ready(function (){
		$('#loginform').validate({
			ignore:[],
			rules:{
				username: {
					required: true
				},
				password: {
					required: true
				},
			},
			messages:{
				username: {
					required: "Vui lòng nhập username!"
				},
				password: {
					required: "Vui lòng nhập password!"
				},
			}
		});
	});
</script> 
	<%
	if (request.getParameter("msg") != null) {
		int msg = Integer.parseInt(request.getParameter("msg"));
		switch (msg) {
		case 0: out.print("<h5 style = 'color:#ffb82c;margin-left:570px'>Sai username hoặc password!</h5>"); break;
		}
	}
	%>
	<div id="loginbox">
		<form id="loginform" class="form-vertical" action="<%=request.getContextPath()%>/auth/login" method="post">
			<div class="control-group normal_text">
				<h3>
					<img src="<%=request.getContextPath()%>/templates/admin/img/logo1.png" alt="Logo" />
				</h3>
			</div>
			<div class="control-group">
				<div class="controls">
					<div class="main_input_box">
						<input type="text" name="username" value="" placeholder="Username" />
					</div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<div class="main_input_box">
						<input type="password" name="password" value="" placeholder="Password" />
					</div>
				</div>
			</div>
			<div class="form-actions">
				<input class="btn-success" name="submit" type="submit" value="Đăng nhập" /> 
			</div>
		</form>
	</div>

<script src="<%=request.getContextPath()%>/templates/admin/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/templates/admin/js/matrix.login.js"></script>
</body>
</html>
