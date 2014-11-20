<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>电磁兼容模型管理平台</title>
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="robots" content="">
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
	<link rel="stylesheet" href="css/style.css" media="all">
	<link rel="stylesheet" href="css/dropzone.css" media="all">
	<link rel="stylesheet" href="css/uploadify.css" type="text/css"></link>
	<!--[if IE]><link rel="stylesheet" href="css/ie.css" media="all" /><![endif]-->
	<script src="js/jquery-1.11.1.min.js"></script>
</head>
<body  onload="startTime()">
<div class="testing">
	<header class="main">
		<h1><strong>电磁兼容模型</strong> 管理平台</h1>
		<input type="text" value="搜索">
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
<section class="content">
	<section class="widget">
		<header>
			<span class="icon">&#59153;</span>
			<hgroup>
				<h1>文件上传</h1>
				<h2>选择上传模型文件、设置上传地点</h2>
			</hgroup>
		</header>
		<div class="content">
			<div id="dropzone">
				<form action="UploadServlet" class="dropzone" id="my-awesome-dropzone" method="post">
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
					<div id="fileList"></div>
					<input id="loadBrowser" name="file" type="file">
					<input class="blue" id="uploadFile" style="width: 80px; display: inline;" type="button"  value="上传文件">
	  				<input class="blue" id="cancelUpload" style="width: 80px; margin-left: 25px; display: inline;" type="button" value="取消上传">
				</form>
			</div>
		</div>
	</section>
</section>
<script src="js/jquery.wysiwyg.js"></script>
<script src="js/custom.js"></script>
<script src="js/jquery.checkbox.min.js"></script>
<script src="js/jquery.tablesorter.min.js"></script>
<script src="js/timerFunction.js"></script>
<script type="text/javascript" src="js/jquery.uploadify.min.js"></script>
<script src="js/swfobject.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#loadBrowser').uploadify({
		//以下参数均是可选
		'swf' : 'js/uploadify.swf', //指定上传控件的主体文件，默认‘uploader.swf’
		'uploader' : 'UploadServlet', //指定服务器端上传处理文件，默认‘upload.php’
		'cancelImg' : 'image/uploadify-cancel.png', //指定取消上传的图片，默认‘cancel.png’
		'queueID' : 'fileList', // 和存放队列的DIV的id一致
		'auto' : false, //选定文件后是否自动上传，默认false
		'fileObjName' : 'file', // 和以下input的name属性一致
		'buttonText' : '选择文件', // 按钮上的文字
		'simUploadLimit' : 1, // 一次同步上传的文件数目
		'multi' : false, //是否允许同时上传多文件，默认false
		'fileTypeDesc' : 'stl文件', //出现在上传对话框中的文件类型描述
		'fileTypeExts' : '*.stl;*.obj', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
		'sizeLimit' : 86400, //控制上传文件的大小，单位byte
		'displayData' : 'percentage',
		'onUploadSuccess':function(file,data,response){
			if (data == "error") {     
                //data就是服务器输出的内容。   
                alert("上传失败！重名文件存在！")
                //$('#uploadFile').uploadify('cancel', '*');   //取消所有上传的文件  
                }
			}
	});
	$('#uploadFile').click(function() {
		$('#loadBrowser').uploadify('upload');
	});
	$('#cancleUpload').click(function() {
		$('#loadBrowser').uploadify('cancle');
	});
});
</script>
</body>
</html>