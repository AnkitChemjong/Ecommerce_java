//package controller.filters;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
////@WebFilter("/*")
//public class AuthenticationFilter implements Filter {
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
//		
//		String uri = req.getRequestURI();	
//		
//		boolean isRegisterServlet = uri.endsWith("RegisterServlet");
//		boolean isLogin = uri.endsWith("login.jsp");
//		boolean isLoginServlet = uri.endsWith("LoginServlet");
//		boolean isLogoutServlet = uri.endsWith("LogoutServlet");
//		
//		
//		HttpSession session = req.getSession(false);
//		boolean isLoggedIn = session != null && session.getAttribute("username") != null;
//		
//		if (uri.endsWith(".css")) {
//			chain.doFilter(request, response);
//			return;
//		}
//		
//		
//		if (uri.endsWith("register.jsp")) {
//			chain.doFilter(req, res);
//			return;
//		}
//		
//		if(uri.endsWith("pages/home.jsp")) {
//			chain.doFilter(request, response);
//			return;
//		}
//		
//		if (!isLoggedIn && isRegisterServlet) {
//			chain.doFilter(req, res);
//			return;
//		}
//		
//		if (!isLoggedIn && !(isLogin || isLoginServlet)) {
//			res.sendRedirect(req.getContextPath() + "/pages/login.jsp");
//		}
//		else {
//			chain.doFilter(request, response);
//		}
//	}
//
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//		// TODO Auto-generated method stub
//		
//	}
//
//}


// Source code is decompiled from a .class file using FernFlower decompiler.
package controller.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"/*"})
public class AuthenticationFilter implements Filter {
   public AuthenticationFilter() {
   }

   public void destroy() {
   }

   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest req = (HttpServletRequest)request;
      HttpServletResponse res = (HttpServletResponse)response;
      String uri = req.getRequestURI();
      if (uri.endsWith(".css")) {
         chain.doFilter(request, response);
      } else if (!uri.endsWith("login.jsp") && !uri.endsWith("landing.jsp") && !uri.endsWith("register.jsp")  && !uri.endsWith("feedback.jsp")) {
         if (!uri.endsWith("RegisterServlet") && !uri.endsWith("CartServlet") && !uri.endsWith("OrderServlet") && !uri.endsWith("Search.jsp")&&   !uri.endsWith("ProductServlet")&& !uri.endsWith("product.jsp") && !uri.endsWith("DisplayServlet") && !uri.contains("EachProductServlet") && !uri.endsWith("landing.jsp") && !uri.endsWith("/") && !uri.endsWith("CookieServlet")) {
            if (!uri.endsWith(".png") && !uri.endsWith(".jpeg") && !uri.endsWith(".mp4") && !uri.endsWith(".jpg")) {
               boolean isLogin = uri.endsWith("login.jsp");
               boolean isHome = uri.endsWith("/");
               boolean isLoginServlet = uri.endsWith("LoginServlet");
               boolean isLogoutServlet = uri.endsWith("LogoutServlet");
               HttpSession session = req.getSession(false);
               boolean isLoggedIn = session != null && session.getAttribute("customer_mail") != null;
               boolean isAdmin = session != null && session.getAttribute("admin_email") != null;
               if (isAdmin) {
                  chain.doFilter(request, response);
               } else if ((isLoggedIn || !isLogin) && !isLoginServlet) {
                  if (!isLoggedIn) {
                 
                     res.sendRedirect(req.getContextPath() + "/pages/login.jsp");
                  } else if (isLoggedIn && isLogin && !isLogoutServlet) {
                     res.sendRedirect(req.getContextPath() + "/pages/product.jsp");
                  } else {
                     chain.doFilter(request, response);
                  }

               } else {
                  chain.doFilter(req, res);
               }
            } else {
               chain.doFilter(request, response);
            }
         } else {
            chain.doFilter(request, response);
         }
      } else {
         chain.doFilter(request, response);
      }
   }

   public void init(FilterConfig arg0) throws ServletException {
   }
}

