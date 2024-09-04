<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*,cwdfunding.bean.FundProjBean"%>
<%!@SuppressWarnings("unchecked")%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>募資管理 - TickitEasy 管理系統</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/2.1.4/css/dataTables.dataTables.css" />

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
                left: 1rem;
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
            .button-shifted {
                left: 1rem;
            }
        }

        /* 保留募資管理頁面的原有樣式 */
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            /*padding-top: 56px;*/
            background-color: #f4f4f4;
            text-align: center;
        }

        main {
            padding: 20px;
        }

        body {
            text-align: center;
        }

        .container{
            position:absolute;
            top:10%;
            height: auto;
            width: auto; 
            padding: 10px;
        }

        .top {
            display: flex;
            justify-content: center; /* 水平排列 */
            align-items: center; /* 垂直居中 */
            padding: 10px;
        }

        .top h1{
            margin: 0 auto; /* 自動水平居中 */
        }

        .btn {
            cursor: pointer;
            border-radius: 20px;
            padding: 5px 10px;
            background-color: #e7e7e7;
            border: 2px solid lightgray;
            font-weight: bold;
        }

        .btn-add {
            font-size: 16px;
        }

        .btn-update .btn-delete {
            /* justify-content: center; */
            align-items: center;
        /* 	display: inline;*/
            padding: 10px;
        }

        .btn-delete{
            border: 2px solid #EF6064;
        }

        .btn-update{
            border: 2px solid #F2BC57;	
        }

        .btn-update-header{
            display: flex;
            justify-content: center; /* 水平排列 */
            align-items: center; /* 垂直居中 */
            padding: 20px 20px;
        }

        .btn-update-header h3{
            margin: 0 auto; /* 自動水平居中 */
        }

        .table-projs {
            border: 2px solid #ffc197;
            margin: 20px;
            text-align: center;
            justify-content: center;	
            /* white-space: nowrap; */
        }

        .table-projs thead {
            background-color: #ffc197;
            text-align: center;
        }

        .div-td {
            height: 20px;
            overflow: hidden;
            text-overflow: ellipsis;
            overflow: hidden;
        }

        .div-actions {
            width: 110px;
            display: flex;
            justift-content: flex-start;
            align-items: center;
        }

        .div-actions-ele{
            width: 55px;
            height: 50px;
        }

        td {
            word-break: break-all;
        }

        .formContainer {
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
        }

        .form-add {
            border: 5px solid #ffc197;
            border-radius: 10px;
            padding: 20px;
			background-color: #f4f4f4;
        }

        .form-update {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            height: auto;
            max-height: 90vh;
            width: 90%;
            max-width: 600px;
            padding: 20px;
            border-radius: 10px;
            border: 5px dashed #ffc197;
            background: #f4f4f4;
            overflow-y: auto;
            z-index: 1000;
        }
        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 999;
            display: none;
        }

        .formEle {
            font-size: 18px;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 10px 20px;
        }

        .formEle label {
            flex-basis: 15%; /* label 占據 15% 的寬度 */
            text-align: right; /* 文字靠右對齊 */
            padding-right: 30px; /* 右側增加間距 */
        }

        .formEle input {
            flex-basis: 40%; /* input 占據 50% 的寬度  */
        }

        .formEle textarea {
            flex-basis: 40%; /* input 占據 50% 的寬度  */
        }

        .formEle select {
            flex-basis: 40%; /* input 占據 50% 的寬度  */
        }

        .formEle-img label{
            flex-basis: 70%; /* label 占據 15% 的寬度 */
        }

        .formEle-img img{
            flex-basis: 5%; /* label 占據 15% 的寬度 */
        }
        .formEle-img input{
            flex-basis: 85%;
            width: 100px;
        } 

        #span-ok {
            display: none;
            font-size: 20px;
        }

        .hidden {
            display: none !important;
        }

        .table-projs img{
            max-width: 100px;
            height: auto;
        }
    </style>
</head>
<body>
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
					href="${pageContext.request.contextPath}/product/GetAllProducts.jsp" class="block py-2 px-4 hover:bg-gray-700 ">商品管理</a> <a
					href="${pageContext.request.contextPath}/GetAllPost" class="block py-2 px-4 hover:bg-gray-700">討論區管理</a> <a
					href="${pageContext.request.contextPath}/FundProjs" class="block py-2 px-4 hover:bg-gray-700 bg-gray-900">募資活動管理</a>
            </nav>
        </div>

        <div class="flex-1 flex flex-col overflow-hidden content-with-sidebar">
            <main class="flex-1 overflow-x-hidden overflow-y-auto ">
                <div class="container mx-auto px-4 py-8">
        
 
 <!-- 原有的內容保持不變 -->
