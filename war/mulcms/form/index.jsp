<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.App" %>
<%@ page import="com.plucial.mulcms.model.assets.*" %>
<%@ page import="com.plucial.mulcms.model.widgets.form.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="com.plucial.mulcms.utils.*" %>
<%
Errors errors =(Errors) request.getAttribute("errors");
List<Form> formList = (List<Form>) request.getAttribute("formList");
List<Page> pageList = (List<Page>) request.getAttribute("pageList");

Page targetPage = (Page)request.getAttribute("page");
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
				<h2 class="page-header"><i class="fa fa-users"></i> フォームの管理</h2>
				
				<div class="row">
					<div class="col-md-3">
						<%if (!errors.isEmpty()){ %>
						<!-- alert -->
						<div class="alert alert-warning alert-dismissable">
							<button type="button" class="close" data-dismiss="alert">×</button>
							<h4><i class="icon fa fa-warning"></i> Alert!</h4>
							<%=errors.get(0) %>
						</div>
						<!-- /alert -->
						<%} %>
					
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">フォームの追加</h3>
							</div>
							<form action="/mulcms/form/addEntry" method="post">
								<div class="box-body">
									<div class="form-group">
	                    				<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-css3"></i></span>
											<input ${f:text("cssQuery")} class="form-control" placeholder="Css Query">
										</div>
									</div>
									<div class="form-group">
										<label>フォーム名</label>
										<input ${f:text("name")} class="form-control" placeholder="コンタクトフォーム">
									</div>
									<div class="form-group">
										<label>処理後に遷移するページ</label>
										<select name="transitionPageKey" class="form-control">
											<option value="">-- Select Page --</option>
											<%for(Page pageObj: pageList) { %>
											<option value="<%=pageObj.getKey().getName() %>"><%=pageObj.getKey().getName() %></option>
											<%} %>
										</select>
									</div>
								</div><!-- /.box-body -->
								<input type="hidden" name="keyString" value="<%=targetPage.getKey().getName() %>">
								<div class="box-footer text-right">
									<button type="submit" class="btn btn-primary">追加</button>
								</div>
							</form>
						</div>
					</div>
	            
					<div class="col-md-9">
						<div class="row">
							
							<%for(Form form: formList) { %>
							<div class="col-md-4">
								<div class="info-box">
									<div class="box-tools pull-right">
										<a class="btn btn-box-tool" href="/mulcms/form/delete?keyString=<%=form.getKey().getName() %>&parentKeyString=<%=form.getAssetsRef().getKey().getName() %>"><i class="fa fa-times"></i></a>
									</div>
									<span class="info-box-icon bg-yellow disabled color-palette"><i class="fa fa-object-group"></i></span>
									<div class="info-box-content">
										<span class="info-box-text" style="text-transform: none;"><i class="fa fa-css3"></i> <%=HtmlUtils.htmlEscape(form.getCssQuery()) %></span>
										<span class="info-box-number"><a href="/mulcms/form/setting?keyString=<%=form.getKey().getName() %>&parentKeyString=<%=form.getAssetsRef().getKey().getName() %>"><%=form.getName() %></a></span>
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
