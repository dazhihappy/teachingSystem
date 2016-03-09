<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<link href="../styles/main.css" rel="stylesheet" type="text/css" />	
		<script type="text/javascript" src="../js/jquery-2.1.4.min.js"></script>	
		<title>添加课程</title>
	</head>
	
	<body>
		<div id="bg" >
			<div id="menu">
				<img src="../images/grid.png" style="margin-left:25px; margin-top:25px;" />
				<span id='user_info'>${sessionScope.name},欢迎使用</span>    
				<a href="logout.action" style="display:block; margin-left:215px; margin-top:-21px;">注销</a>
				<hr id="left">
				<ul style="list-style:none">
					<li style="margin-top:10px;"><a href="home.jsp">主页</a></li>
					<li style="margin-top:10px;"><a href="../views/add_course.jsp">添加课程</a></li>
					<li style="margin-top:10px;"><a href="../views/add_preview.jsp">添加预习</a></li>
					<li style="margin-top:10px;"><a href="../views/add_fill.jsp">添加填空题</a></li>
					<li style="margin-top:10px;"><a href="../views/add_option.jsp">添加选择题</a></li>
					<li style="margin-top:10px;"><a href="../views/display_message.jsp">留言板</a></li>
					<li style="margin-top:10px;"><a href="../views/display_gnosis.jsp">期末感悟</a></li>
					<li style="margin-top:10px;"><a href="../views/display_item.jsp">查看习题</a></li>
					<li style="margin-top:10px;"><a href="../views/display_grade.jsp">查看学生成绩</a></li>
					</ul>
			</div>
			<div id="page">
			<div id="dan" style="display: none;">
				<p style="margin-top:28px; margin-left:20px;">欢迎使用本系统</p>
				<hr id="right">
				<%	if(session.getAttribute("course_name")==null){ %>
					<p style='margin-left:20px;'><strong>未设置课程，请选择</strong></p>  
				<%}else{ %>
					<p style='margin-left:20px;'>当前课程为：${sessionScope.course_name}</p>
				<%} %>     
				
				<div style="font-family:'微软雅黑'"  >
				  <span style="margin-left:20px;">请输入课程号：</span>
				  <input type="text" name="course_id" id="course_id"
				  style="width:170px;height:30px;font-size:15px;font-family:'微软雅黑'" /><br />	
				  
				  <span style="margin-left:20px;">请输入课程名：</span>
				  <input type="text" name="course_name" id="course_name"
				  style="width:170px;height:30px;font-size:15px;font-family:'微软雅黑';margin-top:-1px;" /><br />
				  
				  <span style="margin-left:20px;">请输入章节数：</span>
				  <input type="text" name="chapter_max" id="chapter_max"
				  style="width:170px;height:30px;font-size:15px;font-family:'微软雅黑';margin-top:-1px;" /><br />
				  
				  <input type="button" name="submit" value="提交" id ="submitCourse"
				  style="width:165px;height:25px;border:none;color:white;font-family:'微软雅黑';cursor:pointer;
				  background:#00AAAA;letter-spacing: 5px;border-radius:2px;font-size:15px;margin-left:138px;margin-top:5px;" />
				</div>
			</div>
		</div>
		</div>
		<script type="text/javascript">
			$("#dan").fadeIn(1000);
			$(function(){
				$("#submitCourse").bind("click",function(){
					var course_id =$("#course_id").val();
					var course_name = $("#course_name").val();
					var chapter =$("#chapter_max").val();
					if(course_name==""||chapter==""||course_id==""){
						alert("请输入课程号，课程名和章节数");
					}else{
						if(confirm("你确定添加该课程吗？")){
							$.get("addCourse",{"course_name":course_name,"chapter":chapter,"course_id":course_id},function(data){
								if(data=="true"){
									alert("添加成功");
								}else{
									alert("添加失败,课程号已存在");
								}
							});
						}else{
						}
					}
				});
			});
		</script>
	</body>	
</html>