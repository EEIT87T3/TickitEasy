<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="event.dao.TicketTypesDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="event.object.po.TicketTypesPO" %>
<%@ page import="event.dao.TicketTypesDAO" %>

<!DOCTYPE html>
<html lang="zh-Hant-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>活動票種管理 - TickitEasy 管理系統</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
    	/* 左側選單 */
        .sidebar {  /* 預設樣式 與 開啟選單時 */
			position: fixed;
			top: 4rem;  /* 留出空間給頂部導航欄。 */
			height: calc(100vh - 4rem);  /* 視窗高度 - 頂部導航欄高度，以向下填滿視窗空間。 */
			left: 0;
			width: 14rem; /* 224px */
			z-index: 30;
			transition: left 0.3s ease-in-out;
		}
		.sidebar-closed {  /* 關閉選單時 */
			left: -14rem;
		}
		#sidebarToggle {  /* 左側選單的 toggle 按鈕 */
			position: fixed;
			top: 0.75rem;
			left: 1rem;
			z-index: 9999;
			background-color: #2563eb;
			color: white;
			padding: 0.5rem;
			border-radius: 0.375rem;
			box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
			transition: all 0.3s ease-in-out;
		}
		#sidebarToggle:hover {
			background-color: #2c5282;
		}
		#sidebarToggle i {
			font-size: 1.5rem;
			color: white;
		}

		/* 內容區 */
		.content-with-sidebar {  /* 預設樣式 與 開啟選單時 */
			padding-top: 4rem;  /* 留出空間給頂部導航欄。 */
			margin-left: 14rem; /* 224px */
			transition: margin-left 0.3s ease-in-out;
		}
		.content-full {  /* 關閉選單時 */
			margin-left: 0;
		}
        div.container {
        	margin: auto;
        	max-width: 1250px;  /* 限制最大寬度，以防左側選單 toggle 影響寬度；但是實際測量 DataTable 寬度為 1500px 多，不懂為什麼在此設 1250px 剛好。 */
        }

		/* DataTables */
		.dataTables_wrapper .dataTables_length, .dataTables_wrapper .dataTables_filter,
			.dataTables_wrapper .dataTables_info, .dataTables_wrapper .dataTables_processing,
			.dataTables_wrapper .dataTables_paginate {
			color: #4a5568;
			margin-bottom: 10px;
		}
		.dataTables_wrapper .dataTables_paginate .paginate_button.current,
			.dataTables_wrapper .dataTables_paginate .paginate_button.current:hover
			{
			background: #4299e1;
			border-color: #4299e1;
			color: white !important;
		}
        table.dataTable tbody tr:hover {
            background-color: #dfe3eb;
        }
    </style>
