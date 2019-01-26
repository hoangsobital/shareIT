<%@page import="model.bean.Advertise"%>
<%@page import="model.bean.UserRank"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>

<%@include file="/templates/admin/inc/left_bar.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#addAdvs').validate({
			ignore:[],
			rules : {
				name : {
					required : true,
				},
				link:{
					required : true,
					url : true
				}
			},
			messages : {
				name : {
					required : "Vui lòng nhập tên quảng cáo!",
				},
				link:{
					required : "Vui lòng nhập link!",
					url : "Đường link không đúng định dạng!"
				}
			}
		});
	});
</script>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb">
			<a href="index.html" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> Home</a> 
			<a href="<%=request.getContextPath()%>/admin/advs" class="tip-bottom">Quảng cáo</a> 
			<a href="#" class="current">Sửa quảng cáo</a>
		</div>
		<h1>Quảng cáo</h1>
	</div>
	<div class="container-fluid"><hr>
	<%
		Advertise adv = (Advertise)request.getAttribute("advertise");
		String name = "";
		String link = "";
		if(adv != null){
			name = adv.getName();
			link = adv.getLink();
		}
	
		if (request.getParameter("msg") != null) {
			int msg = Integer.parseInt(request.getParameter("msg"));
			switch (msg) {
			case 0: out.print("<h4 style = 'color:red'>Sửa thất bại!</h4>"); break;
			}
		}
		/* String name = request.getParameter("name");
		String link = request.getParameter("link");
		if(name == null || link == null){
			name = "";
			link = "";
		} */
	%>
		<div class="row-fluid">
			<div class="widget-box">
				<div class="container-fluid">
					<div class="col-lg-12 col-md-12">
					<div class="header">
		        <h4 class="title">Sửa quảng cáo</h4>
		    </div>
		    <div class="content">
		                <form role="form" action="" method="post" enctype="multipart/form-data" id="addAdvs">
		            <div class="row">
		                <div class="col-md-12">
		                    <div class="form-group">
		                        <label>Tên quảng cáo</label>
		                        <input style="width:1030px" type="text" name="name" placeholder="Nhập tên quảng cáo" value="<%=name%>">
		                    </div>
		                </div>
		            </div>
		
		            <div class="row1">
		                <div class="col-md-12">
		                    <div class="form-group">
		                        <label>Link</label>
		                        <input style="width:1030px" type="text" name="link" placeholder="http://..." value="<%=link%>">
		                    </div>
		                </div>
		            </div>
		            
		             <div class="row">
		                <div class="col-md6">
		                    <div class="form-group">
		                        <label>Hình ảnh</label>
		                        <input type="file" name="picture" class="form-control" placeholder="Chọn ảnh" />
		                        <%
		                        	if(adv.getPicture() != null){
		                        %>
		                        <p><img style = "width: 277px;height:185px" src="<%=request.getContextPath()%>/files-adv/<%=adv.getPicture()%>" alt="<%=adv.getPicture()%>"></p>
		                        <%} %>
		                    </div>
		                </div>
		                <div class="col-md6">
		                  <label for="active">Xóa hình ảnh</label>
		                  <p><input type="checkbox" id="active" name="delete_picture">Xóa</p>
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
</div>

<%@include file="/templates/admin/inc/footer.jsp"%>