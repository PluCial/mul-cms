<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.App" %>
<%@ page import="com.plucial.mulcms.model.assets.*" %>
<%@ page import="com.plucial.mulcms.model.widgets.form.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.slim3.controller.validator.Errors" %>
<%@ page import="com.plucial.mulcms.utils.*" %>
<%
Errors errors = (Errors) request.getAttribute("errors");
Form form = (Form) request.getAttribute("form");
List<Page> pageList = (List<Page>) request.getAttribute("pageList");
List<FormControl> controlList = (List<FormControl>) request.getAttribute("controlList");
List<MailAction> mailActionList = (List<MailAction>) request.getAttribute("mailActionList");
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
				<h2 class="page-header"><i class="fa fa-users"></i> <%=form.getName() %></h2>
				
				<div class="row">
					<div class="col-md-3">
						<%if (!errors.isEmpty()){ %>
						<!-- alert -->
						<div class="alert alert-warning alert-dismissable">
							<button type="button" class="close" data-dismiss="alert">×</button>
							<h4><i class="icon fa fa-warning"></i> Alert!</h4>
							<%=errors.get(0) %>
						</div>
						<!-- /alert -->
						<%} %>
					
						<div class="box">
							<div class="box-header with-border">
								<h3 class="box-title">フォームの修正</h3>
							</div>
							<form action="/mulcms/form/updateEntry" method="post">
								<div class="box-body">
									<div class="form-group">
	                    				<div class="input-group">
											<span class="input-group-addon"><i class="fa fa-css3"></i></span>
											<input ${f:text("cssQuery")} class="form-control" placeholder="Css Query">
										</div>
									</div>
									<div class="form-group">
										<label>フォーム名</label>
										<input ${f:text("name")} class="form-control" placeholder="コンタクトフォーム">
									</div>
									<div class="form-group">
										<label>処理後に遷移するページ</label>
										<select name="transitionPageKey" class="form-control">
											<option value="">-- Select Page --</option>
											<%for(Page pageObj: pageList) { %>
											<option value="<%=pageObj.getKey().getName() %>" <%=form.getTransitionPageRef().getKey().equals(pageObj.getKey()) ? "selected" : "" %>><%=pageObj.getKey().getName() %></option>
											<%} %>
										</select>
									</div>
								</div><!-- /.box-body -->
								<div class="box-footer text-right">
									<input type="hidden" name="parentKeyString" value="<%=form.getAssetsRef().getKey().getName() %>" />
									<input type="hidden" name="keyString" value="<%=form.getKey().getName() %>" />
									<button type="submit" class="btn btn-primary">更新</button>
								</div>
							</form>
						</div>
					</div>
	            
					<div class="col-md-6">
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">コントローラー</h3>
							</div><!-- /.box-header -->
	
							<div class=".box-body">
								<div class="table-responsive mailbox-messages">
									<table class="table table-hover table-striped">
										<tbody>
											<tr>
												<th>Name属性</th>
												<th>必須</th>
												<th>翻訳</th>
												<th>削除</th>
											</tr>
											<%for(FormControl control: controlList) { %>
											<tr>
												<td style="width:20%"><b><%=control.getControlName() %></b></td>
												<td><%=control.isRequired() %></td>
												<td><%=control.isTransFlg() %></td>
												<td style="width:60px">
													<a class="btn btn-danger btn-sm" href="/mulcms/form/control/deleteEntry?keyString=<%=control.getKey().getName() %>&formKey=<%=form.getKey().getName() %>&parentKeyString=<%=form.getAssetsRef().getKey().getName() %>">
														<i class="fa fa-trash"></i>
													</a>
												</td>
											</tr>
											<%} %>
										</tbody>
									</table><!-- /.table -->
								</div><!-- /.mail-box-messages -->
		                	</div><!-- /.box-body -->
		                	
		                	<div class="box-footer with-border">
		                		<form action="/mulcms/form/control/addEntry" method="post" style="margin-top: 15px;">
			                		<div class="row">
			                			<div class="form-group col-md-6">
											<input ${f:text("controlName")} class="form-control" placeholder="コントローラーの Name属性">
			                			</div>
			                			<div class="form-group col-md-2">
			                				<div class="checkbox">
												<label>
													<input type="checkbox" name="required"> 必須
												</label>
											</div>
			                			</div>
			                			<div class="form-group col-md-2">
			                				<div class="checkbox">
												<label>
													<input type="checkbox" name="transFlg"> 翻訳
												</label>
											</div>
			                			</div>
			                			<div class="form-group col-md-2">
			                				<input type="hidden" name="parentKeyString" value="<%=form.getAssetsRef().getKey().getName() %>" />
			                				<input type="hidden" name="keyString" value="<%=form.getKey().getName() %>" />
			                				<button type="submit" class="btn btn-primary">追加</button>
			                			</div>
			                		</div>
		                		</form>
		                	</div>
						</div><!-- /. box -->	
					</div><!-- /.col -->
					
					<div class="col-md-3">
						<div class="box box-warning">
							<div class="box-header with-border">
								<h3 class="box-title"><i class="fa fa-envelope"></i> 送信先</h3>
							</div><!-- /.box-header -->
	
							<div class=".box-body">
								<div class="table-responsive mailbox-messages">
									<table class="table table-hover table-striped">
										<tbody>
											<%for(MailAction action: mailActionList) { %>
											<tr>
												<td><%=action.getSendEmail().getEmail() %></td>
												<td style="width:60px">
													<a class="btn btn-box-tool btn-sm" href="/mulcms/form/action/deleteEntry?keyString=<%=action.getKey().getName() %>&parentKeyString=<%=form.getAssetsRef().getKey().getName() %>">
														<i class="fa fa-times"></i>
													</a>
												</td>
											</tr>
											<%} %>
										</tbody>
									</table><!-- /.table -->
								</div><!-- /.mail-box-messages -->
		                	</div><!-- /.box-body -->
		                	
		                	<div class="box-footer with-border">
		                		<form action="/mulcms/form/action/addReceptionMailEntry" method="post" style="margin-top: 15px;">
		                			<input type="hidden" name="parentKeyString" value="<%=form.getAssetsRef().getKey().getName() %>" />
		                			<input type="hidden" name="keyString" value="<%=form.getKey().getName() %>" />
			                		<div class="form-group">
			                			<div class="input-group input-group-sm">
			                				<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
											<input type="email" name="email" class="form-control" placeholder="メールアドレス">
											<span class="input-group-btn">
												<button class="btn btn-primary btn-flat" type="submit">追加</button>
											</span>
										</div>
			               			</div>
		                		</form>
		                	</div>
						</div><!-- /. box -->	
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
