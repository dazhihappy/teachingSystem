<%@page import="dao.DBPool"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="dao.Conn"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link href="../styles/main.css" rel="stylesheet" type="text/css" />
		<link href="../styles/home.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../js/jquery-2.1.4.min.js"></script>
		<title>留言</title>
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
			<div id="page" style="overflow:auto">
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
				<span>留言板</span>
				<br/>
				<table style="display: none;">
					<% 
						Integer course_id =(Integer)session.getAttribute("course_id");
						Connection conn = DBPool.getConnection();
						PreparedStatement p =conn.prepareStatement("select * from message where type=0 and course_id =?");
						p.setInt(1,course_id);
						ResultSet result = p.executeQuery();
						int i = 1;
						while(result.next()){
							Date time=(Date)result.getDate("date");
    	    				String t= new SimpleDateFormat("yyyy-MM-dd").format(time);
							PreparedStatement p1 =conn.prepareStatement("select name from student where id =?");
							p1.setInt(1, result.getInt("student_id"));
							ResultSet result1 = p1.executeQuery();
							String student_name=null;
							if(result1.next()){
								student_name = result1.getString("name");
							}
					%>
					 <tr><td><%=i++%>.</td><td colspan='2'><%=result.getString("context") %></td></tr>
					 <tr><td></td><td><%=student_name %></td><td>&nbsp;&nbsp;发表于 &nbsp;&nbsp;<%=t%></td></tr>
					<%}
					DBPool.resultSetClose(result);
					DBPool.preparedStatementClose(p);
					DBPool.connectionClose(conn);
					 %>
				</table>
				<%} %>
				<script type="text/javascript">
					$("#dan").fadeIn(1000);
					$("table").fadeIn(1000);
				</script>
				</div>				
			</div>
			</div>
		</div>
	</body>					
</html>