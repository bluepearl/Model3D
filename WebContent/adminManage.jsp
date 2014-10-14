<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="master.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
if (request.getAttribute("list") == null ) {
%>
<jsp:forward page="/PrepareForManage">
<jsp:param value="1" name="getdata"/>
</jsp:forward>
<% 
}
%>
<html>
<head>
	<meta charset="utf-8">
	<title>模型管理后台</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="robots" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="css/style.css" media="all" />
	<!--[if IE]><link rel="stylesheet" href="css/ie.css" media="all" /><![endif]-->
</head>
<body>
<section class="content">
	<section class="widget">
		<header>
		
			<span class="icon">&#128196;</span>
			<hgroup>
				<h1>审核</h1>
				<h2>用户上传网格文件审核</h2>
				<form method="post" action="PrepareForManage">
				</form>
			</hgroup>
			<aside>
			<form method="post" action="FileChecked">
					<!-- <a href="#">&#9881;</a> -->
					<button class="green">审核</button>
			</form>
			</aside>
			
		</header>
		<div class="content">
			<table id="myTable" border="0" width="100">
				<thead>
					<tr>
						<th class="header"><input type="checkbox">编号</th>
						<th class="header">模型文件</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="item">
					<tr>
						<td><input type="checkbox">${item[0]}</td>
						<td>${item[1]}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</section>
</section>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<script src="js/jquery.wysiwyg.js"></script>
<script src="js/custom.js"></script>
<script src="js/cycle.js"></script>
<script src="js/jquery.checkbox.min.js"></script>
<script src="js/flot.js"></script>
<script src="js/flot.resize.js"></script>
<script src="js/flot-graphs.js"></script>
<script src="js/flot-time.js"></script>
<script src="js/cycle.js"></script>
<script src="js/jquery.tablesorter.min.js"></script>
</body>
</html>