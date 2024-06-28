//package controller.servlets;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import controller.DatabaseController;
//import model.CustomerModel;
//import utils.StringUtils;
//
//@WebServlet(asyncSupported = true, urlPatterns = { StringUtils.LOGIN_SERVLET })
//public class LoginServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private final DatabaseController dbController = new DatabaseController();
//
//    public LoginServlet() {
//        super();
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        System.out.println(email);
//
//        int login = dbController.CustomerLogin(email, password);
//        System.out.println(login);
//        
//        if (login == 7) {
//            HttpSession adminSession = request.getSession();
//            adminSession.setAttribute("admin_email", email);
//            adminSession.setMaxInactiveInterval(30 * 3);
//
//            Cookie adminCookie = new Cookie("admin_email", email);
//            adminCookie.setMaxAge(30 * 60);
//            response.addCookie(adminCookie);
//
//            CustomerModel admin = null;
//
//            try {
//                admin = dbController.fetchCustomerDetails(email);
//            } catch (ClassNotFoundException ex) {
//                ex.printStackTrace();
//            }
//
//            System.out.println(admin);
//
//            if (admin != null) {
//                // Set success message and redirect to admin dashboard
//                request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_LOGIN_MESSAGE);
//                request.getRequestDispatcher(StringUtils.ADMIN_DASHBOARD).forward(request, response);
//            } else {
//                // If admin details are not found, handle the error appropriately
//                request.setAttribute(StringUtils.ERROR_MESSAGE, "Admin details not found");
//                request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
//            }
//        }
//        
//        
//        else if (login == 1) {
//            HttpSession customerSession = request.getSession();
//            customerSession.setAttribute("customer_mail", email);
//            customerSession.setMaxInactiveInterval(30 * 3);
//
//            Cookie customerCookie = new Cookie("customer_mail", email);
//            customerCookie.setMaxAge(30 * 60);
//            response.addCookie(customerCookie);
//
//            CustomerModel customer = null;
//
//            try {
//                customer = dbController.fetchCustomerDetails(email);
//            } catch (ClassNotFoundException ex) {
//                ex.printStackTrace();
//            }
//            
//
//            if (customer != null) {
//                // Set success message and redirect to welcome page
//            	HttpSession session = request.getSession();
//            	session.setAttribute("PersonalDetails",customer);
//                request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_LOGIN_MESSAGE);
//                request.getRequestDispatcher(StringUtils.DISPLAY_SERVLET).forward(request, response);
//            } else {
//                // If user details are not found, handle the error appropriately
//                request.setAttribute(StringUtils.ERROR_MESSAGE, "User donot exist");
//                request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
//            }
//
//        } else if (login == 0) {
//            request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ERROR_LOGIN_MESSAGE);
//            request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
//        } else {
//            request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.SERVER_ERROR_MESSAGE);
//            request.getRequestDispatcher(StringUtils.LOGIN_PAGE).forward(request, response);
//        }
//    }
//}


package controller.servlets;

import controller.DatabaseController;
import model.CustomerModel;
import utils.StringUtils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
   asyncSupported = true,
   urlPatterns = {"/LoginServlet"}
)
public class LoginServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private final DatabaseController DatabaseController = new DatabaseController();

   public LoginServlet() {
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      System.out.println(email);
      int responsed = this.DatabaseController.CustomerLogin(email, password);
      System.out.println(responsed);
      HttpSession customerSession;
      Cookie customerCookie;
      CustomerModel customer;
      if (responsed == 7) {
         customerSession = request.getSession();
         customerSession.setAttribute("admin_email", email);
         customerSession.setMaxInactiveInterval(90);
         customerCookie = new Cookie("admin_email", email);
         customerCookie.setMaxAge(1800);
         response.addCookie(customerCookie);
         customer = null;

         try {
            customer = this.DatabaseController.fetchCustomerDetails(email);
         } catch (ClassNotFoundException var11) {
            var11.printStackTrace();
         }

         System.out.println(customer);
         if (customer != null) {
            request.setAttribute("request Success", "Admin ligin succesfull");
            request.getRequestDispatcher("/pages/Dashboard.jsp").forward(request, response);
         } else {
            request.setAttribute("User Registration Failed", "Admin details not found");
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
         }
      } else if (responsed == 1) {
         customerSession = request.getSession();
         customerSession.setAttribute("customer_mail", email);
         customerSession.setMaxInactiveInterval(90);
         customerCookie = new Cookie("customer_mail", email);
         customerCookie.setMaxAge(1800);
         response.addCookie(customerCookie);
         customer = null;

         try {
            customer = this.DatabaseController.fetchCustomerDetails(email);
         } catch (ClassNotFoundException var10) {
            var10.printStackTrace();
         }

         System.out.println(customer);
         if (customer != null) {
            HttpSession session = request.getSession();
            session.setAttribute("profileDetails", customer);
            request.setAttribute(StringUtils.SUCCESS_MESSAGE, StringUtils.SUCCESS_LOGIN_MESSAGE);
            request.getRequestDispatcher("/DisplayServlet").forward(request, response);
         } else {
            request.setAttribute("StringUtils.ERROR_MESSAGE", StringUtils.ERROR_USER_MESSAGE);
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
         }
      } else if (responsed == 0) {
         request.setAttribute(StringUtils.ERROR_MESSAGE, StringUtils.ERROR_LOGIN_MESSAGE);
         request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
      } else {
         request.setAttribute("User Registration Failed", "An unexpected server error occurred.");
         request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
      }

   }
}
