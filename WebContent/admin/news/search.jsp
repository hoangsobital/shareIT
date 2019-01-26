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
		<h1>Bài viết</h1>
	</div>
	<div class="container-fluid"> <hr>
		<div class="row-fluid">
			<div class="span12">
			<div class = "timkiem">
				<form action="<%=request.getContextPath()%>/search" method = "post">
					<%
						String timkiem = request.getParameter("timkiem");
						 if(timkiem == null){
							 timkiem = "";
						} 
					%>
					
					<p style = "padding-left: 830px;margin: 0 0 -40px;">
						<label style = "font-weight:bold;display:inline;">Tìm kiếm:  </label>
						<input style = "height:17px;background: #f9f9f9;" type="text" name="timkiem" value="<%=timkiem%>" class="input-medium" placeholder="Tìm kiếm...." />
					</p>
				</form>
			</div>
				<div class="">
					<a href="<%=request.getContextPath()%>/admin/news/add" class="button"> 
						<span class=addtop>Thêm <i class="icon-plus"></i></span> </a>
				</div>
				<%
					if (request.getParameter("msg") != null) {
						int msg = Integer.parseInt(request.getParameter("msg"));
						switch (msg) {
						case 0: out.print("<h4 style = 'color:red'>Có lỗi xảy ra trong quá trình xử lý!</h4>"); break;
						case 1: out.print("<h4 style = 'color:#f87822'>Thêm thành công!</h4>"); break;
						case 2: out.print("<h4 style = 'color:#f87822'>Sửa thành công!</h4>"); break;
						}
					}
				%>
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"><i class="icon-th"></i></span>
						<h5>Data</h5>
					</div>
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
								<td style="text-align: center;"><img src="<%=request.getContextPath()%>/files/<%=item.getPicture()%>" alt="hinhanh" width="120px" height="80px" /></td>
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
							<a href="<%=request.getContextPath()%>/search?page=1" class="ui-button ui-state-disabled" id="">First</a>
							
							<%
								String active = "";
								int sumPage = (Integer)request.getAttribute("sumPage");
								int current_page = (Integer)request.getAttribute("current_page");
								for(int i = 1; i <= sumPage; i++){
							 		if( i== current_page){
							 			active = "ui-state-disabled";
							 		}else{
							 			active = "";
							 		}
							%>
							<span><a href="<%=request.getContextPath()%>/search?page=<%=i%>" class="ui-button <%=active%>"><%=i%></a></span>
							<%} %>
							
							<a href="<%=request.getContextPath()%>/search?page=<%=sumPage%>" class="ui-button ui-state-disabled" id="">Last</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="/templates/admin/inc/footer.jsp"%>