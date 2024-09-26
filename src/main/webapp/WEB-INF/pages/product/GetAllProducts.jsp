<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="product.bean.Products" %>
<%@ page import="product.dao.ProductDao" %>
<%@ page import="org.hibernate.SessionFactory" %>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品管理 - TickitEasy 管理系統</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <!-- 新增試試看 -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.9/css/responsive.dataTables.min.css">
<script type="text/javascript" src="https://cdn.datatables.net/responsive/2.2.9/js/dataTables.responsive.min.js"></script>
    <style>
        .sidebar {
	top: 4rem; /* 64px，使它與頂部導航欄高度相同 */
	height: calc(100vh - 4rem); /*高度設置為視窗高度減去頂部導航欄高度，確保側邊欄填滿剩餘空間。*/
}

/* 內容區樣式 */
.content-with-sidebar {
	padding-top: 4rem; /* 為頂部導航欄留出空間 */
}
/*響應式調整*/
#sidebarToggle {
	position: fixed;
	top: 1rem;
	left: 1rem;
	z-index: 9999; /
	background-color: #3182ce;
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
}

@media ( min-width : 1024px) {
	#sidebarToggle {
		left: 14.5rem; /* 側邊欄打開時的位置 */
	}
	#sidebarToggle.button-shifted {
		left: 1rem; /* 側邊欄打開時，將按鈕移到左邊 */
	}
}

@media ( min-width : 1024px) {
	#sidebarToggle {
		left: 1em; /* 224px，使它與頂部導航欄高度相同 */
		transition: left 0.3s ease-in-out;
	}
	.sidebar {
		position: fixed;
		left: 0;
		width: 14rem; /* 224px */
		z-index: 30;
		transition: left 0.3s ease-in-out;
	}
	.content-with-sidebar {
		margin-left: 14rem; /* 224px */
		transition: margin-left 0.3s ease-in-out;
	}
	.sidebar-closed {
		left: -14rem;
	}
	.content-full {
		margin-left: 0;
	}
	.button-shifted {
		left: 1rem; /* 將按鈕移到左邊 */
	}
}
/* DataTables外觀調整 */
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

