//package com.HRMS.interceptor;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//@Component
//public class SessionValidationInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        HttpSession session = request.getSession(false);
//
//        // Get the context path from the ServerHttpRequest
//        String contextPath = getContextPath(request);
//
//        System.out.println("Requested URI: " + request.getRequestURI());
//        System.out.println("Session ID: " + (session != null ? session.getId() : "null"));
//
//
//        // Allow requests to /login, /logout, and /otp without session validation
//        if (contextPath.equals("/Login") || contextPath.equals("/Logout") || contextPath.equals("/Otp")) {
//            return true;
//        }
//        
//
//        // Check if the session is still valid and contains the required attribute
//        if (session == null || session.getAttribute("otpVerifiedUser") == null) {
//            System.out.print("PATH IS SSSSSSS:" + contextPath);
//            // Session is expired or not present, redirect to the login page
//            response.sendRedirect("/login");
//            return false;
//        }
//
//        // Session is valid, allow the request to proceed
//        return true;
//    }
//
//    private String getContextPath(HttpServletRequest request) {
//        String contextPath = request.getContextPath();
//        if (contextPath.isEmpty()) {
//            // If the contextPath is empty, try to get it from the request URI
//            String requestUri = request.getRequestURI();
//            int endIndex = requestUri.indexOf('/', 1);
//            if (endIndex > 0) {
//                contextPath = requestUri.substring(0, endIndex);
//            }
//        }
//        return contextPath;
//    }
//
//}
