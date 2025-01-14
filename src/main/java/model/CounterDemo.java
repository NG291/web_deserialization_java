package model;

import dao.TheaterDao;

import java.io.IOException;
import java.sql.SQLException;

public class CounterDemo extends Counter {
    public TheaterDao theaterDao;
    public String des;
    public CounterDemo(){}

    public CounterDemo(TheaterDao theaterDao, String des){
        this.theaterDao = theaterDao;
        this.des = des;
    }

    @Override
    public void getLocation() throws IOException, SQLException {
        this.theaterDao.getResult(this.des);
    }
}
