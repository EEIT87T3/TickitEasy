<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="post.bean.PostBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="post.bean.ThemeBean"%>
<%@page import="java.util.List"%>
    <%! @SuppressWarnings("unchecked") %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>論壇文章列表</title>
    <link rel="stylesheet" href="post/css/list.css">
    <link rel="stylesheet" href="post/css/nav.css">
    <style>

    </style>
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
    <div style="margin-top:100px;margin-left:400px">
    <h1>貼文列表</h1>
 	</div>
    <div class="container">
        <div class="article-list">
  <% List<PostBean> posts = (ArrayList<PostBean>)request.getAttribute("post");
			for(PostBean post : posts){ %> 
            <div class="article-item"  onclick="submitForm(<%= post.getPostID() %>)" >
                <img src="path/to/thumbnail1.jpg" alt="文章一縮略圖">
                <div>
                    <h2><a href="GetPost?postID=<%=post.getPostID()%>"><%= post.getPostTitle() %></a></h2>
                    <p><%= post.getPostContent() %></p>
                    <div class="article-meta">
                        <span>喜歡：<%= post.getLikesCount() %></span>
                        <span>瀏覽次數：<%= post.getViewCount() %></span>
                        <span>發佈日期：<%= post.getPostTime() %></span>
                    </div>
                </div>
                        <div class="button-group">
	                        <form action="DeletePost" method="post" style="">
							<input type="hidden" name="postID" value="<%=post.getPostID()%>"/>
							<input class="button"  type="submit" value="刪除" /></form>
						</div>
	                <form id="postForm_<%= post.getPostID() %>" action="GetPost" method="get" style="display: none;">
					    <input type="hidden" name="postID" value="<%= post.getPostID() %>" />
					</form>
            </div>
             <%
        } 
    %>
         
   		 </div>
     </div>
    <script>
    function submitForm(postID) {
        var form = document.getElementById('postForm_' + postID);
        form.submit();
    }
	</script>
</body>
</html>

