package controller;

import dao.MovieDao;
import dao.OrderDao;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Movie;
import model.Order;
import model.Ticket;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@WebServlet(name = "MovieCheckoutController", value = "/movie-checkout")
public class MovieCheckoutController extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/views/movie-checkout.jsp").forward(req, resp);
//    }
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    Cookie[] cookies = req.getCookies();
    if(cookies!=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("User_order")){
                String value = cookie.getValue();
                byte[] decodedValue = Base64.getDecoder().decode(value);
                ByteArrayInputStream bais = new ByteArrayInputStream(decodedValue);
                ObjectInputStream ois = new ObjectInputStream(bais);

                try {
                    Order order = (Order) ois.readObject();
                    req.setAttribute("order",order);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    req.getRequestDispatcher("/views/movie-checkout.jsp").forward(req, resp);
}
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readTree(req.getInputStream());
//        JsonNode movieIdNode = jsonNode.get("MovieId");
//        JsonNode seatsNode = jsonNode.get("Seats");
//
//        if (movieIdNode == null || seatsNode == null || !seatsNode.isArray()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            JsonNode errorResult = objectMapper.createObjectNode().put("status", "error").put("message", "Invalid request data.");
//            resp.getWriter().write(objectMapper.writeValueAsString(errorResult));
//            return;
//        }
//
//        String movieId = movieIdNode.asText();
//        List<String> seatsList = new ArrayList<>();
//        for (JsonNode seatNode : seatsNode) {
//            seatsList.add(seatNode.asText());
//        }
//
//        try {
//            MovieDao movieDao = new MovieDao();
//            Movie movie = movieDao.findById(Integer.valueOf(movieId));
//            if (movie == null) {
//                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                JsonNode errorResult = objectMapper.createObjectNode().put("status", "error").put("message", "Movie not found.");
//                resp.getWriter().write(objectMapper.writeValueAsString(errorResult));
//                return;
//            }
//
//            Ticket ticket = new Ticket(movie, "20h:20-12-2024", "Beta", seatsList);
//            User user = (User) req.getAttribute("user");
//            Order order = new Order(user, ticket);
//            OrderDao orderDao = new OrderDao();
//            if (orderDao.exitsOrder(order)) {
//                orderDao.updateOrder(order);
//            } else {
//                orderDao.saveOrder(order);
//            }
//            resp.setContentType("application/json");
//            resp.setCharacterEncoding("UTF-8");
//            JsonNode result = objectMapper.createObjectNode().put("status", "success");
//            resp.getWriter().write(objectMapper.writeValueAsString(result));
//
//        } catch (SQLException | ClassNotFoundException e) {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            JsonNode errorResult = objectMapper.createObjectNode().put("status", "error").put("message", "Internal server error.");
//            resp.getWriter().write(objectMapper.writeValueAsString(errorResult));
//            e.printStackTrace();
//        }
//    }
protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // Đọc JSON từ request body
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = objectMapper.readTree(req.getInputStream());
    JsonNode seats = jsonNode.get("Seats");
    String movieID = jsonNode.get("MovieId").asText();
    List<String> seatsList = new ArrayList<>();
    if (seats.isArray()) {
        for (JsonNode nameNode : seats) {
            // Lấy giá trị String từ JsonNode
            String name = nameNode.asText();
            seatsList.add(name);
        }
    }
    try {
        MovieDao movieDao = new MovieDao();
        Movie movie = movieDao.findById(Integer.valueOf(movieID));
        //hard code for fast review
        Ticket ticket =  new Ticket(movie,"20h:20-12-2024","Beta",seatsList);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        //create response json
        ObjectMapper objectmapper = new ObjectMapper();

        JsonNode result = objectmapper.createObjectNode()
                .put("status","success");


        //create order
        User user = (User) req.getAttribute("user");
        Order order = new Order(user,ticket);
        OrderDao orderDao = new OrderDao();
        String serializeData ;
        if(orderDao.exitsOrder(order)){
            serializeData =  orderDao.updateOrder(order);
        }else {
            serializeData = orderDao.saveOrder(order);
        }
        Cookie cookie = new Cookie("User_order",serializeData);
        cookie.setMaxAge(60 * 60 * 24); // 1 ngày (tính bằng giây)
        resp.addCookie(cookie);



        resp.getWriter().write(objectMapper.writeValueAsString(result));
//            System.out.println(ticket);
    } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
//
    }
}
}

