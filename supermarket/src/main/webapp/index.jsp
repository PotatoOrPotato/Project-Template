<%@ page import="unmanned.supermarket.pay.service.impl.TestServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 //EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
<%--<a href="supermarket/findUser.jsp">用户测试查询</a><br>--%>
<form action="<%=basePath%>/file/test1" method="post" enctype="multipart/form-data">
    <input type="file" name="multipartFile" value="fileValues">
    <input type="submit" value="提交" style="height: 35px; ">
</form>
<a href="javascript:window.history.go(-1);">返回</a>
</body>
</html>