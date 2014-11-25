/**
 * 此文件存放母版页所需要的各种函数
 */


	var subContent=new Array();       //记录子页面的名称
	subContent[0]="hostPage";
	subContent[1]="modelView";
	subContent[2]="computeTask";
	subContent[3]="computeResult";
	var sourceLocation=new Array();
	sourceLocation[0]="fromlocal";
	sourceLocation[1]="fromDB";
	//-----------------------------------------------------------------------------
	/*
	 * selectSubContent函数是mainpage页面的各个子页面切换的函数
	 */
	function selectSubContent(showContent){
    // 操作标签
    	var i=0;
    	for(i=0;i<4;i++){
    		document.getElementById(subContent[i]).style.display="none"; 
    	}
    	document.getElementById(subContent[showContent]).style.display="block";
	}
	/*
	 * startTime函数是用于动态显示当前时刻的函数
	 */
	function startTime() {
		var today = new Date();
		var week = new Array("Sunday", "Monday", "Tuesday", "Wednesday",
				"Thursday", "Friday", "Saturday");
		var year = today.getFullYear();
		var month = today.getMonth() + 1;
		var date = today.getDate();
		var day = today.getDay();
		var h = today.getHours();
		var m = today.getMinutes();
		var s = today.getSeconds();
		// add a zero in front of numbers<10
		h = checkTime(h);
		m = checkTime(m);
		s = checkTime(s);
		document.getElementById('day').innerHTML = " " + year + "." + month+ "." + date+" " + week[day]+" " + h + ":" + m + ":" + s+ " ";
		t = setTimeout('startTime()', 500);
	};
	/*
	 *checkTime函数服务于startTime函数，是用于显示时间在未满10的数字前添加0 
	 */
	function checkTime(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	};
	
	/*
	 * selectTab函数是mainpage页面下上传任务子页面的弹出模态页面切换模型文件来源的函数
	 */
	function selectTab(showSource,e){
		e=e||window.event;
		e.target=e.target||e.srcElement;
		var i=0;
		var x=document.getElementById("modelsource");
		var y=x.getElementsByTagName("li");			
		while(i<2){
			y[i].className="";
			i++;
		}
		e.target.className="selected";
		
		var j=0;
    	for(j=0;j<2;j++){
    		document.getElementById(sourceLocation[j]).style.display="none"; 
    	}
    	document.getElementById(sourceLocation[showSource]).style.display="block";
	}
	
	
	/*
	 * modelWindowClose、modelWindowShow函数是mainpage页面下计算任务子页面的上传任务弹出模态页面和关闭的函数
	 */	
	function modelWindowClose()
	{
		document.getElementById("PageCover").style.display="none";
		document.getElementById("ks-component702").style.display="none";
	}
	function modelWindowShow()
	{
		document.getElementById("PageCover").style.display="block";
		document.getElementById("ks-component702").style.display="block";
		document.getElementById("ks-component702").focus();
	}