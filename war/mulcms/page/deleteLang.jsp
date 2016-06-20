<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.App" %>
<%@ page import="com.plucial.mulcms.model.assets.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.plucial.global.Lang" %>
<%@ page import="java.util.Properties" %>
<%
Page pageObj = (Page) request.getAttribute("page");
Lang lang = Lang.valueOf((String) request.getAttribute("lang"));

Properties userProp = (Properties) request.getAttribute("userProp");
%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/mulcms/includes/html_head.jsp" />
</head>
<body class="skin-blue layout-top-nav">
	<div class="wrapper">
		<!-- site-header -->
		<jsp:include page="/mulcms/includes/site_header.jsp" />
		<!-- /site-header -->
      

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
	
	        <!-- Main content -->
			<section class="content">
				<div class="row">
            
            		<div class="col-md-6 col-md-offset-3">
						<div class="box box-danger box-solid">
			                <div class="box-header with-border">
			                  <h3 class="box-title">削除</h3>
			                </div><!-- /.box-header -->
			                <div class="box-body">
			                  <b>[<%=pageObj.getKey().getName() %>]</b> の <b><%=userProp.getProperty("lang." + lang.toString()) %></b> を削除してよろしいですか？
			                </div><!-- /.box-body -->
			                <div class="box-footer">
			                  <form action="/mulcms/page/deleteLangEntry" method="post">
			                  	<input type="hidden" name="keyString" value="<%=pageObj.getKey().getName() %>">
			                  	<input type="hidden" name="lang" value="<%=lang.toString() %>">
			                  	<a href="/mulcms/page/resource?keyString=<%=pageObj.getKey().getName() %>&lang=<%=lang.toString() %>" class="btn btn-default pull-left"><i class="fa fa-reply"></i></a>
			                    <button type="submit" class="btn btn-danger btn-flat pull-right">Delete</button>
			                  </form>
			                </div>
			              </div>
					</div><!-- /.col -->
					
				</div><!-- /.row -->
			</section><!-- /.content -->
			<!-- /.content -->
		</div><!-- /.content-wrapper -->
      
		<!-- Control Sidebar -->
		<jsp:include page="/mulcms/includes/control_sidebar.jsp" />   
		<!-- /.control-sidebar -->
		
		<!-- page footer -->
	    <jsp:include page="/mulcms/includes/site_footer.jsp" />
		<!-- /page footer -->
	      
		<!-- Add the sidebar's background. This div must be placed
	           immediately after the control sidebar -->
		<div class='control-sidebar-bg'></div>
	    </div><!-- ./wrapper -->
	
	    
	    <!-- page script -->
	    <jsp:include page="/mulcms/includes/html_script.jsp" />
	    <!-- page script -->

  </body>
</html>
