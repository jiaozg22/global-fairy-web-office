<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>数据导入页</title>
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css"></link>
  </head>
  
  <body>
  <div class="dataFromExcelToDatabase-container">
  	<div class="top"></div>
  	<div class="main">
  	<center>
  	<c:forEach items="${templateTableList}"  begin="0" end="0" varStatus="status" var="templateTable" >
  		<h1><c:out value="${templateTable.tableCommont}"></c:out></h1>
  		<table class="hovertable" id="hovertable">
  		<tr class="tableTitle">
  		<form action="watchDataControler/watchdata/showByCondation.action" method="POST" name="formCondation">
  			<td>
	  			<input type="hidden" name="tableName" value="${templateTable.tableName}"/>
	  	 	</td>
	  	 	<td>责任人：</td>
	  	 	<td>
	  	 		<input type="text" name="columnValue" value="${columnValue}" title="责任人"/>
	  	 		<input type="hidden" name="columnName" value="column_D4F0C8CEC8CB"/>
	  	 	</td>
	  	 	<td>
	  	 		问题反馈时间查询时间起点：<input type="text" class="laydate-icon"  name="startTime" onclick="laydate()" value="${startTime}"/>
	  	 	</td>
	  	 	<td>
	  	 		问题反馈时间查询时间终点：<input type="text" class="laydate-icon"  name="endTime" onclick="laydate()" value="${endTime}"/>
	  	 	</td>
	  	 	<td>
	  	 		<input type="submit" />
	  	 	</td>
	  	</form>
	  	</tr>
	  	<tr>
  			<td>总条数<c:out value="${fn:length(templateTable.rowsList)}"></c:out> </td>
  			<td>处理意见统计:<c:out value="${sumDisposedeal}"></c:out> </td>
  			<td><span><a href="<%=basePath%>/webpage/index.jsp">导入数据页</a></span> </td>
  			
  		</tr>
  		<tr class="tableTitle">
  		<c:forEach items="${templateTable.cell}" var="dataColumnParam">
  			<td><c:out value="${dataColumnParam.columnComment}"></c:out></td>
	  	</c:forEach>
	  	</tr>
  		
  		<c:forEach items="${templateTable.rowsList}" var="rows">
  		
  		<tr class="tableRows">
  			<c:forEach items="${rows}" var="row">
  				<td><c:out value="${row}"></c:out></td>
  			</c:forEach>
  		</tr>
	  	</c:forEach>
  		</table>
	</c:forEach>
  		</center>
  	</div>
  	<center>
  	<div>
  		<span><a href="<%=basePath%>/webpage/index.jsp">导入数据页</a></span>
  	</div>
  	</center>
  </div>
  	
  </body>
</html>
