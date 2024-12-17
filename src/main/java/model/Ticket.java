package model;

import java.io.Serializable;
import java.util.List;

public class Ticket implements Serializable {
    private String id;
    private Movie movie; // Quan hệ với Movie
    private String showTime;
    private String cinema;
    private List<String> seatNumber;
    private double price;

    public Ticket( Movie movie, String showTime, String cinema, List<String> seatNumber) {
        this.movie = movie;
        this.showTime = showTime;
        this.cinema = cinema;
        this.seatNumber = seatNumber;

        this.price = movie.getBase_Price() * seatNumber.size();
    }

    public List<String> getSeatNumber() {
        return seatNumber;
    }

    public Movie getMovie() {
        return movie;
    }
    public  String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void displayTicketDetails() {
        System.out.println("Ticket ID: " + id);
        System.out.println("Movie: " + movie.getTitle());
        System.out.println("Showtime: " + showTime);
        System.out.println("Cinema: " + cinema);
        System.out.println("Seat: " + seatNumber);
        System.out.println("Price: " + price + " VND");
    }
}