<!--  <main role="main"> -->
    <div class="container">
        <div class="top">
            <button class="btn btn-add">➕ 新增</button>
            <h1>募資專案</h1>
        </div>
        <div class="formContainer hidden">
            <div class="form-add">
                <form action="<%= request.getContextPath() %>/FundProjs?action=insert"
                    method="post" enctype="multipart/form-data">
						<div class="formEle">
							<label>專案名稱</label><input type="text" id="title" name="title">
						</div>
						<div class="formEle">
							<label>專案敘述</label>
							<textarea name="description" maxlength="150"
								placeholder="上限150個字" name="description"></textarea>
						</div>
						<div class="formEle">
							<label>專案圖片</label><input type="file" id="image" name="image">
						</div>
						<div class="formEle">
							<label>開始日期</label><input type="text" id="startDate"
								placeholder="yyyy-mm-dd" name="startDate">
						</div>
						<div class="formEle">
							<label>截止日期</label><input type="text" id="endDate"
								placeholder="yyyy-mm-dd" name="endDate">
						</div>
						<div class="formEle">
							<label>目標金額</label><input type="text" id="targetAmount"
								name="targetAmount">
						</div>
						<div class="formEle">
							<label>目前金額</label><input type="text" id="currentAmount"
								name="currentAmount">
						</div>
						<div class="formEle">
							<label>展延門檻</label><input type="text" id="threshold"
								placeholder="0-1" name="threshold">
						</div>
						<div class="formEle">
							<label>展延日期</label><input type="text" id="postponeDate"
								placeholder="yyyy-mm-dd" name="postponeDate">
						</div>
						<div class="formEle">
							<label>分類</label> <select id="category" name="category">
								<option value="表演" selected>表演</option>
								<option value="音樂會">音樂會</option>
								<option value="藝術">藝術</option>
								<option value="其他">其他</option>
							</select>
						</div>
						<div class="formEle">
                            <input class="btn btn-check" type="submit" value="確定" />
                            <button type="button" class="btn btn-cancel">取消</button>
                        </div>
                    </form>
                </div>
            </div>
            <table border="1" class="table-projs">

				<thead>
					<tr>
						<th nowrap>專案編號
						<th nowrap>專案名稱
						<th nowrap>專案內容
						<th nowrap>專案圖片
						<th nowrap>開始時間
						<th nowrap>截止時間
						<th nowrap>目標金額 
						<th nowrap>目前金額
						<th nowrap>展延門檻
						<th nowrap>展延到期時間
						<th nowrap>專案類別
						<th nowrap>選項
				</thead>
				<tbody>
					<%
					List<FundProjBean> projs = (ArrayList<FundProjBean>) request.getAttribute("projs");
								for (FundProjBean proj : projs) {
					%>
					<tr>
						<td><div><%=proj.getProjectID()%></div></td>
						<td><div><%=proj.getTitle()%></div></td>
						<td><div><%=proj.getDescription()%></div></td>
						<td><div><img src="<%= request.getContextPath() %>/cwdfunding/images/<%=proj.getImage()%>" alt="image">
							<input type="hidden" value="<%=proj.getImage()%>"></div></td>
						<td><%=proj.getStartDate()%></td>
						<td><%=proj.getEndDate()%></td>
						<td><%=proj.getTargetAmount()%></td>
						<td><%=proj.getCurrentAmount()%></td>
						<td><%=proj.getThreshold()%></td>
						<td><%=proj.getPostponeDate()%></td>
						<td><%=proj.getCategory()%></td>
						<td>
							<div class="div-actions">			
								<div class="div-actions-ele">
									<button class="btn btn-update">修改</button>
								</div>								
								<div class="div-actions-ele">
								<form action="<%= request.getContextPath() %>/FundProjs?action=delete" method="post">
									<input type="hidden" name="del-projectID" value="<%=proj.getProjectID()%>"> 								
									<button type="submit" class="btn btn-delete" onclick="return confirm('確定刪除嗎？')">刪除</button>
								</form>
								</div>
							</div> <%}%>
						
				</tbody>
            </table>
            <h3>共<%=projs.size()%>筆募資專案</h3>
        </div>
</div>
    </main>
