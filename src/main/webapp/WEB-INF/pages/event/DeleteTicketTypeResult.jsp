<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>刪除票種 結果</title>
</head>
<body style="background-color:#e6f3ff">
<div align="center">
	<h2>刪除票種 結果</h2>
	<p><%= Boolean.TRUE.equals(request.getAttribute("result")) ? "刪除成功！" : "刪除失敗。" %></p>
	<a href="<%= request.getContextPath() %>/event/TicketType">回到「活動票種管理」首頁</a>
</div>
</body>
</html>