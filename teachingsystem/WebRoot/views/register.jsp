<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>注册</title>
	<link rel="stylesheet" href="../styles/register.css" />
	<script type="text/javascript" src="../js/jquery-2.1.4.min.js"></script>
	<style type="text/css">
			body{
				margin: 0 0 0 0;
				width:100%;
				height:100%; 
				font-family: "微软雅黑";
			}
			#bg{
				width:100%;
				height:100%; 
				background-image: url("../images/backgroundPic.jpg"); 
				filter:alpha(opacity=50); 
				-moz-opacity:0.5; 
				opacity: 0.5; 
				font-family:"微软雅黑";
				position:fixed;
			}
	</style>
</head>
<body>
	<div id="bg">			
	
	<div class="lg-container">
		<h2 align="center">教师注册</h2>
		<div  id="lg-form" name="lg-form" >
			<div>
				<label for="id">账户:</label>
				<input type="text" name="id" id="id" placeholder="账户,必须数字"/>
			</div>
			<div>
				<label for="name">昵称:</label>
				<input type="text" name="name" id="name" placeholder="昵称"/>
			</div>
			<div>
				<label for="password1">密码:</label>
				<input type="password" name="password1" id="password1" placeholder="16位以内密码" />
			</div>
			<div>
				<label for="password2">确认密码:</label>
				<input type="password" name="password2" id="password2" placeholder="确认密码" />
			</div>
			<div>
				<button type="button" id="register">注册</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$("#register").bind("click",function(){
				var id = $("#id").val();
				var name =$("#name").val();
				var password1=$("#password1").val();
				var password2=$("#password2").val();
				if(id==""||name==""||password1==""||password2==""){
					alert("请输入完整信息");
				}else{
					if(password1!=password2){
						alert("两次输入密码不一致");
					}else{
						$.post("register",{"id":id,"name":name,"password":password2},function(data){
							if(data=="true"){
								alert("注册成功");
								window.location.href="logout.jsp";
							}else{
								alert("注册失败,"+data);
							}		
						});
					}
				}
			});
		});
	</script>
	</div>
</body>
</html>
