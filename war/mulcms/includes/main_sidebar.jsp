<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.App" %>
<%
String contentsType = request.getParameter("contentsType");
%>
	<aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

          <!-- sidebar menu: : style can be found in sidebar.less -->
          <ul class="sidebar-menu">
            <li class="header">MAIN NAVIGATION</li>
            <li class="<%=contentsType.equals("page") ? "active" : "" %>">
              <a href="/mulcms/page/">
                <i class="fa fa-files-o"></i> <span>ページ</span>
              </a>
            </li>
            
            
            <li class="<%=contentsType.equals("form") ? "active" : "" %>">
            	<a href="/mulcms/form/"><i class="fa fa-users"></i> <span>フォーム</span></a>
            </li>
            
            <li class="<%=contentsType.equals("page") ? "setting" : "" %>">
              <a href="/mulcms/setting/">
                <i class="fa fa-gears"></i> <span>設定</span>
              </a>
            </li>
            
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>