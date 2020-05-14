package ticket4u.sqlConnection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowsEntity extends DbConnect{

    String showName;
    int showType;
    Date date;
    String theaterName;
    String description;
    String showHour;

    public ShowsEntity(String showName, int showType, Date date, String theaterName, String description, String showHour) {
        this.showName = showName;
        this.showType = showType;
        this.date = date;
        this.theaterName = theaterName;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShowHour() {
        return showHour;
    }

    public void setShowHour(String showHour) {
        this.showHour = showHour;
    }
}
