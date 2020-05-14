package ticket4u.controllers.ticketStore;

import java.sql.Date;
import ticket4u.sqlConnection.TheaterEntity;

public class TheaterDto {
    String location;
    String theaterName;
    String showName;
    String seatMap ;

    public TheaterDto(String location, String theaterName, String showName, String seatMap ) {
        this.location = location;
        this.theaterName = theaterName;
        this.showName = showName;
        this.seatMap = seatMap;
    }
    public TheaterDto(TheaterEntity theater){
        location =theater.getLocation();
        theaterName =  theater.getTheaterName();
        showName =theater.getShowName();
        seatMap = theater.getSeatMap();
    }

    public TheaterEntity toEntity(){
        return new TheaterEntity(location,theaterName,showName,seatMap);
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
    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getTheaterName() {
        return theaterName;
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
