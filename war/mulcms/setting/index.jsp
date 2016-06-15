<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.App" %>
<%@ page import="com.plucial.mulcms.model.*" %>
<%@ page import="com.plucial.mulcms.enums.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.plucial.global.Lang" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.slim3.util.StringUtil" %>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%
Errors errors =(Errors) request.getAttribute("errors");

Map<String, String> appPropertyMap = (Map<String, String>)request.getAttribute("appPropertyMap");

Lang appBaseLang = Lang.valueOf(appPropertyMap.get(AppProperty.APP_BASE_LANG.toString()));
String adminEmail = appPropertyMap.get(AppProperty.APP_ADMIN_EMAIL.toString());
String gcsBucketName = appPropertyMap.get(AppProperty.APP_GCS_BUCKET_NAME.toString());
String apiServerKey = appPropertyMap.get(AppProperty.GOOGLE_API_PUBLIC_SERVER_KEY.toString());
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
      
		<!-- Left side column. contains the logo and sidebar -->
<%-- 		<jsp:include page="/mulcms/includes/main_sidebar.jsp">
			<jsp:param name="contentsType" value="setting" />
		</jsp:include> --%>
      

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
	
	        <!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-offset-1 col-md-9">
						<h2 class="page-header">アプリケーションの設定</h2>
						
						<div class="row">
							<div class="col-md-5">
								<div class="box">
									<div class="box-header with-border">
										<h3 class="box-title">基本情報</h3>
									</div>
									
									<div class="box-body">	
										<table class="table table-bordered text-center">
											<tr>
												<th>アプリケーション ID</th>
												<td><%=appPropertyMap.get(AppProperty.APP_ID.toString()) %></td>
											</tr>
											<tr>
												<th>アプリケーションデフォルトURL</th>
												<td><%=appPropertyMap.get(AppProperty.APP_DEFAULT_HOST_NAME.toString()) %></td>
											</tr>
										</table>
											
									</div><!-- /.box-body -->
								</div>
							</div>
		
							<div class="col-md-7">
								<%if (!errors.isEmpty()){ %>
								<!-- alert -->
								<div class="alert alert-warning alert-dismissable">
									<button type="button" class="close" data-dismiss="alert">×</button>
									<h4><i class="icon fa fa-warning"></i> Alert!</h4>
									<%=errors.get(0) %>
								</div>
								<!-- /alert -->
								<%} %>
							
								<div class="box">
									<div class="box-header with-border">
										<h3 class="box-title">設定</h3>
									</div>
									
									<div class="box-body">	
										<div class="form-group">
											<form action="/mulcms/setting/updateEntry" method="post">
												<label>アプリケーションのベースとなる言語</label>
												<div class="row">
													<div class="col-md-10">
														<select name="propertyValue" class="form-control">
															<%for(Lang lang: Lang.values()) { %>
															<option value="<%=lang.toString() %>" <%=lang == appBaseLang ? "selected" : "" %>><%=lang.getName() %></option>
															<%} %>
														</select>
													</div>
													<div class="col-md-2">
														<button type="submit" name="propertyKey" value="<%=AppProperty.APP_BASE_LANG.toString() %>" class="btn btn-primary form-control">変更</button>
													</div>
												</div>
											</form>
										</div>
										
										<div class="form-group">
											<form action="/mulcms/setting/updateEntry" method="post">
												<label>Admin Email(メールの送信元メールアドレス)</label>
												<div class="row">
													<div class="col-md-10">
														<input name="propertyValue" class="form-control" value="<%=StringUtil.isEmpty(adminEmail) ? "" : adminEmail %>">
													</div>
													<div class="col-md-2">
														<button type="submit" name="propertyKey" value="<%=AppProperty.APP_ADMIN_EMAIL.toString() %>" class="btn btn-primary form-control">変更</button>
													</div>
												</div>
											</form>
										</div>
										
										<div class="form-group">
											<form action="/mulcms/setting/updateEntry" method="post">
												<label>Google クラウドストレージのBUCKET名</label>
												<div class="row">
													<div class="col-md-10">
														<input name="propertyValue" class="form-control" value="<%=StringUtil.isEmpty(gcsBucketName) ? "" : gcsBucketName %>">
													</div>
													<div class="col-md-2">
														<button type="submit" name="propertyKey" value="<%=AppProperty.APP_GCS_BUCKET_NAME.toString() %>" class="btn btn-primary form-control">変更</button>
													</div>
												</div>
											</form>
										</div>
											
											
										<div class="form-group">
											<form action="/mulcms/setting/updateEntry" method="post">
												<label>Google API サーバーキー</label>
												<div class="row">
													<div class="col-md-10">
														<input name="propertyValue" class="form-control" value="<%=StringUtil.isEmpty(apiServerKey) ? "" : apiServerKey %>">
													</div>
													<div class="col-md-2">
														<button type="submit" name="propertyKey" value="<%=AppProperty.GOOGLE_API_PUBLIC_SERVER_KEY.toString() %>" class="btn btn-primary form-control">変更</button>
													</div>
												</div>
											</form>
										</div>
											
									</div><!-- /.box-body -->
								</div>
							</div>
							
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
