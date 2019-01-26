<%@page import="model.bean.Advertise"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>

<%@include file="/templates/admin/inc/left_bar.jsp"%>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb">
			<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> 
			<a href="<%=request.getContextPath()%>/admin/advs" class="current">Quảng cáo</a>
		</div>
		<h3>Quảng cáo</h3>
	</div>
	<div class="container-fluid">
		<hr>
		<div class="row-fluid">
			<div class="span12">
				<div>
					<a href="<%=request.getContextPath()%>/admin/adv/add" class="button"> 
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
								<th style="width: 10%;">ID</th>
								<th>Tên quảng cáo</th>
								<th style="width: 16%;">Hình ảnh</th>
								<th style="width: 30%;">Link</th>
								<th style="width: 15%;">Chức năng</th>
							</tr>
						</thead>
						<tbody>
						<%
							ArrayList<Advertise> advertise = (ArrayList<Advertise>)request.getAttribute("advertise");
							if(advertise != null && advertise.size() > 0){
								for(Advertise item : advertise){
						%>
							<tr class="gradeX">
								<td><%=item.getId()%></td>
								<td><%=item.getName()%></td>
								<td style="text-align: center;">
									<img src="<%=request.getContextPath()%>/files-adv/<%=item.getPicture()%>" alt="<%=item.getPicture()%>" width="120px" height="70px"/>
								</td>
								<td><a href="<%=item.getLink()%>" target="_blank"><%=item.getLink()%></a></td>
								<td style="text-align: center;">
									<a href="<%=request.getContextPath()%>/admin/adv/edit?id=<%=item.getId()%>"><img src="<%=request.getContextPath()%>/templates/admin/img/edit.gif" alt="" /> Sửa</a> &nbsp;-&nbsp; 
									<a href="<%=request.getContextPath()%>/admin/adv/del?id=<%=item.getId()%>" onclick="return confirm('Bạn có thật sự muốn xóa?')"><img src="<%=request.getContextPath()%>/templates/admin/img/del.gif" alt="" /> Xóa</a></td>
							</tr>
						<%}} %>
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