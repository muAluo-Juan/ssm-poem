<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
	<title>欢迎来到夜雨时</title>
</head>
<body>
	<div class="header">
    	<nav class="nav navbar fixed-top ">
      		<div class="container">
      			<span>欢迎登录，管理员${sessionScope.admin.userName}</span>
        		<a class="navbar-brand" href="${pageContext.request.contextPath}/login/loginOut">退出登录</a>
        		<div id="navbarResponsive" class="collapse navbar-collapse">
          			<ul id="menu-nav-menu" class="navbar-nav ml-auto">
            			<a title="首页" href="#" class="nav-link" aria-current="page">首页</a>
            			<a title="个人原创" href="#" class="nav-link">诗歌管理</a>
            			<a title="语文百科" href="#" class="nav-link">游戏管理</a>
            			<a title="转载文摘" href="#" class="nav-link">社区管理</a>
            			<a title="关于本站" href="#" class="nav-link">用户管理</a>
          			</ul>
        		</div>
      		</div>
    	</nav>
    	<div class="banner">
      		<div class="overlay"></div>
      		<div class="content text-center" style="background-image: url(${pageContext.request.contextPath}/images/w6.jpg);">
        		<div class="introduce animated fadeInUp">
          			<div class="title">时间是如此的浅</div>
          			<div class="mate">一举步便踏到明天</div>
        		</div>
      		</div>
    	</div>
  </div>
  <div class="poem">
    <div class="content">
      <div class="title animated fadeInUp">
        <a href="./poem.html" class="text">诗歌管理</a>
      </div>
    </div>
  </div>
  <div class="game">
    <div class="content">
      <div class="title animated fadeInUp">
        <a href="./game.html" class="text">游戏管理</a>
      </div>
    </div>
  </div>
  <div class="community">
    <div class="content">
      <div class="title animated fadeInUp">
        <a href="./community.html" class="text">社区管理</a>
      </div>
    </div>
  </div>
  <div class="footer">
    <p>COPYRIGHT © 2020 夜雨时. 第四小组.</p>
  </div>
</body>
</html>