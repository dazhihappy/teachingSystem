<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>		
		<title>登录</title>
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
			}
			#login {
				box-shadow: #666 4px 4px 10px;
				height:300px; 
				width:600px; 
				background-color:#f3f5f5; 
				position: absolute; left: 390px; top:220px;
				}
			#form  {
				border: 1px solid #999;
				height:90px; 
				width:260px; 
				background-color:#ffffff; 
				margin:60 0 0 155; 
				}
			#name {
				height:100%; 
				width:80%; 
				float:left; 
				border:0px;
				color:gray;
				font-family: '微软雅黑';
				font-size: 16px;
				outline:0;
			}
			#word {
				height:100%; 
				width:80%;
				float:left;
				border:0px;
				font-size: 16px;
				outline:0;
			}
		</style>
		<script type="text/javascript" src="../js/jquery-2.1.4.min.js"></script>
	</head>	
	<body>
		<div id="bg">			
		</div>
		<div id="login">
			<div id="form" >
				<div id="username" style="border: 0px solid #999; height:50%; width:100%;">
					<img src="../images/welcome.png" style="float:left; margin:3% 3% 0% 3%;" />
					<input type="text" id="name" name="name" placeholder="账户"/>
				</div>
				<div id="password" style="border: 0px solid #999; height:50%; width:100%;">
					<img src="../images/doc-list.png" style="float:left; margin:3% 3% 0% 3%;" />
					<input type="password" id="word" name="word" placeholder="密码"/>
				</div>
				<input type="submit" name="submit" value="登录" id="loginButton"
					style="height:33px; width:100px; background: #00AAAA; color:white; margin: 15 10 0 15" />
				<input type="button" name="regist" value="注册"  onclick="window.location.href='register.jsp'"
					style="height:33px; width:100px; background: #00AAAA; color:white; margin: 15 15 0 15" />
			</div>			
		</div>
		<script type="text/javascript">
			$(function(){
				$("#loginButton").bind("click",function(){
					var id =$("#name").val();
					var password =$("#word").val();
					if(username==""||password==""){
						alert("请输入用户名或密码");
					}else{
						$.post("login",{"id":id,"password":password},function(data){
							if(data=="true"){
								window.location.href="home.jsp";
							}else{
								alert("用户名或密码错误");
							}
						});	
					}
				});
			});			
		</script>
	</body>
</html>