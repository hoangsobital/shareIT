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
			<a href="<%=request.getContextPath()%>/admin/news" class="current">Bài viết</a>
		</div>
		<h3>Bài viết</h3>
	</div>
	<div class="container-fluid"> <hr>
		<div class="row-fluid">
			<div class="span12">
			<div class = "timkiem">
				<form action="<%=request.getContextPath()%>/search" method = "post">
					<p style = "padding-left: 830px;margin: 0 0 -40px;">
						<label style = "font-weight:bold;display:inline;">Tìm kiếm:  </label>
						<input style = "height:17px;background: #f9f9f9;" type="text" name="timkiem" value="" class="input-medium" placeholder="Tìm kiếm...." />
					</p>
				</form>
			</div>
				<div>
					<a href="<%=request.getContextPath()%>/admin/news/add" class="button"> 
						<span class="btn-success" style="padding: 3px 7px; border-radius: 3px;">Thêm <i class="icon-plus"></i></span> </a>
				</div>
				<%
					if (request.getParameter("msg") != null) {
						int msg = Integer.parseInt(request.getParameter("msg"));
						switch (msg) {
						case 0: out.print("<h4 style = 'color:red'>Có lỗi xảy ra trong quá trình xử lý!</h4>"); break;
						case 1: out.print("<h4 style = 'color:#f87822'>Thêm thành công!</h4>"); break;
						case 2: out.print("<h4 style = 'color:#f87822'>Sửa thành công!</h4>"); break;
						case 3: out.print("<h4 style = 'color:#f87822'>Xóa thành công!</h4>"); break;
						case 4: out.print("<h4 style = 'color:#f87822'>Tin bạn muốn tìm không tồn tại!</h4>"); break;
						}
					}
				%>
				<div class="widget-box">
					
					<table class="table table-bordered">
						<thead>
							<tr>
								<th style="width: 5%;">ID</th>
								<th>Tên bài viết</th>
								<th style="width: 15%;">Hình ảnh</th>
								<th style="width: 13%;">Ngày tạo</th>
								<th style="width: 13%;">Danh mục</th>
								<th style="width: 13%;">Chức năng</th>
							</tr>
						</thead>
						<tbody>
						<%
							ArrayList<News> news = (ArrayList<News>)request.getAttribute("news");
							if(news != null && news.size() > 0){
								for(News item : news){
						%>
							<tr class="gradeX">
								<td style="text-align: center;"><%=item.getId()%></td>
								<td><%=item.getName()%></td>
								<td style="text-align: center;"><img src="<%=request.getContextPath()%>/files/<%=item.getPicture()%>" alt="hinhanh" style = "width: 120px;height:70px"/></td>
								<td><%=item.getDate_create()%></td>
								<td><%=item.getCategory().getName()%></td>
								<td style="text-align: center;">
									<a href="<%=request.getContextPath()%>/admin/news/edit?id=<%=item.getId()%>"><img src="<%=request.getContextPath()%>/templates/admin/img/edit.gif" alt="" /> Sửa</a> &nbsp;-&nbsp; 
									<a href="<%=request.getContextPath()%>/admin/news/del?id=<%=item.getId() %>" onclick="return confirm('Bạn có thật sự muốn xóa?')"><img src="<%=request.getContextPath()%>/templates/admin/img/del.gif" alt="" /> Xóa</a></td>
							</tr>
						<%}}else{ %>
							<tr> <td colspan="2">Chưa có dữ liệu!</td> </tr>
						<%} %>
						</tbody>
					</table>
					<div class="paginate">
						<div class="dataTables_paginate" >
								<a href = "" class="ui-button ui-state-disabled" id="">«</a>
								<span><a href = "" class="ui-button ui-state-disabled">1</a></span>
								<a href = "" class="ui-button ui-state-disabled" id="">»</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="/templates/admin/inc/footer.jsp"%>