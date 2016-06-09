<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.App" %>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="com.plucial.mulcms.model.template.*" %>
<%@ page import="com.plucial.global.Lang" %>
<%@ page import="com.plucial.mulcms.utils.*" %>
<%
Errors errors =(Errors) request.getAttribute("errors");
MailTemplate template = (MailTemplate)request.getAttribute("template");
String html = (String)request.getAttribute("html");
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
			<jsp:param name="contentsType" value="mailTemplate" />
		</jsp:include>
      

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>メールテンプレート<small>修正</small></h1>
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
							<form action="/mulcms/template/mail/editEntry" method="post">
								<div class="box-body">
									<div class="row">
										<div class="form-group col-md-6">
											<label for="inputName">テンプレート名</label>
											<input ${f:text("name")} class="form-control" id="inputName" placeholder="Name">
										</div>
										<div class="form-group col-md-6">
											<label for="inputName">テンプレートの言語</label>
											<select name="lang" class="form-control">
												<option value="">-- Select Lang --</option>
												<%for(Lang lang: Lang.values()) { %>
												<option value="<%=lang.toString() %>" <%=template.getLang() == lang ? "selected" : "" %>><%=lang.getName() %></option>
												<%} %>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1">コンテンツ</label>
										<textarea class="form-control" name="html" rows="20" placeholder="Enter ..."><%=HtmlUtils.htmlEscape(html) %></textarea>
									</div>
								</div><!-- /.box-body -->

								<input type="hidden" name="keyString" value="<%=template.getKey().getName() %>">
								<div class="box-footer">
									<a class="btn btn-default pull-left" href="/mulcms/template/"><i class="fa fa-reply"></i></a>
									<button type="submit" class="btn btn-primary pull-right">更新</button>
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
