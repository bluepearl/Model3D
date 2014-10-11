/**
 * 
 */
	var subContent=new Array();
	subContent[0]="hostPage";
	subContent[1]="modelView";
	subContent[2]="computeTask";
	subContent[3]="computeResult";
	function selectSubContent(showContent){
    // 操作标签
    	var i=0;
    	for(i=0;i<4;i++){
    		document.getElementById(subContent[i]).style.display="none"; 
    	}
    	document.getElementById(subContent[showContent]).style.display="block";
	}
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

	function checkTime(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	};