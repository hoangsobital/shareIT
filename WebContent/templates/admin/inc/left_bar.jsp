<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--sidebar-menu-->
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> Dashboard</a>
  <ul>
    <li class = ""><a href="<%=request.getContextPath()%>/admin/cats"><i class="icon icon-th"></i> <span>Danh mục</span></a></li>
    <li class = ""><a href="<%=request.getContextPath()%>/admin/news"><i class="icon-list-alt"></i> <span>Bài viết</span></a></li>
    <li class = ""><a href="binhluan.html"><i class="icon-comments"></i> <span>Bình luận</span></a></li>
    <li class = ""><a href="<%=request.getContextPath()%>/admin/users"><i class="icon-user-md"></i> <span>Quản trị viên</span></a></li>
    <li class = ""><a href="<%=request.getContextPath()%>/admin/advs"><i class="icon-credit-card"></i> <span>Quảng cáo</span></a></li>
    
  </ul>
</div>
<script>
 $(document).ready(function () {
		$('a[href="' + this.location.pathname + '"]').parent().addClass('active');
	});
 </script>
<!--sidebar-menu-->