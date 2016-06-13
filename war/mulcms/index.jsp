<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.App" %>
<%@ page import="com.plucial.mulcms.model.*" %>
<%@ page import="com.plucial.mulcms.model.assets.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.plucial.global.Lang" %>
<%
List<Page> pageList = (List<Page>) request.getAttribute("pageList");
%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/mulcms/includes/html_head.jsp" />
	<style>
	.box-header {
		padding-bottom: 0;
	}
	.box-body {
		padding-top: 0;
	}
	.page-info {
		padding-top: 10px;
	}
	</style>
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
					<div class="col-md-offset-1 col-md-10">
						<h2 class="page-header"><i class="fa fa-sitemap"></i> Pages</h2>
						
						<div class="row">
							<%for(Page pageObj: pageList) { %>
							<div class="col-md-4">
								<div class="box">
									<div class="box-header">
										<h2 class="box-title"><i class="fa fa-file-o"></i> <%=pageObj.getKey().getName() %></h2>
										<div class="box-tools pull-right">
											<a class="btn btn-box-tool" href="/mulcms/page/delete?keyString=<%=pageObj.getKey().getName() %>"><i class="fa fa-times"></i></a>
										</div>
										<div class="page-info">
											<span class="label label-primary">Language (<%=pageObj.getLangList().size() %> / <%=Lang.values().length %>)</span>
											<a class="btn btn-box-tool" target="view" href="/<%=pageObj.getHtmlLang().toString() %><%=pageObj.getKey().getName() %>"><i class="fa fa-external-link"></i> <%=pageObj.getHtmlLang().getName() %></a>
											<div class="progress progress-xxs">
												<div class="progress-bar progress-bar-primary" style="width: <%=((float)pageObj.getLangList().size() / Lang.values().length) * 100 %>%"></div>
											</div>
										</div>
									</div>
									<div class="box-body text-center">
										
										<a class="btn btn-app" href="/mulcms/page/edit?keyString=<%=pageObj.getKey().getName() %>">
											<i class="fa fa-html5"></i> Html
										</a>
			
										<a class="btn btn-app" href="/mulcms/page/resource?keyString=<%=pageObj.getKey().getName() %>&lang=<%=pageObj.getHtmlLang() %>">
											<i class="fa fa-language"></i> Resource
										</a>
										<a class="btn btn-app" href="/mulcms/form/?keyString=<%=pageObj.getKey().getName() %>">
											<i class="fa fa-users"></i> Form
										</a>
									</div><!-- /.box-body -->
								</div>
							</div>
							<%} %>
							
						</div><!-- /.row -->	
					</div>
				</div>
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
