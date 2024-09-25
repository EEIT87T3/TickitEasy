<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="post.bean.ThemeBean"%>
<%@page import="java.util.List"%>
    <%! @SuppressWarnings("unchecked") %>
<!DOCTYPE html>
<html lang="zh-TW">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/post/css/theme.css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/post/css/nav.css">
    <style>
 
    </style>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>主題分類</title>
 
    
</head>
<body>
 <nav>
        <a class="navbar-brand" href="#">logo</a>
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="#">購票首頁</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="GetAllTheme">文章分類</a>
            </li>
        </ul>
        <div class="auth-buttons">
            <a class="auth-button" href="#">會員主頁</a>
            <a class="auth-button" href="#">登入</a>
            <a class="auth-button" href="#">登出</a>
        </div>
    </nav>
    <div class="container">
    <% List<ThemeBean> themes = (ArrayList<ThemeBean>)request.getAttribute("theme");
			for(ThemeBean theme : themes){ %> 
        <div class="theme-card" onclick="submitForm(<%= theme.getThemeID() %>)">
        <%=theme. getThemeID() %>
            <img src="path/to/image1.jpg" alt="主題1" class="theme-image">
            <h2 class="theme-title"><%=theme. getThemeName() %></h2>
         <form id="themeForm_<%= theme.getThemeID() %>" action="GetPostByTheme" method="get" style="display: none;">
					    <input type="hidden" name="themeID" value="<%= theme.getThemeID() %>" />
					</form>
        </div>
        <%} %>
    
    </div>
     <script>
    function submitForm(themeID) {
        var form = document.getElementById('themeForm_' + themeID);
        form.submit();
    }
	</script>
</body>
</html>
