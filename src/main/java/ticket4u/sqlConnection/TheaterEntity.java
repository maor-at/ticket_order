package ticket4u.sqlConnection;
import java.sql.*;

public class TheaterEntity extends DbConnect{
    String location;
    String theaterName;
    String showName;
    String seatMap ;

    public TheaterEntity(String location, String theaterName, String showName, String seatMap) {
        this.location = location;
        this.theaterName = theaterName;
        this.showName = showName;
        this.seatMap = seatMap;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return theaterName;
    }

    public void setName(String name) {
        this.theaterName = name;
    }

    public String getShowName() {
        return showName;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(String seatMap) {
        this.seatMap = seatMap;
    }
}
