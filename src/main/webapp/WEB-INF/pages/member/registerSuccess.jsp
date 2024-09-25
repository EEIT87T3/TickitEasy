<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>註冊成功</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="container mx-auto mt-10 p-4">
        <h1 class="text-3xl font-bold mb-6">註冊成功</h1>
        <p class="mb-4">您的帳號已成功創建。</p>
        <a href="${pageContext.request.contextPath}/member/login" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">前往登入</a>
    </div>
</body>
</html>