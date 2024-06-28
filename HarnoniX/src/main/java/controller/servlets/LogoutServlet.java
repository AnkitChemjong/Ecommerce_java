package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet({"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public LogoutServlet() {
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = request.getSession(false);
      if (session != null) {
         session.invalidate();
         System.out.println("Session successfully invalidated.");
      } else {
         System.out.println("Session Not Found");
      }

      Cookie[] cookies = request.getCookies();
      if (cookies != null) {
         System.out.println("Cookies.");
         Cookie[] var8 = cookies;
         int var7 = cookies.length;

         for(int var6 = 0; var6 < var7; ++var6) {
            Cookie cookie = var8[var6];
            cookie.setMaxAge(0);
            response.addCookie(cookie);
         }

         System.out.println("Cookies Deleted.");
      } else {
         System.out.println("No cookies.");
      }

      try {
         response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
         System.out.println("Redirected to login page.");
      } catch (IOException var9) {
         var9.printStackTrace();
         System.out.println("Failed to redirect to login page.");
      }

   }
}
