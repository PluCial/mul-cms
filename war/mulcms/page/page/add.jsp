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
			<h1>Stting Pages<small>designer</small></h1>
		</section>

        <!-- Main content -->
		<section class="content">
			<div class="row">
				<div class="col-md-3">
					<jsp:include page="/mulcms/includes/admin_main_nav.jsp" >
						<jsp:param name="contentsType" value="page" />
					</jsp:include>
				</div><!-- /.col -->
            
            <div class="col-md-9">
            
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
