package admin.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AdminFilter
 */
/*  確保只有已登錄的管理員可以訪問管理員頁面
如果未登入嘗試進入管理員頁面，將其重定向到登入頁面
防止已登入的管理員再次進入登入頁面，而是直接將他們重定向到後台管理主頁。*/

//管理員訪問限制攔截所有以 "/admin/" 開頭的請求
@WebFilter("/admin/*")
public class AdminFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	// 建構子
	public AdminFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	// 當過濾器被銷毀時調用
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	// 過濾
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;	//取得請求
		HttpServletResponse httpResponse = (HttpServletResponse) response;	//取得回應
		HttpSession session = httpRequest.getSession(false);	//取得session

		boolean isLoggedIn = (session != null && session.getAttribute("admin") != null);//定義登錄相關的 URI 和判斷條件：登入為 true，否則為 false
		//處理不同的訪問
		String loginURI = httpRequest.getContextPath() + "/admin/adminLogin";	//登入頁

		boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI); 	//登入請求

		boolean isLoginPage = httpRequest.getRequestURI().endsWith("adminLogin.jsp"); //登入頁
		// 管理員已登入或正在嘗試登入
		if (isLoggedIn && (isLoginRequest || isLoginPage)) {
			// 管理員已登入，重定向到管理員首頁
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/admin/adminDashboard.jsp");
		} else if (isLoggedIn || isLoginRequest || isLoginPage) {
			// 管理員已登入或正在嘗試登入，繼續請求
			chain.doFilter(request, response);
		} else {
			// 管理員未登入，重定向到登入頁面
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/admin/adminLogin.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	// 初始化
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
