<%@page import="model.bean.User"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>

<%@include file="/templates/admin/inc/left_bar.jsp"%>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb">
			<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> 
			<a href="<%=request.getContextPath()%>/admin/users" class="current">Quản trị viên</a>
		</div>
		<h3>Quản trị viên</h3>
	</div>
	<div class="container-fluid"> <hr>
		<div class="row-fluid">
			<div class="span12">
				<div class="">
					<a href="<%=request.getContextPath()%>/admin/user/add" class="button"> 
						<span class="btn-success" style="padding: 3px 7px; border-radius: 3px;">Thêm <i class="icon-plus"></i></span> </a>
				</div>
				<%
					User userinfo = (User)session.getAttribute("userLogin");
					if (request.getParameter("msg") != null) {
						int msg = Integer.parseInt(request.getParameter("msg"));
						switch (msg) {
						case 0: out.print("<h4 style = 'color:red'>Có lỗi xảy ra trong quá trình xử lý!</h4>"); break;
						case 1: out.print("<h4 style = 'color:#f87822'>Thêm thành công!</h4>"); break;
						case 2: out.print("<h4 style = 'color:#f87822'>Sửa thành công!</h4>"); break;
						case 3: out.print("<h4 style = 'color:#f87822'>Xóa thành công!</h4>"); break;
						case 4: out.print("<h4 style = 'color:red'>Không được quyền xóa admin!</h4>"); break;
						}
					}
				%>
				<div class="widget-box">
					
					<table class="table table-bordered">
						<thead>
							<tr>
								<th style="width:7%;">ID</th>
								<th style="width:18%;">Họ tên</th>
								<th style="width:15%;">Fullname</th>
								<th>Email</th>
								<th style="width:15%;">Cấp bậc</th>
								<th style="width:15%;">Chức năng</th>
							</tr>
						</thead>
						<tbody>
						<%
							ArrayList<User> users = (ArrayList<User>)request.getAttribute("users");
							if(users != null && users.size() > 0){
								for(User item : users){
						%>
							<tr class="gradeX">
								<td style="text-align: center;"><%=item.getId()%></td>
								<td><%=item.getUsername()%></td>
								<td style="text-align: center;"><%=item.getFullname() %></td>
								<td><%=item.getEmail()%></td>
								<td><%=item.getUserRank().getName()%></td>
								<td>
	                               <%
	                               	if("admin".equalsIgnoreCase(userinfo.getUsername())){
	                               %>
	                                   <a href="<%=request.getContextPath()%>/admin/user/edit?id=<%=item.getId()%>"><img src="<%=request.getContextPath() %>/templates/admin/img/edit.gif" alt=""> Sửa</a> &nbsp;-&nbsp;
	                                   <a href="<%=request.getContextPath()%>/admin/user/del?id=<%=item.getId()%>"  onclick="return confirm('Bạn có thật sự muốn xóa?')" ><img src="<%=request.getContextPath() %>/templates/admin/img/del.gif" alt="" > Xóa</a>
	                               <%}else{ %>
	                               	<%
	                               		if(userinfo.getId()==item.getId()){
	                               	%>
	                               	<a href="<%=request.getContextPath()%>/admin/user/edit?id=<%=item.getId()%>"><img src="<%=request.getContextPath() %>/templates/admin/img/edit.gif" alt=""> Sửa</a>
	                               <%} }%>
	                               </td>
								</tr>
						<%}}else{ %>
							<tr> <td colspan="2">Chưa có dữ liệu!</td> </tr>
						<%} %>
						</tbody>
					</table>
					
				</div>
			</div>
		</div>
	</div>
</div>
				
<%@include file="/templates/admin/inc/footer.jsp"%>