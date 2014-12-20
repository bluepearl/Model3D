<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
if (request.getAttribute("list") == null ) {
%>
<jsp:forward page="/PrepareforMainpage">
<jsp:param value="1" name="getdata"/>
</jsp:forward>
<% 
}
%>
<html>
<head>
<meta charset="utf-8">
<title>电磁兼容模型管理平台</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="robots" content="">
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<link rel="stylesheet" href="css/style.css" media="all">
<!--[if IE]><link rel="stylesheet" href="css/ie.css" media="all" /><![endif]-->
<!--[if lt IE 9]><link rel="stylesheet" href="css/lt-ie-9.css" media="all" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" href="css/IE/ie7.css" media="all" /><![endif]-->
<!--[if IE 8]><link rel="stylesheet" href="css/IE/ie8.css" media="all" /><![endif]-->
</head>
<body>
	<section class="widget small">
		<header> 
			<span class="icon">🔾</span>
			<hgroup>
				<h1>近期使用模型</h1>
				<h2>详情查看模型查看</h2>
			</hgroup>
			<aside>
				<span>
					<a href="#">⚙</a>
				</span>
			</aside>
		</header>
		<div class="content">
			<section class="stats-wrapper">
				<div class="stats">
					<p><span style="font-size:24px">plane.model</span></p>
					<p>位于节点DB_Site1上</p>
				</div>
				<div class="stats">
					<p><span style="font-size:24px">NO-747.model</span></p>
					<p>位于节点DB_Site2上</p>
				</div>
			</section>
			<section class="stats-wrapper">
				<div class="stats">
					<p><span style="font-size:24px">25F.model</span></p>
					<p>位于节点DB_Site1上</p>
				</div>
				<div class="stats">
					<p><span style="font-size:24px">.model</span></p>
					<p>位于节点DB_Site3上</p>
				</div>
			</section>
		</div>
	</section>
</body>
</html>