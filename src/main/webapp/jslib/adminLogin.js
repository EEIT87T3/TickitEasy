/**
 * 
 */
$(document).ready(function() {
          $("#loginForm").submit(function(event) {
              event.preventDefault();
              $.ajax({
                  url: "adminLogin",
                  type: "POST",
                  data: $(this).serialize(),
                  success: function(response) {
                      if(response === "success") {
                          window.location.href = "adminDashboard.jsp";
                      } else {
                          alert("登入失敗：" + response);
                      }
                  }
              });
          });
      });