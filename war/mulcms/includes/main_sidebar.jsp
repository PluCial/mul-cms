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
                <i class="fa fa-files-o"></i> <span>Page</span>
              </a>
            </li>
            <li class="<%=contentsType.equals("pageTemplate") ? "active" : "" %>">
              <a href="/mulcms/template/page/">
                <i class="fa fa-file-text"></i> <span>Template</span>
              </a>
            </li>
            <%-- <li class="treeview <%=contentsType.equals("pageTemplate") || contentsType.equals("widgetTemplate") ? "active" : "" %>">
              <a href="#">
                <i class="fa fa-files-o"></i>
                <span>Template</span>
                <i class="fa fa-angle-left pull-right"></i>
              </a>
              <ul class="treeview-menu">
                <li class="<%=contentsType.equals("pageTemplate") ? "active" : "" %>"><a href="/mulcms/template/page/"><i class="fa fa-circle-o"></i> Page Template</a></li>
                <li class="<%=contentsType.equals("widgetTemplate") ? "active" : "" %>"><a href="/mulcms/template/widget/"><i class="fa fa-circle-o"></i> Widget Template</a></li>
              </ul>
            </li> --%>
            
            
            <li>
              <a href="#">
                <i class="fa fa-envelope"></i> <span>Mailbox</span>
                <small class="label pull-right bg-yellow">12</small>
              </a>
            </li>
            
            <li><a href="#"><i class="fa fa-book"></i> <span>Documentation</span></a></li>
            
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>