<%@page pageEncoding="UTF-8" isELIgnored="false" session="false" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>
<%@ page import="com.plucial.mulcms.App" %>
<!-- 	<header class="main-header">
        Logo
        <a href="/mulcms/" class="logo">
          mini logo for sidebar mini 50x50 pixels
          <span class="logo-mini"><b>Mul</b></span>
          logo for regular state and mobile devices
          <span class="logo-lg"><b>MulCMS</b></span>
        </a>
        Header Navbar: style can be found in header.less
        <nav class="navbar navbar-static-top" role="navigation">
          Sidebar toggle button
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              Control Sidebar Toggle Button
              <li>
                <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
              </li>
            </ul>
          </div>
        </nav>
      </header> -->
      <header class="main-header">               
        <nav class="navbar navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <a href="/mulcms/" class="navbar-brand"><b>MulCms</b></a>
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                <i class="fa fa-bars"></i>
              </button>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <!-- Navbar Right Menu -->
              <div class="navbar-custom-menu">
              	<ul class="nav navbar-nav">
                	<li><a href="/mulcms/page/add"><i class="fa fa-plus" aria-hidden="true"></i> ページを追加</a></li>
                	<li><a href="/mulcms/setting/"><i class="fa fa-cogs" aria-hidden="true"></i> 設定</a></li>
              	</ul>
              </div><!-- /.navbar-custom-menu -->
          </div><!-- /.container-fluid -->
        </nav>
      </header>