<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員管理 - TickitEasy 管理系統</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        .sidebar {
            top: 4rem;
            height: calc(100vh - 4rem);
        }
        .content-with-sidebar {
            padding-top: 4rem;
        }
        #sidebarToggle {
            position: fixed;
            top: 1rem;
            left: 1rem;
            z-index: 9999;
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
        @media (min-width: 1024px) {
            #sidebarToggle {
                left: 14.5rem;
            }
            #sidebarToggle.button-shifted {
                left: 1rem;
            }
            .sidebar {
                position: fixed;
                left: 0;
                width: 14rem;
                z-index: 30;
                transition: left 0.3s ease-in-out;
            }
            .content-with-sidebar {
                margin-left: 14rem;
                transition: margin-left 0.3s ease-in-out;
            }
            .sidebar-closed {
                left: -14rem;
            }
            .content-full {
                margin-left: 0;
            }
        }
        .dataTables_wrapper .dataTables_length, 
        .dataTables_wrapper .dataTables_filter,
        .dataTables_wrapper .dataTables_info, 
        .dataTables_wrapper .dataTables_processing,
        .dataTables_wrapper .dataTables_paginate {
            color: #4a5568;
            margin-bottom: 10px;
        }
        .dataTables_wrapper .dataTables_paginate .paginate_button.current,
        .dataTables_wrapper .dataTables_paginate .paginate_button.current:hover {
            background: #4299e1;
            border-color: #4299e1;
            color: white !important;
        }
        .modal {
            display: none;
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.4);
        }
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            max-width: 500px;
        }
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
        .modal-image {
            width: 100%;
            height: auto;
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
                <a href="${pageContext.request.contextPath}/admin/memberManagement" class="block py-2 px-4 hover:bg-gray-700 bg-gray-900">會員管理</a>
                <a href="${pageContext.request.contextPath}/admin/memberStatistics" class="block py-2 px-4 hover:bg-gray-700">會員統計分析</a>
                <a href="${pageContext.request.contextPath}/event/ReadAllTicketTypes.jsp" class="block py-2 px-4 hover:bg-gray-700">活動管理</a>
                <a href="${pageContext.request.contextPath}/order/ordersHTML/prodOrders.html" class="block py-2 px-4 hover:bg-gray-700">訂單管理</a>
                <a href="${pageContext.request.contextPath}/product/GetAllProducts.jsp" class="block py-2 px-4 hover:bg-gray-700">商品管理</a>
                <a href="${pageContext.request.contextPath}/GetAllPost" class="block py-2 px-4 hover:bg-gray-700">討論區管理</a>
                <a href="${pageContext.request.contextPath}/FundProjs" class="block py-2 px-4 hover:bg-gray-700">募資活動管理</a>
            </nav>
        </div>

        <!-- 主要內容區 -->
        <div class="flex-1 flex flex-col overflow-hidden content-with-sidebar">
            <main class="flex-1 overflow-x-hidden overflow-y-auto bg-gray-200">
                <div class="container mx-auto px-4 py-8">
                    <h1 class="text-3xl font-bold mb-4">會員管理</h1>
                    <div class="overflow-x-auto">
                        <table id="memberTable" class="w-full bg-white">
                            <thead class="bg-gray-800 text-white">
                                <tr>
                                    <th class="py-3 px-4 text-left whitespace-nowrap">ID</th>
                                    <th class="py-3 px-4 text-left whitespace-nowrap">頭像</th>
                                    <th class="py-3 px-4 text-left whitespace-nowrap">Email</th>
                                    <th class="py-3 px-4 text-left whitespace-nowrap">姓名</th>
                                    <th class="py-3 px-4 text-left whitespace-nowrap">暱稱</th>
                                    <th class="py-3 px-4 text-left whitespace-nowrap">生日</th>
                                    <th class="py-3 px-4 text-left whitespace-nowrap">電話</th>
                                    <th class="py-3 px-4 text-left whitespace-nowrap">註冊日期</th>
                                    <th class="py-3 px-4 text-left whitespace-nowrap">狀態</th>
                                    <th class="py-3 px-4 text-left whitespace-nowrap">操作</th>
                                </tr>
                            </thead>
                            <tbody class="text-gray-700">
                                <c:forEach var="member" items="${members}">
                                    <tr data-member-id="${member.memberID}">
                                        <td class="py-3 px-4">${member.memberID}</td>
                                        <td class="py-3 px-4">
                                            <img src="${pageContext.request.contextPath}${member.profilePic}"
                                                 alt="${empty member.profilePic ? '預設頭像' : '用戶頭像'}" 
                                                 class="w-10 h-10 rounded-full cursor-pointer"
                                                 onclick="openModal(this.src)">
                                        </td>
                                        <td class="py-3 px-4">${member.email}</td>
                                        <td class="py-3 px-4">${member.name}</td>
                                        <td class="py-3 px-4">${member.nickname}</td>
                                        <td class="py-3 px-4">${member.birthDate}</td>
                                        <td class="py-3 px-4">${member.phone}</td>
                                        <td class="py-3 px-4">${member.registerDate}</td>
                                        <td class="py-3 px-4">
                                            <select id="status_${member.memberID}" class="bg-white border border-gray-300 rounded-md py-1 px-2 w-full">
                                                <option value="未驗證" ${member.status == '未驗證' ? 'selected' : ''}>未驗證</option>
                                                <option value="已驗證" ${member.status == '已驗證' ? 'selected' : ''}>已驗證</option>
                                                <option value="討論區停權" ${member.status == '討論區停權' ? 'selected' : ''}>討論區停權</option>
                                            </select>
                                        </td>
                                        <td class="py-3 px-4">
                                            <div class="flex space-x-1">
                                                <button onclick="updateMemberStatus(${member.memberID})" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded text-xs">更新狀態</button>
                                                <button onclick="removeProfilePic(${member.memberID})" class="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded text-xs">移除頭貼</button>
                                                <button onclick="edit(${member.memberID})" class="bg-yellow-500 hover:bg-yellow-700 text-white font-bold py-1 px-2 rounded text-xs">編輯</button>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </main>
        </div>
    </div>

    <div id="imageModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <img id="modalImage" class="modal-image" src="" alt="放大的頭像">
        </div>
    </div>


	<script>
    var contextPath = '${pageContext.request.contextPath}';
    var modal, modalImg, span;

    $(document).ready(function() {
        initializeDataTable();
        initializeSidebar();
        initializeModal();
        
        $('#sidebarToggle').click(toggleSidebar);
        $(window).resize(initializeSidebar);

        // 處理所有圖片路徑
        $('img').each(function() {
            var src = $(this).attr('src');
            if (!src.startsWith('http') && !src.startsWith('/')) {
                $(this).attr('src', contextPath + '/' + src);
            }
        });
    });

    function initializeDataTable() {
        $('#memberTable').DataTable({
            "language": {
                "url": contextPath + "/resource/Chinese-traditional.json"
            },
            "pageLength": 10,
            "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "全部"]],
            "responsive": true
        });
    }

    function initializeSidebar() {
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

    function toggleSidebar() {
        $('.sidebar').toggleClass('sidebar-closed');
        $('.content-with-sidebar').toggleClass('content-full');
        $(this).toggleClass('button-shifted');
    }

    function initializeModal() {
        modal = document.getElementById("imageModal");
        modalImg = document.getElementById("modalImage");
        span = document.getElementsByClassName("close")[0];

        if (span) {
            span.onclick = closeModal;
        }

        window.onclick = function(event) {
            if (event.target == modal) {
                closeModal();
            }
        }
    }

    function edit(memberId) {
        window.location.href = contextPath + '/admin/memberManagement/edit?action=edit&memberId=' + memberId;
    }

    function updateMemberStatus(memberId) {
        var newStatus = document.getElementById('status_' + memberId).value;
        $.ajax({
            url: contextPath + '/admin/memberManagement/updateStatus',
            type: 'POST',
            data: {
                action: 'updateStatus',
                memberId: memberId,
                status: newStatus
            },
            success: function(response) {
                alert('會員狀態已更新');
                location.reload();
            },
            error: function() {
                alert('更新失敗，請稍後再試');
            }
        });
    }

    function removeProfilePic(memberId) {
        if (confirm('確定要移除此會員的頭貼嗎？')) {
            $.ajax({
                url: contextPath + '/admin/memberManagement/removeProfilePic',
                type: 'POST',
                data: {
                    action: 'removeProfilePic',
                    memberId: memberId
                },
                success: function(response) {
                    alert('會員頭貼已移除');
                    // 更新特定會員的頭像
                    var imgElement = $('tr[data-member-id="' + memberId + '"] img');
                    imgElement.attr('src', contextPath + '/images/default-avatar.png');
                },
                error: function() {
                    alert('移除失敗，請稍後再試');
                }
            });
        }
    }

    function openModal(imgSrc) {
        if (modal && modalImg) {
            modal.style.display = "block";
            // 確保 imgSrc 是絕對路徑
            if (!imgSrc.startsWith('http') && !imgSrc.startsWith('/')) {
                imgSrc = contextPath + '/' + imgSrc;
            }
            modalImg.src = imgSrc;
        }
    }

    function closeModal() {
        if (modal) {
            modal.style.display = "none";
        }
    }
</script>

</body>
</html>