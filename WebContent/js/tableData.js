/**
 * 
 */
var trNum=0;
var model=new Array();
model[0]="ball.stl";
model[1]="slotted_disk.stl";
model[2]="pr2_head_pan.stl";
model[3]="f002.stl";
var modelindex;
var modelname;
$(document).ready(function () {
	
    //奇偶行不同颜色
	$("#myTable tbody tr:odd").addClass("odd");
	
    //或者
    //$("#table2 tbody tr:odd").css("background-color", "#bbf"),
    //$("#table2 tbody tr:even").css("background-color", "#ffc")
});

function tableclick(rowindex){
	//$("#mytable td").click(
			//function() {
				//var trSeq = $(this).parent().find("tr").index($(this)[0]);
				 //$(this).parent().parent().find('tr').index($(this).parent()[0])+ 1; 
				//var trSeq = $(this).parent().parent().find("tr").index($(this).parent()[0]);
	trNum=rowindex+1;
	modelindex=rowindex;

		//alert("第" + (modelindex) + "行");
	//threeStart();
		//})
}
function viewmodel(rowindex)
{
	
	var x=document.getElementById("filename");
	var y=x.getElementsByTagName("tr");
	var z=y[rowindex].getElementsByTagName("td");
	var m=z[1].innerHTML;
	//alert("第" + (m) + "行");
	window.open('testmodel.jsp?modelname='+m);
}
function quickview(rowindex)
{
	
	var x=document.getElementById("searchlist");
	var y=x.getElementsByTagName("tr");
	var z=y[rowindex].getElementsByTagName("td");
	var m=z[2].innerHTML;
	//alert("第" + (m) + "行");
	window.open('testmodel.jsp?modelname='+m);
}
