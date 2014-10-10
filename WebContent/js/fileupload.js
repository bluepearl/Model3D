/**
 * 
 */
$(document).ready(function() {
		$('#loadBrowser').uploadify({
			//以下参数均是可选
			'swf' : 'js/uploadify.swf', //指定上传控件的主体文件，默认‘uploader.swf’
			'uploader' : 'UploadServlet', //指定服务器端上传处理文件，默认‘upload.php’
			'cancelImg' : 'image/uploadify-cancel.png', //指定取消上传的图片，默认‘cancel.png’
			'queueID' : 'fileList', // 和存放队列的DIV的id一致
			'auto' : false, //选定文件后是否自动上传，默认false
			'fileDataName' : 'file', // 和以下input的name属性一致
			'buttonClass' : 'blue',
			'buttonText' : '选择文件', // 按钮上的文字
			'simUploadLimit' : 1, // 一次同步上传的文件数目
			'multi' : false, //是否允许同时上传多文件，默认false
			'fileTypeDesc' : 'rar文件或zip文件', //出现在上传对话框中的文件类型描述
			'fileTypeExts' : '*.rar;*.zip', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
			'sizeLimit' : 86400, //控制上传文件的大小，单位byte
			'displayData' : 'percentage',
			 onUploadComplete : function() {
				alert("文件上传成功");
			},
			onUploadError : function(event, queueID, fileObj) {
				alert("文件:" + fileObj.name + "上传失败");
			}
		});
		$('#uploadFile').click(function() {
			$('#loadBrowser').uploadifyUpload();
		});
	});