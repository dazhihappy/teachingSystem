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
		<title>查看习题</title>
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
	                <span>选择章节：</span>
	                <select name="chapter"  id="chapter" style="font-family:'微软雅黑';width:90px;">
	                		<option value="0">期末测试</option>
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
					</select> <br />
	                <span>选择题型：</span>
	                <select name="type" id="type" style="font-family:'微软雅黑';width:90px;">
		                <option value="0">填空题</option>
		                <option value="1">选择题</option>
	                </select>
	                <br />
	                <span>选择类型：</span>
	                <select name="type1" id="type1" style="font-family:'微软雅黑';width:90px;">
		                <option value="0">测试题</option>
		                <option value="1">复习题</option>
	                </select>
	                <br />
	                <input type="button" name="submit" value="确定" class="submit" id="submitS"
	                style="width:90px;height:23px;font-size:15px;margin-left:87px;margin-top:5px;background:#00AAAA; color:white;" />
	                <br>
	                <br>
	                <!-- 此处布局更改 -->
	                <ol id="quesData" style="display: none;">
	                		
	                </ol>
	                <%} %>
                </div>				
			</div>
			</div>
		</div>
		<script type="text/javascript">
			$("#dan").fadeIn(1000);
			$(function(){
				$("#submitS").bind("click",function(){
					var chapter=$("#chapter").val();
					var type=$("#type").val(); //0-填空；1-选择
					var type1=$("#type1").val();
					$.get("showQuestion",{"chapter":chapter,"type":type,"type1":type1},function(data){
						var dataObj = JSON.parse(data);
						if(type==0){
							$("#quesData").children().remove();
							$.each(dataObj.gradeList,function(i,item){
								var li ="<li><div>"
								+"<p>"+item.question+"</p>"
								+"<p>答案:"+item.answer+"</p>"
								+"<p>解析:"+item.explain+"</p>"
								+"<button style='background:#00AAAA; color:white;' onclick='var i= deleteQues("+item.id+","+item.tag+"); if(i==1){$(this).parent().parent(). slideUp(500);}' >删除</button>"
								+"&nbsp;&nbsp;<button style='background:#00AAAA; color:white;' onclick='editQues("+item.id+","+item.tag+");'>修改</button>"
								+"</div></li>";																								
								$("#quesData").append(li);
								$("#quesData").hide();
								$("#quesData").fadeIn("fast");
							});						
						}else{
							$("#quesData").children().remove();
							$.each(dataObj.gradeList,function(i,item){
								var li ="<li><div>"
								+"<p>"+item.question+"</p>"
								+"<p>A."+item.chooseA+"&nbsp;&nbsp;B."+item.chooseB+"&nbsp;&nbsp;C."+item.chooseC+"&nbsp;&nbsp;D."+item.chooseD+"</p>"
								+"<p>答案:"+item.answer+"</p>"
								+"<p>解析:"+item.explain+"</p>"
								+"<button style='background:#00AAAA; color:white;' onclick='var i=deleteQues("+item.id+","+item.tag+"); if(i==1){$(this).parent().parent(). slideUp(500);} ' >删除</button>"
								+"&nbsp;&nbsp;<button style='background:#00AAAA; color:white;' onclick='editQues("+item.id+","+item.tag+");'>修改</button>"
								+"</div></li>";	
								$("#quesData").append(li);
								$("#quesData").hide();
								$("#quesData").fadeIn("fast");
							});
						}
					});
				});
			});
			//删除
			function deleteQues(id , tag){
				if(confirm("确认删除？")){
					$.get("deleteQuestion",{"id":id,"tag":tag},function(data){
						if(data=="true"){
							//alert("删除成功");							 
						}else{
							alert("删除失败");
						}
					});					
					return 1;
				}else{
					return 0;
				}
			}
			//修改
			function editQues(id,tag){
				window.location.href="editQuestion?id="+id+"&tag="+tag;
			}
		</script>
	</body>	
</html>