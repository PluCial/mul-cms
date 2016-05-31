<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.App" %>
<%@ page import="com.plucial.mulcms.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%
Errors errors =(Errors) request.getAttribute("errors");

List<Page> pageList = (List<Page>) request.getAttribute("pageList");
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
			<jsp:param name="contentsType" value="page" />
		</jsp:include>
      

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
	
	        <!-- Main content -->
			<section class="content">
				<h2 class="page-header">ページ管理</h2>
				
				<div class="row">
					<div class="col-md-3">
						<%if (!errors.isEmpty()){ %>
					<!-- alert -->
					<div class="alert alert-warning alert-dismissable">
					<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
					<h4><i class="icon fa fa-warning"></i> Alert!</h4>
					<%=errors.get(0) %>
					</div>
					<!-- /alert -->
					<%} %>
					
						<div class="box box-primary box-solid">
							<div class="box-header with-border">
								<h3 class="box-title">ページの追加</h3>
							</div>
							<form action="/mulcms/page/addEntry" method="post">
								<div class="box-body">
									<div class="form-group">
										<label for="exampleInputEmail1">Path(URL)</label>
										<input ${f:text("url")} class="form-control" id="exampleInputEmail1" placeholder="/aaaa/xxx.html">
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1">Template</label>
										<select name="template" class="form-control">
											<option value="">-- Select Template --</option>
											<%for(Template temp: templateList) { %>
											<option value="<%=temp.getKey().getName() %>"><%=temp.getName() %></option>
											<%} %>
										</select>
									</div>
								</div><!-- /.box-body -->
								<div class="box-footer text-right">
									<button type="submit" class="btn btn-primary">追加</button>
								</div>
							</form>
						</div>
					</div>
            
            		<div class="col-md-8">
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">ページ一覧</h3>
							</div><!-- /.box-header -->
	
							<div class=".box-body">
								<div class="table-responsive mailbox-messages">
									<table class="table table-hover table-striped">
										<tbody>
											<tr>
												<th>Path</th>
												<th>Template</th>
												<th>Language</th>
												<th>Create Date</th>
												<th>Update Date</th>
											</tr>
											<%for(Page pageObj: pageList) { 
												Template template = pageObj.getTemplateRef().getModel();
											%>
											<tr>
												<td class="mailbox-name"><a href="/mulcms/page/setting?keyString=<%=pageObj.getKey().getName() %>&lang=<%=template.getLang().toString() %>"><%=pageObj.getKey().getName() %></a></td>
												<td>
													<a href="/mulcms/template/page/edit?keyString=<%=template.getKey().getName() %>"><%=template.getName()%></a>
												</td>
												<td><%=pageObj.getLangList().size() %></td>
												<td class="mailbox-date"><%=dateSdf.format(pageObj.getCreateDate()) %></td>
												<td class="mailbox-date"><%=dateSdf.format(pageObj.getUpdateDate()) %></td>
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