#sidebarToggle i {
	font-size: 1.5rem; /* 調整大小 */
	color: white; /* 調整顏色 */
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
               <a href="${pageContext.request.contextPath}/admin/dashboard"
					class="block py-2 px-4 hover:bg-gray-700">後台管理首頁</a> <a
					href="${pageContext.request.contextPath}/admin/memberManagement"
					class="block py-2 px-4 hover:bg-gray-700 ">會員管理</a>
					<a
					href="${pageContext.request.contextPath}/admin/memberStatistics.jsp"
					class="block py-2 px-4 hover:bg-gray-700">會員統計分析</a>
					 <a
					href="${pageContext.request.contextPath}/event/ReadAllTicketTypes.jsp" class="block py-2 px-4 hover:bg-gray-700 ">活動管理</a> <a
					href="${pageContext.request.contextPath}/order/ordersHTML/prodOrders.html" class="block py-2 px-4 hover:bg-gray-700">訂單管理</a> <a
					href="${pageContext.request.contextPath}/product/GetAllProducts.jsp" class="block py-2 px-4 hover:bg-gray-700 bg-gray-900">商品管理</a> <a
					href="${pageContext.request.contextPath}/GetAllPost" class="block py-2 px-4 hover:bg-gray-700">討論區管理</a> <a
					href="${pageContext.request.contextPath}/FundProjs" class="block py-2 px-4 hover:bg-gray-700">募資活動管理</a>
            </nav>
        </div>

        <!-- 主要內容區 -->
        <div class="flex-1 flex flex-col overflow-hidden content-with-sidebar">
            <main class="flex-1 overflow-x-hidden overflow-y-auto bg-gray-200">
                <div class="container mx-auto px-4 py-8">
                    <h1 class="text-3xl font-bold mb-4">商品管理</h1>
                    <div class="mb-4 flex justify-between">
                        <!-- <form method="get" action="<%= request.getContextPath() %>/product/AddProduct.html"> -->
                        <!-- <form method="post" action="<%= request.getContextPath() %>/AddProduct/addProduct" style="display:inline;">-->
                        <!--  
                        <form method="post" action="${pageContext.request.contextPath}/AddProduct/addProduct" enctype="multipart/form-data">    
                            <input type="submit" value="新增商品" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                        </form>-->
                          <!-- 測試-->
                        <form method="get" action="${pageContext.request.contextPath}/AddProduct/showForm">
					    <input type="submit" value="新增商品" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
						</form>
                        <!-- <form method="get" action="<%= request.getContextPath() %>/product/GetProduct.html">
                            <input type="submit" value="查詢商品資料" class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">
                        </form>-->
                    </div>
                    <div class="overflow-x-auto">
                        <table id="productTable" class="w-full bg-white">
                            <thead class="bg-gray-800 text-white">
                                <tr>
                                    <th class="py-3 px-4 text-left">商品編號</th>
                                    <th class="py-3 px-4 text-left">分類</th>
                                    <th class="py-3 px-4 text-left">商品名稱</th>
                                    <th class="py-3 px-4 text-left">商品圖片</th>
                                    <th class="py-3 px-4 text-left">商品描述</th>
                                    <th class="py-3 px-4 text-left">價格</th>
                                    <th class="py-3 px-4 text-left">庫存</th>
                                    <th class="py-3 px-4 text-left">狀態</th>
                                    <th class="py-3 px-4 text-left">評論總數</th>
                                    <th class="py-3 px-4 text-left">總評分</th>
                                    <th class="py-3 px-4 text-left">操作</th>
                                </tr>
                            </thead>
                            <tbody class="text-gray-700">
                                <!--SessionFactory sessionFactory= new SessionFactory();
                                ProductDao productDao = new ProductDao(sessionFactory);
                                try {
                                    productList = productDao.findAll();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (productList != null) {
                                if (productList != null) {
                                    for (Products product : productList) {-->
                                <%
                                List<Products> productList = (List)request.getAttribute("product");
                                    for (Products product : productList) {    
                                    
                                %>
                                <tr>
                                    <td class="py-3 px-4"><%= product.getProductID() %></td>
                                    <td class="py-3 px-4"><%= product.getCategory() %></td>
                                    <td class="py-3 px-4"><%= product.getProductName() %></td>
                                    <td class="py-3 px-4">
                                       <!-- <img src="<%= request.getContextPath() + "/" + product.getProductPic() %>" alt="商品圖片" class="w-80 object-cover" />-->
                                        <!--  <img src="<%= request.getContextPath() + "/product/images/" + product.getProductPic() %>" alt="商品圖片" class="w-80 object-cover" />-->
                                    	<!--  測試過這樣只有新增或更新才會讀到?但也不一定<img src="<%= request.getContextPath() %>/product/images/<%=product.getProductPic()%>" alt="image" style="max-width:70px; height: auto">-->
                                    	<img src="<%= request.getContextPath() %>/product/images/<%=product.getProductPic()%>" alt="image" style="max-width:70px; height: auto">
                                    	 
                                    </td>
                                    <td class="py-3 px-4"><%= product.getProductDesc() %></td>
                                    <td class="py-3 px-4"><%= product.getPrice() %></td>
                                    <td class="py-3 px-4"><%= product.getStock() %></td>
                                    <td class="py-3 px-4"><%= product.getStatus() %></td>
                                    <td class="py-3 px-4"><%= product.getProdTotalReviews() %></td>
                                    <td class="py-3 px-4"><%= product.getProdTotalScore() %></td>
                                    <td class="py-3 px-4">
                                        <div class="flex space-x-1">
                                             <!--<form method="get" action="<%= request.getContextPath() %>/UpdateProduct" style="display:inline;"> 
                                             <form method="post" action="<%= request.getContextPath() %>/UpdateProduct/updateProductById" style="display:inline;"> 
                                                <input type="hidden" name="productID" value="<%= product.getProductID() %>">
                                                <button type="submit" class="bg-yellow-500 hover:bg-yellow-700 text-white font-bold py-1 px-2 rounded text-xs">修改</button>
                                            </form>-->
                                            <form method="get" action="${pageContext.request.contextPath}/UpdateProduct/getProductForUpdate" style="display:inline;">
											    <input type="hidden" name="productID" value="<%= product.getProductID() %>">
											    <button type="submit" class="bg-yellow-500 hover:bg-yellow-700 text-white font-bold py-1 px-2 rounded text-xs">修改</button>
											</form>
                                            <!-- <form method="post" action="<%= request.getContextPath() %>/DeleteProduct" style="display:inline;"> -->
                                            	<form method="post" action="<%= request.getContextPath() %>/DeleteProduct/deleteProductById" style="display:inline;">
                                                <input type="hidden" name="productID" value="<%= product.getProductID() %>">
                                                <button type="submit" class="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded text-xs">刪除</button>
                                            </form>
                                        </div>
                                    </td>
                                </tr>
                                <%
                                    }
                                
                                %>
                                <tr>
                                 <!--   <td colspan="11" class="py-3 px-4 text-center">目前沒有商品資料</td>-->  
                                </tr>
                                <%
                                
                                %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </main>
        </div>
    </div>

    <script>
    $(document).ready(function() {
        $('#productTable').DataTable({
            "language": {
                "url" : "${pageContext.request.contextPath}/resource/Chinese-traditional.json"
            },
            "pageLength": 10,
            "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "全部"]],
            "responsive": true,
            "ordering": true, // 确保排序功能开启
            "searching": true  // 确保搜索功能开启
        });
 


        $('#sidebarToggle').click(function() {
            $('.sidebar').toggleClass('sidebar-closed');
            $('.content-with-sidebar').toggleClass('content-full');
            $(this).toggleClass('button-shifted');
        });

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

        initSidebar();
        $(window).resize(initSidebar);
    });
    </script>
</body>
</html>