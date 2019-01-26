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
			<a href="<%=request.getContextPath()%>/admin/news" class="current">Bình luận</a>
		</div>
		<h3>Bình luận</h3>
	</div>
	<div class="container-fluid"> <hr>
		<div class="row-fluid">
			<div class="span12">
			<%-- <div class = "timkiem">
				<form action="<%=request.getContextPath()%>/search" method = "get">
					<p style = "padding-left: 830px;margin: 0 0 -40px;">
						<label style = "font-weight:bold;display:inline;">Tìm kiếm:  </label>
						<input style = "height:17px;background: #f9f9f9;" type="text" name="timkiem" value="" class="input-medium" placeholder="Tìm kiếm...." />
					</p>
				</form>
			</div> --%>
				<%-- <%
					if (request.getParameter("msg") != null) {
						int msg = Integer.parseInt(request.getParameter("msg"));
						switch (msg) {
						case 0: out.print("<h4 style = 'color:red'>Có lỗi xảy ra trong quá trình xử lý!</h4>"); break;
						case 1: out.print("<h4 style = 'color:#f87822'>Thêm thành công!</h4>"); break;
						case 2: out.print("<h4 style = 'color:#f87822'>Sửa thành công!</h4>"); break;
						case 3: out.print("<h4 style = 'color:#f87822'>Xóa thành công!</h4>"); break;
						}
					}
				%> --%>
				<div class="widget-box">
					
					<table class="table table-bordered">
						<thead>
							<tr>
								<th style="width: 5%;">ID</th>
								<th>Tên bài viết</th>
								<th style="width: 12%;">Họ và tên</th>
								<th>Email</th>
								<th>Website</th>
								<th>Bình luận</th>
								<th style="width: 7%;">Trạng thái</th>
								<th style="width: 7%;">Chức năng</th>
							</tr>
						</thead>
						<tbody>
						<%-- <%
							ArrayList<News> news = (ArrayList<News>)request.getAttribute("news");
							if(news != null && news.size() > 0){
								for(News item : news){
						%> --%>
							<tr class="gradeX">
								<td style="text-align: center;"></td>
								<td></td>
								<td style="text-align: center;"></td>
								<td></td>
								<td></td>
								<td></td>
								<td style="text-align: center;">
									<a href="<%=request.getContextPath()%>/admin/news/del?id=" onclick=""><img src="<%=request.getContextPath()%>/templates/admin/img/disactive.png" width="20px"/></a></td>
								<td style="text-align: center;">
									<a href="<%=request.getContextPath()%>/admin/news/del?id=" onclick="return confirm('Bạn có thật sự muốn xóa?')"><img src="<%=request.getContextPath()%>/templates/admin/img/del.gif" alt="" /> Xóa</a></td>
							</tr>
						<%-- <%}}else{ %>
							<tr> <td colspan="2">Chưa có dữ liệu!</td> </tr>
						<%} %> --%>
						</tbody>
					</table>
					<%-- <div class="paginate">
						<div class="dataTables_paginate" >
							<a href="<%=request.getContextPath()%>/admin/news?page=1" class="ui-button ui-state-disabled" id="">First</a>
							<%	int back = 0; int next = 0; int view = 0; 
				
								int sumPage = (Integer)request.getAttribute("sumPage");
								int current_page = (Integer)request.getAttribute("current_page");
							%>
							<%if(current_page != 1){
								back = current_page - 1;
							 %>
							<a href="<%=request.getContextPath()%>/admin/news?page=<%=back%>" class="ui-button ui-state-disabled" id="">Previous</a>
							<% }%>
							<%
								String active = "";
								int j = 1;
								if(current_page >= 2){
									j = current_page;
								}
								if(current_page > sumPage - 2){
									j = sumPage - 1;//--> sai
									view = sumPage;
								}else{
									view = current_page + 2;
								}
								
							  	for(int i=j; i <= view; i++){
							  		if(current_page == i){
							  			active = "ui-state-disabled";
							  		}else{
							  			active = "";
							  		}
							%>
							<%
								String active = "";
								for(int i = 1; i <= sumPage; i++){
							 		if( i== current_page){
							 			active = "ui-state-disabled";
							 		}else{
							 			active = "";
							 		}
							%>
							<span><a href="<%=request.getContextPath()%>/admin/news?page=<%=i%>" class="ui-button <%=active%>"><%=i%></a></span>
							<%} %>
							<%
								if(current_page != sumPage){
								next = current_page + 1;
							%>
							<a href="<%=request.getContextPath()%>/admin/news?page=<%=next%>" class="ui-button ui-state-disabled" id="">Next</a> 
							<%} %>
							<a href="<%=request.getContextPath()%>/admin/news?page=<%=sumPage%>" class="ui-button ui-state-disabled" id="">Last</a>
						</div>
					</div> --%>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="/templates/admin/inc/footer.jsp"%>