</head>
<body class="bg-gray-100">
    <button id="sidebarToggle" class="fixed top-5 left-4 z-50 bg-blue-600 text-white p-2 rounded-md">
        <i class="fas fa-bars"></i>
    </button>

    <!-- 頂部導航欄 -->
    <nav class="bg-blue-600 text-white shadow-lg fixed top-0 left-0 right-0 z-50">
        <div class="max-w-7xl mx-auto px-4">
            <div class="flex items-center justify-between h-16">
                <div class="flex items-center">
                    <span class="font-semibold text-xl ml-2">TickitEasy 管理系統</span>
                </div>
                <div>
                    <a href="${pageContext.request.contextPath}/admin/adminLogout" class="text-white hover:text-gray-200">登出</a>
                </div>
            </div>
        </div>
    </nav>

    <div class="flex h-screen bg-gray-100">
        <!-- 側邊欄 -->
        <div class="bg-gray-800 text-white w-56 flex-shrink-0 sidebar">
            <div class="p-4">
                <h2 class="text-2xl font-semibold">管理選單</h2>
            </div>
            <nav class="mt-4">
                <a href="${pageContext.request.contextPath}/admin/dashboard" class="block py-2 px-4 hover:bg-gray-700">後台管理首頁</a>
                <a href="${pageContext.request.contextPath}/admin/memberManagement" class="block py-2 px-4 hover:bg-gray-700 ">會員管理</a>
                <a href="${pageContext.request.contextPath}/admin/memberStatistics" class="block py-2 px-4 hover:bg-gray-700">會員統計分析</a>
                <a href="${pageContext.request.contextPath}/event/TicketType" class="block py-2 px-4 hover:bg-gray-700 bg-gray-900">活動管理</a>
                <a href="${pageContext.request.contextPath}/order" class="block py-2 px-4 hover:bg-gray-700">訂單管理</a>
                <a href="${pageContext.request.contextPath}/GetAllProducts/getAllProducts" class="block py-2 px-4 hover:bg-gray-700">商品管理</a>
                <a href="${pageContext.request.contextPath}/post/findAll" class="block py-2 px-4 hover:bg-gray-700">討論區管理</a>
                <a href="${pageContext.request.contextPath}/FundProjs" class="block py-2 px-4 hover:bg-gray-700">募資活動管理</a>
			</nav>
        </div>

        <!-- 主要內容區 -->
        <div class="flex-1 flex flex-col overflow-hidden content-with-sidebar">
            <main class="flex-1 overflow-x-hidden overflow-y-auto bg-gray-200">
                <div class="container mx-auto px-4 py-8">
                    <div class="flex items-center justify-between mb-4">
                    	<h1 class="text-3xl font-bold mb-4 ml-4">活動票種管理</h1>
                        <a href="<%= request.getContextPath() %>/event/CreateEvent" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mr-4 rounded">新增活動</a>
                    </div>
                    <div class="overflow-x-auto">
                        <table id="ticketTypesTable" class="w-full bg-white">
                            <thead class="bg-gray-800 text-white">
                                <tr>
                                    <th class="py-3 px-4 text-center">活動名稱</th>
                                    <th class="py-3 px-4 text-center">場次名稱</th>
                                    <th class="py-3 px-4 text-center">票種名稱</th>
                                    <th class="py-3 px-4 text-center">價格</th>
                                    <th class="py-3 px-4 text-center">票種限制總數</th>
                                    <th class="py-3 px-4 text-center">開始售票時間</th>
                                    <th class="py-3 px-4 text-center">結束售票時間</th>
                                    <th class="py-3 px-4 text-center">詳情</th>
                                </tr>
                            </thead>
                            <tbody class="text-gray-700">
                                <%
                                	List<TicketTypesPO> ticketTypeslist = (List<TicketTypesPO>) request.getAttribute("ticketTypes");
	                                if (ticketTypeslist != null) {
	                                    for (TicketTypesPO ticketType : ticketTypeslist) {
                                %>
                                <tr>
                                    <td class="py-3 px-4"><%= /*ticketType.getSession().getEvent().getEventName()*/ "（活動名稱）" %></td>
                                    <td class="py-3 px-4"><%= /*ticketType.getSession().getSessionName()*/ "（場次名稱）" %></td>
                                    <td class="py-3 px-4"><%= ticketType.getTypeName() %></td>
                                    <td class="py-3 px-4 text-center"><%= ticketType.getPrice() %></td>
                                    <td class="py-3 px-4 text-center"><%= ticketType.getQuantityAvailable() == null ? "（未限制）" : ticketType.getQuantityAvailable() %></td>
                                    <td class="py-3 px-4 text-center"><%= ticketType.getStartSaleTime() %></td>
                                    <td class="py-3 px-4 text-center"><%= ticketType.getEndSaleTime() %></td>
                                    <td class="py-3 px-4 text-center">
                                        <form method="get" action="<%= request.getContextPath() %>/event/TicketType/<%= ticketType.getTicketTypeID() %>" style="display:inline;">
                                            <input type="submit" value="查看" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded text-xs">
                                        </form>
                                    </td>
                                </tr>
                                <%
	                                    }
	                                } else {
                                %>
                                <tr>
                                    <td colspan="8" class="py-3 px-4 text-center">目前沒有活動票種資料</td>
                                </tr>
                                <%
                                	}
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </main>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#ticketTypesTable').DataTable({
                "language": {
                    "url": "${pageContext.request.contextPath}/resource/Chinese-traditional.json"
                }
            });

            // 側邊欄切換功能
            $('#sidebarToggle').click(function() {
                $('.sidebar').toggleClass('sidebar-closed');
                $('.content-with-sidebar').toggleClass('content-full');
                $(this).toggleClass('button-shifted');
            });

            // 初始化側邊欄位置
            function initSidebar() {
                if (window.innerWidth >= 1024) {
                    $('.sidebar').removeClass('sidebar-closed');
                    $('.content-with-sidebar').removeClass('content-full');
                    $('#sidebarToggle').removeClass('button-shifted');
                } else {
                    $('.sidebar').addClass('sidebar-closed');
                    $('.content-with-sidebar').addClass('content-full');
                    $('#sidebarToggle').addClass('button-shifted');
                }
            }

            // 頁面加載時初始化側邊欄
            initSidebar();
            $(window).resize(initSidebar);
        });
    </script>
</body>
</html>