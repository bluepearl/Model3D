<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>3D模型查看</title>
<link rel="stylesheet" href="css/style.css" media="all">
<script type="text/javascript" charset="utf-8" src="WebGL/three.js"></script>
	<script src="WebGL/STLLoader.js"></script>
	<script src="WebGL/TrackballControls.js"></script>
	<script src="WebGL/AsciiEffect.js"></script>
	<style>body{overflow:hidden;background:#eee}</style>
<!--[if IE]><link rel="stylesheet" href="css/ie.css" media="all" /><![endif]-->
<!--[if lt IE 9]><link rel="stylesheet" href="css/lt-ie-9.css" media="all" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" href="css/IE/ie7.css" media="all" /><![endif]-->
<!--[if IE 8]><link rel="stylesheet" href="css/IE/ie8.css" media="all" /><![endif]-->
</head>
<script>	
	var WIDTH,HEIGHT;
	var	renderer;
	function initThree() {
		WIDTH = document.documentElement.clientWidth;<!--{foreach from=$recommended_goods item=rgoods}--> <!-- {/foreach} -->
		HEIGHT = document.documentElement.clientHeight;

		/* 渲染器 */
		renderer = new THREE.WebGLRenderer();
		renderer.setSize(WIDTH , HEIGHT);
		document.body.appendChild(renderer.domElement);
	}
	
	/* 摄像头 */
	var camera;
	function initCamera() {
		var VIEW_ANGLE = 45,
		ASPECT = WIDTH / HEIGHT,
		NEAR = 0.1,
		FAR = 10000;
	    camera = new THREE.PerspectiveCamera(VIEW_ANGLE, ASPECT, NEAR, FAR);
		camera.position.set(20, 0, 0);
		//设置视野的中心坐标
		camera.lookAt(scene.position);
	}
	
    /* 场景 */	
	var scene;
	function initScene() {
		scene = new THREE.Scene();
	}
	
	/* 灯光 */
	var light,light2,light3;
	function initLight() {
		//平行光
		light = new THREE.DirectionalLight(0xFFFFFF);
		light.position.set(0, 99, 0).normalize();
		scene.add(light);
		//环境光
		light2 = new THREE.AmbientLight(0x999999);
		scene.add(light2);
		//点光源
		light3 = new THREE.PointLight(0x00FF00);
        light3.position.set(300, 0, 0);
        scene.add(light3);
	}
	
	/* 显示对象 */
	var cube;
	var loadstlname=(GetRequest())["modelname"];

	function initObject(){
		// ASCII file
		var loader = new THREE.STLLoader();
		loader.addEventListener( 'load', function ( event ) {
			var loading = document.getElementById("Loading");
			container = document.createElement( 'div' );
			document.body.appendChild( container );
			loading.parentNode.removeChild(loading);
			
			var targetname=document.getElementById("stlfilename");
			targetname.value=loadstlname;
			
			var geometry = event.content;
			//geometry.computeVertexNormals();
			//var material = new THREE.MeshPhongMaterial( { ambient: 0xff5533, color: 0xff5533, specular: 0x111111, shininess: 200 } );
			//var material = new THREE.MeshBasicMaterial( { envMap: THREE.ImageUtils.loadTexture( 'http://localhost:8080/textures/metal.jpg', new THREE.SphericalReflectionMapping() ), overdraw: true } ) ;
			var material = new THREE.MeshLambertMaterial( { color:0xff5533, side: THREE.DoubleSide } );
			//var material = new THREE.MeshBasicMaterial( { map: THREE.ImageUtils.loadTexture( 'http://localhost:8080/textures/metal.jpg', null,function(t){}) } ) ;
			
			//var texture = THREE.ImageUtils.loadTexture("http://localhost:8080/textures/metal.jpg",null,function(t){});
			//var material = new THREE.MeshBasicMaterial( { map:texture } ) ;
			
			var mesh = new THREE.Mesh( geometry, material );

			var center = THREE.GeometryUtils.center(geometry);
			var boundbox=geometry.boundingBox;
			var vector3 = boundbox.size(null);
			var scale = vector3.length();
			camera.position.set(scale, 0, 0);
			camera.lookAt(scene.position);
			scene.add(camera);

			mesh.position.set(0,0,0);
			//mesh.position.x = scene.position.x;
			//mesh.position.y = scene.position.y ;
			//mesh.position.z = scene.position.z;
			scene.add(mesh);
		
			renderer.clear(); 
			renderer.render(scene, camera);
		} );
		loader.load( 'STLfile/'+loadstlname);
		
	}
	
	//控制
	var effect;
	var controls;
	function initControl(){
		effect = new THREE.AsciiEffect( renderer );
		effect.setSize( WIDTH, HEIGHT );
		controls = new THREE.TrackballControls( camera );
	}

	function animate() {	
		requestAnimationFrame( animate );
		controls.update();
		effect.render( scene, camera );
	}
	
	function threeStart() {
		initThree();
		initScene();
		initCamera();	
		initLight();
		initObject();
		initControl();
		animate();
	}
	function GetRequest() {
		   var url = location.search; //获取url中"?"符后的字串
		   var theRequest = new Object();
		   if (url.indexOf("?") != -1) {
		      var str = url.substr(1);
		      strs = str.split("&");
		      for(var i = 0; i < strs.length; i ++) {
		         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
		      }
		   }
		   return theRequest;
		}
</script>
<body onload="threeStart()">
	<section class="widget" style="min-height:0px">
		<header> <span class="icon">📈</span> <hgroup>
		<h1>3D模型查看</h1>
		<h2>模型预览&仿真计算</h2>
		</hgroup> <aside>
		<input id="stlfilename" type="hidden" value=".stl">
		<form method="link" action="myprotocol:">
		<button class="left-btn" style="font-size: 15px; background: #719e37" onclick="alert(document.getElementById('stlfilename').value+'已缓存于D:/STLfile')">新建计算</button>
		
			<button class="right-btn"
				style="font-size: 15px; background: #719e37">添加磁场</button>
		</form>
		</aside> </header>

		<div>
			<div id="Loading">loading……</div>
		</div>
	</section>
</body>
</html>