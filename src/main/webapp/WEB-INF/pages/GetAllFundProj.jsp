<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,cwdfunding.bean.FundProjBean, java.time.LocalDateTime, java.time.format.DateTimeFormatter"%>

<!DOCTYPE html>
<html lang="zh-Hant-TW">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>活動票種管理 - TickitEasy 管理系統</title>
<link
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="https://unpkg.com/nice-forms.css@0.1.7/dist/nice-forms.css" />
<style>


/* 左側選單 */
.sidebar { /* 預設樣式 與 開啟選單時 */
	position: fixed;
	top: 4rem; /* 留出空間給頂部導航欄。 */
	height: calc(100vh - 4rem); /* 視窗高度 - 頂部導航欄高度，以向下填滿視窗空間。 */
	left: 0;
	width: 14rem; /* 224px */
	z-index: 30;
	transition: left 0.3s ease-in-out;
}

.sidebar-closed { /* 關閉選單時 */
	left: -14rem;
}

#sidebarToggle { /* 左側選單的 toggle 按鈕 */
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
.content-with-sidebar { /* 預設樣式 與 開啟選單時 */
	padding-top: 4rem; /* 留出空間給頂部導航欄。 */
	margin-left: 14rem; /* 224px */
	transition: margin-left 0.3s ease-in-out;
}

.content-full { /* 關閉選單時 */
	margin-left: 0;
}

