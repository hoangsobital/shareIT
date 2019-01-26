<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>

<%@include file="/templates/admin/inc/left_bar.jsp"%>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb">
			<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> 
			<a href="<%=request.getContextPath()%>/admin/cats" class="current">Danh mục</a>
		</div>
		<h3>Danh mục</h3>
	</div>
	<div class="container-fluid"><hr>
		<div class="row-fluid">
			<div class="span12">
				<div class="">
					<a href="<%=request.getContextPath()%>/admin/cat/add" class="button">
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
						}
					}
				%>
				<div class="widget-box">
					
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Tên danh mục</th>
								<th style="width: 30%;">Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<%
								ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
								if (categories != null && categories.size() > 0) {
									for (Category item : categories) {
										if (item.getParent_id() == 0) {
							%>
							<tr class="gradeX">
								<td><a href="" style="text-transform: uppercase;"><%=item.getName()%> </a></td>
								<td style="text-align: center;">
									<a href="<%=request.getContextPath()%>/admin/cat/edit?id=<%=item.getId()%>"> <img src="<%=request.getContextPath()%>/templates/admin/img/edit.gif" alt="" /> Sửa</a> &nbsp;-&nbsp;
									<a href="<%=request.getContextPath()%>/admin/cat/del?id=<%=item.getId()%>" onclick="return confirm('Bạn có thật sự muốn xóa?')"><img src="<%=request.getContextPath()%>/templates/admin/img/del.gif" alt="" /> Xóa</a></td>
							</tr>
							<% } for (Category itemc : categories) { 
								if(item.getId() == itemc.getParent_id()){
							
							%>
							<tr class="gradeX">
								<td><a href="" style="text-transform: uppercase;">&nbsp;&nbsp;&nbsp;&nbsp;>>&nbsp;<%=itemc.getName()%>
								</a></td>
								<td style="text-align: center;">
									<a href="<%=request.getContextPath()%>/admin/cat/edit?id=<%=itemc.getId()%>"><img src="<%=request.getContextPath()%>/templates/admin/img/edit.gif" alt="" /> Sửa</a> &nbsp;-&nbsp; 
									<a href="<%=request.getContextPath()%>/admin/cat/del?id=<%=itemc.getId()%>" onclick="return confirm('Bạn có thật sự muốn xóa?')"><img src="<%=request.getContextPath()%>/templates/admin/img/del.gif" alt="" /> Xóa</a></td>
							</tr>
							<%}}}}else{%>
							<tr> <td colspan="2">Chưa có dữ liệu!</td> </tr>
							<%}%>
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