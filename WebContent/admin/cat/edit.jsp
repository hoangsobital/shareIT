<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>

<%@include file="/templates/admin/inc/left_bar.jsp"%>
<script type="text/javascript">
	$(document).ready(function (){
		$('#editCat').validate({
			rules:{
				name: {
					required: true,
				}
			},
			messages:{
				name: {
					required: "Vui lòng nhập tên chuyên mục!",
				}
			}
		});
	});
</script>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb">
			<a href="<%=request.getContextPath()%>/admin/index.jsp" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
			<a href="<%=request.getContextPath()%>/admin/cats" class="tip-bottom">Danh mục</a> <a href="#" class="current">Sửa danh mục</a>
		</div>
		<h1>Danh Mục</h1>
	</div>
	<div class="container-fluid"><hr>
		<%
			ArrayList<Category> categories = (ArrayList<Category>)request.getAttribute("categories");
			Category cat = (Category)request.getAttribute("cat");
			String name = "";
			if(cat != null){
				name = cat.getName();
			}else{
				name= request.getParameter("name");
			}
       		if(request.getParameter("msg") != null){
       			int msg = Integer.parseInt(request.getParameter("msg"));
       			switch(msg){
       			case 0: out.print("<h4 style = 'color:red'>Sửa thất bại!</h4>"); break;
       			case 1: out.print("<h4 style = 'color:red'>Trùng tên chuyên mục!</h4>"); break;
       			}
       		}
		%>
		<div class="widget-box">
			<div class="container-fluid">
				<div class="col-lg-12 col-md-12">
					<div class="header">
						<h4 class="title">Sửa chuyên mục</h4>
					</div>
					<div class="content">
						<form action="" method="post" id="editCat">
							<div class="row">
									<label>Tên chuyên mục (*)</label>
									<input type="text" name="name" value="<%=name%>" class="input" placeholder="Nhập tên chuyên mục">
							</div>
							<div class="row">
								<label>Danh mục cha</label> 
								<select name="parent_id" class="input">
								<option value="0">--Không có--</option>
								<%
									String selected = "";
									if(categories != null && categories.size() > 0){
										for(Category item : categories){
											if(item.getId() == cat.getParent_id()){
												selected = "selected = 'selected'";
											}else{
												selected = "";
											}
											if(item.getParent_id() == 0){
								%>
									<option <%=selected%> value="<%=item.getId()%>"><%=item.getName()%></option>
								<%}}} %>
								</select>
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