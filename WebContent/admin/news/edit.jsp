<%@page import="model.bean.News"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>

<%@include file="/templates/admin/inc/left_bar.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#editNews').validate({
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
					required : "Vui lòng nhập tên chuyên mục!",
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
			<a href="#" class="current">Sửa bài viết</a>
		</div>
		<h1>Bài viết</h1>
	</div>
	<div class="container-fluid"> <hr>
<%
	ArrayList<Category> categories = (ArrayList<Category>)request.getAttribute("categories");
	News news = (News)request.getAttribute("news");
	String name = "";
	String preview = "";
	String detail = "";
	if(news != null){
		name = news.getName();
		preview = news.getPreview();
		detail = news.getDetail();
	}
	
	if (request.getParameter("msg") != null) {
		int msg = Integer.parseInt(request.getParameter("msg"));
		switch (msg) {
		case 0: out.print("<h4 style = 'color:red'>Sửa thất bại!</h4>"); break;
		case 1: out.print("<h4 style = 'color:#f87822'>Sửa thành công!</h4>"); break;
		}
	}
%>
		<div class="widget-box">
			<div class="container-fluid">
				<div class="col-lg-12 col-md-12">
					<div class="header">
						<h4 class="title">Sửa bài viết</h4>
					</div>
					<div class="content">
						<form action="" method="post" enctype="multipart/form-data" id="editNews">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label>Tên bài viết</label> 
										<input type="text" name="name" value="<%=name %>" class="form-input" placeholder="Nhập tên bài viết" > 
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group">
										<label>Danh mục</label> 
										<select name="category" class="form-input">
											<%
												String selected = "";
												if(categories != null && categories.size() > 0){
													for(Category item : categories){
														if(item.getId() == news.getCategory().getId()){
															selected = "selected = 'selected'";
														}else{
															selected = "";
														}
														if(item.getParent_id() == 0){
												
											%>
											<option <%=selected %> value="<%=item.getId()%>"><%=item.getName() %></option>
											<%}for(Category itemc : categories){
												if(itemc.getId() == news.getCategory().getId()){
													selected = "selected = 'selected'";
												}else{
													selected = "";
												}
												if(item.getId() == itemc.getParent_id()){
											%>
											<%=item.getId() %>
											<option <%=selected %> value="<%=itemc.getId()%>">|----<%=itemc.getName() %></option>
											<%}}}} %>
										</select>
									
									</div>
								</div>
								<div class="col-md-2">
									<label for="is_slide">Slide</label>
									<%	String checked = "";
										if(news.getIs_slide() == 1){ 
											checked = "checked = 'checked'";
										}
									%>
									<p>
										<input type="checkbox" id="is_slide" name="is_slide" <%=checked%> >Chọn
									</p>
									
								</div>
							</div>

							<div class="row1">
								<div class="col-md-12">
									<div class="form-group">
										<label>Hình ảnh</label> 
										<input type="file" name="picture" class="form-input" placeholder="Chọn ảnh" />
										<%
											if(!"".equals(news.getPicture())){
										%>
										<P><img style = "width: 277px;height:185px" src = "<%=request.getContextPath()%>/files/<%=news.getPicture()%>" alt = "<%=news.getPicture()%>"/></P>
										<%} %>
									</div>
								</div>
								<div class="col-md-12">
				                  <label class = "xoa">Xóa hình ảnh: </label>
				                  <input type="checkbox" id="active" name="delete_picture">Xóa
				                </div>
							</div>

							<div class="row1">
								<div class="col-md-12">
									<div class="form-group">
										<label>Mô tả</label>
										<textarea name="preview" rows="5" class="form-input textarea"
											placeholder="Mô tả bài viết"><%=preview%></textarea>
									</div>
								</div>
							</div>
							<div class="row1">
								<div class="col-md-12">
									<div class="form-group">
										<label>Chi tiết</label> <br>
										<textarea name="detail" rows="6" class="textarea ckeditor" placeholder="Nội dung bài viết"><%=detail%></textarea>
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