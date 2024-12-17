package controller;
import  java.io.*;
import dao.UserDao;
import model.User;
import util.Error_custom;
import util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@WebServlet(name = "LoginController" ,value="/login")
public class LoginController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/sign-in.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = MD5.md5(request.getParameter("password"));
        UserDao userDao = new UserDao();
        User user = userDao.login(email,password);
        if(user != null) {
            String token = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("token",token);
            response.addCookie(cookie);
            userDao.saveToken(user.getUser_id(),token);
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/index");

        }else{
            Error_custom errors = new Error_custom("Username or Password is incorrect");
            request.setAttribute("errors",errors);
            request.getRequestDispatcher("/views/sign-in.jsp").forward(request, response);
        }

    }
}
