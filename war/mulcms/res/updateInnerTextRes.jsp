<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.App" %>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="com.plucial.mulcms.model.assets.*" %>
<%@ page import="com.plucial.mulcms.enums.*" %>
<%@ page import="com.plucial.mulcms.model.res.*" %>
<%@ page import="com.plucial.global.Lang" %>
<%@ page import="com.plucial.mulcms.utils.*" %>
<%
Errors errors =(Errors) request.getAttribute("errors");
InnerTextRes res = (InnerTextRes)request.getAttribute("res");

String parentKeyString = (String)request.getAttribute("parentKeyString");
String lang = (String)request.getAttribute("lang");
String content = (String)request.getAttribute("lang");
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
				<div class="row">
            
					<div class="col-md-6 col-md-offset-3">
						<h2 class="page-header">リソースの修正</h2>
						
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
							<form action="/mulcms/res/updateInnerTextResEntry" method="post">
								<div class="box-header with-border">
									<h3 class="box-title"><%=HtmlUtils.htmlEscape(res.getCssQuery()) %></h3>
									<div class="box-tools pull-right">
										<div class="checkbox">
											<label><input type="checkbox" ${f:checkbox("editMode")}/> Edit Mode</label>
										</div>
									</div>
				                </div><!-- /.box-header -->
								<div class="box-body">
									<div class="form-group">
										<label>Css Query</label>
										<input ${f:text("cssQuery")} class="form-control" placeholder="Css Query">
									</div>
									<div class="form-group">
										<label>Value</label>
										<%if(res.isLongText()) { %>
										<textarea class="form-control" name="content" rows="20" placeholder="Enter ..."><%=res.getValueString() %></textarea>
										<%}else {%>
										<input ${f:text("content")} class="form-control" id="inputName" placeholder="Name">
										<%} %>
									</div>
									<div class="form-group">
										<div class="checkbox">
											<label><input type="checkbox" ${f:checkbox("longText")} /> 改行を許可</label>
										</div>
									</div>
								</div><!-- /.box-body -->

								<input type="hidden" name="keyString" value="<%=res.getKey().getName() %>">
								<input type="hidden" name="parentKeyString" value="<%=parentKeyString %>">
								<input type="hidden" name="lang" value="<%=lang %>">
								<div class="box-footer">
									<a class="btn btn-default pull-left" href="/mulcms/page/resource?keyString=<%=parentKeyString %>&lang=<%=lang %>"><i class="fa fa-reply"></i></a>
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
