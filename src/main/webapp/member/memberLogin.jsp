<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>會員登入頁面-TickitEasy</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.dowebok .google, .dowebok .fb {
	display: inline-block;
	width: 32px;
	height: 32px;
	background-size: cover;
}

/*  .dowebok .google {
                background-image: url(images/google.png);
            }

            .dowebok .fb {
                background-image: url(images/fb.svg);
            } */
.dowebok .google {
	background-image: url("${pageContext.request.contextPath}/images/g.png");
}

.dowebok .fb {
	background-image:
		url("${pageContext.request.contextPath}/images/fb.svg");
}
</style>
</head>

<body
	class="min-h-screen bg-gray-100 text-gray-900 flex justify-center dowebok">
	<div
		class="max-w-screen-xl m-0 sm:m-20 bg-white shadow sm:rounded-lg flex justify-center flex-1">
		<div class="lg:w-1/2 xl:w-5/12 p-6 sm:p-12">
			<div class="mt-12 flex flex-col items-center">
				<h1 class="text-2xl xl:text-3xl font-extrabold text-blue-500">TickitEasy
					會員登入</h1>
				<div class="w-full flex-1 mt-8">
					<div class="flex flex-col items-center">
						<button
							class="w-full max-w-xs font-bold shadow-sm rounded-lg py-3 bg-indigo-100 text-gray-800 flex items-center justify-center ease-in-out focus:outline-none hover:shadow focus:shadow-sm focus:shadow-outline">
							<div class="google"></div>
							<span class="ml-4"> 使用google登入</span>
						</button>
						<button
							class="w-full max-w-xs font-bold shadow-sm rounded-lg py-3 bg-indigo-100 text-gray-800 flex items-center justify-center ease-in-out focus:outline-none hover:shadow focus:shadow-sm focus:shadow-outline mt-5">
							<div class="fb"></div>
							<span class="ml-4">使用Facebook登入</span>
						</button>
					</div>
					<div class="my-12 border-b text-center">
						<div
							class="leading-none px-2 inline-block text-sm text-gray-600 tracking-wide font-medium bg-white transform translate-y-1/2">
							或使用電子信箱登入</div>
					</div>
					<form id="loginForm" class="mx-auto max-w-xs">
						<input
							class="w-full px-8 py-4 rounded-lg font-medium bg-gray-100 border border-gray-200 placeholder-gray-500 text-sm focus:outline-none focus:border-gray-400 focus:bg-white"
							type="email" name="email" placeholder="電子信箱" required /> <input
							class="w-full px-8 py-4 rounded-lg font-medium bg-gray-100 border border-gray-200 placeholder-gray-500 text-sm focus:outline-none focus:border-gray-400 focus:bg-white mt-5"
							type="password" name="password" placeholder="密碼" required />
						<button type="submit"
							class="mt-5 tracking-wide font-semibold bg-indigo-500 text-gray-100 w-full py-4 rounded-lg hover:bg-indigo-700 transition-all duration-300 ease-in-out flex items-center justify-center focus:shadow-outline focus:outline-none">
							<span class="ml-3">登入</span>
						</button>
						<p class="mt-6 text-xs text-gray-600 text-center">
							還沒有帳號? <a
								href="${pageContext.request.contextPath}/member/register.html"
								class="text-indigo-600 hover:underline">註冊</a>
						</p>
					</form>
				</div>
			</div>
		</div>
		<%-- <div class="flex-1 bg-blue-50 text-center hidden lg:flex">
			<div class="m-12 xl:m-16 w-full bg-contain bg-center bg-no-repeat"
				style="background-image: url('${pageContext.request.contextPath}/images/good.svg');"></div>
		</div> --%>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function() {
			$("#loginForm").submit(function(event) {
				event.preventDefault();
				$.ajax({
					url : "login",
					type : "POST",
					data : $(this).serialize(),
					success : function(response) {
						if (response === "success") {
							window.location.href = "memberDashboard.jsp";
						} else {
							alert(response);
						}
					}
				});
			});
		});
	</script>
</body>
</html>