<%@page import="dao.DBPool"%>
<%@page import="bean.Blank"%>
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
		<title>编辑填空题</title>
	</head>
	<%
		Blank blank = (Blank) request.getAttribute("blank");
	%>
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
				<div>
								<span style="margin-left:20px;">选择章节：</span>
				<select name="chapter" style="font-family:'微软雅黑'" id="chapter">
						<option value="0">期末测试</option>
					<%
	                	Connection conn = DBPool.getConnection();
	                	PreparedStatement p =conn.prepareStatement("select chapter from course where id =?");
	                	p.setInt(1, (Integer)session.getAttribute("course_id"));
	                	ResultSet result = p.executeQuery();
	                 	while(result.next()){
	                 	for(int i =1; i<result.getInt("chapter")+1; i++){
	                 	if(i==blank.getChapter()){
	                 %>
	                	<option value=<%=i%> selected="selected">第<%=i%>章</option>	
	                 <%}else{%>
	                 	<option value=<%=i%>>第<%=i%>章</option>	
	                 <% }}}
	                 	DBPool.resultSetClose(result);
						DBPool.preparedStatementClose(p);
						DBPool.connectionClose(conn);
	                  %>
				</select>
				<br />
				<br />
				<span style="margin-left:20px;">选择类型：</span>
				<%if(blank.getType()==0){ %>
					<input type="radio" name="type" value="0" checked="checked" /> 测试
					<input type="radio" name="type" value="1" /> 复习
				<%}else{ %>
					<input type="radio" name="type" value="0" /> 测试
					<input type="radio" name="type" value="1" checked="checked"/> 复习
				<%} %>
				<br />
				<br />
				<span style="margin-left:20px">题目：</span>
				<div style="margin-left:20px;">
				  <script type="text/plain" id="title" name="question" ></script>
				</div>
				<span style="margin-left:20px">答案：</span>
				<input type="text" name="answer" id="answer" value=<%=blank.getAnswer()%>>
				<br />
				<br />
				<span style="margin-left:20px">解释：</span>
				<div style="margin-left:20px;">
				  <script type="text/plain" id="explain" name="explain"></script>
				</div>

				<script type="text/javascript" src="../ueditor/ueditor.config.js"></script>   
				    <script type="text/javascript" src="../ueditor/ueditor.all.js"></script>
				    <script type="text/javascript" src="../ueditor/lang/zh-cn/zh-cn.js"></script>
				    <script type="text/javascript"> 
				      var ue = UE.getEditor('explain',{toolbars:[["source",'|',
				        "undo","redo",'|',"bold","italic","underline","strikethrough",'|'
				        , 'insertorderedlist', 'insertunorderedlist', '|',"superscript","subscript"
				        ,'|',"justifyleft","justifycenter","justifyright","justifyjustify",'|',"indent"
				        ,"rowspacingbottom","rowspacingtop","lineheight","|",'selectall', 'cleardoc'],
				        ["fontfamily","fontsize", '|',"forecolor","backcolor", '|',"pasteplain",'removeformat',
				         'formatmatch',"autotypeset",'|',"insertimage", 'insertvideo','|',"link","unlink","spechars",
				         '|','horizontal', 'date', 'time', '|', 'preview', "fullscreen"]]   
				      ,initialFrameWidth:750  //初始化编辑器宽度,默认1000   
				      ,initialFrameHeight:100  //初始化编辑器高度,默认320   
				      ,initialContent:'<%=blank.getExplain()%>'     
				      ,wordComWords:10000       //允许的最大字符数   
				      ,autoHeightEnabled:false // 是否自动长高,默认true   
				      ,enableAutoSave: false  //自动保存
				      ,wordCount:false          //是否开启字数统计
				      ,elementPathEnabled :false //是否启用元素路径，默认是显示
				      ,textarea:"content"  
				      ,initialStyle:'body{font-size:14px}'   //编辑器内部样式,可以用来改变字体等   
				      }); 
				
				  var ue2 = UE.getEditor('title',{toolbars:[["undo","redo",'|',"fontfamily","fontsize", '|',
				    "bold","italic","underline","strikethrough",'|',"insertimage",'cleardoc']]   
				      ,initialFrameWidth:750  //初始化编辑器宽度,默认1000   
				      ,initialFrameHeight:50  //初始化编辑器高度,默认320    
				      ,enableAutoSave: false  //自动保存
				      ,wordCount:false          //是否开启字数统计
				      ,elementPathEnabled :false //是否启用元素路径，默认是显示
				      ,maximumWords:10000       //允许的最大字符数
				      ,autoHeightEnabled:false
				      ,initialContent:'<%=blank.getQuestion()%>'
				      }); 
				</script>
					<input type="button" name="submit" class="submit" value="提交" id="editBlank"
					style="background:#00AAAA; color:white;margin-left:670px;margin-top:5px;width:100px;height:25px;font-size:16px;" />
					</div>
			</div>
			</div>
		</div>
		<script type="text/javascript">
			$("#dan").fadeIn(1000);
			$(function(){
				$("#editBlank").bind("click",function(){
					var chapter = $("#chapter").val();					
					var type = $('input:radio[name="type"]:checked').val();
					var title =UE.getEditor('title').getContent();
					var answer =$("#answer").val(); 
					var explain =UE.getEditor('explain').getContent();
					//待修改
					var id =<%=blank.getId()%>;
					$.get("updateBlank",{"chapter":chapter,"type":type,"title":title,"answer":answer,"explain":explain,"id":id},function(data){
						if(data=="true"){
							alert("修改成功");
							window.location.href="display_item.jsp";
						}else{
							alert("修改失败");
						}
					});
				});
				
			});
		</script>
	</body>	
</html>