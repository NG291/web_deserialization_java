package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.SQLException;

public class Theater implements Serializable {
    private static final long serialVersionUID = 1L;
    Counter counter;
    public Theater(Counter counter){
        this.counter = counter;
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException, SQLException{
        try{
            in.defaultReadObject();
            this.counter.getLocation();
        }catch(Exception e){
            throw new SQLException(e);
        }
    }
}
