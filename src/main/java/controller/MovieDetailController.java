package controller;

import dao.MovieDao;
import model.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MovieDetailController",value = "/movie-details")
public class MovieDetailController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer movieId = Integer.valueOf(req.getParameter("id"));
        try {
            MovieDao moviedao = new MovieDao();
            Movie movie = moviedao.findById(movieId);
            req.setAttribute("movie", movie);
            req.getRequestDispatcher("/views/movie-details.jsp").forward(req, resp);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
