<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.model.assets.*" %>
<%@ page import="com.plucial.mulcms.model.template.*" %>
<%@ page import="com.plucial.mulcms.enums.*" %>
<%@ page import="com.plucial.mulcms.model.res.*" %>
<%@ page import="java.util.List" %>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="com.plucial.mulcms.utils.*" %>
<%@ page import="com.plucial.global.Lang" %>
<%
Page targetPage =  (Page) request.getAttribute("targetPage");
Template template = targetPage.getTemplateRef().getModel();
String html =(String) request.getAttribute("pageHtml");

List<Lang> supportLangList = targetPage.getLangList();
Lang targetLang = Lang.valueOf((String) request.getAttribute("lang"));

List<Res> appResList = (List<Res>) request.getAttribute("appResList");
List<Res> appLangResList = (List<Res>) request.getAttribute("appLangResList");
List<Res> assetsResList = (List<Res>) request.getAttribute("assetsResList");
List<Res> assetsLangResList = (List<Res>) request.getAttribute("assetsLangResList");
%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/mulcms/includes/html_head.jsp" />
</head>
<body class="skin-blue sidebar-mini control-sidebar-open">
	<div class="wrapper">
		<!-- site-header -->
		<jsp:include page="/mulcms/includes/site_header.jsp" />
		<!-- /site-header -->
      
		<!-- Left side column. contains the logo and sidebar -->
		<jsp:include page="/mulcms/includes/main_sidebar.jsp">
			<jsp:param name="contentsType" value="page" />
		</jsp:include>
      

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1><%=targetPage.getKey().getName() %><small><%=targetLang.getName() %></small></h1>
			</section>
			
			<section class="content">
				<div class="row">
					<div class="col-md-3">
						
						<%if(template.getLang() != targetLang) { %>
						<div class="box box-solid">
							<div class="box-header with-border">
								<h3 class="box-title"><%=supportLangList.indexOf(targetLang) < 0 ? "翻訳" : "再翻訳" %></h3>
								<div class="box-tools">
									<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
								</div>
							</div>
							<form action="/mulcms/page/pageTransEntry" method="post">
								<div class="box-body">
									<div class="form-group">
										<select name="srcLang" class="form-control">
											<%for(Lang lang: supportLangList) { 
												if(lang != targetLang) {
											%>
											<option value="<%=lang.toString() %>"><%=lang.getName() %></option>
											<%} %>
											<%} %>
										</select>
									</div>
									<div class="form-group text-right">
										<input type="hidden" name="targetLang" value="<%=targetLang.toString() %>" />
										<button name="keyString" value="<%=targetPage.getKey().getName() %>" type="submit" class="btn btn-primary">翻訳</button>
									</div>
									<%if(supportLangList.indexOf(targetLang) >= 0) { %>
									<p>すでに翻訳されている項目は再翻訳されません。<p>
									<p>全てを再翻訳したい場合は、このページを一度削除してから再度翻訳を行ってください。</p>
									<%} %>
								</div><!-- /.box-body -->
							</form>
						</div>
						<%} %>
						
						<%if(supportLangList.indexOf(targetLang) >= 0) { %>
						<a class="btn btn-default btn-block margin-bottom" target="view" href="/<%=targetLang.toString() %><%=targetPage.getKey().getName() %>"><i class="fa fa-external-link"></i> ページの確認</a>
						<%} %>
						
						<%if(template.getLang() == targetLang) { %>
						<a href="/mulcms/page/extractionResEntry?keyString=<%=targetPage.getKey().getName() %>&lang=<%=targetLang.toString() %>" class="btn btn-primary btn-block margin-bottom"><i class="fa fa-refresh"></i> リソースの更新</a>
						<%} %>
						
						<%if(supportLangList.indexOf(targetLang) >= 0) { %>
						<a class="btn btn-danger btn-block margin-bottom" href="/mulcms/page/deleteLang?keyString=<%=targetPage.getKey().getName() %>&lang=<%=targetLang.toString() %>"><i class="fa fa-trash"></i> <%=targetLang.getName() %>ページの削除</a>
						<%} %>
					</div>
					
					<div class="col-md-9">
						
						<%if(appLangResList.size() > 0) { %>
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">共通リソース<small><%=targetLang.getName() %>の全てのページに適用される</small></h3>
							</div><!-- /.box-header -->
	
							<div class=".box-body">
								<div class="table-responsive mailbox-messages">
									<table class="table table-hover table-striped">
										<tbody>
											<tr>
												<th>ID</th>
												<th>Value</th>
												<th>Delete</th>
											</tr>
											<%for(Res res: appLangResList) { %>
											<tr>
												<td style="width:20%"><b><%=res.getResId() %></b></td>
												<td>
													<%=res.getValueString() %>
												</td>
												<td style="width:60px">
													<a class="btn btn-danger btn-sm" href="/mulcms/page/deleteRes?keyString=<%=res.getKey().getName() %>&pageKey=<%=targetPage.getKey().getName() %>&lang=<%=targetLang %>">
														<i class="fa fa-trash"></i>
													</a>
												</td>
											</tr>
											<%} %>

										</tbody>
									</table><!-- /.table -->
								</div><!-- /.mail-box-messages -->
		                	</div><!-- /.box-body -->
						</div><!-- /. box -->
						<%} %>
						
						<%if(assetsLangResList.size() > 0) { %>
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">言語リソース<small>このページの<%=targetLang.getName() %>バージョンにのみ適用される</small></h3>
							</div><!-- /.box-header -->
	
							<div class=".box-body">
								<div class="table-responsive mailbox-messages">
									<table class="table table-hover table-striped">
										<tbody>
											<tr>
												<th>ID</th>
												<th>Rendering</th>
												<th>Value</th>
												<th>Edit Mode</th>
												<th>Edit</th>
												<th>Delete</th>
											</tr>
											<%for(Res res: assetsLangResList) { %>
											<tr>
												<td style="width:20%"><b><%=res.getResId() %></b></td>
												<td>
													<%=res.getRenderingType().toString() %><%=res.getRenderingType() == RenderingType.attr ? " : " + res.getRenderingAttr() : "" %>
												</td>
												<td><%=res.getValueString() %></td>
												<td><%=res.isEditMode() %></td>
												<td style="width:60px"><a class="btn btn-default btn-sm" href="/mulcms/page/updateRes?keyString=<%=res.getKey().getName() %>&pageKey=<%=targetPage.getKey().getName() %>&lang=<%=targetLang %>"><i class="fa fa fa-edit"></i></a></td>
												<td style="width:60px"><a class="btn btn-danger btn-sm" href="/mulcms/page/deleteRes?keyString=<%=res.getKey().getName() %>&pageKey=<%=targetPage.getKey().getName() %>&lang=<%=targetLang %>"><i class="fa fa-trash"></i></a></td>
											</tr>
											<%} %>

										</tbody>
									</table><!-- /.table -->
								</div><!-- /.mail-box-messages -->
		                	</div><!-- /.box-body -->
						</div><!-- /. box -->
						<%} %>
					</div>
				</div>
			</section>


		</div><!-- /.content-wrapper -->
      
		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-light" style="position: fixed;max-height: 100%;overflow: auto;padding-bottom: 50px;">
			<div class="tab-content">
				<h3 class="control-sidebar-heading">サポート言語<span class="label label-primary pull-right"><%=supportLangList.size() %> / <%=Lang.values().length %></span></h3>
				<div class="progress progress-xxs">
                    <div class="progress-bar progress-bar-primary" style="width: <%=((float)supportLangList.size() / Lang.values().length) * 100 %>%"></div>
                  </div>
				<hr />
				<ul class="control-sidebar-menu">
					<%for(Lang lang: Lang.values()) { %>
					<li>
						<a href="/mulcms/page/setting?keyString=<%=targetPage.getKey().getName() %>&lang=<%=lang.toString() %>">
							<span class="control-sidebar-subheading">
								<%if(targetLang == lang) { %>
									<b><%=lang.getName() %></b>
								<%}else { %>
									<%=lang.getName() %>
								<%} %>
								
								<%if(targetPage.getLangList().indexOf(lang) >= 0) { %>
								<i class="fa fa-check-circle text-primary pull-right"></i>
								<%} %>
							</span>
						</a>
					</li>
					<%} %>
				</ul>
			</div>
		</aside>
		<div class='control-sidebar-bg'></div>
		<!-- /.control-sidebar -->
		
		<!-- page footer -->
	    <jsp:include page="/mulcms/includes/site_footer.jsp" />
		<!-- /page footer -->

	</div><!-- ./wrapper -->
	
	    
	<!-- page script -->
	<jsp:include page="/mulcms/includes/html_script.jsp" />
	<!-- page script -->

  </body>
</html>
