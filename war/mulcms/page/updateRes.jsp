<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.App" %>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="com.plucial.mulcms.model.assets.*" %>
<%@ page import="com.plucial.mulcms.model.template.*" %>
<%@ page import="com.plucial.mulcms.enums.*" %>
<%@ page import="com.plucial.mulcms.model.res.*" %>
<%@ page import="com.plucial.global.Lang" %>
<%
Errors errors =(Errors) request.getAttribute("errors");
Res res = (Res)request.getAttribute("res");

String pageKey = (String)request.getAttribute("pageKey");
String lang = (String)request.getAttribute("lang");
String content = (String)request.getAttribute("lang");
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
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Add Pages Template<small>designer</small></h1>
			</section>

        	<!-- Main content -->
			<section class="content">
				<div class="row">
            
					<div class="col-md-8 col-md-offset-2">
					
						<%if (!errors.isEmpty()){ %>
						<!-- alert -->
						<div class="alert alert-warning alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
							<h4><i class="icon fa fa-warning"></i> Alert!</h4>
							<%=errors.get(0) %>
						</div>
						<!-- /alert -->
						<%} %>
                  
						<div class="box box-primary">
						
							<!-- form start -->
							<form action="/mulcms/page/updateResEntry" method="post">
								<div class="box-body">
									<div class="form-group">
										<div class="checkbox">
											<label><input type="checkbox" ${f:checkbox("editMode")}/> Edit Mode</label>
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1">Value</label>
										<%if(res.getRenderingType() == RenderingType.long_text) { %>
										<textarea class="form-control" name="content" rows="20" placeholder="Enter ..."><%=res.getValueString() %></textarea>
										<%}else {%>
										<input ${f:text("content")} class="form-control" id="inputName" placeholder="Name">
										<%} %>
									</div>
								</div><!-- /.box-body -->

								<input type="hidden" name="keyString" value="<%=res.getKey().getName() %>">
								<input type="hidden" name="pageKey" value="<%=pageKey %>">
								<input type="hidden" name="lang" value="<%=lang %>">
								<div class="box-footer">
									<a class="btn btn-default pull-left" href="/mulcms/page/setting?keyString=<%=pageKey %>&lang=<%=lang %>"><i class="fa fa-reply"></i></a>
									<button type="submit" class="btn btn-primary pull-right">Submit</button>
								</div>
							</form>
						</div><!-- /.box -->
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
