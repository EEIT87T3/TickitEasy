<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="post.bean.PostBean"%>
<%@page import="post.bean.CommentBean"%>
<%@page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Timestamp" %>
<%
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/post/css/post.css">
    <link rel="stylesheet" href="post/css/nav.css">
    <title>論壇</title>
</head>
<body>
    <!-- Your navigation and other elements go here -->
     <nav>
    <a href="GetAllPost" class="button">回列表</a>
    
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
    <%
        PostBean post = (PostBean) request.getAttribute("post");
        if (post != null) {
    %>
    <div class="posts">
        <div class="post">
            <div class="title">
                <h1><%= post.getPostTitle() %></h1>
            </div>
            <div class="author">
                <img class="avatar" src="<%= post.getMemberProfilePic() %>" alt="頭像">
                <ul>
                    <li><div ><%= post.getMemberNickname() %></div></li>
                    <li><div><%= sdf.format(post.getPostTime()) %></div></li>
                </ul>
                <div class="button-group">
                    <form action="" method="post">
                        <input type="hidden" name="postID" value="<%= post.getPostID() %>"/>
                        <input type="submit" value="檢舉"/>
                    </form>
                    
                    <form action="GetUpdatePost" method="post">
                        <input type="hidden" name="postID" value="<%= post.getPostID() %>"/>
                        <input type="submit" value="修改"/>
                    </form>
                      <form action="DeletePost" method="post" style="">
							<input type="hidden" name="postID" value="<%=post.getPostID()%>"/>
							<input  type="submit" value="刪除" />
					</form>
                </div>
            </div>
            <br>
            <hr>
            <div><%= post.getPostContent() %></div>
            <%
                } else {
                    out.println("未找到相關帖子");
                }
            %>
            <div class="comments">
                <h3>回應</h3>
         
                <%
                    List<CommentBean> comments = (List<CommentBean>) request.getAttribute("comment");
                    if (comments != null && !comments.isEmpty()) {
                        for (CommentBean comment : comments) {
                %>
                <div class="comment">
                    <div class="author">
                        <img class="avatar" src="Img/gal_o_man.png" alt="頭像">
                        <div class="name"><%= comment.getMemberNickname() %></div>
                    </div>
                    <p><%= comment.getContent() %></p>
                    <p><%= sdf.format(comment.getCommentDate() )%></p>
                </div>
                <% 
                        }
                    } else {
                        out.println("目前沒有回應");
                    }
                %>
            </div>
            <div class="new-comment">
                <h3>新增回應</h3>
                <form method="post" action="InsertComment">
                <input type="hidden"  name="postID" value="1" /><p>
                <input type="hidden"  name="memberID" value="1" /><p>
  
                    <textarea name="content" style="resize:none;height:100px;" placeholder="輸入您的回應"></textarea>
                    <input type="submit" value="發表">
                </form>
            </div>
        </div>
    </div>
    <script>
        function textareaHeight(box){
            let obj = $(box);
            obj.style.height = obj.scrollHeight + 'px';
        }
        document.querySelector('textarea').addEventListener('input', function () {
            this.style.height = 'auto';
            this.style.height = (this.scrollHeight) + 'px';
        });
    </script>
</body>
</html>
