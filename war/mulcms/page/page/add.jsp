<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%
List<PageTemplate> templateList = (List<PageTemplate>) request.getAttribute("templateList");
Errors errors =(Errors) request.getAttribute("errors");
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
		<jsp:include page="/mulcms/includes/main_sidebar.jsp" />
      

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>Stting Pages<small>designer</small></h1>
		</section>

        <!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-3">
					<jsp:include page="/mulcms/includes/mulcms_main_nav.jsp" >
						<jsp:param name="contentsType" value="page" />
					</jsp:include>
				</div><!-- /.col -->
            
	            <div class="col-md-9">
	            
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
						<form action="/mulcms/page/page/addEntry" method="post">
							<div class="box-header with-border">
								<h3 class="box-title">Add Page</h3>
							</div><!-- /.box-header -->
							
							<div class="box-body">
	                  					
								<div class="row">
									<div class="form-group col-md-6">
										<label for="exampleInputEmail1">Path(URL)</label>
										<input ${f:text("url")} class="form-control" id="exampleInputEmail1" placeholder="/aaaa/xxx.html">
									</div>
									<div class="form-group col-md-6">
										<label for="exampleInputEmail1">Template</label>
										<select name="template" class="form-control">
											<option value="">-- Select Template --</option>
											<%for(Template temp: templateList) { %>
											<option value="<%=temp.getKey().getName() %>"><%=temp.getName() %></option>
											<%} %>
										</select>
									</div>
								</div>
							</div><!-- /.box-body -->
	
							<div class="box-footer text-right">
								<button type="submit" class="btn btn-primary">Submit</button>
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
