<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改票種 結果</title>
</head>
<body style="background-color:#e6f3ff">
<div align="center">
	<h2>修改票種 結果</h2>
	<p><%= request.getAttribute("result") %></p>
	<a href="<%= request.getContextPath() %>/event/TicketType">回到「活動票種管理」首頁</a>
</div>
</body>
</html>