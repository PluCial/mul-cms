<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.App" %>
<%
String contentsType = request.getParameter("contentsType");
%>
					
					<div class="box box-solid">
						<div class="box-header with-border">
							<h3 class="box-title">Page</h3>
							<div class='box-tools'>
								<button class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-minus'></i></button>
							</div>
						</div>
						<div class="box-body no-padding">
							<ul class="nav nav-pills nav-stacked">
								<li class="<%=contentsType.equals("page") ? "active" : "" %>"><a href="/mulcms/page/page/"><i class="fa fa-file-text-o"></i> Pages</a></li>
								<li class="<%=contentsType.equals("widget") ? "active" : "" %>"><a href="/mulcms/page/widget/"><i class="fa fa-files-o"></i> Widget</a></li>
							</ul>
						</div><!-- /.box-body -->
					</div><!-- /. box -->
              
					<div class="box box-solid">
						<div class="box-header with-border">
							<h3 class="box-title">Resource</h3>
							<div class='box-tools'>
								<button class='btn btn-box-tool' data-widget='collapse'><i class='fa fa-minus'></i></button>
							</div>
						</div>
						<div class="box-body no-padding">
							<ul class="nav nav-pills nav-stacked">
								<li><a href="#"><i class="fa fa-text-width"></i> Text</a></li>
								<li><a href="#"><i class="fa fa-picture-o"></i> Image</a></li>
							</ul>
						</div><!-- /.box-body -->
					</div><!-- /.box -->