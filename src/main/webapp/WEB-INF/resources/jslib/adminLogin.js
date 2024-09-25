/**
 * 管理員登入頁面的 JavaScript 代碼
 * 這段代碼處理登入表單的提交,使用 AJAX 發送登入請求,
 * 並根據響應結果進行相應的處理。
 */
$(document).ready(function() {
    $("#loginForm").submit(function(event) {
        // 阻止表單的默認提交行為
        event.preventDefault();
        
        // 使用 AJAX 發送登入請求
        $.ajax({
            // 更新 URL 以匹配新的控制器方法
            url: "${pageContext.request.contextPath}/admin/adminLogin",
            type: "POST",
            data: $(this).serialize(), // 序列化表單數據
            success: function(response) {
                if(response === "success") {
                    // 登入成功,重定向到管理員儀表板
                    // 更新 URL 以匹配新的控制器方法
                    window.location.href = "${pageContext.request.contextPath}/admin/dashboard";
                } else {
                    // 登入失敗,顯示錯誤消息
                    alert("登入失敗：" + response);
                }
            },
            error: function(xhr, status, error) {
                // 添加錯誤處理
                console.error("AJAX 請求失敗:", status, error);
                alert("發生錯誤,請稍後再試");
            }
        });
    });
});