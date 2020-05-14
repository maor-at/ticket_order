package ticket4u.sqlConnection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketEntity extends DbConnect{

    String _userName;
    String showName;
    int row;
    int column;
    Date date;
    String theaterName;
    int price;
    String showHour;

    public TicketEntity(String userName, String showName, int row, int column, Date date, String theaterName, int price, String showHour) {
        this._userName = userName;
        this.showName = showName;
        this.row = row;
        this.column = column;
        this.date = date;
        this.theaterName = theaterName;
        this.price = price;
        this.showHour = showHour;
    }

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String userName) {
        this._userName = userName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getShowHour() {
        return showHour;
    }

    public void setShowHour(String showHour) {
        this.showHour = showHour;
    }
}
