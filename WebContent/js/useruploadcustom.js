/**
 * 
 */
var ProjectSuccessFlag=true;
$(document).ready(function() {
	$('#upload_modelfile').uploadify({
		//以下参数均是可选
		'swf' : 'js/uploadify.swf', //指定上传控件的主体文件，默认‘uploader.swf’
		'uploader' : 'UploadServlet', //指定服务器端上传处理文件，默认‘upload.php’
		'height' : 40,
		'width' : 80,
		'cancelImg' : 'image/uploadify-cancel.png', //指定取消上传的图片，默认‘cancel.png’
		'queueID' : 'modelfileList', // 和存放队列的DIV的id一致
		'auto' : false, //选定文件后是否自动上传，默认false
		'fileObjName' : 'upload_modelfile', // 和以下input的name属性一致
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
				ProjectSuccessFlag=false;
                alert("上传失败！重名模型文件存在！")
                //$('#uploadFile').uploadify('cancel', '*');   //取消所有上传的文件  
                }
			}
	});
	$('#upload_profile').uploadify({
		//以下参数均是可选
		'swf' : 'js/uploadify.swf', //指定上传控件的主体文件，默认‘uploader.swf’
		'uploader' : 'UploadServlet', //指定服务器端上传处理文件，默认‘upload.php’
		'height' : 40,
		'width' : 80,
		'cancelImg' : 'image/uploadify-cancel.png', //指定取消上传的图片，默认‘cancel.png’
		'queueID' : 'profileList', // 和存放队列的DIV的id一致
		'auto' : false, //选定文件后是否自动上传，默认false
		'fileObjName' : 'upload_profile', // 和以下input的name属性一致
		'buttonText' : '选择文件', // 按钮上的文字
		'simUploadLimit' : 1, // 一次同步上传的文件数目
		'multi' : false, //是否允许同时上传多文件，默认false
		'fileTypeDesc' : 'stl文件', //出现在上传对话框中的文件类型描述
		'fileTypeExts' : '*.pre;*.obj', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
		'sizeLimit' : 86400, //控制上传文件的大小，单位byte
		'displayData' : 'percentage',
		'onUploadSuccess':function(file,data,response){
			if (data == "error") {     
                //data就是服务器输出的内容。   
				ProjectSuccessFlag=false;
                alert("上传失败！重名工程文件存在！")
                //$('#uploadFile').uploadify('cancel', '*');   //取消所有上传的文件  
                }
			}
	});
	$('#upload_confile').uploadify({
		//以下参数均是可选
		'swf' : 'js/uploadify.swf', //指定上传控件的主体文件，默认‘uploader.swf’
		'uploader' : 'UploadServlet', //指定服务器端上传处理文件，默认‘upload.php’
		'height' : 40,
		'width' : 80,
		'cancelImg' : 'image/uploadify-cancel.png', //指定取消上传的图片，默认‘cancel.png’
		'queueID' : 'confileList', // 和存放队列的DIV的id一致
		'auto' : false, //选定文件后是否自动上传，默认false
		'fileObjName' : 'upload_confile', // 和以下input的name属性一致
		'buttonText' : '选择文件', // 按钮上的文字
		'simUploadLimit' : 1, // 一次同步上传的文件数目
		'multi' : false, //是否允许同时上传多文件，默认false
		'fileTypeDesc' : 'stl文件', //出现在上传对话框中的文件类型描述
		'fileTypeExts' : '*.cfm;*.obj', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
		'sizeLimit' : 86400, //控制上传文件的大小，单位byte
		'displayData' : 'percentage',
		'onUploadSuccess':function(file,data,response){
			if (data == "error") {     
                //data就是服务器输出的内容。   
				ProjectSuccessFlag=false;
                alert("上传失败！重名配置文件存在！")
                //$('#uploadFile').uploadify('cancel', '*');   //取消所有上传的文件  
                }
			if(ProjectSuccessFlag)
				{
					//AddOneRecordtoProject();
				}
			}
	});
	$('#uploadProject').click(function() {
		$('#upload_modelfile').uploadify('upload');
		$('#upload_profile').uploadify('upload');
		$('#upload_confile').uploadify('upload');
	});
	$('#cancleUpload').click(function() {
		$('#upload_modelfile').uploadify('cancle');
		$('#upload_profile').uploadify('cancle');
		$('#upload_confile').uploadify('cancle');
		
	});
});