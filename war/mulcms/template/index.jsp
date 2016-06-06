<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.App" %>
<%@ page import="com.plucial.mulcms.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
List<PageTemplate> templateList = (List<PageTemplate>) request.getAttribute("templateList");

SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy/MM/dd/ HH:mm");
dateSdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
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
			<jsp:param name="contentsType" value="pageTemplate" />
		</jsp:include>
      

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

	        <!-- Main content -->
			<section class="content">
				<h2 class="page-header"><i class="fa fa-object-group"></i> テンプレート管理</h2>
				
				<div class="row">
					<div>
						<div class="col-md-2">
							<a href="/mulcms/template/page/add" class="btn btn-primary btn-block margin-bottom"><i class="fa fa-plus"></i> 追加</a>
						</div>
					</div>
	            
					<div class="col-md-10">
						<div class="row">
							
							<%for(Template temp: templateList) { %>
							<div class="col-md-4">
								<div class="info-box">
									<div class="box-tools pull-right">
										<a class="btn btn-box-tool" href="/mulcms/template/delete?keyString=<%=temp.getKey().getName() %>"><i class="fa fa-times"></i></a>
									</div>
									<span class="info-box-icon bg-teal disabled color-palette"><i class="fa fa-object-group"></i></span>
									<div class="info-box-content">
										<span class="info-box-text"><%=temp.getLang().getName() %></span>
										<span class="info-box-number"><a href="/mulcms/template/page/edit?keyString=<%=temp.getKey().getName() %>"><%=temp.getName() %></a></span>
									</div><!-- /.info-box-content -->
								</div>
							</div>
							<%} %>
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
