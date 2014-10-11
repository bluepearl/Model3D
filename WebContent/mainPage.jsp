
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
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<link rel="stylesheet" href="css/style.css" media="all">

<!--[if IE]><link rel="stylesheet" href="css/ie.css" media="all" /><![endif]-->
<!--[if lt IE 9]><link rel="stylesheet" href="css/lt-ie-9.css" media="all" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" href="css/IE/ie7.css" media="all" /><![endif]-->
<!--[if IE 8]><link rel="stylesheet" href="css/IE/ie8.css" media="all" /><![endif]-->
</head>
<body onload="startTime()"> 
<div class="testing">
<header class="main">
	<h1><strong>电磁兼容模型</strong> 管理平台</h1>
	<span class="button blue" style="margin-top: -23px; float: right; position: relative;" onclick="window.open('SearchFile.jsp?filename='+document.getElementById('search').value)">查询</span>
	<input type="text" value="模型名称" id="search">
</header>
<section class="user" >
	<div class="profile-img">
		<p><img src="image/button_logout.jpg" alt="" height="40" width="40"> 您好！用户</p>
		<p id="day" style="display:inline">2014.07.24</p>
	</div>
	<div class="buttons">
		<button class="ico-font">⏶</button>
		<span class="button">设置</span>
		<span class="button">帮助</span>
		<span class="button blue"><a href="login.jsp">登出</a></span>
	</div>
</section>
</div>
<nav>
	<ul>
		<li onclick="selectSubContent(0);"><a><span class="icon">🛇</span> 我的首页</a></li>
		<li onclick="selectSubContent(1);"><a><span class="icon">📄</span> 模型查看</a></li>
		<li onclick="selectSubContent(2);"><a><span class="icon">🌄</span> 计算任务管理 <span class="pip">7</span></a></li>
		<li onclick="selectSubContent(3);"><a><span class="icon"></span> 计算结果管理 <span class="pip">12</span></a>
		</li>
		<li></li>
	</ul>
</nav>
<div id="alert">
	<section class="alert">
		<div class="green">	
			<p>当前模型计算进行中 <a href="#">3 项</a>和等待计算 <a href="#">7项</a>,详情参看计算任务管理</p>
				<span class="close" onclick="document.getElementById('alert').close();">✖</span>
			</div>
	</section>
</div>
<section id="hostPage" class="content">
	<div class="widget-container">
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

		<section class="widget small">
			<header> 
				<span class="icon">🕫</span>
				<hgroup>
					<h1>近期完成计算任务</h1>
					<h2>详情查看计算结果管理</h2>
				</hgroup>
				<aside>
					<span>
						<a href="#">⚙</a>
					</span>
				</aside>
			</header>
			<div class="content no-padding timeline">
				<table id="myTable" border="0" width="100">
				<thead>
					<tr>
						<th class="header">任务标号</th>
						<th class="header">任务名称</th>
					</tr>
				</thead>
					<tbody>
						<tr class="odd">
							<td><input type="checkbox"> 1</td>
							<td>歼-25模型磁场</td>
						</tr>
						<tr>
							<td><input type="checkbox"> 3</td>
							<td>隐形侦察机模型红外场畸变</td>
						</tr>
						<tr class="odd">
							<td><input type="checkbox"></td>
							<td></td>

						</tr>
					</tbody>
				</table>
			</div>
		</section>
	</div>
</section>
<section id="modelView" class="content" style="display:none">
	<div>
		<section class="widget" style="min-height:150px;height:150px"> 
			<header> 
				<span class="icon">📈</span> 
				<hgroup>
					<h1>存储节点</h1>
					<h2>选择存储节点以及表</h2>
				</hgroup> 
				<aside></aside> 
			</header>
			<div class="content">
			<form action="PrepareforMainpage" method="post">
				<label>存储节点:</label> 
				<select>
					<!-- <option label=""> -->
					<option label="DB_Site1"></option>
					<option label="DB_Site2"></option>
					<option label="DB_Site3"></option>
				</select>
				<label>表:</label> 
				<select>
					<!-- <option label=""> -->
					<option label="Tabel1"></option>
					<option label="Tabel2"></option>
					<option label="Tabel3"></option>
				</select>
				<button class="black">加载</button>
				</form>
			</div>
		</section>
		<section class="widget" style="min-height:300px;height:auto" > 
			<header> 
				<span class="icon">📈</span> 
				<hgroup>
					<h1>模型文件</h1>
					<h2>当前表中文件列表</h2>
				</hgroup> 
				<aside>
					<button class="right-btn" style="font-size: 10px; background: #719e37;margin-top:-15px" onclick="window.open('fileUpload.jsp')">添加</button>
				</aside> 
			</header>
			<div class="content no-padding timeline" style="margin-left:20px">
			<table id="myTable" border="0" width="80px">
				<thead>
					 <tr>
						<th class="header" style="min-width:50px;padding-left:20px;"><input type="checkbox">编号</th>
						<th class="header" style="min-width:50px;padding-left:20px;">模型文件</th>
						<th class="header" style="min-width:50px;padding-left:20px;">查看</th>
					</tr>
				</thead>
				<tbody id="filename">
					<c:forEach items="${list}" var="item" varStatus="status">
					<tr>
						<td><input type="checkbox">${item[0]}</td>
						<td>${item[1]}</td>
						<td><span onclick="viewmodel(${status.index})"><a href="#" >查看模型</a></span></td>
					</tr>
				</c:forEach>
					<!-- 					<tr class="odd">
						<td><input type="checkbox"> 1</td>
						<td>歼-25模型</td>
					</tr>
					<tr>
						<td><input type="checkbox"> 2</td>
						<td>波音747模型</td>
					</tr>
					<tr class="odd">
						<td><input type="checkbox"> 3</td>
						<td>隐形侦察机模型</td>
					</tr>
					<tr>
						<td><input type="checkbox"> 4</td>
						<td>雄鹿直升机模型</td>
					</tr>
					<tr class="odd">
						<td><input type="checkbox"> 5</td>
						<td></td>
					</tr>
 -->
				</tbody>
			</table>
		</div>
		</section>
	</div>

