<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电磁 electronic model manage platform</title>
<script type="text/javascript">
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
		document.getElementById('day').innerHTML = " " + year + "." + month
				+ "." + date;
		document.getElementById('week').innerHTML = " " + week[day];
		document.getElementById('time').innerHTML = " " + h + ":" + m + ":" + s
				+ " ";
		t = setTimeout('startTime()', 500);
	};

	function checkTime(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	};
</script>
</head>
<body onload="startTime()">
	<div style="height: 700px; width: 1240px">
		<div
			style="background-image: url(./image/background_title.jpg); width: 80%; height: 20%; position: relative; left: 10%;">
			<div style="width: 70%; height: 100%; display: inline;" align="left">
				<label style="font-size: 36px; position: relative; top: 30%; left: 5%;">electronic model manage platform</label>
			</div>
			<div style="width: 30%; height: 100%; float: right" align="justify">
				<button style="background-image: url(./image/button_logout.jpg); height: 80px; width: 72px; margin-top: 30px"></button>
				<div style="width: 200px; float: right; margin-top: 30px;">
					<label
						style="display: block; font-style: italic; color: wheat; font-size: large;">User1</label>
					<label id="day" style="display: block; font-family: cursive; font-size: small; color: whitesmoke; margin-top: 10px;">2014.07.24</label>
					<label id="week" style="font-family: cursive; color: whitesmoke;">Thursday,</label>
					<label id="time" style="font-family: cursive; color: whitesmoke;">15:37:30</label>
				</div>
			</div>
		</div>
		<div
			style="width: 80%; height: 80%; background: Gainsboro; position: relative; left: 10%;">
			<div style="width: 30%; height: 90%; float: left">
				<fieldset title="存储信息" style="position: relative; width: 90%; margin: 20px 13px;">
					<div>
						<label>存储节点:</label> <select>
							<!-- <option label=""> -->
							<option label="DB_Site1"></option>
							<option label="DB_Site2"></option>
							<option label="DB_Site3"></option>
						</select>
					</div>
					<div>
						<label>表:</label> <select>
							<!-- <option label=""> -->
							<option label="Tabel1"></option>
							<option label="Tabel2"></option>
							<option label="Tabel3"></option>
						</select>
						<a href="myprotocol:">test</a>  
					</div>
				</fieldset>
				<div >
					<table style="width: 90%; position: relative; margin: 0 auto;">
						<thead title="Gridfile">
							<tr style="font-size: smaller;">
								<th>文件编号</th>
								<th>网格文件</th>
							</tr>
							<tr>
								<td style="background: aliceblue;">1</td>
								<td style="background: aliceblue;">gridfile1</td>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div style="width: 70%; height: 90%; float: left">
				<div id="optionBar" style="margin-left: 40px;">
					<input type="button" style="background: url(./image/execute.JPG); height: 25px; width: 30px;">
				</div>
				<canvas style="width: 90%;height: 90%;margin: 20px 40px;background: antiquewhite;"></canvas>
			</div>
		</div>
	</div>
</body>
</html>