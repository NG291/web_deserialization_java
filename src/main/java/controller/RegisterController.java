package controller;
import model.User;
import util.ValidateUser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import util.*;
import  dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "RegisterController",value = "/register")
public class RegisterController extends HttpServlet {

    //hanle GET request
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/sign-up.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("comfirm_password");
        UserDao userDao = new UserDao();
        String username = "User"+ UUID.randomUUID().toString().substring(0,8);
        List <Error_custom> errors = new ArrayList<>();
        if(!ValidateUser.validateUser(username,email,password)){
            //validate email
            if(!ValidateUser.validateEmail(email)){
                errors.add(new Error_custom("email","Email không hợp lệ.","ERR01"));
            }else if(!ValidateUser.validatePassword(password)){
                errors.add(new Error_custom("password","Password phải chứa ít nhất một chữ hoa, một chữ thường, một số và một ký tự đặc biệt.","ERR02"));
            } else if (!Objects.equals(password, confirmPassword)) {
                errors.add(new Error_custom("comfirm_password","Mật khẩu xác nhận không giống mật khẩu ban đầu", "ERR03"));
            }

        }
        try {
            if (userDao.EmailExist(email)) {
                errors.add(new Error_custom("email","Email đã tồn tại trong hệ thống", "ERR03"));

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(!errors.isEmpty()){
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/views/sign-up.jsp").forward(request,response);
        }
        // primary logic
        password = MD5.md5(password);
        User user = new User(username,email,password);
        try {
            userDao.addUser(user);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        //redirect login
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/login");
    }

}
