
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
<link rel="stylesheet" href="css/ui.css" media="all">
<style type="text/css">
</style>
<script src="js/jquery-1.11.1.min.js"></script>
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
		<li class="section" onclick="selectSubContent(0);"><a><span class="icon">&#128711;</span> 我的首页</a></li>
		<li onclick="selectSubContent(1);"><a><span class="icon">&#59214;</span> 模型查看</a></li>
		<li onclick="selectSubContent(2);"><a><span class="icon">&#128187;</span> 计算任务管理 <span class="pip">7</span></a></li>
		<li onclick="selectSubContent(3);"><a><span class="icon">&#128228;</span> 计算结果管理 <span class="pip">12</span></a>
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
	<div class="widget-container">
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
		<section class="widget 	small" style="width:80%;margin-left:5%">
			<header>
				<span class="icon">🔿</span>
				<hgroup>
					<h1>计算任务列表</h1>
					<h2>上传计算任务&启动计算</h2>
				</hgroup>
				<aside>
					<button class="green" onclick="modelWindowShow()">+</button>
				</aside>
			</header>
			<div class="content">
			<table id="myTable" border="0" width="100">
				<thead>
					<tr>
						<th class="header"><input type="checkbox">任务</th>
						<th class="header">时间</th>
						<th class="header">Solver</th>
						<th class="header">进度</th>
						<th class="header">删除</th>
						<th class="header">启动</th>
					</tr>
				</thead>
				<tbody>
					<tr class="odd">
						<td><input type="checkbox"> 1</td>
						<td>01/3/2013</td>
						<td>歼-25模型磁场</td>
						<td>
							<div id="progressbar5" class="ui-progressbar ui-widget ui-widget-content ui-corner-all" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="38" style="width: 100px;height: 10px;">
								<div class="ui-progressbar-value ui-widget-header ui-corner-left" style="width: 38%;height: 10px;">
								</div>
							</div>
						</td>
						<td><a>delete</a></td>
						<td><a>start</a></td>
					</tr>
					<tr>
						<td><input type="checkbox"> 3</td>
						<td>07/3/2013</td>
						<td>隐形侦察机模型红外场畸变</td>
						<td>
							<div id="progressbar5" class="ui-progressbar ui-widget ui-widget-content ui-corner-all" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="38" style="width: 100px;height: 10px;">
								<div class="ui-progressbar-value ui-widget-header ui-corner-left" style="width: 60%;height: 10px;">
								</div>
							</div>
						</td>
						<td><a>delete</a></td>
						<td><a>start</a></td>
					</tr>
					<tr class="odd">
						<td><input type="checkbox"></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<button type="submit" class="black">删除</button> 
			<button type="submit" class="green">启动</button> 
			</div>
		</section>
		
<!-- 		<section class="widget small">
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
		-->
	</div>
</section>
<section  id="computeResult" class="content"  style="display:none">
	<div class="widget-container">
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
							<th class="header">备注</th>
							<th class="header">计算结果</th>
						</tr>
					</thead>
					<tbody>
						<tr class="odd">
							<td><input type="checkbox"> 1</td>
							<td>01/3/2013</td>
							<td>歼-25模型磁场</td>
							<td>无</td>
							<td><a href="#">查看</a></td>
						</tr>
						<tr>
							<td><input type="checkbox"> 2</td>
							<td>06/3/2013</td>
							<td>波音747模型磁场扭曲</td>
							<td>无</td>
							<td><a href="#">查看</a></td>
						</tr>
						<tr class="odd">
							<td><input type="checkbox"> 3</td>
							<td>07/3/2013</td>
							<td>隐形侦察机模型红外场畸变</td>
							<td>优先计算</td>
							<td><a href="#">查看</a></td>
						</tr>
						<tr>
							<td><input type="checkbox"> 4</td>
							<td>07/3/2013</td>
							<td>雄鹿直升机气流场</td>
							<td>无</td>
							<td><a href="#">查看</a></td>
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
	</div>
</section>
<div id="PageCover" style="width: 100%; left: 0px; top: 0px; height: 100%; position: fixed; -webkit-user-select: none; z-index: 99999;background: tan;filter: alpha(Opacity=80);-moz-opacity: 0.5;opacity: 0.5;display:none">
</div>
<div id="ks-component702" role="dialog" aria-labelledby="ks-stdmod-header-ks-component702" style="width: 600px; height: 360px;  left: 35%; top:30%;position: absolute;display:none;z-index: 99999;" aria-hidden="false;">
	<section class="widget" style="min-height: 360px;">
		<header>
			<span class="icon">&#59153;</span>
			<hgroup>
				<h1>任务上传</h1>
				<h2>新建上传任务</h2>
			</hgroup>
			<aside>
				<span onclick="modelWindowClose()">
					<a href="#">⚙</a>
				</span>
			</aside>
		</header>
		<div class="content">
			<div id="dropzone">
				<form action="UploadServlet" class="dropzone" id="my-awesome-dropzone" method="post">
					<div>选择模型：
						<p id="modelfile"></p>
						<input type="button" class="black" value="确定" style="display:none;">
						<ul id="modelsource" class="smallTab"  >
							<li onclick="selectTab(0)" class="selected">从本地选择</li>
							<li onclick="selectTab(1)">从节点选择</li>
						</ul>
						<div id="fromlocal"><br>
							<a href="javascript:void(0);" class="upload" title="选文件">选文件
								<input style="" id="upload_file" type="file" multiple="">
							</a>
						</div>
						<br>
						<div id="fromDB" style="height:150px;display:none">
							<table id="myTable" border="0" width="100">
								<thead>
									<tr>
										<th class="header fixedth">ID</th>
										<th class="header fixedth">模型文件</th>
										<th class="header fixedth">预览</th>
									</tr>
								</thead>
								</table>
								<div style="width:550px; height:100px;overflow-y: scroll;border-bottom:1px solid #ddd;">
								<table id="myTable" border="0" width="100">
								<tbody>
									<tr class="odd">
										<td><input type="radio" name="modelfileID"></td>
										<td>ball.stl</td>
										<td><a>查看</a></td>
									</tr>
									<tr>
										<td><input type="radio" name="modelfileID"></td>
										<td>ball.stl</td>
										<td><a>查看</a></td>
									</tr>
									<tr class="odd">
										<td><input type="radio" name="modelfileID"></td>
										<td>ball.stl</td>
										<td><a>查看</a></td>
									</tr>
								</tbody>
							</table></div>
						</div>
					</div>
					<div id="fileList">上传工程：<br><br>
						<a href="javascript:void(0);" class="upload" title="选文件">选文件
							<input style="" id="upload_file" type="file" multiple="">
						</a>
					</div>
					<br>
					<div id="fileList">配置文件：<br><br>
						<a href="javascript:void(0);" class="upload" title="选文件">选文件
							<input style="" id="upload_file" type="file" multiple="">
						</a>
					</div>
					<br>
					<input class="blue" id="uploadFile" style="width: 80px; display: inline;" type="button"  value="上传文件">
	  				<input class="blue" id="cancelUpload" style="width: 80px; margin-left: 25px; display: inline;" type="button" value="取消上传">
				</form>
			</div>
		</div>
	</section>
</div>


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