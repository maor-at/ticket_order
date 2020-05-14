package ticket4u.controllers.ticketStore;

import ticket4u.sqlConnection.ShowsEntity;
import java.sql.Date;
public class ShowsDto {
    String showName;
    int showType;
    Date date;
    String theaterName;
    String description;
    String showHour;

    public ShowsDto(String showName, int showType, Date date, String theaterName,  String description, String showHour) {
        this.showName = showName;
        this.showType = showType;
        this.date = date;
        this.theaterName = theaterName;
        this.description = description;
        this.showHour = showHour;
    }

    public ShowsDto(ShowsEntity show){
        showName = show.getShowName();
        showType = show.getShowType();
        date = show.getDate();
        theaterName = show.getTheaterName();
        description = show.getDescription();
        showHour = show.getShowHour();
    }

    public ShowsEntity toEntity()
    {

        return new ShowsEntity(showName,showType,date,theaterName,description, showHour);
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
