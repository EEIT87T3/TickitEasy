<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>編輯會員 - TickitEasy 管理系統</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="container mx-auto px-4 py-8">
        <h1 class="text-3xl font-bold mb-4">編輯會員</h1>
        <form id="editMemberForm" action="${pageContext.request.contextPath}/admin/memberManagement" method="post" enctype="multipart/form-data" class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="memberId" value="${member.memberID}">

            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="email">
                    Email
                </label>
                <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="email" type="email" name="email" value="${member.email}" required readonly>
            </div>

            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="name">
                    姓名
                </label>
                <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="name" type="text" name="name" value="${member.name}" required>
            </div>

            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="nickname">
                    暱稱
                </label>
                <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="nickname" type="text" name="nickname" value="${member.nickname}">
            </div>

            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="birthDate">
                    生日
                </label>
                <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="birthDate" type="date" name="birthDate" value="${member.birthDate}">
            </div>

            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="phone">
                    電話
                </label>
                <input class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" id="phone" type="tel" name="phone" value="${member.phone}">
            </div>

            <div class="mb-4">
                <label class="block text-gray-700 text-sm font-bold mb-2" for="profilePic">
                    頭像
                </label>
                <input type="file" id="profilePic" name="profilePic" accept="image/*">
                <c:if test="${not empty member.profilePic}">
                    <img src="${pageContext.request.contextPath}/${member.profilePic}" alt="Current Profile Picture" class="mt-2 w-32 h-32 object-cover rounded-full">
                </c:if>
            </div>

            <div class="flex items-center justify-between">
                <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="submit">
                    更新會員資料
                </button>
                <a href="${pageContext.request.contextPath}/admin/memberManagement" class="inline-block align-baseline font-bold text-sm text-blue-500 hover:text-blue-800">
                    取消
                </a>
            </div>
        </form>
    </div>

    <script src="${pageContext.request.contextPath}/jslib/editMember.js"></script>
</body>
</html>