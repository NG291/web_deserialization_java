package controller;
import dao.TheaterDao;
import model.CounterDemo;
import model.Theater;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Base64;

@WebServlet(name = "MovieController",value = "/movie-list")
public class MovieListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TheaterDao theaterDao = null;
        theaterDao = new TheaterDao();

        CounterDemo counter = new CounterDemo(theaterDao,"123");

        Theater threater = new Theater(counter);

        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(threater);
            byte[] serializedObject = byteArrayOutputStream.toByteArray();
            String encoded = Base64.getEncoder().encodeToString(serializedObject);
            System.out.println(encoded);
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<p>This is a response from the Servlet.</p>");
            out.println(encoded);
        }catch (Exception e){
            e.printStackTrace();
        }

//        req.getRequestDispatcher("/views/movie-list.jsp").forward(req, resp);
    }
}
