<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TickitEasy 後台管理平台</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
</head>
<body class="bg-gray-100">
	<div class="min-h-screen flex flex-col">
		<!-- 頂部導航欄 -->
		<nav class="bg-blue-600 text-white shadow-lg">
			<div class="max-w-7xl mx-auto px-4">
				<div class="flex items-center justify-between h-16">
					<div class="flex items-center">
						<span class="font-semibold text-xl">TickitEasy 管理系統</span>
					</div>
					<div>
						<a href="${pageContext.request.contextPath}/admin/adminLogout"
							class="text-white hover:text-gray-200">登出</a>
					</div>
				</div>
			</div>
		</nav>

		<!-- 主要內容 -->
		<div class="flex-grow container mx-auto mt-8 px-4">
			<h1 class="text-3xl font-bold mb-8">後台管理主頁</h1>

			<div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
				<!-- 會員管理 -->
				<div class="bg-white rounded-lg shadow-md p-6">
					<h2 class="text-xl font-semibold mb-4">會員管理</h2>
					<p class="mb-4">管理網站會員，查看會員信息，更改會員狀態。</p><br>
					<a href="${pageContext.request.contextPath}/admin/memberManagement"
						class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
						進入會員管理 </a>
				</div>

				<!-- 活動管理 -->
				<div class="bg-white rounded-lg shadow-md p-6">
					<h2 class="text-xl font-semibold mb-4">活動管理</h2>
					<p class="mb-4">創建和管理活動，設置票種和場次。</p>
					<br> <a href="${pageContext.request.contextPath}/event/ReadAllTicketTypes.jsp"
						class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">
						進入活動管理 </a>
				</div>
				<!-- 訂單管理 -->
				<div class="bg-white rounded-lg shadow-md p-6">
					<h2 class="text-xl font-semibold mb-4">訂單管理</h2>
					<p class="mb-4">查看和處理訂單，管理退款請求。</p>
					<br> <a href="${pageContext.request.contextPath}/order/ordersHTML/prodOrders.html"
						class="bg-yellow-500 hover:bg-yellow-700 text-white font-bold py-2 px-4 rounded">
						進入訂單管理 </a>
				</div>
				<!-- 商城管理 -->
				<div class="bg-white rounded-lg shadow-md p-6">
					<h2 class="text-xl font-semibold mb-4">周邊商品管理</h2>
					<p class="mb-4">上架和管理商品，設置商品種類。</p>
					<a href="${pageContext.request.contextPath}/product/GetAllProducts.jsp"
						class="bg-red-500 hover:bg-yellow-700 text-white font-bold py-2 px-4 rounded">
						進入商品管理 </a>
				</div>
				<!-- 討論區管理 -->
				<div class="bg-white rounded-lg shadow-md p-6">
					<h2 class="text-xl font-semibold mb-4">討論區管理</h2>
					<p class="mb-4">管理討論區文章和回覆。</p>
					<a href="${pageContext.request.contextPath}/GetAllPost"
						class="bg-pink-500 hover:bg-yellow-700 text-white font-bold py-2 px-4 rounded">
						進入討論區管理 </a>
				</div>
				<!-- 募資活動管理 -->
				<div class="bg-white rounded-lg shadow-md p-6">
					<h2 class="text-xl font-semibold mb-4">募資活動管理</h2>
					<p class="mb-4">創建和管理募資活動。</p>
					<a href="${pageContext.request.contextPath}/FundProjs"
						class="bg-purple-500 hover:bg-yellow-700 text-white font-bold py-2 px-4 rounded">
						進入募資活動管理 </a>
				</div>

			</div>
		</div>

		<!-- 頁腳 -->
		<footer class="bg-gray-200 text-center py-4 mt-8">
			<p>&copy; 2024 TickitEasy. All rights reserved.</p>
		</footer>
	</div>
</body>
</html>