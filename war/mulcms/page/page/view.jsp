<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="com.plucial.mulcms.utils.*" %>
<%
List<PageTemplate> templateList = (List<PageTemplate>) request.getAttribute("templateList");
Errors errors =(Errors) request.getAttribute("errors");

Page targetPage =  (Page) request.getAttribute("targetPage");
String html =(String) request.getAttribute("pageHtml");

List<Widget> widgetList = (List<Widget>) request.getAttribute("widgetList");
List<WidgetTemplate> widgetTemplateList = (List<WidgetTemplate>) request.getAttribute("widgetTemplateList");
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
			<h1><%=targetPage.getKey().getName() %><small>designer</small></h1>
		</section>

        <!-- Main content -->
		<section class="content">
			<div class="row">
			
				<div class="col-md-8">
	            	<div class="box box-primary">
						<div class="box-header with-border">
							<h3 class="box-title">Page Src Preview</h3>
						</div><!-- /.box-header -->
							
						<div class="box-body">
<pre class="prettyprint" style="min-height:500px;max-height: 500px;"><code class="lang-html"><%=html %></code></pre>
						</div>
					</div><!-- /.box -->
	            </div><!-- /.col -->
            
	            <div class="col-md-4">
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
						<form action="/mulcms/page/page/editEntry" method="post">
							<div class="box-header with-border">
								<h3 class="box-title">Edit Page Meta</h3>
							</div><!-- /.box-header -->
							
							<div class="box-body">
								<div class="form-group">
									<label for="exampleInputEmail1">Path(URL)</label>
									<input type="text" name="url" class="form-control" id="exampleInputEmail1" placeholder="/aaaa/xxx.html" value="<%=targetPage.getKey().getName() %>" disabled />
								</div>
								<div class="form-group">
									<label for="exampleInputEmail1">Template</label>
									<select name="template" class="form-control">
										<option value="">-- Select Template --</option>
										<%for(Template temp: templateList) { %>
										<option value="<%=temp.getKey().getName() %>" <%=temp.getKey().getName().equals(targetPage.getTemplateRef().getKey().getName()) ? "selected" : "" %>><%=temp.getName() %></option>
										<%} %>
									</select>
								</div>
							</div><!-- /.box-body -->
							
							<input type="hidden" name="keyString" value="<%=targetPage.getKey().getName() %>" />
	
							<div class="box-footer text-right">
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</form>
					</div><!-- /.box -->
					
					
					<div>
						<ul class="timeline">
							<!-- timeline time label -->
							<li class="time-label">
								<span class="bg-green">Widget List</span>
							</li>
							<!-- /.timeline-label -->
							
							<%for(Widget widget: widgetList) { 
								WidgetTemplate template = (WidgetTemplate)widget.getTemplate();
							%>
							<!-- timeline item -->
							<li>
								<!-- timeline icon -->
								<i class="fa fa-arrow-left bg-blue"></i>
								<div class="timeline-item">
						            <span class="time"><i class="fa fa-angle-down"></i> <i class="fa fa-angle-up"></i></span>
						
						            <h3 class="timeline-header"><%=HtmlUtils.htmlEscape(template.getCssQuery()) %></h3>
						
						            <div class="timeline-body">
						                <b>Template: <a href="/mulcms/template/widget/edit?keyString=<%=template.getKey().getName() %>"><%=template.getName() %></a></b>
						            </div>
						
						            <div class='timeline-footer'>
						                <a class="btn btn-danger btn-xs">Delete</a>
						            </div>
						        </div>
						    </li>
						    <!-- END timeline item -->
						    <%} %>
						    <li>
						    	<div class="timeline-item">
						    		<div class="timeline-body">
						    			<form action="/mulcms/page/widget/addEntry" method="post">
							    			<div class="input-group input-group-sm">
	                    						<select name="template" class="form-control">
													<option value="">-- Select Template --</option>
													<%for(Template temp: widgetTemplateList) { %>
													<option value="<%=temp.getKey().getName() %>"><%=temp.getName() %></option>
													<%} %>
												</select>
												<input type="hidden" name="keyString" value="<%=targetPage.getKey().getName() %>" />
												
							                    <span class="input-group-btn">
							                      <button class="btn btn-primary" type="submit">Add</button>
							                    </span>
											</div>
										</form>
						            </div>
						        </div>
						    </li>

						</ul>
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
