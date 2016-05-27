<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="com.plucial.mulcms.utils.*" %>
<%@ page import="com.plucial.mulcms.utils.*" %>
<%
Page targetPage =  (Page) request.getAttribute("targetPage");
String html =(String) request.getAttribute("pageHtml");

List<TextRes> appTextResList = (List<TextRes>) request.getAttribute("appTextResList");
List<TextRes> pageTextResList = (List<TextRes>) request.getAttribute("pageTextResList");
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
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1><%=targetPage.getKey().getName() %><small>designer</small></h1>
			</section>
			
			<section class="content">
				<div class="row">
					
					<div class="col-md-10">
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">App Text Resource</h3>
								<div class="box-tools pull-right">
									<a href="/mulcms/page/add" class="btn btn-primary btn-sm">Translate</a>
								</div><!-- /.box-tools -->
							</div><!-- /.box-header -->
	
							<div class=".box-body">
								<div class="table-responsive mailbox-messages">
									<table class="table table-hover table-striped">
										<tbody>
											<tr>
												<th>ID</th>
												<th>Css Query</th>
												<th>Rendering Type</th>
												<th>Value</th>
												<th>Delete</th>
											</tr>
											<%for(TextRes res: appTextResList) { %>
											<tr>
												<td class="mailbox-name"><%=res.getResId() %></td>
												<td><%=HtmlUtils.htmlEscape(res.getCssQuery()) %></td>
												<td><%=res.getRenderingType() %></td>
												<td class="mailbox-date" style="width:300px;">
													<form action="/mulcms/page/updateTextResEntry" method="post">
														<div class="input-group input-group-sm">
															<input type="text" name="content" class="form-control" value="<%=res.getContentString() %>">
															<input type="hidden" name="keyString" value="<%=res.getKey().getName() %>" />
															<input type="hidden" name="pageKey" value="<%=targetPage.getKey().getName() %>" />
															<span class="input-group-btn">
																<button class="btn btn-default btn-flat" type="submit">Update</button>
															</span>
														</div>
														
													</form>
												</td>
												<td style="width:60px"><a class="btn btn-danger btn-sm" href="/mulcms/page/deleteTextRes?keyString=<%=res.getKey().getName() %>&pageKey=<%=targetPage.getKey().getName() %>"><i class="fa fa-trash"></i></a></td>
											</tr>
											<%} %>

										</tbody>
									</table><!-- /.table -->
								</div><!-- /.mail-box-messages -->
		                	</div><!-- /.box-body -->
						</div><!-- /. box -->
						
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title"><%=targetPage.getKey().getName() %> Text Resource</h3>
								<div class="box-tools pull-right">
									<a href="/mulcms/page/add" class="btn btn-primary btn-sm">Translate</a>
								</div><!-- /.box-tools -->
							</div><!-- /.box-header -->
	
							<div class=".box-body">
								<div class="table-responsive mailbox-messages">
									<table class="table table-hover table-striped">
										<tbody>
											<tr>
												<th>ID</th>
												<th>Css Query</th>
												<th>Rendering Type</th>
												<th>Value</th>
												<th>Delete</th>
											</tr>
											<%for(TextRes res: pageTextResList) { %>
											<tr>
												<td class="mailbox-name"><%=res.getResId() %></td>
												<td><%=HtmlUtils.htmlEscape(res.getCssQuery()) %></td>
												<td><%=res.getRenderingType() %></td>
												<td class="mailbox-date" style="width:300px;">
													<form action="/mulcms/page/updateTextResEntry" method="post">
														<div class="input-group input-group-sm">
															<input type="text" name="content" class="form-control" value="<%=res.getContentString() %>">
															<input type="hidden" name="keyString" value="<%=res.getKey().getName() %>" />
															<input type="hidden" name="pageKey" value="<%=targetPage.getKey().getName() %>" />
															<span class="input-group-btn">
																<button class="btn btn-default btn-flat" type="submit">Update</button>
															</span>
														</div>
														
													</form>
												</td>
												<td style="width:60px"><a class="btn btn-danger btn-sm" href="/mulcms/page/deleteTextRes?keyString=<%=res.getKey().getName() %>&pageKey=<%=targetPage.getKey().getName() %>"><i class="fa fa-trash"></i></a></td>
											</tr>
											<%} %>

										</tbody>
									</table><!-- /.table -->
								</div><!-- /.mail-box-messages -->
		                	</div><!-- /.box-body -->
						</div><!-- /. box -->
					</div>
					
					<div class="col-md-2">
						<a href="/mulcms/page/templateReread?keyString=<%=targetPage.getKey().getName() %>" class="btn btn-primary btn-block margin-bottom">Translate all</a>
						<a href="/mulcms/page/templateReread?keyString=<%=targetPage.getKey().getName() %>" class="btn btn-warning btn-block margin-bottom">Template re-read</a>
						<a class="btn btn-danger btn-block margin-bottom" href="/mulcms/page/delete?keyString=<%=targetPage.getKey().getName() %>">Delete</a>
					</div>
					
				</div>
			</section>


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