</div>
</div>
<!-- </main> -->

			<div class="form-update hidden">
				<div class="btn-update-header">
					<button class="btn btn-update-close">➖ 關閉</button>
					<h3>修改</h3>
				</div>
					<form action="<%= request.getContextPath() %>/FundProjs?action=update" 
						method="post" enctype="multipart/form-data">
						<div class="formEle">
							<label>專案編號</label><input type="text" id="udt-projectID" name="udt-projectID" readonly>
						</div>
						<div class="formEle">
							<label>專案名稱</label><input type="text" id="udt-title" name="udt-title">
						</div>
						<div class="formEle">
							<label>專案敘述</label>
							<textarea maxlength="150"
								placeholder="上限150個字" name="udt-description"></textarea>
						</div>
						<div class="formEle formEle-img">
							<label>專案圖片</label><img id="udt-image-preview" src="" alt="專案圖片" style="max-width: 100px; height: auto;">
							<input type="file" id="udt-image" name="udt-image">
							<input type="hidden" id="old-image" name="old-image"> 								
						</div>
						<div class="formEle">
							<label>開始日期</label><input type="text" id="udt-startDate"
								placeholder="yyyy-mm-dd" name="udt-startDate">
						</div>
						<div class="formEle">
							<label>截止日期</label><input type="text" id="udt-endDate"
								placeholder="yyyy-mm-dd" name="udt-endDate">
						</div>
						<div class="formEle">
							<label>目標金額</label><input type="text" id="udt-targetAmount"
								name="udt-targetAmount">
						</div>
						<div class="formEle">
							<label>目前金額</label><input type="text" id="udt-currentAmount"
								name="udt-currentAmount">
						</div>
						<div class="formEle">
							<label>展延門檻</label><input type="text" id="udt-threshold"
								placeholder="0-1" name="udt-threshold">
						</div>
						<div class="formEle">
							<label>展延日期</label><input type="text" id="udt-postponeDate"
								placeholder="yyyy-mm-dd" name="udt-postponeDate">
						</div>
						<div class="formEle">
							<label>分類</label> <select id="udt-category" name="udt-category">
								<option value="表演" selected>表演</option>
								<option value="音樂會">音樂會</option>
								<option value="藝術">藝術</option>
								<option value="其他">其他</option>
							</select>
						</div>
						<div class="formEle">
							<input class="btn btn-check" type="submit" value="確定" />
						</div>
					</form>
					</div>
			
            <div class="overlay"></div>
		<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
		<script src="https://cdn.datatables.net/2.1.4/js/dataTables.js"></script>
		<script>
			$(function() {
				//code
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


				$('.btn-add').click(function() {
                $('.formContainer').toggleClass('hidden');
                $('.overlay').toggleClass('hidden');
                $('body').toggleClass('overflow-hidden');
            });

            $('.btn-cancel').click(function() {
                $('.formContainer').addClass('hidden');
                $('.overlay').addClass('hidden');
                $('body').removeClass('overflow-hidden');
            });

            // 點擊遮罩層關閉表單
            $('.overlay').click(function() {
                $('.formContainer').addClass('hidden');
                $('.overlay').addClass('hidden');
                $('body').removeClass('overflow-hidden');
            });

				$('.btn-check').click(function() {
					$('.formContainer').addClass("hidden");
					$('.btn-add').html("➕ 新增");
					console.log('bye');
				})
				$('.btn-update').click(function(){
					if ($('.form-update').hasClass("hidden")) {
						    let row = $(this).closest('tr');
					        
					        // 取出專案的各個數據
					        let projectID = row.find('td:eq(0) div').text().trim();
					        let title = row.find('td:eq(1) div').text().trim();
					        let description = row.find('td:eq(2) div').text().trim();
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
					        $('.form-update').removeClass("hidden");
					        $('.container').css({'filter':'blur(20px)'});
 					} else {
						$('.form-update').addClass("hidden");
					}
				})
				$('.btn-update-close').click(function(){
					if(!$('.form-update').hasClass("hidden")){
						$('.form-update').addClass("hidden");
						$('.container').css({'filter':''});
					}
				})

				 $(document).ready(function() {
					$('.table-projs').DataTable();
				}); 
				 $('.table-projs').DataTable(
						{
							//設定屬性(預設功能)區塊
							"searching" : true, // 預設為true 搜尋功能，若要開啟不用特別設定
							"paging" : false, // 預設為true 分頁功能，若要開啟不用特別設定
							"ordering": true, // 預設為true 排序功能，若要開啟不用特別設定
							"sPaginationType" : "full_numbers", // 分頁樣式 預設為"full_numbers"，若需其他樣式才需設定
							"lengthMenu" : [ [ 10, 25, 50, -1 ],
									[ 10, 25, 50, "All" ] ], //顯示筆數設定 預設為[10, 25, 50, 100]
							"pageLength" : '10'// 預設為'10'，若需更改初始每頁顯示筆數，才需設定
						   /*  "columnsDefs":[
								{targets:'_all',
								 className:'dt-center',
								}
							]   */
							
						})  
			})
		</script>
</body>
</html>
