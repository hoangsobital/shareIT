<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>

<%@include file="/templates/admin/inc/left_bar.jsp"%>

<div id="content">
	<div id="content-header">
		<div id="breadcrumb">
			<a href="index.html" title="Go to Home" class="tip-bottom"><i
				class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Danh
				mục</a> <a href="#" class="current">Thêm danh mục</a>
		</div>
		<h1>Danh Mục</h1>
	</div>
	<div class="container-fluid"><hr>
		<div class="row-fluid">
			<div class="widget-box">
				<div class="container-fluid">
					<div class="col-lg-12 col-md-12">
						<div class="header">
							<h4 class="title">Thêm chuyên mục</h4>
						</div>
						<div class="content">
							<form action="" method="post" id="addCat">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label>Tên chuyên mục (*)</label>
											<input type="text" name="name" value="" class="input" placeholder="Nhập tên chuyên mục">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label>Danh mục cha</label> <select name="parent_id" class="input">
												<option value="0">--Không có--</option>
												<option value="29">Tin tức</option>
												<option value="30">Khoa học Công nghệ</option>
												<option value="37">Thủ thuật</option>
											</select>
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
</div>

<%@include file="/templates/admin/inc/footer.jsp"%>