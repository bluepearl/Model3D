/**
 * 
 */
var ProjectSuccessFlag=true;
var solver;
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
			else {
				solver=data;
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
		'fileTypeDesc' : '所有文件', //出现在上传对话框中的文件类型描述
		'fileTypeExts' : '*', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
		'sizeLimit' : 86400, //控制上传文件的大小，单位byte
		'displayData' : 'percentage',
		'onUploadSuccess':function(file,data,response){
			if (data == "error") {     
                //data就是服务器输出的内容。   
				ProjectSuccessFlag=false;
                alert("上传失败！重名工程文件存在！")
                //$('#uploadFile').uploadify('cancel', '*');   //取消所有上传的文件  
                }
			else {
				solver=data;
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
		'fileTypeDesc' : '所有文件', //出现在上传对话框中的文件类型描述
		'fileTypeExts' : '*', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
		'sizeLimit' : 86400, //控制上传文件的大小，单位byte
		'displayData' : 'percentage',
		'onUploadSuccess':function(file,data,response){
				if (data == "error") {
					// data就是服务器输出的内容。
					ProjectSuccessFlag = false;
					alert("上传失败！重名配置文件存在！")
					// $('#uploadFile').uploadify('cancel', '*');
					// //取消所有上传的文件
				} else {
					var param = data.split(',');
					taskname=param[0];
					solver=param[1];
				}
				if(ProjectSuccessFlag)
				{
					if(solver){
						NewComputeTask('/Model3D/NewComputeTask?taskname='+taskname+'&solver='+solver+'&time='+(new Date()).Format('yyyy-MM-dd'));
					}
					else alert(solver);//"上传的并非可计算的任务文件"
				}
				else alert("project upload failed!")
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

//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}
