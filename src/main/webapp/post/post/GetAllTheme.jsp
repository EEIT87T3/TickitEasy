<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="post.bean.ThemeBean"%>
<%@page import="java.util.List"%>
    <%! @SuppressWarnings("unchecked") %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>員工資料</title>
	<style>
        table {
            width: 40%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f4f4f4;
        }
        .button {
            padding: 4px 8px;
            margin: 2px;
            border: none;
            color: white;
            cursor: pointer;
        }
        .delete-button {
            background-color: red;
        }
        .edit-button {
            background-color: blue;
        }
        .search-container, .add-topic-container {
            margin: 20px 0;
        }
    </style>
</head>
<body style="background-color:#fdf5e6">
	<div align="center">
		<h2>主題列表</h2>
	
	
	<form method="post" action="GetTheme" >
	
	搜尋 : <input type="text" name="themeID" placeholder="請輸入主題id" />

	<input type="submit" value="確定" />
	<br><br>
	</form>
	
		<table border="1">
		<tr style="background-color:#a8fefa">
		<th>主題編號<th>主題名稱<th>動作
		<% List<ThemeBean> themes = (ArrayList<ThemeBean>)request.getAttribute("theme");
			for(ThemeBean theme : themes){ %> 
			<tr><td><%=theme. getThemeID()%>
			<td><%=theme. getThemeName() %>
			<td>	<form action="DeleteTheme" method="post" style="display:inline;">
						<input type="hidden" name="themeID" value="<%=theme.getThemeID()%>"/>
						<input type="submit" value="刪除" /></form>
			<%} %>
		</table>
		<h3>共<%=themes.size() %>種主題</h3>
	</div>
	<div align="center">
	<h2>新增</h2>
	<form method="post" action="InsertTheme" >
	
	請輸入主題名稱 <input type="text" name="themeName"  /><p>

	<input type="submit" value="確定" />
	</form>
	</div>

</body>
</html>