</section>
<section id="computeTask" class="content"  style="display:none">
	<div class="widget-container">
		<section class="widget 	small">
			<header>
				<span class="icon">🔿</span>
				<hgroup>
					<h1>已完成计算</h1>
					<h2>查看计算结果</h2>
				</hgroup>
				<aside>
					<span>
						<a href="#">⚙</a>
					</span>
				</aside>
			</header>
			<div class="content">
			<table id="myTable" border="0" width="100">
				<thead>
					<tr>
						<th class="header">任务标号</th>
						<th class="header">时间</th>
						<th class="header">任务名称</th>
					</tr>
				</thead>
				<tbody>
					<tr class="odd">
						<td><input type="checkbox"> 1</td>
						<td>01/3/2013</td>
						<td>歼-25模型磁场</td>
					</tr>
					<tr>
						<td><input type="checkbox"> 3</td>
						<td>07/3/2013</td>
						<td>隐形侦察机模型红外场畸变</td>
					</tr>
					<tr class="odd">
						<td><input type="checkbox"></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<button type="submit" class="green">查看</button> 
			</div>
		</section>
		
		<section class="widget small">
			<header>
				<span class="icon"></span>
				<hgroup>
					<h1>等待计算任务</h1>
					<h2>计算节点排队中……</h2>
				</hgroup>
				<aside>
					<span>
						<a href="#">⚙</a>
					</span>
				</aside>
			</header>
			<div class="content no-padding timeline">
				<div class="tl-post comments">
					<span class="icon"></span>
					<p>
						<a href="#">Task 1.雄鹿直升机气流场</a>
						<span class="reply"><input type="text" value="排队等待中..."></span>
					</p>
				</div>
				<div class="tl-post comments">
					<span class="icon"></span>
					<p>
						<a href="#"></a>
						<span class="reply"><input type="text" value=""></span>
					</p>
				</div>
				<span class="show-more"><a href="#">More</a></span>
			</div>
		</section>
	</div>
</section>
<section  id="computeResult" class="content"  style="display:none">
	<section class="widget">
		<header>
			<span class="icon">📄</span>
			<hgroup>
				<h1>模型计算结果列表</h1>
				<h2>已完成计算，请查看</h2>
			</hgroup>
			<aside>
				<span>
					<a href="#">⚙</a>
				</span>
			</aside>
		</header>
		<div class="content">
			<table id="myTable" border="0" width="100">
				<thead>
					<tr>
						<th class="header">任务标号</th>
						<th class="header">时间</th>
						<th class="header">任务名称</th>
						<th class="header">任务状态</th>
						<th class="header">备注</th>
					</tr>
				</thead>
					<tbody>
						<tr class="odd">
							<td><input type="checkbox"> 1</td>
							<td>01/3/2013</td>
							<td>歼-25模型磁场</td>
							<td>已完成</td>
							<td>无</td>
						</tr>
						<tr>
							<td><input type="checkbox"> 2</td>
							<td>06/3/2013</td>
							<td>波音747模型磁场扭曲</td>
							<td>进行中</td>
							<td>无</td>
						</tr>
						<tr class="odd">
							<td><input type="checkbox"> 3</td>
							<td>07/3/2013</td>
							<td>隐形侦察机模型红外场畸变</td>
							<td>已完成</td>
							<td>优先计算</td>
						</tr>
						<tr>
							<td><input type="checkbox"> 4</td>
							<td>07/3/2013</td>
							<td>雄鹿直升机气流场</td>
							<td>等待中</td>
							<td>无</td>
						</tr>
						<tr class="odd">
							<td><input type="checkbox"> 5</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
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
<script src="js/flot-time.js"></script>
<script src="js/flot-pie.js"></script>
<script src="js/flot-graphs.js"></script>
<script src="js/cycle.js"></script>
<script src="js/jquery.tablesorter.min.js"></script>
<script src="js/timerFunction.js"></script>
<script src="js/tableData.js"></script>
<script type="text/javascript">
// Feature slider for graphs
$('.cycle').cycle({
	fx: "scrollHorz",
	timeout: 0,
    slideResize: 0,
    prev:    '.left-btn', 
    next:    '.right-btn'
});
</script>
</body>
</html>