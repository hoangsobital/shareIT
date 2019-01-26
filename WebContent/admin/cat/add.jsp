<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>

<%@include file="/templates/admin/inc/left_bar.jsp"%>
<script type="text/javascript">
	$(document).ready(function (){
		$('#addCat').validate({
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
			<a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a>
			<a href="<%=request.getContextPath()%>/admin/cats" class="tip-bottom">Danh mục</a> <a href="#" class="current">Thêm danh mục</a>
		</div>
		<h3>Danh Mục</h3>
	</div>
	<div class="container-fluid"><hr>
		<%
			ArrayList<Category> categories = (ArrayList<Category>)request.getAttribute("categories");
       		if(request.getParameter("msg") != null){
       			int msg = Integer.parseInt(request.getParameter("msg"));
       			switch(msg){
       			case 0: out.print("<h3 style = 'color:#ffb82c'>Thêm thất bại!</h3>"); break;
       			case 1: out.print("<h3 style = 'color:#ffb82c'>Trùng tên chuyên mục!</h3>"); break;
       			}
       		}
			
			String name = request.getParameter("name");
			String parent_id = request.getParameter("parent_id");
			int parent = 0;
			if(name == null){
				name = "";
			}
			if(parent_id != null){
				parent = Integer.parseInt(parent_id);
			}
		%>
		<div class="widget-box">
			<div class="container-fluid">
				<div class="col-lg-12 col-md-12">
					<div class="header">
						<h4>Thêm chuyên mục</h4>
					</div>
					<div class="content">
						<form action="" method="post" id="addCat">
							<div class="row">
									<label>Tên chuyên mục (*)</label>
									<input type="text" name="name" value="<%=name %>" class="input" placeholder="Nhập tên chuyên mục">
							</div>
							<div class="row">
								<label>Danh mục cha</label> 
								<select name="parent_id" class="input">
								<option value="0">--Không có--</option>
								<%
									String selected = "";
									if(categories != null && categories.size() > 0){
										for(Category item : categories){
											if(item.getParent_id() == 0){
												if(item.getId() == parent){
													selected = "selected = 'selected'";
												}else{
													selected = "";
												}
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