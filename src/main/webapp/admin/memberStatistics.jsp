<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員統計分析 - TickitEasy</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
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
					class="block py-2 px-4 hover:bg-gray-700">會員管理</a>
					<a
					href="${pageContext.request.contextPath}/admin/memberStatistics.jsp"
					class="block py-2 px-4 hover:bg-gray-700 bg-gray-900">會員統計分析</a> <a
					href="${pageContext.request.contextPath}/event/ReadAllTicketTypes.jsp" class="block py-2 px-4 hover:bg-gray-700">活動管理</a> <a
					href="#" class="block py-2 px-4 hover:bg-gray-700">訂單管理</a> <a
					href="${pageContext.request.contextPath}/product/GetAllProducts.jsp" class="block py-2 px-4 hover:bg-gray-700">商品管理</a> <a
					href="#" class="block py-2 px-4 hover:bg-gray-700">討論區管理</a> <a
					href="${pageContext.request.contextPath}/FundProjs" class="block py-2 px-4 hover:bg-gray-700">募資活動管理</a>
            </nav>
        </div>

        <!-- 主要內容區 -->
        <div class="flex-1 overflow-x-hidden overflow-y-auto bg-gray-100 content-with-sidebar">
            <div class="container mx-auto p-6">
                <h1 class="text-3xl font-bold mb-6">會員統計分析</h1>

                <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
                    <div class="bg-white p-6 rounded-lg shadow-md">
                        <h2 class="text-xl font-semibold mb-4">會員註冊趨勢</h2>
                        <canvas id="registrationTrendChart"></canvas>
                    </div>
                    <div class="bg-white p-6 rounded-lg shadow-md">
                        <h2 class="text-xl font-semibold mb-4">會員年齡分佈</h2>
                        <canvas id="ageDistributionChart"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
    $(document).ready(function() {
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

        // 獲取統計資料並繪製圖表chart.js
        $.ajax({
            url: '${pageContext.request.contextPath}/admin/memberStatistics',
            method: 'GET',
            dataType: 'json',
            success: function(statisticsData) {
                // 會員註冊趨勢圖表
                var registrationTrendCtx = document.getElementById('registrationTrendChart').getContext('2d');// 2d繪圖
                new Chart(registrationTrendCtx, {
                    type: 'line',// 線性圖
                    data: {
                        labels: Object.keys(statisticsData.registrationTrend),
                        datasets: [{
                            label: '新會員註冊數量',
                            data: Object.values(statisticsData.registrationTrend),
                            borderColor: 'rgb(75, 192, 192)',
                            tension: 0.1
                        }]
                    },
                    options: {
                        responsive: true,// 響應式繪圖
                        scales: {
                            y: {
                                beginAtZero: true// y軸起始值為0
                            }
                        }
                    }
                });

                // 會員年齡分佈圖表
                var ageDistributionCtx = document.getElementById('ageDistributionChart').getContext('2d');
                new Chart(ageDistributionCtx, {
                    type: 'pie',//圓餅圖
                    data: {
                        labels: Object.keys(statisticsData.ageDistribution),
                        datasets: [{
                            label: '年齡分佈',
                            data: Object.values(statisticsData.ageDistribution),
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)'
                            ],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true
                    }
                });
            },
            error: function(xhr, status, error) {
                console.error("Error fetching statistics:", error);
            }
        });
    });
    </script>
</body>
</html>