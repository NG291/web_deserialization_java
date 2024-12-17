package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import conf.Database;

public class UserDao {

    public User login(String email,String password){
        String query = "select * from users where email=?  and password=?";
        try {
            Connection connection =  Database.getConnection();
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
               String User_username =   resultSet.getString("username");
               String Email =   resultSet.getString("email");
               String password_user =   resultSet.getString("password");
               int user_id =   resultSet.getInt("user_id");
                return new User(user_id,User_username, Email, password_user);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean saveToken(int user_id, String token){

        String query = "INSERT INTO tokens (user_id,token) VALUES (?,?)";
        try {
            Connection connection =  Database.getConnection();
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, token);
            int resultSet = preparedStatement.executeUpdate();
            if(resultSet != 0){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

//    public boolean isVaildToken(String token) throws SQLException, ClassNotFoundException {
//        String query = "select * from tokens where token=?";
//        Connection connection =  Database.getConnection();
//        assert connection != null;
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setString(1, token);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        if(resultSet.next()){
//            return true;
//        }
//        return false;
//    }

    public User getUserFromToken(String token) throws SQLException, ClassNotFoundException {
        String query = "select * from users join tokens on users.user_id = tokens.user_id where token=?";
        Connection connection =  Database.getConnection();
        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, token);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            String User_username =   resultSet.getString("username");
            String Email =   resultSet.getString("email");
            String password_user =   resultSet.getString("password");
            int user_id =   resultSet.getInt("user_id");
            return new User(user_id,User_username, Email, password_user);
        }
        return null;
    }

    public boolean addUser(User user) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO users (username,email,password) VALUES (?,?,?)";
        Connection connection =  Database.getConnection();
        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,user.getUsername());
        preparedStatement.setString(2,user.getEmail());
        preparedStatement.setString(3,user.getPassword());
        int resultSet = preparedStatement.executeUpdate();
        if(resultSet != 0){
            return true;
        }

        return  false;
    }
    public boolean EmailExist(String email) throws SQLException, ClassNotFoundException {
        String query = "select * from users where email=?";
        Connection connection =  Database.getConnection();
        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return true;
        }
        return false;

    }

}
