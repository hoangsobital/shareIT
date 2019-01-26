<%@page import="model.bean.UserRank"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>

<%@include file="/templates/admin/inc/left_bar.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#addUser').validate({
			ignore:[],
			rules : {
				username : {
					required : true,
				},
				fullname:{
					required : true,
				},
				password:{
					required : true,
				},
				repassword:{
					required : true,
					equalTo : "#password",
				},
				email:{
					required : true,
					email : true
				}
			},
			messages : {
				username : {
					required : "Vui lòng nhập họ tên!",
				},
				fullname:{
					required : "Vui lòng nhập họ tên đầy đủ!",
				},
				password:{
					required : "Vui lòng nhập password!",
				},
				repassword:{
					required: "Vui lòng xác nhận lại password!",
					equalTo: "Password không trùng khớp!",
				},
				email:{
					required : "Vui lòng nhập email!",
					email : "Email không đúng định dạng!",
				}
			}
		});
	});
</script>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb">
			<a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> 
			<a href="<%=request.getContextPath()%>/admin/users" class="tip-bottom">Quản trị viên</a> 
			<a href="#" class="current">Thêm quản trị viên</a>
		</div>
		<h1>Quản trị viên</h1>
	</div>
	<div class="container-fluid"><hr>
	<%
		if (request.getParameter("msg") != null) {
			int msg = Integer.parseInt(request.getParameter("msg"));
			switch (msg) {
			case 0: out.print("<h4 style = 'color:red'>Thêm thất bại!</h4>"); break;
			case 1: out.print("<h4 style = 'color:red'>Trùng tên đăng nhập!</h4>"); break;
			}
		}
		String username = request.getParameter("username");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String capbac = request.getParameter("capbac");
		int cap = 0;
		if(username == null || fullname == null || email == null){
			username = "";
			fullname="";
			email="";
		}
		if(capbac != null){
			cap = Integer.parseInt(capbac);
		}
	%>
		<div class="row-fluid">
			<div class="widget-box">
				<div class="container-fluid">
					<div class="col-lg-12 col-md-12">
					<div class="header">
        <h4 class="title">Thêm quản trị viên</h4>
    </div>
    <div class="content">
                <form action="" method="post" id="addUser">
            <div class="row">
                <div class="col-md4">
                    <div class="form-group">
                        <label>Tên đăng nhập (*)</label>
                        <input type="text" name="username" value="<%=username%>" class="form-control border-input" placeholder="abcxyz">
                    </div>
                </div>
                <div class="col-md4">
                    <div class="form-group">
                        <label for="fullname">Nhập tên đầy đủ</label>
                        <input type="text" class="form-control border-input" value="<%=fullname%>" name="fullname"  placeholder="Nguyễn Văn A">
                    </div>
                </div>
                <div class="col-md">
                    <div class="form-group">
                        <label for="capbac">Cấp bậc</label>
                          <select class="input" name="capbac" id="capbac" style="width: 37.2%;">
                          <%
                        	  String selected = "";
                          	ArrayList<UserRank> usersR = (ArrayList<UserRank>)request.getAttribute("usersR");
                          	if(usersR != null && usersR.size() > 0){
                          		for(UserRank item : usersR){
                          			if(item.getId() == cap){
										selected = "selected = 'selected'";
									}else{
										selected = "";
									}
                          %>
                            <option <%=selected%> value="<%=item.getId()%>"><%=item.getName()%></option>
                          <%}} %>
                          </select>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md6">
                    <div class="form-group">
                        <label for="password">Nhập mật khẩu</label>
                        <input type="password" class="form-control border-input" name="password" id="password" placeholder="******">
                    </div>
                </div>
                <div class="col-md6">
                    <div class="form-group">
                        <label for="password">Nhập lại mật khẩu</label>
                        <input type="password" class="form-control border-input" name="repassword" id="repassword" placeholder="******">
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="email">Nhập email</label>
                        <input type="email" class="form-control border-input" name="email" value="<%=email%>" placeholder="abcxyz@gmail.com">
                    </div>
                </div>
                
            </div>
            
            <div class="text-center">
                <input type="submit" class="submit" value="Thực hiện" />
            </div>
            <div class="clearfix"></div>
        </form>
    </div>	
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="/templates/admin/inc/footer.jsp"%>