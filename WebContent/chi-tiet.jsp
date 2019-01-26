<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/templates/public/inc/header.jsp" %>
<div class="wrapper row3">
  <div id="container">
    <!-- ################################################################################################ -->
	<div id="homepage" class="clear">
    <div id="portfolio" class="three_quarter first">
      <ul class="clear">
        <li class="one_half1 first">
          <article class="clear">
            <figure class="post-image"><img src="<%=request.getContextPath()%>/templates/public/images/demo/1200x400.gif" alt=""></figure>
            <header>
              <h2 class="blog-post-title"><a href="#">Pellentesque Tempor Tellus</a></h2>
              <div class="blog-post-meta">
                <ul>
                  <li class="blog-post-date">
                    <time datetime="2000-04-06T08:15+00:00"><strong>Completed:</strong> 6<sup>th</sup> April 2000</time>
                  </li>
                  <li class="blog-post-cats"><a href="#">Category 1</a></li>
                </ul>
              </div>
            </header>
            <p>Portortornec condimenterdum eget consectetuer condis consequam pretium pellus sed mauris enim. Puruselit mauris nulla hendimentesque elit semper nam a sapien urna sempus. Aliquatjusto quisque nam consequat doloreet vest orna partur scetur portortis nam. Metadipiscing eget facilis elit sagittis felisi eger id justo maurisus convallicitur.</p>
            
          </article>
        </li>
        
      </ul>
      
	  	<!-- ################################################################################################ -->
				<div class="respon">
		  <h2 class = "thaoluan">Thảo luận</h2>
		  
							<div class="strator">
					<h5>Animi sapiente vel id facere</h5>
					<p>Lúc 01-01-1970</p>
					<div class="strator-left">
						<img src="images/co.png" class="img-responsive" alt="">
					</div>
					<div class="strator-right">
						<p class="sin">Quaerat dignissimos impedit, irure voluptatem suscipit eum modi proident, enim.</p>
					</div>
					<div class="clearfix"></div>
					<div class="rep">
						<a href="javascript:void(0)" class="reply" onclick="openform(86)">Trả lời</a>
					</div>
				  </div>
				  <div id="open-form-86"   style="display:none" >
					<div class="comment">
					  <div class="form-reply">
						<form action="javascript:void(0)" class="contact_form form-86" method="post" novalidate="novalidate">
						  <input type="hidden" name="_token" value="nKa7gutnv9RRp2HvunYltKyZasm7eplagXkKOOAB">
						  <input type="text" name="hotenr" class="textbox" placeholder="Nhập họ tên">
						  <input type="text" name="emailr" class="textbox" placeholder="Nhập email">
						  <input type="text" name="websiter" class="textbox" placeholder="Nhập website">
						  <textarea name="contentr" placeholder="Nội dung bình luận"></textarea>
						  <div class="form-group">
							<div class="row">
							  <div class="col-md-12 thong-bao-86">
							  </div>
							</div>
						  </div>
						  <div class="form-group">
							<div class="row">
							  <div class="col-md-6">
								<input type="submit" value="Phản hồi" onclick="reply(86)">
								<input type="reset" value="Đóng" onclick="closeform(86)">
								<a class="loading" style="display:none">
								  <img src="images/loading.gif" width="40px">
								</a>
							  </div>
							</div>
						  </div>
						</form>
					  </div>
					</div>
				  </div>
											  
		  <div class="comment">
			  <h2>Viết bình luận</h2>
			  <form action="javascript:void(0)" class="contact_form" method="post" id="comment">
				  <input type="hidden" name="_token" value="nKa7gutnv9RRp2HvunYltKyZasm7eplagXkKOOAB">
				  <input type="text" name="hoten" class="textbox" placeholder="Nhập họ tên">
				  <input type="text" name="email" class="textbox" placeholder="Nhập email">
				  <input type="text" name="website" class="textbox" placeholder="Nhập website">
				  <textarea name="content" placeholder="Nội dung bình luận"></textarea>

				  <div class="thong-bao"></div>
				  <div class="smt1">
					  <input type="submit" value="Gửi">
					  <a id='loadingmessage' style='display:none'>
						<img src='/resources/assets/templates/public/images/loading.gif' width="40px">
					  </a>
				  </div>
			  </form>
		  </div>
</div>
		<!-- #######################################################################################################
			  <nav class="pagination">
				<ul>
				  <li class="prev"><a href="#">&laquo; Previous</a></li>
				  <li><a href="#">1</a></li>
				  <li><a href="#">2</a></li>
				  <li class="splitter"><strong>&hellip;</strong></li>
				  <li><a href="#">6</a></li>
				  <li class="current"><strong>7</strong></li>
				  <li><a href="#">8</a></li>
				  <li class="splitter"><strong>&hellip;</strong></li>
				  <li><a href="#">14</a></li>
				  <li><a href="#">15</a></li>
				  <li class="next"><a href="#">Next &raquo;</a></li>
				</ul>
			  </nav> -->
    </div>
    <!-- ################################################################################################ -->
	
 	<!-- Right_bar -->
     <%@ include file = "/templates/public/inc/right_bar.jsp" %>
	  <!-- End Right_bar -->
	</div>
    <!-- ################################################################################################ -->
    <div class="clear"></div>
  </div>
</div>
<%@ include file = "/templates/public/inc/footer.jsp" %>