package dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conf.Database;
import model.Order;
import java.util.Base64;
public class OrderDao {
    private  Connection connection;

    public OrderDao() throws SQLException, ClassNotFoundException {
        this.connection = Database.getConnection();
        assert connection != null;
    }
    public String saveOrder(Order order) throws SQLException, ClassNotFoundException, IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(order);
        byte[] serializedObject = byteArrayOutputStream.toByteArray();
        String encoded = Base64.getEncoder().encodeToString(serializedObject);
        System.out.println(encoded);

        String query = "INSERT INTO `order` (orderId,customerId,data) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, order.getOrderId());
        preparedStatement.setInt(2,order.getCustomer().getUser_id());
        preparedStatement.setString(3, encoded);
        int resultSet = preparedStatement.executeUpdate();
        if(resultSet > 0) {
            return encoded;
        }
        return "Falid";
    }
    public String updateOrder(Order order) throws SQLException, ClassNotFoundException, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(order);
        byte[] serializedObject = byteArrayOutputStream.toByteArray();
        String encoded = Base64.getEncoder().encodeToString(serializedObject);
        String query = "UPDATE `order` set data =? where customerId =?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, encoded);
        preparedStatement.setInt(2,order.getCustomer().getUser_id());
        int resultSet = preparedStatement.executeUpdate();
        if(resultSet > 0) {
            return encoded;
        }
        return "Falid";
    }
    public boolean exitsOrder(Order order) throws SQLException, ClassNotFoundException, IOException {
        String query = "SELECT * FROM `order` WHERE customerId =?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, order.getCustomer().getUser_id());
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            return true;
        }
        return false;
    }
}
