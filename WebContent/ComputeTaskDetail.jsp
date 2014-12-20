<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>计算任务详情</title>
<link rel="stylesheet" href="css/style.css" media="all">
<link rel="stylesheet" href="css/ui.css" media="all">
<style>
body{overflow:hidden;background:#eee}
label+input{background:#eee;opacity: 0.5;width:80%}
</style>
<!--[if IE]><link rel="stylesheet" href="css/ie.css" media="all" /><![endif]-->
<!--[if lt IE 9]><link rel="stylesheet" href="css/lt-ie-9.css" media="all" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" href="css/IE/ie7.css" media="all" /><![endif]-->
<!--[if IE 8]><link rel="stylesheet" href="css/IE/ie8.css" media="all" /><![endif]-->
</head>
<body>
<body>
	<section class="widget" style="min-height:0px">
		<header> 
			<span class="icon">📈</span> 
			<hgroup>
				<h1>计算任务详情</h1>
				<h2>计算进度&任务信息</h2>
			</hgroup> 
			<aside>
				<input id="stlfilename" type="hidden" value=".stl">
				<button class="left-btn" style="font-size: 15px; background: #719e37" >删除本计算</button>
				<button class="right-btn"style="font-size: 15px; background: #719e37" >返回</button>
			</aside> 
		</header>
		<div class="content">
			<div role="dialog" aria-labelledby="ks-stdmod-header-ks-component702" style="width: 600px; height: 360px;  left: 35%; top:15%;position: absolute;z-index: 99999;" aria-hidden="false;">
				<h1 style="font-family: 'Open Sans', sans-serif;line-height: 23px;font-size: 16px;">基础信息</h1>
				<div>
					<br>
					<label style="color: #5daced;">任务号：</label><input  value="001" disabled="disabled">
					<br>
					<label style="color: #5daced;">任务名称：</label><input  value="磁场畸变" disabled="disabled">
					<br>
					<label style="color: #5daced;">创建时间：</label><input  value="2014.11.20 11:28:54" disabled="disabled">
					<br>
					<label style="color: #5daced;">包含文件：</label><input  value="001" disabled="disabled">
					<br>
					<label style="color: #5daced;">存储路径：</label><input  value="001" disabled="disabled">
					<br>
				</div>
				<h1 style="font-family: 'Open Sans', sans-serif;line-height: 23px;font-size: 16px;">进度信息</h1>
				<br>
				<div>
					<label style="color: #5daced;">任务状态：</label><input  value="正在执行" disabled="disabled">
					<br>
					<label style="color: #5daced;">开始时间：</label><input  value="2014.11.22 11:28:54" disabled="disabled">
					<br>
					<label style="color: #5daced;">完成度：&nbsp
							<div id="progressbar5" class="ui-progressbar ui-widget ui-widget-content ui-corner-all" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="38" style="width: 80%;height: 15px;display: inline-block;margin-bottom: 0;">
								<div class="ui-progressbar-value ui-widget-header ui-corner-left" style="width: 38%;height: 15px;">
								</div>
							</div>
					
				</div>
				<br>
				<h1 style="font-family: 'Open Sans', sans-serif;line-height: 23px;font-size: 16px;">计算结果</h1>
				<br>
				<div>
					<label style="color: #5daced;">完成时间：</label><input  value="尚未完成" disabled="disabled" >
					<br>
					<label style="color: #5daced;">结果位置：</label><input  value="D：\ComputeResult" disabled="disabled" style="display:inline;width:70%">
					<input type="button" class="blue" value="查看" style="width:50px;display:inline;"><br>
					<label style="color: red;">未完成不能查看</label>
					<br>
				</div>
			</div>
		</div>
	</section>
</body>
</body>
</html>