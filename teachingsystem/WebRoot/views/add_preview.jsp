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
		<title>添加预习</title>
		<script type="text/javascript">
				$(function(){
					$("#dan").fadeIn(1000);
					$("#tijiao").bind("click",function(){
						var context =UE.getEditor('content').getContent();
						var chapter =$("#chapter").val();
						var title  =$("#title").val();
						$.get("addPreview",{"context":context,"chapter":chapter,"title":title},function(data){
							if(data=="true"){
								alert("添加成功");
								location.href="add_preview.jsp";
							}else if(data=="wrong"){
								alert("您已添加了该章节预习，无需重复添加");
								location.href="add_preview.jsp";
							}else{
								alert("添加失败");
								location.href="add_preview.jsp";
							}
						});
					});
				});					
		</script>
	</head>
	
	<body>
		<div id="bg">
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
				<% if(session.getAttribute("course_id")!=null){ %> 
				<span style="margin-left:20px;">选择章节：</span>
                <select id="chapter" style="font-family:'微软雅黑';font-size:15px;width:150px;height:28px;margin-bottom:-1px;" name="chapter">
	                <%
	                	Connection conn = DBPool.getConnection();
	                	PreparedStatement p =conn.prepareStatement("select chapter from course where id =?");
	                	p.setInt(1, (Integer)session.getAttribute("course_id"));
	                	ResultSet result = p.executeQuery();
	                 	while(result.next()){
		                 	for(int i =1; i<result.getInt("chapter")+1; i++){
		                 	%>
		                		<option value=<%=i%>>第<%=i%>章</option>	
		                 	<%}
	                 	} 
	                 	DBPool.resultSetClose(result);
	                 	DBPool.preparedStatementClose(p);
	                 	DBPool.connectionClose(conn);
	                 %>				
				</select><br/>
                <span style="margin-left:20px;">文章标题：</span>
                <input type="text" name="title" style="height:26px;width: 150px" id="title"/>

 
  				<!-- 导入ueditor -->
			    <script type="text/plain" id="content" name="content" style="margin-left:20px;"></script>
			    <script type="text/javascript" src="../ueditor/ueditor.config.js"></script>   
			    <script type="text/javascript" src="../ueditor/ueditor.all.js"></script>
			    <script type="text/javascript" src="../ueditor/lang/zh-cn/zh-cn.js"></script>
			    <script type="text/javascript">  
			      var ue = UE.getEditor('content',{toolbars:[["source",'|',
			        "undo","redo",'|',"bold","italic","underline","strikethrough",'|'
			        , 'insertorderedlist', 'insertunorderedlist', '|',"superscript","subscript"
			        ,'|',"justifyleft","justifycenter","justifyright","justifyjustify",'|',"indent"
			        ,"rowspacingbottom","rowspacingtop","lineheight","|",'selectall', 'cleardoc'],
			        ["fontfamily","fontsize", '|',"forecolor","backcolor", '|',"pasteplain",'removeformat',
			         'formatmatch',"autotypeset",'|',"insertimage", 'insertvideo','|',"link","unlink","spechars",
			         '|','horizontal', 'date', 'time', '|', 'preview', "fullscreen"]]   
			       ,initialFrameWidth:750  //初始化编辑器宽度,默认1000   
			        ,initialFrameHeight:300  //初始化编辑器高度,默认320   
			        ,initialContent:''   //初始化编辑器的内容,也可以通过textarea/script给值，看官网例子    
			        ,maximumWords:10000       //允许的最大字符数   
			       ,enableAutoSave: false  //自动保存
			       ,wordCount:false          //是否开启字数统计
			        ,elementPathEnabled :false //是否启用元素路径，默认是显示  
			        ,initialstyle:'p{line-height:1em}'
			        ,autoHeightEnabled:false
			       });  
			    </script> 

				<input type="button" value="提交" name="submit" class="submit"  id="tijiao"
				style="margin-left:670px;margin-top:5px;width:100px;font-size:16px;background:#00AAAA; color:white;" />				
				<%} %>
			</div>
		</div>
		</div>
	</body>	
</html>