/**
 * 
 */
var contextPath = '${pageContext.request.contextPath}';

        $(document).ready(function() {
            $('#memberTable').DataTable({
                "language": {
                    "url": "//cdn.datatables.net/plug-ins/1.10.24/i18n/Chinese-traditional.json"
                },
                "pageLength": 10,
                "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "全部"]]
            });

            // 側邊欄切換功能
            $('#sidebarToggle').click(function() {
                $('.sidebar').toggleClass('open');
            });

            // 在小屏幕上初始隱藏側邊欄
            function initSidebar() {
                if (window.innerWidth < 1024) {
                    $('.sidebar').removeClass('open');
                } else {
                    $('.sidebar').addClass('open');
                }
            }

            // 頁面加載時初始化側邊欄
            initSidebar();
            // 窗口大小改變時重新初始化側邊欄
            $(window).resize(initSidebar);

            // 點擊內容區域時關閉側邊欄
            $('.content-with-sidebar').click(function() {
                if ($('.sidebar').hasClass('open') && window.innerWidth < 1024) {
                    $('.sidebar').removeClass('open');
                }
            });
        });

        function editMember(memberId) {
            window.location.href = contextPath + '/admin/memberManagement?action=edit&memberId=' + memberId;
        }

        function updateMemberStatus(memberId) {
            var newStatus = document.getElementById('status_' + memberId).value;
            $.ajax({
                url: contextPath + '/admin/memberManagement',
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
                    url: contextPath + '/admin/memberManagement',
                    type: 'POST',
                    data: {
                        action: 'removeProfilePic',
                        memberId: memberId
                    },
                    success: function(response) {
                        alert('會員頭貼已移除');
                        location.reload();
                    },
                    error: function() {
                        alert('移除失敗，請稍後再試');
                    }
                });
            }
        }