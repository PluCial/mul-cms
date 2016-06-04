<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.App" %>
<%@ page import="com.plucial.mulcms.model.*" %>
<%@ page import="com.plucial.mulcms.model.res.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
Res res = (Res) request.getAttribute("res");
String pageKey = (String) request.getAttribute("pageKey");
String lang = (String) request.getAttribute("lang");
%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/mulcms/includes/html_head.jsp" />
</head>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- site-header -->
		<jsp:include page="/mulcms/includes/site_header.jsp" />
		<!-- /site-header -->
      
		<!-- Left side column. contains the logo and sidebar -->
		<jsp:include page="/mulcms/includes/main_sidebar.jsp">
			<jsp:param name="contentsType" value="page" />
		</jsp:include>
      

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
	
	        <!-- Main content -->
			<section class="content">
				<div class="row">
            
            		<div class="col-md-6 col-md-offset-3">
						<div class="box box-danger box-solid">
			                <div class="box-header with-border">
			                  <h3 class="box-title">Delete</h3>
			                </div><!-- /.box-header -->
			                <div class="box-body">
			                  <b>Are you sure you want to delete this resource?</b><br /><br />
			                  <%=res.getValueString() %>
			                </div><!-- /.box-body -->
			                <div class="box-footer">
			                  <form action="/mulcms/page/deleteResEntry" method="post">
			                  	<input type="hidden" name="keyString" value="<%=res.getKey().getName() %>">
			                  	<input type="hidden" name="pageKey" value="<%=pageKey %>">
			                  	<input type="hidden" name="lang" value="<%=lang %>">
			                  	<a href="/mulcms/page/setting?keyString=<%=pageKey %>&lang=<%=lang %>" class="btn btn-default pull-left"><i class="fa fa-reply"></i></a>
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