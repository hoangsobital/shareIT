<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>

<%@include file="/templates/admin/inc/left_bar.jsp"%>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb">
			<a href="#" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="<%=request.getContextPath()%>/admin/news" class="current">Bài viết</a>
		</div>
		<h3>Bài viết</h3>
	</div>
	<div class="container-fluid"> <hr>
		<div class="row-fluid">
			<div class="span12">
				<div class="">
					<a href="<%=request.getContextPath()%>/admin/user/add" class="button"> 
					<span class="btn-success" style="padding: 3px 7px; border-radius: 3px;">Thêm <i class="icon-plus"></i></span>
					</a>
				</div>
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"><i class="icon-th"></i></span>
					</div>
					<table class="table table-bordered data-table">
						<thead>
							<tr>
								<th>Tên bài viết</th>
								<th style="width: 15%;">Hình ảnh</th>
								<th style="width: 13%;">Ngày tạo</th>
								<th style="width: 13%;">Danh mục</th>
								<th style="width: 13%;">Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<tr class="gradeX">
								<td>Trident</td>
								<td>Trident</td>
								<td>Trident</td>
								<td>Trident</td>
								<td style="text-align: center;">
									<a href=""><img src="<%=request.getContextPath()%>/templates/admin/img/edit.gif" alt="" /> Sửa</a> &nbsp;-&nbsp; 
									<a href="" onclick="return confirm('Bạn có thật sự muốn xóa?')"><img src="<%=request.getContextPath()%>/templates/admin/img/del.gif" alt="" /> Xóa</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="/templates/admin/inc/footer.jsp"%>