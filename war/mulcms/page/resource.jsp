<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.model.assets.*" %>
<%@ page import="com.plucial.mulcms.enums.*" %>
<%@ page import="com.plucial.mulcms.model.res.*" %>
<%@ page import="java.util.List" %>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="com.plucial.mulcms.utils.*" %>
<%@ page import="com.plucial.global.Lang" %>
<%@ page import="java.util.Properties" %>
<%
Errors errors =(Errors) request.getAttribute("errors");
Page pageObj =  (Page) request.getAttribute("page");
String html =(String) request.getAttribute("pageHtml");

List<Lang> supportLangList = pageObj.getLangList();
Lang targetLang = Lang.valueOf((String) request.getAttribute("lang"));

List<AttrRes> attrResList = (List<AttrRes>) request.getAttribute("attrResList");
List<InnerTextRes> innerTextResList = (List<InnerTextRes>) request.getAttribute("innerTextResList");

Properties userProp = (Properties) request.getAttribute("userProp");
%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/mulcms/includes/html_head.jsp" />
</head>
<body class="skin-blue layout-top-nav control-sidebar-open">
	<div class="wrapper">
		<!-- site-header -->
		<jsp:include page="/mulcms/includes/site_header.jsp" />
		<!-- /site-header -->
      

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1><%=pageObj.getKey().getName() %><small><%=userProp.getProperty("lang." + targetLang.toString()) %></small></h1>
			</section>
			
			<section class="content">
				<div class="row">
					<div class="col-md-3">
						<%if (!errors.isEmpty()){ %>
						<!-- alert -->
						<div class="alert alert-warning alert-dismissable">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
							<h4><i class="icon fa fa-warning"></i> Alert!</h4>
							<%=errors.get(0) %>
						</div>
						<!-- /alert -->
						<%} %>
						
						<%if(supportLangList.indexOf(targetLang) >= 0) { %>
						<div class="nav-tabs-custom">
							<ul class="nav nav-tabs pull-right">
								<li><a href="#tab_2-2" data-toggle="tab"><i class="fa fa-code"></i> 属性</a></li>
								<li class="active"><a href="#tab_1-1" data-toggle="tab"><i class="fa fa-text-width"></i> テキスト</a></li>
								<li class="pull-left header" style="font-size: 16px;"><i class="fa fa-plus"></i> 追加</li>
							</ul>
							<div class="tab-content" style="padding-top: 30px;">
								<div class="tab-pane active" id="tab_1-1">
                    				<form action="/mulcms/res/addInnerTextResEntry" method="post">
                    					<div class="form-group">
	                    					<div class="input-group">
												<span class="input-group-addon"><i class="fa fa-css3"></i></span>
												<input type="text" name="cssQuery" class="form-control" placeholder="Css Query">
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
			                    				<div class="form-group">
													<div class="checkbox">
														<label><input type="checkbox" name="longText"/> 改行を許可</label>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="checkbox">
														<label><input type="checkbox" name="editMode"/> 編集可能</label>
													</div>
												</div>
											</div>
										</div>
										<input type="hidden" name="parentKeyString" value="<%=pageObj.getKey().getName() %>" />
										<input type="hidden" name="lang" value="<%=targetLang.toString() %>" />
										<div class="form-group text-right">
											<button type="submit" class="btn btn-default">追加</button>
										</div>
                    				</form>
								</div><!-- /.tab-pane -->
								<div class="tab-pane" id="tab_2-2">
									<form action="/mulcms/res/addAttrResEntry" method="post">
										<div class="form-group">
	                    					<div class="input-group">
												<span class="input-group-addon"><i class="fa fa-css3"></i></span>
												<input type="text" name="cssQuery" class="form-control" placeholder="Css Query">
											</div>
										</div>
										<div class="form-group">
	                    					<div class="input-group">
												<span class="input-group-addon"><i class="fa fa-code"></i></span>
												<input type="text" name="attr" class="form-control" placeholder="class, src, href...">
											</div>
										</div>
										<input type="hidden" name="parentKeyString" value="<%=pageObj.getKey().getName() %>" />
										<input type="hidden" name="lang" value="<%=targetLang.toString() %>" />
										<div class="form-group text-right">
											<button type="submit" class="btn btn-default">追加</button>
										</div>
									</form>
								</div><!-- /.tab-pane -->
							</div><!-- /.tab-content -->
						</div>
						<%} %>
						
						<%if(pageObj.getHtmlLang() != targetLang) { %>
						<div class="box box-solid">
							<div class="box-header with-border">
								<h3 class="box-title"><%=supportLangList.indexOf(targetLang) < 0 ? "翻訳してぺージを追加" : "再翻訳" %></h3>
								<div class="box-tools">
									<button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
								</div>
							</div>
							<form action="/mulcms/page/pageTransEntry" method="post">
								<div class="box-body">
									<div class="form-group">
										<select name="srcLang" class="form-control">
											<option value="">--- 翻訳元の言語 ---</option>
											<%for(Lang lang: supportLangList) { 
												if(lang != targetLang) {
											%>
											<option value="<%=lang.toString() %>"><%=userProp.getProperty("lang." + lang.toString()) %></option>
											<%} %>
											<%} %>
										</select>
									</div>
									
									<%if(supportLangList.indexOf(targetLang) >= 0) { %>
									<div class="form-group">
										<div class="checkbox">
											<label>
												<input type="checkbox" name="transAll">全てを再翻訳する
											</label>
										</div>
										<small class="text-muted">[全てを再翻訳する] をチェックして翻訳すると、既に翻訳済みの項目も再翻訳されます。</small>
									</div>
									<%} %>
									
									<div class="form-group text-right">
										<input type="hidden" name="targetLang" value="<%=targetLang.toString() %>" />
										<button name="keyString" value="<%=pageObj.getKey().getName() %>" type="submit" class="btn btn-primary">翻訳</button>
									</div>
									<%-- <%if(supportLangList.indexOf(targetLang) >= 0) { %>
									<p>すでに翻訳されている項目は再翻訳されません。<p>
									<p>全てを再翻訳したい場合は、このページを一度削除してから再度翻訳を行ってください。</p>
									<%} %> --%>
								</div><!-- /.box-body -->
							</form>
						</div>
						<%} %>
						
						<%if(supportLangList.indexOf(targetLang) >= 0) { %>
						<a class="btn btn-default btn-block margin-bottom" target="view" href="/<%=targetLang.toString() %><%=pageObj.getKey().getName() %>"><i class="fa fa-external-link"></i> ページを確認</a>
						<%} %>
						
						<%if(pageObj.getHtmlLang() == targetLang) { %>
						<a href="/mulcms/page/extractionResEntry?keyString=<%=pageObj.getKey().getName() %>&lang=<%=targetLang.toString() %>" class="btn btn-primary btn-block margin-bottom"><i class="fa fa-refresh"></i> リソースを更新</a>
						<%} %>
						
						<%if(supportLangList.indexOf(targetLang) >= 0) { %>
						<a class="btn btn-danger btn-block margin-bottom" href="/mulcms/page/deleteLang?keyString=<%=pageObj.getKey().getName() %>&lang=<%=targetLang.toString() %>"><i class="fa fa-trash"></i> <%=userProp.getProperty("lang." + targetLang.toString()) %>を削除</a>
						<%} %>
					</div>
					
					<div class="col-md-9">
						
						<%if(supportLangList.indexOf(targetLang) >= 0) { %>
						<div class="box box-primary">
							<div class="box-header with-border">
								<i class="fa fa-text-width"></i> 
								<h3 class="box-title">テキストリソース<small>翻訳対象</small></h3>
							</div><!-- /.box-header -->
	
							<div class=".box-body">
								<div class="table-responsive mailbox-messages">
									<table class="table table-hover table-striped">
										<tbody>
											<tr>
												<th class="text-center">Edit</th>
												<th><i class="fa fa-css3"></i> Css Query</th>
												<th>Value</th>
												<th></th>
												<th></th>
											</tr>
											<%for(InnerTextRes res: innerTextResList) { %>
											<tr>
												<td class="text-center" style="width:60px">
													<%if(res.isEditMode()) { %>
													<i class="fa fa-check-square text-primary"></i>
													<%}else { %>
													<i class="fa fa-square-o"></i>
													<%} %>
												</td>
												<td  style="width:40%"><code><%=HtmlUtils.htmlEscape(res.getCssQuery()) %></code></td>
												<td><%=res.getValueString() %></td>
												<td style="width:60px"><a class="btn btn-default btn-sm" href="/mulcms/res/updateInnerTextRes?keyString=<%=res.getKey().getName() %>&parentKeyString=<%=pageObj.getKey().getName() %>&lang=<%=targetLang %>"><i class="fa fa fa-edit"></i></a></td>
												<td style="width:60px"><a class="btn btn-danger btn-sm" href="/mulcms/res/deleteRes?keyString=<%=res.getKey().getName() %>&parentKeyString=<%=pageObj.getKey().getName() %>&lang=<%=targetLang %>"><i class="fa fa-trash"></i></a></td>
											</tr>
											<%} %>

										</tbody>
									</table><!-- /.table -->
								</div><!-- /.mail-box-messages -->
		                	</div><!-- /.box-body -->
						</div><!-- /. box -->
						
						<div class="box box-success">
							<div class="box-header with-border">
								<h3 class="box-title"><i class="fa fa-code"></i> 属性リソース</h3>
							</div><!-- /.box-header -->
	
							<div class=".box-body">
								<div class="table-responsive mailbox-messages">
									<table class="table table-hover table-striped">
										<tbody>
											<tr>
												<th><i class="fa fa-css3"></i> Css Query</th>
												<th>Value</th>
												<th></th>
												<th></th>
											</tr>
											<%for(AttrRes res: attrResList) { %>
											<tr>
												<td style="width:40%"><code><%=HtmlUtils.htmlEscape(res.getCssQuery()) %>: <%=res.getAttr() %></code></td>
												<td>
													<%=res.getValueString() %>
												</td>
												<td style="width:60px"><a class="btn btn-default btn-sm" href="/mulcms/res/updateAttrRes?keyString=<%=res.getKey().getName() %>&parentKeyString=<%=pageObj.getKey().getName() %>&lang=<%=targetLang %>"><i class="fa fa fa-edit"></i></a></td>
												<td style="width:60px">
													<a class="btn btn-danger btn-sm" href="/mulcms/res/deleteRes?keyString=<%=res.getKey().getName() %>&parentKeyString=<%=pageObj.getKey().getName() %>&lang=<%=targetLang %>">
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
						<a href="/mulcms/page/resource?keyString=<%=pageObj.getKey().getName() %>&lang=<%=lang.toString() %>">
							<span class="control-sidebar-subheading">
								<%if(targetLang == lang) { %>
									<b><%=userProp.getProperty("lang." + lang.toString()) %></b>
								<%}else { %>
									<%=userProp.getProperty("lang." + lang.toString()) %>
								<%} %>
								
								<%if(pageObj.getLangList().indexOf(lang) >= 0) { %>
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
