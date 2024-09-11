<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="post.bean.ThemeBean"%>
<%@page import="java.util.List"%>
    <%! @SuppressWarnings("unchecked") %>
       <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>員工資料</title>
</head>
<body style="background-color:#fdf5e6">
	<div align="center">
		<h2>主題列表</h2>
		<table border="1" id="table_id" class="display">
		<tr style="background-color:#a8fefa">
		<th>主題編號<th>主題名稱<th>動作
		<%ThemeBean theme = (ThemeBean) request.getAttribute("themes");
        if (theme != null){ %> 
			<tr><td><%=theme. getThemeID()%></td>
			<td><%=theme. getThemeName() %></td>
			<td>	<form action="DeleteTheme" method="post" style="display:inline;">
						<input type="hidden" name="themeID" value="<%=theme.getThemeID()%>"/>
						<input type="submit" value="刪除" /></form>
			</tr>
			<%} %>
		</table>
		<h3></h3>
	</div>
	<div align="center">
	<h2>新增</h2>
	<form method="post" action="InsertTheme" >
	
	請輸入主題名稱 <input type="text" name="themeName"  /><p>

	<input type="submit" value="確定" />
	</form>
	</div>
	<div align="center">
	<h2>搜尋</h2>
	<form method="post" action="GetTheme" >
	
	themeID : <input type="text" name="themeID" value="1" /><p>

	<input type="submit" value="確定" />
	</form>
	</div>
	<div align="center">
	<form action="GetAllTheme"  style="display:inline;">
						<input type="hidden" name="themeID" />
						<input type="submit" value="回上頁" /></form>
						</div>
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
	<script>$(document).ready( function () {
	    $('#table_id').DataTable(); 
	} );</script>
</body>
</html>