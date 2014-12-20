<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>电磁兼容模型平台</title>
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="robots" content="">
	<link rel="stylesheet" href="css/style.css" media="all">
	<link rel="stylesheet" href="css/ui.css" media="all">
	<!--[if IE]><link rel="stylesheet" href="css/ie.css" media="all" /><![endif]-->
	<script src="js/jquery-1.11.1.min.js"></script>
</head>
<body style="padding-top:0px">
			<div>
				<form action="UploadServlet" class="dropzone" id="my-awesome-dropzone"
					method="post">
					<div>
						选择模型：
						<p id="modelfile"></p>
						<input type="button" class="black" value="确定" style="display: none;">
						<ul id="modelsource" class="smallTab">
							<li onclick="selectTab(0)" class="selected">从本地选择</li>
							<li onclick="selectTab(1)">从节点选择</li>
						</ul>
						<div id="fromlocal"><br>
							<div id="modelfileList"></div>
							<br> <a href="javascript:void(0);" class="upload" title="选文件">选文件</a>
								<input style="" id="upload_modelfile" type="file" multiple="" name="upload_modelfile">
							
						</div>
						<br>
						<div id="fromDB" style="height: 150px; display: none">
							<table id="myTable" border="0" width="100">
								<thead>
									<tr>
										<th class="header fixedth">ID</th>
										<th class="header fixedth">模型文件</th>
										<th class="header fixedth">预览</th>
									</tr>
								</thead>
							</table>
							<div
								style="width: 550px; height: 100px; overflow-y: scroll; border-bottom: 1px solid #ddd;">
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
								</table>
							</div>
						</div>
					</div>
					<div id="profileList">
						上传工程：<br>
						<div id="profileList"></div>
						<br> <a href="javascript:void(0);" class="upload" title="选文件">选文件</a>
							<input style="" id="upload_profile" type="file" multiple="" name="upload_profile">
						
					</div>
					<br>
					<div id="confileList">
						配置文件：<br>
						<div id="confileList"></div>
						<br> <a href="javascript:void(0);" class="upload" title="选文件">选文件</a>
							<input style="" id="upload_confile" type="file" multiple="" name="upload_confile">
						
					</div>
					<br> <input class="blue" id="uploadProject"
						style="width: 80px; display: inline;" type="button" value="上传文件">
					<input class="blue" id="cancelUpload"
						style="width: 80px; margin-left: 25px; display: inline;"
						type="button" value="取消上传">
				</form>
			</div>
</body>
<script src="js/timerFunction.js"></script>
<script language="javascript" type="text/javascript">  
//防止客户端缓存文件，造成uploadify.js不更新，而引起的“喔唷，崩溃啦”  
document.write("<script type='text/javascript' "  
        + "src='js/jquery.uploadify.min.js?" + new Date()  
        + "'></s" + "cript>");  
</script>
<script src="js/swfobject.js"></script>
<script type="text/javascript" src="js/useruploadcustom.js"></script>
</html>
