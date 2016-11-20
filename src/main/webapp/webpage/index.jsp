<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>数据导入页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="<%=basePath%>js/angular/lib/angular.mini.js"></script>
  </head>
  
  <body>
  <div class="dataFromExcelToDatabase-container">
  <center>
  	<form action="watchDataControler/springUpload.action" method="post" enctype="multipart/form-data" name="fileForm">  
        <input type="hidden" name="method" value="upload"/>  
  
        <input type="file" name="file"/>  
  
        <input type="submit" value="导入数据"/>  
  
    </form>  
    </center>
    <center>
    	<div><a href="<%=basePath%>">返回显示页</a></div>
    </center>
  </div>
  </body>
</html>
