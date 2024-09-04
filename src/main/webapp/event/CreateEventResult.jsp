<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增活動 結果</title>
</head>
<body style="background-color:#e6f3ff">
<div align="center">
	<h2>新增活動 結果</h2>
	<p><%= request.getAttribute("result") %></p>
	<a href="CreateEvent.html">回到「新增活動」頁面</a>
	<br>
	<a href="ReadAllTicketTypes.jsp">回到「活動票種管理」首頁</a>
</div>
</body>
</html>