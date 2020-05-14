package ticket4u.controllers.ticketStore;

import java.sql.Date;

public class CreateShowRequest
{
    String showName;
    int showType;
    Date date;
    String theaterName;
    String location;
    String description;
    String seatMap ;
    String showHour;

    public CreateShowRequest(String showName, int showType, Date date, String theaterName, String location, String description, String seatMap,String showHour) {
        this.showName = showName;
        this.showType = showType;
        this.date = date;
        this.theaterName = theaterName;
        this.location = location;
        this.description = description;
        this.seatMap = seatMap;
        this.showHour = showHour;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeatMap() {
        return seatMap;
    }

    public void setSeatMap(String seatMap) {
        this.seatMap = seatMap;
    }

    public String getShowHour() {
        return showHour;
    }

    public void setShowHour(String showHour) {
        this.showHour = showHour;
    }
}
