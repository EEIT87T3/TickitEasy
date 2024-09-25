package admin.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(1)
public class AdminFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String requestURI = request.getRequestURI();
        
        if (!requestURI.startsWith(request.getContextPath() + "/admin/")) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("admin") != null);

        String loginURI = request.getContextPath() + "/admin/login";
        boolean isLoginRequest = requestURI.equals(loginURI);
        boolean isLoginPage = requestURI.endsWith("login");

        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
            // 管理員已登入，重定向到管理員首頁
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        } else if (isLoggedIn || isLoginRequest || isLoginPage) {
            // 管理員已登入或正在嘗試登入，繼續請求
            filterChain.doFilter(request, response);
        } else {
            // 管理員未登入，重定向到登入頁面
            response.sendRedirect(request.getContextPath() + "/admin/login");
        }
    }
}