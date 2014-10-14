/**
 * 此文件存放母版页所需要的各种函数
 */


	var subContent=new Array();       //记录子页面的名称
	subContent[0]="hostPage";
	subContent[1]="modelView";
	subContent[2]="computeTask";
	subContent[3]="computeResult";
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