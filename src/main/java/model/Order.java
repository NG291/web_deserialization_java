package model;

import java.io.Serializable;

public class Order implements Serializable {
    private static long counter = 0;
    private final int orderId;
    private User customer;
    private Ticket ticket;
    private double finalPrice;
    public Order(User customer, Ticket ticket) {
        this.orderId = (int) generateId();
        this.customer = customer;
        this.ticket = ticket;
        this.finalPrice= ticket.getPrice() + 15;
    }
    private static synchronized long generateId() {
        return ++counter;
    }
    public int getOrderId() {
        return orderId;
    }
    public User getCustomer() {
        return customer;
    }
    public Ticket getTicket() {
        return ticket;
    }
}
