<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.App" %>
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
				<h1>Add Widget Template<small>designer</small></h1>
			</section>

        	<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-3">
						<jsp:include page="/mulcms/includes/mulcms_main_nav.jsp" >
							<jsp:param name="contentsType" value="widgetTemplate" />
						</jsp:include>
					</div><!-- /.col -->
            
					<div class="col-md-9">
						<div class="box box-primary">

							<!-- form start -->
							<form action="/mulcms/template/widget/addEntry" method="post">
								<div class="box-body">
                  					
                  					<div class="row">
										<div class="form-group col-md-6">
											<label for="exampleInputEmail1">Name</label>
											<input ${f:text("name")} class="form-control" id="exampleInputEmail1" placeholder="Template Name">
										</div>
										<div class="form-group col-md-6">
											<label for="exampleInputEmail1">Css Query</label>
											<input ${f:text("cssQuery")} class="form-control" id="exampleInputEmail1" placeholder="#aaa > bbb.ccc">
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1">HTML</label>
										<textarea class="form-control" name="html" rows="20" placeholder="html ..."></textarea>
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
