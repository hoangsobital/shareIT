<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="icon" type="image/png" sizes="96x96" href="<%=request.getContextPath()%>/templates/admin/img/logo-admin.png">
<title>ShareIT</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/templates/admin/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/templates/admin/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/templates/admin/css/uniform.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/templates/admin/css/select2.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/templates/admin/css/matrix-style.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/templates/admin/css/matrix-media.css" />
<link href="<%=request.getContextPath() %>//templates/admin/css/themify-icons.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/templates/admin/css/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
<script src="<%=request.getContextPath()%>/templates/admin/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/templates/admin/js/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath()%>/templates/admin/js/ckeditor/ckeditor.js"></script>
</head>
<body>

<!--Header-part-->
<div id="header">
  <h1><a href=""><span style="color: #58a808;">Vinaenter</span><span style="color: #ffb82c; text-align='bold';">EDU</span></a></h1>
</div>
<!-- <div class="toptext">
	<h1><a href="/" style="color: #FC8010" title="Trung tâm đào tạo VinaEnter">Vina<span style="color:#58a808">ENTER</span></a></h1>
	<h2 class="slogan">Đã Học Là Làm Được</h2>
</div> -->
<!--close-Header-part--> 

<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
  <ul class="nav">
  <%
  		User userLogin = (User)session.getAttribute("userLogin");
  		if(userLogin != null){
  %>
    <li  class="dropdown" id="profile-messages" ><a href="<%=request.getContextPath()%>/admin/user/edit?id=<%=userLogin.getId()%>" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user"></i><span class="text"> Chào bạn <%=userLogin.getFullname()%></span><b></b></a>     
    </li><%} %>
    <li><a title="" href="<%=request.getContextPath()%>/auth/logout"><i class="icon icon-share-alt"></i> <span class="text">Logout</span></a></li>
  </ul>
</div>
<!--close-top-Header-menu-->