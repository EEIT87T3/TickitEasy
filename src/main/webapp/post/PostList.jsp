<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="post.model.Post"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Timestamp" %>

<%
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    List<Post> posts = (List<Post>)request.getAttribute("posts");
%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>討論區列表</title>
     <link rel="stylesheet" href="${pageContext.request.contextPath}/post/css/post.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/post/css/list.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/post/css/nav.css">
 
</head>
<body>
    <nav>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/dashboard">後台主頁</a>
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
	    <h2>文章列表 </h2>
	    <div class="button-group funtion">
		    
		    
		    
		    
	
			<form method="post" action="GetPostByTheme" >
				<span>選擇主題:</span>
				<select name="themeID" onchange="this.form.submit()">
					<option value="" selected>請選擇主題</option>
					<option value="0">所有主題</option>
					<option value="1">音樂</option>
					<option value="2" >舞蹈</option>
					<option value="3">戲劇</option>
					<option value="4">音樂劇</option>
					<option value="5" >親子</option>
					<option value="6">講座</option>
					<option value="7">展覽</option>
					<option value="8">電影</option>
					<option value="9">體育</option>
					<option value="10">旅遊</option>
					<option value="11">其他</option>
				</select>
					
			</form>
		
			
			<div class="bor">
			      <form >
				    <span>依條件排序:</span>
				    <select id="sortCriteria" onchange="sortArticlesBySelect()" >
					    <option value="" selected>請選擇條件</option>
					    <option value="date">按發佈日期新→舊</option>
					    <option value="views">按瀏覽次數多→少</option>
					    <option value="likes" >按喜歡數量多→少</option>
				
					</select>
					
				</form>
			</div>
			
			<div class="bor ">
				<form method="post" action="GetPostByEnter"  >
			    <span>搜尋:</span>
			    <input  type="text" name="enter" />
			    <input  class="button" type="submit" value="確定" />
		    </form>
			</div>
			<div class="bor ">
				<a class="button" href="post/insertpost.html">新增文章</a>
				 
				<div id="selectedTheme" class="selected-theme">
			            <!-- 這裡將顯示選擇的主題 -->
			    </div>
				
		    </div>
		</div>
        <div class="article-list">
            <% if (posts != null && !posts.isEmpty()) { %>
                <% for (Post post : posts) { %> 
                    <div class="article-item" onclick="submitForm(<%= post.getPostID() %>)" 
                        data-likes="<%= post.getLikesCount() %>" data-views="<%= post.getViewCount() %>" data-date="<%= post.getPostTime() %>">
                        <img src="path/to/thumbnail1.jpg" alt="文章縮略圖">
                        <div>
                            <span class="theme"><%= post.getThemeName() %></span> 
                            <a href="GetPost?postID=<%=post.getPostID()%>"><%= post.getPostTitle() %></a> 
                            <p><%= post.getPostContent() %></p>
                            <div class="article-meta">
                                <span>喜歡：<%= post.getLikesCount() %></span>
                                <span>瀏覽次數：<%= post.getViewCount() %></span>
                                <span>發佈日期：<%= sdf.format(post.getPostTime()) %></span>
                            </div>
                        </div>
                        <div class="button-group">
                            <form action="GetUpdatePost" method="post">
                                <input type="hidden" name="postID" value="<%=post.getPostID()%>"/>
                                <input class="button" type="submit" value="修改" />
                            </form>
                            <form action="DeletePost" method="post">
                                <input type="hidden" name="postID" value="<%=post.getPostID()%>"/>
                                <input class="button" type="submit" value="刪除" />
                            </form>
                        </div>
                        <form id="postForm_<%= post.getPostID() %>" action="GetPost" method="get" style="display: none;">
                            <input type="hidden" name="postID" value="<%= post.getPostID() %>" />
                        </form>
                    </div>
                <% } %>
            <% } else { %>
                <div class="article-item">
                    <div class="article-meta">
                        <h2>查無文章</h2>
                    </div>
                </div>
            <% } %>
        </div>
    </div>

    <script>
    function submitForm(postID) {
        var form = document.getElementById('postForm_' + postID);
        form.submit();
    }

    function sortArticlesBySelect() {
        const criteria = document.getElementById('sortCriteria').value;
        let articles = Array.from(document.querySelectorAll('.article-item'));

        articles.sort((a, b) => {
            switch (criteria) {
                case 'date':
                    return new Date(b.dataset.date) - new Date(a.dataset.date);
                case 'views':
                    return b.dataset.views - a.dataset.views;
                case 'likes':
                    return b.dataset.likes - a.dataset.likes;
                default:
                    return 0;
            }
        });

        const container = document.querySelector('.article-list');
        container.innerHTML = '';
        articles.forEach(article => container.appendChild(article));
    }
    </script>
</body>
</html>