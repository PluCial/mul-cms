<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.model.*" %>
<%@ page import="com.plucial.mulcms.model.res.*" %>
<%@ page import="java.util.List" %>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="com.plucial.mulcms.utils.*" %>
<%@ page import="com.plucial.global.Lang" %>
<%
Page targetPage =  (Page) request.getAttribute("targetPage");
String html =(String) request.getAttribute("pageHtml");

List<Lang> supportLangList = targetPage.getLangList();
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
				<h1><a href="/mulcms/page/langs?keyString=<%=targetPage.getKey().getName() %>" style="color: #333;"><%=targetPage.getKey().getName() %></a><small>Language(<%=supportLangList.size() %>)</small></h1>
			</section>
			
			<section class="content">
				<div class="row">
					
					<div class="col-md-7 col-md-offset-1">
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">Lang List</h3>
							</div><!-- /.box-header -->
	
							<div class=".box-body">
								<div class="table-responsive mailbox-messages">
									<table class="table table-hover table-striped">
										<tbody>
											<%for(Lang lang: supportLangList) { %>
											<tr>
												<td>
													<a href="/mulcms/page/view?keyString=<%=targetPage.getKey().getName() %>&lang=<%=lang.toString() %>"><%=lang.getName() %></a>
												</td>
												<td style="width:60px"><a class="btn btn-default btn-xs" target="view" href="/<%=lang.toString() %><%=targetPage.getKey().getName() %>"><i class="fa fa-external-link"></i></a></td>
												<td style="width:60px"><a class="btn btn-danger btn-xs" href="/mulcms/page/deletePageLang?keyString=<%=targetPage.getKey().getName() %>&lang=<%=lang.toString() %>"><i class="fa fa-trash"></i></a></td>
											</tr>
											<%} %>

										</tbody>
									</table><!-- /.table -->
								</div><!-- /.mail-box-messages -->
		                	</div><!-- /.box-body -->
						</div><!-- /. box -->
						
					</div>
					
					
					<div class="col-md-3">
						
						<%if(supportLangList.size() > 0 && supportLangList.size() < Lang.values().length) { %>
						<div class="box box-solid">
							<div class="box-header with-border">
								<h3 class="box-title">Translation</h3>
								<div class="box-tools">
									<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
								</div>
							</div>
							<form action="/mulcms/page/pageTransEntry" method="post">
								<div class="box-body">
									<div class="form-group">
										<select name="srcLang" class="form-control">
											<option value="">-- Src Lang --</option>
											<%for(Lang lang: supportLangList) { %>
											<option value="<%=lang.toString() %>"><%=lang.getName() %></option>
											<%} %>
										</select>
									</div>
									<div class="form-group text-center">
										<i class="fa fa-arrow-down"></i>
									</div>
									<div class="form-group">
										<select name="targetLang" class="form-control">
											<option value="">-- Target Lang --</option>
											<%for(Lang lang: Lang.values()) { %>
											<%if(supportLangList.indexOf(lang) < 0) { %>
											<option value="<%=lang.toString() %>"><%=lang.getName() %></option>
											<%} %>
											<%} %>
										</select>
									</div>
								</div><!-- /.box-body -->
								<div class="box-footer text-right">
									<button name="keyString" value="<%=targetPage.getKey().getName() %>" type="submit" class="btn btn-primary">Submit</button>
								</div>
							</form>
						</div>
						<%} %>
						
						<a href="/mulcms/page/extractionResEntry?keyString=<%=targetPage.getKey().getName() %>" class="btn btn-primary btn-block margin-bottom">Resource Extraction</a>
						<a class="btn btn-danger btn-block margin-bottom" href="/mulcms/page/delete?keyString=<%=targetPage.getKey().getName() %>">Delete Page</a>
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
