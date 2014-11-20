<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="master.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
if (request.getAttribute("list") == null ) {
%>
<jsp:forward page="/PrepareforSearch">
<jsp:param value="1" name="getdata"/>
</jsp:forward>
<% 
}
%>
<html>
<head>
	<meta charset="utf-8">
	<title>搜索结果</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="robots" content="" />
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="css/style.css" media="all" />
	<script src="js/jquery-1.11.1.min.js"></script>
	<!--[if IE]><link rel="stylesheet" href="css/ie.css" media="all" /><![endif]-->
</head>
<body>
<section class="content">
	<section class="widget">
			<header>
				<span class="icon"></span>
				<hgroup>
					<h1>搜索结果</h1>
					<h2>位于各个存储上的相应模型</h2>
				</hgroup>
				<aside>
					<span>
						<a href="#">⚙</a>
						<ul class="settings-dd">
							<li><label>Option a</label><div class="checkbox-wrap"><input type="checkbox" style="position: absolute; z-index: -1; visibility: hidden;"><span class="jquery-checkbox"><span class="mark"><img src="images/empty.png"></span></span></div></li>
							<li><label>Option b</label><div class="checkbox-wrap"><input type="checkbox" checked="checked" style="position: absolute; z-index: -1; visibility: hidden;"><span class="jquery-checkbox jquery-checkbox-checked"><span class="mark"><img src="images/empty.png"></span></span></div></li>
							<li><label>Option c</label><div class="checkbox-wrap"><input type="checkbox" style="position: absolute; z-index: -1; visibility: hidden;"><span class="jquery-checkbox"><span class="mark"><img src="images/empty.png"></span></span></div></li>
						</ul>
					</span>
				</aside>
			</header>
			<div class="content">
				<table id="myTable" border="0" width="100">
					<thead>
						<tr>
							<th class="header">序号</th>
							<th class="header">本地编号</th>
							<th class="header">名称</th>
							<th class="header">存储节点</th>
							<th class="header">查看</th>
						</tr>
					</thead>
					<tbody id="searchlist">
						<c:forEach items="${list}" var="item" varStatus="status">
							<tr>
								<td>${status.index+1}</td>
								<td>${item[0]}</td>
								<td>${item[1]}</td>
								<td>DBsite1<td>
								<td><span onclick="quickview(${status.index})"><a href="#" >查看模型</a></span></td>
							</tr>
						</c:forEach>
				</tbody>
			</table>
			</div>
		</section>
	</section>
<script src="js/jquery.wysiwyg.js"></script>
<script src="js/custom.js"></script>
<script src="js/cycle.js"></script>
<script src="js/jquery.checkbox.min.js"></script>
<script src="js/flot.js"></script>
<script src="js/flot.resize.js"></script>
<script src="js/flot-time.js"></script>
<script src="js/flot-pie.js"></script>
<script src="js/flot-graphs.js"></script>
<script src="js/cycle.js"></script>
<script src="js/jquery.tablesorter.min.js"></script>
<script src="js/timerFunction.js"></script>
<script src="js/tableData.js"></script>
</body>
</html>