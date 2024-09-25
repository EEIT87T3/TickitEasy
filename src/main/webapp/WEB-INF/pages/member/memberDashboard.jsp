<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員中心 - TickitEasy</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="container mx-auto px-4 py-8">
        <header class="flex justify-between items-center mb-8">
            <h1 class="text-3xl font-bold">會員中心</h1>
            <a href="${pageContext.request.contextPath}/member/logout" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">
                登出
            </a>
        </header>

        <c:if test="${not empty member}">
            <div class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
                <h2 class="text-2xl font-bold mb-4">歡迎，${member.name}！</h2>
                <div class="mb-4">
                    <p><strong>Email:</strong> ${member.email}</p>
					<p><strong>暱稱:</strong> ${member.nickname}</p>
				
                    <!-- 可以顯示更多會員資訊 -->
                </div>
               <%--  <div class="flex space-x-4">
                    <a href="${pageContext.request.contextPath}/member/edit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                        編輯個人資料
                    </a>
                    <a href="${pageContext.request.contextPath}/member/orders" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">
                        我的訂單
                    </a>
                    <!-- 可添加更多功能按鈕 -->
                </div> --%>
            </div>
        </c:if>

        <!-- 這裡可以添加更多會員相關的內容，例如最近的活動、推薦等 -->
        <div class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
            <h2 class="text-2xl font-bold mb-4">最近活動</h2>
            <!-- 這裡可以使用 JSTL 來遍歷和顯示最近的活動 -->
            <ul class="list-disc pl-5">
                <li>活動 1</li>
                <li>活動 2</li>
                <li>活動 3</li>
            </ul>
        </div>
    </div>

    <script>
		// js
    </script>
</body>
</html>