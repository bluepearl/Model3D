/**
 * 
 */
var xmlhttp;
function NewComputeTask(url)
{
xmlhttp=null;
if (window.XMLHttpRequest)
  {// code for IE7, Firefox, Opera, etc.
  xmlhttp=new XMLHttpRequest();
  }
else if (window.ActiveXObject)
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
if (xmlhttp!=null)
  {
  xmlhttp.onreadystatechange=state_Change;
  xmlhttp.open("GET",url,true);
  xmlhttp.send(null);
  }
else
  {
  alert("Your browser does not support XMLHTTP.");
  }
}

function state_Change()
{
if (xmlhttp.readyState==4)
  {// 4 = "loaded"
  if (xmlhttp.status==200)
    {// 200 = "OK"
	  var param= (xmlhttp.responseText).split(',');
	  //taskid,submittime,taskname,status,solver
	  AddOneRecordtoProject(param[0],param[1],param[2],param[3],param[4]);
    }
  else
    {
    alert("Problem retrieving XML data:" + xmlhttp.statusText);
    }
  }
}

/*
 * AddOneRecordtoProject
 */	
function AddOneRecordtoProject(taskid,submittime,taskname,status,solver)
{
	/*0.任务1.任务名称2.时间3.Solver4.进度5.删除6.启动*/
	var x=window.parent.document.getElementById("computeTask");
	var y=x.getElementsByTagName("table")[0].insertRow(1);
	var z=new Array();
	for(var i=0;i<7;i++)
	{
		z[i]=y.insertCell(i);
	}
	var c = document.createElement("input");   
    c.setAttribute("type","checkbox");    
	z[0].appendChild(c);
    z[0].innerHTML=taskid;
    z[1].innerHTML=taskname;
    z[2].innerHTML=submittime;
    z[3].innerHTML=solver;
    if(status=='0'){
	    var statusimage=document.createElement("img");
	    statusimage.src="image/waiting2.gif";
	    statusimage.alt="waiting";
	    statusimage.height=20;
	    statusimage.width=20;
	    z[4].appendChild(statusimage);
    }
    else{
    	z[4].innerHTML="已完成";
    }
    var a = document.createElement("a");   
    a.innerHTML="delete";    
    z[5].appendChild(a);
    var b = document.createElement("a");   
    b.innerHTML="start";    
    z[6].appendChild(b);
}

