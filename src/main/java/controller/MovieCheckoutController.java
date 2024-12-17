package controller;

import dao.MovieDao;
import dao.OrderDao;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Movie;
import model.Order;
import model.Ticket;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MovieCheckoutController", value = "/movie-checkout")
public class MovieCheckoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/movie-checkout.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(req.getInputStream());

        JsonNode movieIdNode = jsonNode.get("movieId");
        JsonNode seatsNode = jsonNode.get("Seats");

        if (movieIdNode == null || seatsNode == null || !seatsNode.isArray()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonNode errorResult = objectMapper.createObjectNode().put("status", "error").put("message", "Invalid request data.");
            resp.getWriter().write(objectMapper.writeValueAsString(errorResult));
            return;
        }

        String movieID = movieIdNode.asText();
        List<String> seatsList = new ArrayList<>();
        for (JsonNode seatNode : seatsNode) {
            seatsList.add(seatNode.asText());
        }

        try {
            MovieDao movieDao = new MovieDao();
            Movie movie = movieDao.findById(Integer.valueOf(movieID));
            if (movie == null) {
                // Trả về lỗi nếu không tìm thấy phim
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                JsonNode errorResult = objectMapper.createObjectNode().put("status", "error").put("message", "Movie not found.");
                resp.getWriter().write(objectMapper.writeValueAsString(errorResult));
                return;
            }

            Ticket ticket = new Ticket(movie, "20h:20-12-2024", "Beta", seatsList);
            User user = (User) req.getAttribute("user");
            Order order = new Order(user, ticket);

            OrderDao orderDao = new OrderDao();
            if (orderDao.exitsOrder(order)) {
                orderDao.updateOrder(order);
            } else {
                orderDao.saveOrder(order);
            }

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            JsonNode result = objectMapper.createObjectNode().put("status", "success");
            resp.getWriter().write(objectMapper.writeValueAsString(result));

        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonNode errorResult = objectMapper.createObjectNode().put("status", "error").put("message", "Internal server error.");
            resp.getWriter().write(objectMapper.writeValueAsString(errorResult));
            e.printStackTrace();
        }
    }
}

