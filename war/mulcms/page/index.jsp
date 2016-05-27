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
List<Page> pageList = (List<Page>) request.getAttribute("pageList");

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
			<jsp:param name="contentsType" value="page" />
		</jsp:include>
      

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
	
	        <!-- Main content -->
			<section class="content">
				<div class="row">
            
            		<div class="col-md-8 col-md-offset-2">
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">Page List</h3>
								<div class="box-tools pull-right">
									<a href="/mulcms/page/add" class="btn btn-primary btn-sm"><i class="fa fa-plus"></i></a>
								</div><!-- /.box-tools -->
							</div><!-- /.box-header -->
	
							<div class=".box-body">
								<div class="table-responsive mailbox-messages">
									<table class="table table-hover table-striped">
										<tbody>
											<tr>
												<th>Path</th>
												<th>Template</th>
												<th>Create Date</th>
												<th>Update Date</th>
												<th>View</th>
											</tr>
											<%for(Page pageObj: pageList) { %>
											<tr>
												<td class="mailbox-name"><a href="/mulcms/page/view?keyString=<%=pageObj.getKey().getName() %>"><%=pageObj.getKey().getName() %></a></td>
												<td>
													<%Template template = pageObj.getTemplateRef().getModel();%>
													<a href="/mulcms/template/page/edit?keyString=<%=template.getKey().getName() %>"><%=template.getName()%></a>
												</td>
												<td class="mailbox-date"><%=dateSdf.format(pageObj.getCreateDate()) %></td>
												<td class="mailbox-date"><%=dateSdf.format(pageObj.getUpdateDate()) %></td>
												<td>
													<a class="btn btn-default btn-xs" target="view" href="/ja<%=pageObj.getKey().getName() %>"><i class="fa fa-external-link"></i></a>
												</td>
											</tr>
											<%} %>

										</tbody>
									</table><!-- /.table -->
								</div><!-- /.mail-box-messages -->
		                	</div><!-- /.box-body -->
	
						</div><!-- /. box -->
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
