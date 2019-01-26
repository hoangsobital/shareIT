<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>

<%@include file="/templates/admin/inc/left_bar.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#addNews').validate({
			ignore:[],
			rules : {
				name : {
					required : true,
				},
				preview:{
					required : true,
				},
				detail:{
					required : true,
				}
			},
			messages : {
				name : {
					required : "Vui lòng nhập tên bài viết!",
				},
				preview:{
					required : "Vui lòng nhập mô tả!",
				},
				detail:{
					required : "Vui lòng nhập chi tiết!",
				}
			}
		});
	});
</script>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb">
			<a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> 
			<a href="<%=request.getContextPath()%>/admin/cats" class="tip-bottom">Bài viết</a> 
			<a href="#" class="current">Thêm bài viết</a>
		</div>
		<h1>Bài Viết</h1>
	</div>
	<div class="container-fluid"> <hr>
<%
	ArrayList<Category> categories = (ArrayList<Category>)request.getAttribute("categories");

	if (request.getParameter("msg") != null) {
		int msg = Integer.parseInt(request.getParameter("msg"));
		switch (msg) {
		case 0: out.print("<h4 style = 'color:red'>Thêm thất bại!</h4>"); break;
		case 1: out.print("<h4 style = 'color:#f87822'>Thêm thành công!</h4>"); break;
		}
	}
	String name = request.getParameter("name");
	String preview = request.getParameter("preview");
	String detail = request.getParameter("detail");
	String is_slide = request.getParameter("is_slide");
	String category = request.getParameter("category");
	String checked = "";
	String selected = "";
	int cat = 0;
	if(name == null || preview == null || detail == null){
		name ="";
		preview = "";
		detail = "";
		
	}
	if(category != null){
		cat = Integer.parseInt(category);
	}
	
%>
		<div class="widget-box">
			<div class="container-fluid">
				<div class="col-lg-12 col-md-12">
					<div class="header">
						<h4 class="title">Thêm bài viết</h4>
					</div>
					<div class="content">
						<form action="" method="post" enctype="multipart/form-data" id="addNews">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Tên bài viết</label> 
										<input type="text" name="name" value="<%=name%>" class="form-input" placeholder="Nhập tên bài viết" > 
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Danh mục</label> 
										<select name="category" class="form-input">
											<option value="0">-- Không chuyên mục --</option>
											<%
												if(categories != null && categories.size() > 0){
													for(Category item : categories){
														if(item.getId() == cat){
															selected = "selected = 'selected'";
														}else{
															selected = "";
														}
														if(item.getParent_id() == 0){
											%>
											<option <%=selected %> value="<%=item.getId()%>"><%=item.getName()%></option>
											<%for(Category itemc : categories){
												/* if(itemc.getId() == cat){
													selected = "selected = 'selected'";
												}else{
													selected = "";
												} */
												if(item.getId() == itemc.getParent_id()){
											%>
											<option <%=selected %> value="<%=itemc.getId()%>">|----<%=itemc.getName()%></option>
											<%}}}}} %>
										</select>
									</div>
								</div>
								<div class="col-md-2">
									<label for="is_slide">Slide</label>
									<%
										if(is_slide != null){
											checked = "checked = 'checked'";
										}else{
											checked = "";
										}
									%>
									<p>
										<input <%=checked %> type="checkbox" id="is_slide" name="is_slide" >Chọn
									</p>
								</div>
							</div>

							<div class="row1">
								<div class="col-md-12">
									<div class="form-group">
										<label>Hình ảnh</label> 
										<input type="file" name="picture" class="form-input" placeholder="Chọn ảnh" />
									</div>
								</div>
							</div>

							<div class="row1">
								<div class="col-md-12">
									<div class="form-group">
										<label>Mô tả</label>
										<textarea name="preview" rows="5" class="form-input textarea"
											placeholder="Mô tả bài viết"><%=preview %></textarea>
									</div>
								</div>
							</div>
							<div class="row1">
								<div class="col-md-12">
									<div class="form-group">
										<label>Chi tiết</label> <br>
										<textarea name="detail" rows="6" class="textarea ckeditor" placeholder="Nội dung bài viết"><%=detail %></textarea>
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

<%@include file="/templates/admin/inc/footer.jsp"%>