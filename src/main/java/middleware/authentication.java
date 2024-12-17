package middleware;

import conf.Database;
import dao.UserDao;
import model.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebFilter("/*")
public class authentication implements Filter {
    private UserDao userDao;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userDao = new UserDao();
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

       String url =httpRequest.getRequestURI();
        if (url.endsWith("/login") || url.endsWith("/register") || url.startsWith("/demo3_war_exploded/asset")) {
            chain.doFilter(request, response);
            return;
        }
       //check token exits
        String token  = null ;
        Cookie[] cookies = httpRequest.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        try {
            if(token != null && isVaildToken(token)){
                User user = getUserFromToken(token);
                httpRequest.setAttribute("user", user);
                chain.doFilter(request, response);
            }else{
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean isVaildToken(String token) throws SQLException, ClassNotFoundException {
      String query = "SELECT * FROM tokens WHERE token=?";
        Connection connection = Database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,token);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
    private User getUserFromToken(String token) throws SQLException, ClassNotFoundException {
       String query = "SELECT * FROM users u JOIN tokens t ON u.user_id = t.user_id where t.token=?";
       Connection connection = Database.getConnection();
       PreparedStatement preparedStatement = connection.prepareStatement(query);
       preparedStatement.setString(1,token);
       ResultSet resultSet = preparedStatement.executeQuery();
       if(resultSet.next()){
           int userId = resultSet.getInt("user_id");
           String username = resultSet.getString("username");
           String email = resultSet.getString("email");
           String password = resultSet.getString("password");
           return new User(userId,username, email,password);
       }
       return userDao.getUserFromToken(token);
    }
}
