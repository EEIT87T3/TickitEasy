<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="post.bean.PostBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/mycss/post.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/mycss/nav.css">
 <style>
 body {
    font-family: Arial, sans-serif;
    margin: 20px;
    background-color: #f4f4f4;
}
 
 .post {
    background-color: white;
    padding: 80px;
    width: 50%;
    margin: auto;
    margin-top: 5%;
    margin-bottom: 15px;
    border-radius: 5px;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}
 </style>
<title>insert post</title>
   <script>
     
    </script>
</head>

<body>
   <%
        PostBean post = (PostBean) request.getAttribute("post");
        //comment
        if (post != null) {
    %>
   <nav>
 		<a href="findById?postID=<%=post.getPostID()%>" class="button">回貼文</a>
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
   
<div class="post">
<h2>編輯貼文</h2>
	<form method="post" action="updatePost" >
	<input type="hidden" name="postID" value="<%=post.getPostID()%>"/>
	<input type="hidden" name="memberID" value="<%=post.getMemberID()%>"/>

	輸入貼文標題 : <input type="text" name="postTitle" value="<%= post.getPostTitle() %>"/><p>
	選擇主題分類 :
	<select name="themeID">
    <option value="<%= post.getThemeID() %>" selected><%= post.getThemeName() %></option>
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
	<br><br>
	輸入貼文內容 :<br><textarea style="resize:none;width:100%;" name="postContent" rows="10" cols="40" ><%= post.getPostContent() %></textarea>
	<br><br>
	輸入圖片網址 : <input type="text" name="postImgUrl" /><p>
	
	狀態 : <input type="text" name="status" value="1" readonly/><p>
	<input type="submit" value="確定" />
	</form>
</div>
   <%
        } else {
            out.println("未找到相關帖子");
        }
    %>
 <script>
   function textareaHeight(box){
       let obj =$(box);
       obj.style.height = obj.scrollHeight +'px';
   }
   document.querySelector('textarea').addEventListener('input', function () {
	    this.style.height = 'auto';
	    this.style.height = (this.scrollHeight) + 'px';
	});
   </script>
</body>
</html>

