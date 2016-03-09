<%@page import="dao.DBPool"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="dao.Conn"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="../styles/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery-2.1.4.min.js"></script>
		<title>查看成绩</title>
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
				<div style="margin-left:20px;">  
				<%if(session.getAttribute("course_id")!=null){ %>
				<span>查询：</span>
	                <select name="chapter"  id="chapter" style="font-family:'微软雅黑';width:90px;">
	                	<option value="0">期末成绩</option>
	                	<%
		                	Connection conn = DBPool.getConnection();
		                	PreparedStatement p =conn.prepareStatement("select chapter from course where id =?");
		                	p.setInt(1, (Integer)session.getAttribute("course_id"));
		                	ResultSet result = p.executeQuery();
		                 	while(result.next()){
		                 	for(int i =1; i<result.getInt("chapter")+1; i++){
		                 %>
		                	<option value=<%=i%>>第<%=i%>章</option>	
		                 <%}}
		                 	DBPool.resultSetClose(result);
							DBPool.preparedStatementClose(p);
							DBPool.connectionClose(conn);
		                  %>               	  
					</select> <br/>
					
	                <input type="button" name="submit" value="确定" class="submit" id="submitGrade"
	                style="width:90px;height:23px;font-size:15px;margin-left:55px;margin-top:5px;background:#00AAAA; color:white;" />
	                <br>
					<br><br>
					<table id ="scoreData" style="display: none;text-align:center; margin-left:20px;border-style: solid;border-width: 1px">
						<tbody align="center" >
		                	<tr><th style="width: 100px;">姓名</th><th style="width: 100px;">学号</th><th style="width: 100px;">年级</th>
		                	<th style="width: 100px;">班级</th><th style="width: 100px;">成绩</th></tr>		                			               		
		                </tbody>
					</table>
				<%} %>
			</div>
			</div>
		 </div>
		</div>
		<script type="text/javascript">
			$("#dan").fadeIn(1000);
			$(function(){
				$("#submitGrade").bind("click",function(){
					$("#scoreData").hide();
					var chapter =$("#chapter").val();
					$.get("showGrade",{"chapter":chapter},function(data){
						var dataObj = JSON.parse(data); 
						$("tr:not(':first')").remove();	//删除之前数据
						$.each(dataObj.gradeList,function(i,item){
							$("#scoreData").append("<tr><td>"+item.name+"</td><td>"+item.id+"</td><td>"+item.grade+"</td><td>"+item.class+"</td><td>"+item.score+"</td></tr>");
						});
						$("#scoreData").fadeIn(1000);
						$("tr:even").css("background-color","#ccf");
					});
				});
			});
		</script>
	</body>	
</html>