div.container {
	margin: auto;
	max-width: 1250px;
	/* 限制最大寬度，以防左側選單 toggle 影響寬度；但是實際測量 DataTable 寬度為 1500px 多，不懂為什麼在此設 1250px 剛好。 */
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

/* button */
        .btn {
            cursor: pointer;
            border-radius: 10px;
            padding: 10px 15px;
            background-color: #e7e7e7;
            border: 0.5px solid lightgray; 
            font-weight: bold;
        }
        .btn-X {
            cursor: pointer;
			text-font: 30px;
			font-weight: bold;
        }
        /* 確定按鈕 */
        .btn-check {
            background-color: #4CAF50; /* 綠色 */
            color: white;
        }

        .btn-check:hover {
            background-color: #45a049; /* 懸停時稍微變深 */
        }

        .btn-check:active {
            transform: scale(0.95); /* 按下時微縮 */
        }

        /* 取消按鈕 */
        .btn-cancel {
            background-color: #f44336; /* 紅色 */
            color: white;
        }

        .btn-cancel:hover {
            background-color: #e53935; /* 懸停時稍微變深 */
        }

        .btn-cancel:active {
            transform: scale(0.95); /* 按下時微縮 */
        }

/* Form */
        .form {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 90%;
            max-width: 600px;
            max-height: 90vh;
            overflow-y: auto;
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            z-index: 1000;
            border: 1px solid lightgray; 
            border-radius: 10px;
            padding: 20px;
			background-color: #f4f4f4;
			box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;         
		}
        
        .hidden {
            display: none !important;
        }
        
        .form-header{
        	display: flex;
        	justify-content: space-between;
        }
        
        .form h3{
        	font-size: 20px;
        	font-weight: bold;
        }
 		.sm {
			color: red;
		} 
			
</style>
</head>

<body class="bg-gray-100">
	<button id="sidebarToggle"
		class="fixed top-5 left-4 z-50 bg-blue-600 text-white p-2 rounded-md">
		<i class="fas fa-bars"></i>
	</button>

	<!-- 頂部導航欄 -->
	<nav
		class="bg-blue-600 text-white shadow-lg fixed top-0 left-0 right-0 z-50">
		<div class="max-w-7xl mx-auto px-4">
			<div class="flex items-center justify-between h-16">
				<div class="flex items-center">
					<span class="font-semibold text-xl ml-2">TickitEasy 管理系統</span>
				</div>
				<div>
					<a href="${pageContext.request.contextPath}/admin/adminLogout"
						class="text-white hover:text-gray-200">登出</a>
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
					class="block py-2 px-4 hover:bg-gray-700 ">會員管理</a> <a
					href="${pageContext.request.contextPath}/admin/memberStatistics.jsp"
					class="block py-2 px-4 hover:bg-gray-700">會員統計分析</a> <a
					href="${pageContext.request.contextPath}/event/ReadAllTicketTypes.jsp"
					class="block py-2 px-4 hover:bg-gray-700 bg-gray-900">活動管理</a> <a
					href="${pageContext.request.contextPath}/order/ordersHTML/prodOrders.html"
					class="block py-2 px-4 hover:bg-gray-700">訂單管理</a> <a
					href="${pageContext.request.contextPath}/product/GetAllProducts.jsp"
					class="block py-2 px-4 hover:bg-gray-700">商品管理</a> <a
					href="${pageContext.request.contextPath}/GetAllPost"
					class="block py-2 px-4 hover:bg-gray-700">討論區管理</a> <a
					href="${pageContext.request.contextPath}/FundProjs"
					class="block py-2 px-4 hover:bg-gray-700">募資活動管理</a>
			</nav>
		</div>

		<!-- 主要內容區 -->
		<div class="flex-1 flex flex-col overflow-hidden content-with-sidebar">
			<main class="flex-1 overflow-x-hidden overflow-y-auto bg-gray-200">
				<div class="container mx-auto px-4 py-8">
					<div class="flex items-center justify-between mb-4">
						<h1 class="text-3xl font-bold mb-4 ml-4">募資管理</h1>
						<button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 mr-4 rounded btn-add">新增</button>
					</div>
					<div class="overflow-x-auto">
						<table id="table-projs" class="w-full bg-white">
							<thead class="bg-gray-800 text-white">
								<tr>
									<th class="py-3 px-4 text-center" nowrap>編號</th>
									<th class="py-3 px-4 text-center" nowrap>名稱</th>
									<th class="py-3 px-4 text-center" nowrap>內容</th>
									<th class="py-3 px-4 text-center" nowrap>圖片</th>
									<th class="py-3 px-4 text-center" nowrap>開始時間</th>
									<th class="py-3 px-4 text-center" nowrap>截止時間</th>
									<th class="py-3 px-4 text-center" nowrap>目標金額</th>
									<th class="py-3 px-4 text-center" nowrap>目前金額</th>
									<th class="py-3 px-4 text-center" nowrap>展延門檻</th>
									<th class="py-3 px-4 text-center" nowrap>展延到期時間</th>
									<th class="py-3 px-4 text-center" nowrap>專案類別</th>
									<th class="py-3 px-4 text-center" nowrap>選項</th>
								</tr>
							</thead>
							<tbody class="text-gray-700">
								<%
									List<FundProjBean> projs = (ArrayList<FundProjBean>) request.getAttribute("projs");
										for (FundProjBean proj : projs) {
								%>
								<tr>
									<td class="py-3 px-4 text-center"><%= proj.getProjectID() %></td>
									<td class="py-3 px-4 "><%= proj.getTitle() %></td>
									<td class="py-3 px-4 "><%= proj.getDescription() %></td>
									<td class="py-3 px-4 text-center">
									<img src="<%= request.getContextPath() %>/images/<%=proj.getImage()%>" alt="image" style="max-width:70px; height: auto"> 
										<input type="hidden" value="<%=proj.getImage()%>"></td>
									<td class="py-3 px-4 text-center"><%=proj.getFormattedStartDate() %></td>
									<td class="py-3 px-4 text-center"><%=proj.getFormattedEndDate()%></td>
									<td class="py-3 px-4 text-center"><%=proj.getTargetAmount()%></td>
									<td class="py-3 px-4 text-center"><%=proj.getCurrentAmount()%></td>
									<td class="py-3 px-4 text-center"><%=proj.getThreshold()%></td>
									<td class="py-3 px-4 text-center"><%=proj.getFormattedPostponeDate()%></td>
									<td class="py-3 px-4 text-center"><%=proj.getCategory()%></td>
									<td class="py-3 px-4 text-center">
										<div class="div-actions">			
											<div class="div-actions-ele">
												<button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-1 px-2 rounded text-xs btn-update">修改</button>
											</div>								
											<div class="div-actions-ele">
											<form action="<%= request.getContextPath() %>/FundProj/delete" method="post">
												<input type="hidden" name="del-projectID" value="<%=proj.getProjectID()%>"> 								
												<button type="submit" class="bg-red-500 hover:bg-red-700 text-white font-bold py-1 px-2 rounded text-xs btn-delete">刪除</button>
											</form>
											</div>
										</div></td> <%}%>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</main>
		</div>
		<!-- 新增表單 -->
		<div class="form form-add hidden">
				<div class="form-header">
					<h3>新增</h3>
					<button class="btn-X btn-close">X</button>
				</div>
				<form action="<%= request.getContextPath() %>/FundProj/insert" method="post" enctype="multipart/form-data">						<div class="nice-form-group">
							<label>專案名稱</label><small class="sm" id="sp-title" style="font-size:12.5px; font-weight: bold"></small>
							<input type="text" id="title" name="title" data-error="sp-title" >
						</div>
						<div class="nice-form-group">
							<label>專案敘述</label><small class="sm" id="sp-description" style="font-size:12.5px; font-weight: bold"></small>
							<textarea name="description" maxlength="150"
								placeholder="上限200個字" name="description" data-error="sp-description" ></textarea>
						</div>
						<div class="nice-form-group">
							<label>專案圖片</label><small class="sm" id="sp-image" style="font-size:12.5px; font-weight: bold"></small>
							<input type="file" id="image" name="image" >
						</div>
						<div class="nice-form-group">
							<label>開始日期</label><small class="sm" id="sp-startDate" style="font-size:12.5px; font-weight: bold"></small>
							<input type="datetime-local" id="startDate"
								placeholder="yyyy-mm-dd" name="startDate" >
						</div>
						<div class="nice-form-group">
							<label>截止日期</label><small class="sm" id="sp-endDate" style="font-size:12.5px; font-weight: bold"></small>
							<input type="datetime-local" id="endDate"
								placeholder="yyyy-mm-dd" name="endDate" >
						</div>
						<div class="nice-form-group">
							<label>目標金額</label><small class="sm" id="sp-targetAmount" style="font-size:12.5px; font-weight: bold"></small>
							<input type="text" id="targetAmount"
								name="targetAmount" >
						</div>
						<div class="nice-form-group">
							<label>目前金額</label><small class="sm" id="sp-currentAmount" style="font-size:12.5px; font-weight: bold"></small>
							<input type="text" id="currentAmount"
								name="currentAmount" >
						</div>
						<div class="nice-form-group">
							<label>展延門檻</label><small class="sm" id="sp-threshold" style="font-size:12.5px; font-weight: bold"></small>
							<input type="text" id="threshold"
								placeholder="0-1" name="threshold">
						</div>
						<div class="nice-form-group">
							<label>展延日期</label><small class="sm" id="sp-postponeDate" style="font-size:12.5px; font-weight: bold"></small>
							<input type="datetime-local" id="postponeDate"
								placeholder="yyyy-mm-dd" name="postponeDate">
						</div>
						<div class="nice-form-group">
							<label>分類</label> <small class="sm" id="sp-category" style="font-size:12.5px; font-weight: bold"></small>
							<select id="category" name="category" >
								<option value="表演" selected>表演</option>
								<option value="音樂會">音樂會</option>
								<option value="藝術">藝術</option>
								<option value="其他">其他</option>
							</select>
						</div>
						<div class="nice-form-group">
                            <input id="id-btn-add" class="btn btn-check" type="submit" value="確定" />
                            <button type="button" class="btn btn-close btn-cancel">取消</button>
                        </div>
                 </form>
            </div>
       <!-- 修改表單 -->
       <div class="form form-update hidden">
				<div class="form-header">
					<h3>修改</h3>
					<button class="btn-X btn-close">X</button>
				</div>
					<form action="<%= request.getContextPath() %>/FundProj/update" 
						method="post" enctype="multipart/form-data">
						<div class="nice-form-group">
							<label>專案編號</label><input type="text" id="udt-projectID" name="udt-projectID" readonly>
						</div>
						<div class="nice-form-group">
							<label>專案名稱</label><small class="sm" id="sp-udt-title" style="font-size:12.5px; font-weight: bold"></small>
							<input type="text" id="udt-title" name="udt-title">
						</div>
						<div class="nice-form-group">
							<label>專案敘述</label><small class="sm" id="sp-udt-description" style="font-size:12.5px; font-weight: bold"></small>
							<textarea maxlength="200"
								placeholder="上限200個字" name="udt-description"></textarea>
						</div>
						<div class="nice-form-group formEle-img">
							<label>專案圖片</label><small class="sm" id="sp-udt-image" style="font-size:12.5px; font-weight: bold"></small>
							<img id="udt-image-preview" src="" alt="專案圖片" style="max-width: 100px; height: auto;">
							<input type="file" id="udt-image" name="udt-image">
							<input type="hidden" id="old-image" name="old-image"> 								
						</div>
						<div class="nice-form-group">
							<label>開始日期</label><small class="sm" id="sp-udt-startDate" style="font-size:12.5px; font-weight: bold"></small>
							<input type="datetime-local" id="udt-startDate"
								placeholder="yyyy-mm-dd" name="udt-startDate">
						</div>
						<div class="nice-form-group">
							<label>截止日期</label><small class="sm" id="sp-udt-endDate" style="font-size:12.5px; font-weight: bold"></small>
							<input type="datetime-local" id="udt-endDate"
								placeholder="yyyy-mm-dd" name="udt-endDate">
						</div>
						<div class="nice-form-group">
							<label>目標金額</label><small class="sm" id="sp-udt-targetAmount" style="font-size:12.5px; font-weight: bold"></small>
							<input type="text" id="udt-targetAmount"
								name="udt-targetAmount">
						</div>
						<div class="nice-form-group">
							<label>目前金額</label><small class="sm" id="sp-udt-currentAmount" style="font-size:12.5px; font-weight: bold"></small>
							<input type="text" id="udt-currentAmount"
								name="udt-currentAmount">
						</div>
						<div class="nice-form-group">
							<label>展延門檻</label><small class="sm" id="sp-udt-threshold" style="font-size:12.5px; font-weight: bold"></small>
							<input type="text" id="udt-threshold"
								placeholder="0-1" name="udt-threshold">
						</div>
						<div class="nice-form-group">
							<label>展延日期</label><small class="sm" id="sp-udt-postponeDate" style="font-size:12.5px; font-weight: bold"></small>
							<input type="datetime-local" id="udt-postponeDate"
								placeholder="yyyy-mm-dd" name="udt-postponeDate">
						</div>
						<div class="nice-form-group">
							<label>分類</label><small class="sm" id="sp-category" style="font-size:12.5px; font-weight: bold"></small>
							 <select id="udt-category" name="udt-category">
								<option value="表演" selected>表演</option>
								<option value="音樂會">音樂會</option>
								<option value="藝術">藝術</option>
								<option value="其他">其他</option>
							</select>
						</div>
						<div class="nice-form-group">
							<input class="btn btn-check" type="submit" value="確定" />
                            <button type="button" class="btn btn-close btn-cancel">取消</button>							
						</div>
				</form>
			</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/validate.js/0.13.1/validate.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
		$(document).ready(	
					function() {
							$('#table-projs').DataTable(
								{ "language" : 
								{"url" : "${pageContext.request.contextPath}/resource/Chinese-traditional.json"}
								});

							// 側邊欄切換功能
							$('#sidebarToggle').click(
									function() {
										$('.sidebar').toggleClass(
												'sidebar-closed');
										$('.content-with-sidebar').toggleClass(
												'content-full');
										$(this).toggleClass('button-shifted');
									});

							// 初始化側邊欄位置
							function initSidebar() {
								if (window.innerWidth >= 1024) {
									$('.sidebar').removeClass('sidebar-closed');
									$('.content-with-sidebar').removeClass(
											'content-full');
									$('#sidebarToggle').removeClass(
											'button-shifted');
								} else {
									$('.sidebar').addClass('sidebar-closed');
									$('.content-with-sidebar').addClass(
											'content-full');
									$('#sidebarToggle').addClass(
											'button-shifted');
								}
							}

							// 頁面加載時初始化側邊欄
							initSidebar();
							$(window).resize(initSidebar);
							
							//[按鈕]新增表單顯示與隱藏
							$('.btn-add').click(function() {
								if ($('.form-add').hasClass('hidden')){
				                	$('.form-add').removeClass('hidden');
				                	//$('body').toggleClass('overflow-hidden');
				                	$('.container').css({'filter':'opacity(20%)'})
								}
				            });
							
/* 							//[按鈕] 表單送出後隱藏表單
							$('.btn-check').click(function(event){
								event.preventDefault();
								if( !$('.form-add').hasClass('hidden')){
					                $('.form-add').addClass('hidden');
								}
								if( !$('.form-update').hasClass('hidden')){
					                $('.form-update').addClass('hidden');
								}
							}) */
							
							//[按鈕]關閉X 1.新增表單 2.修改表單
							$('.btn-close').click(function(event) {
								event.preventDefault();
				                $('.form-add').addClass('hidden');
				                $('.form-update').addClass('hidden');
				                $('.container').css({'filter':''})				                
				            });
							
							//[按鈕]刪除按鈕使用sweetalert風格
							$('.btn-delete').click(function(event){
								event.preventDefault();
								swal({
									title: "確定刪除？",
									icon: "warning",
									buttons: {
										cancel:{
											text:"取消",
											visible: true
										},
										confirm:{
											text:"刪除",
											visible: true
										}
									},
									dangerMode: true
								}).then((willDelete) => {
									if (willDelete) {
										$(this).closest('form').submit();  
									}
								});
							})
							
							//[按鈕]修改表單顯示與隱藏 & 自動填充數據至修改表單
							$('.btn-update').click(function(){
								if ($('.form-update').hasClass("hidden")) {
						    		let row = $(this).closest('tr');
							        
							        // 取出專案的各個數據
							        let projectID = row.find('td:eq(0)').text().trim();
							        let title = row.find('td:eq(1)').text().trim();
							        let description = row.find('td:eq(2)').text().trim();
							        let image = row.find('td:eq(3) img').attr('src').trim();
							        let oldImage = row.find('td:eq(3) input').val().trim();
							        let startDate = row.find('td:eq(4)').text().trim();
							        let endDate = row.find('td:eq(5)').text().trim();
							        let targetAmount = row.find('td:eq(6)').text().trim();
							        let currentAmount = row.find('td:eq(7)').text().trim();
							        let threshold = row.find('td:eq(8)').text().trim();
							        let postponeDate = row.find('td:eq(9)').text().trim();
							        let category = row.find('td:eq(10)').text().trim();
		
							        // 將這些數據填充到表單中
							        $('#udt-projectID').val(projectID);
							        $('#udt-title').val(title);
							        $('textarea[name="udt-description"]').val(description);
							        $('#udt-image-preview').attr('src',image);
							        $('#old-image').val(oldImage);
							        $('#udt-startDate').val(startDate);
							        $('#udt-endDate').val(endDate);
							        $('#udt-targetAmount').val(targetAmount);
							        $('#udt-currentAmount').val(currentAmount);
							        $('#udt-threshold').val(threshold);
							        $('#udt-postponeDate').val(postponeDate);
							        $('#udt-category').val(category);
							        
							        //顯示表單
							        $('.form-update').removeClass("hidden");
				                	$('.container').css({'filter':'opacity(20%)'})
 								} 
							})
							
							/* 表單驗證 */
							const constraint_add = {
					            title: {
					                presence: { allowEmpty: false, message: "必填" },
					                length: {
					                    maximum: 50,
					                    message: "上限50字"
					                }
					            },
					            description: {
					                presence: { allowEmpty: false, message: "必填" },
					                length: {
					                    maximum: 200,
					                    message: "上限200字"
					                }
					            },
					            image: {
					                presence: { allowEmpty: false, message: "必填" },
					            },
					            startDate: {
					                presence: { allowEmpty: false, message: "必填" },
					            },
					            endDate: {
					                presence: { allowEmpty: false, message: "必填" },
					            },
					            targetAmount: {
					                presence: { allowEmpty: false, message: "必填" },
					                numericality: {
					                	  onlyInteger: true, // 只能是整數
					                	  greaterThanOrEqualTo: 0, // 只能大於等於零
					                	  message: "金額需大於等於0"
					                }
					            },
					            currentAmount: {
					                presence: { allowEmpty: false, message: "必填" },
					                numericality: {
					                	  onlyInteger: true, // 只能是整數
					                	  greaterThanOrEqualTo: 0, // 只能大於等於零
					                	  message: "金額需大於等於0"
					                }
					            },
					            threshold: {
					                presence: { allowEmpty: true, message: "必填" },
					                numericality: {
					                	  lessThanOrEqualTo: 1,
					                	  greaterThanOrEqualTo: 0, // 只能大於等於零
					                	  message: "介於0-1的小數"
					                }
					            },
					            postponeDate: {
					                presence: { allowEmpty: false, message: "必填" },
					            },
					            category: {
					                presence: { allowEmpty: false, message: "必填" },
					            },
							};
							
							const constraint_update = {
					            ['udt-title']: {
					                presence: { allowEmpty: false, message: "必填" },
					                length: {
					                    maximum: 50,
					                    message: "上限50字"
					                }
					            },
					            ['udt-description']: {
					                presence: { allowEmpty: false, message: "必填" },
					                length: {
					                    maximum: 200,
					                    message: "上限200字"
					                }
					            },
					            ['udt-startDate']: {
					                presence: { allowEmpty: false, message: "必填" },
					            },
					            ['udt-endDate']: {
					                presence: { allowEmpty: false, message: "必填" },
					            },
					            ['udt-targetAmount']: {
					                presence: { allowEmpty: false, message: "必填" },
					                numericality: {
					                	  onlyInteger: true, // 只能是整數
					                	  greaterThanOrEqualTo: 0, // 只能大於等於零
					                	  message: "金額需大於等於0"
					                }
					            },
					            ['udt-currentAmount']: {
					                presence: { allowEmpty: false, message: "必填" },
					                numericality: {
					                	  onlyInteger: true, // 只能是整數
					                	  greaterThanOrEqualTo: 0, // 只能大於等於零
					                	  message: "金額需大於等於0"
					                }
					            },
					            ['udt-threshold']: {
					                presence: { allowEmpty: true },
					                numericality: {
					                	  lessThanOrEqualTo: 1,
					                	  greaterThanOrEqualTo: 0, // 只能大於等於零
					                	  message: "介於0-1的小數"
					                }
					            },
					            ['udt-postponeDate']: {
					                presence: { allowEmpty: false, message: "必填" },
					            },
					            ['udt-category']: {
					                presence: { allowEmpty: false, message: "必填" },
					            }
					    	};


							// 表單整體驗證的函數，檢查是否所有欄位通過驗證
							function checkFormValidation(constraints) {
							    const formValues = {};
							    $(this).closest('form').find('input, textarea, select').each(function() {
							        formValues[$(this).attr('name')] = $(this).val();
							    });

							    const errors = validate(formValues, constraints);

							    if (!errors) {
							        // 如果表單沒有任何錯誤，啟用按鈕
							        $('.btn-check').prop('disabled', false)
							     	$('.btn-check').css({'background-color':'#4CAF50'});
							    } else {
							        // 如果有錯誤，保持按鈕禁用
							        $('.btn-check').prop('disabled', true);
							     	$('.btn-check').css({'background-color':'gray'});
							    }
							}

							
 							// Bind blur event to each input, textarea and select
					        $('.form-add input, .form-add textarea, .form-add select').on('blur', function() {
					            const fieldName = $(this).attr('name');
					            const fieldErrors = validate.single($(this).val(), constraint_add[fieldName]);
					            const errorSpanStr = "#sp-"+fieldName;
					            const errorSpan = $(errorSpanStr);

					            errorSpan.text(''); 
					            if (fieldErrors) {
					                errorSpan.text(fieldErrors[0]);
					            }
					            checkFormValidation.call(this, constraint_add);
					        });   
 							
					        $('.form-update input, .form-update textarea, .form-update select').on('blur', function() {
					            const fieldName = $(this).attr('name');
					            const fieldErrors = validate.single($(this).val(), constraint_update[fieldName]);
					            const errorSpanStr = "#sp-"+fieldName;
					            const errorSpan = $(errorSpanStr);
								console.log(fieldErrors);
					            
					            errorSpan.text(''); 
					            if (fieldErrors) {
					                errorSpan.text(fieldErrors[0]);
					            }
					            checkFormValidation.call(this, constraint_update);
					        });   
 							
					     	// 預設禁用按鈕
					        $('.btn-check').prop('disabled', true);
					     	$('.btn-check').css({'background-color':'gray'});

					        // 初始化時檢查表單，防止一開始就啟用按鈕
					        checkFormValidation.call($('.form-add input:first'), constraint_add);
					        checkFormValidation.call($('.form-update input:first'), constraint_update);
				});
	</script>
</body>
</html>