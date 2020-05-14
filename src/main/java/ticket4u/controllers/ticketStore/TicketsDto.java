
package ticket4u.controllers.ticketStore;

import java.sql.Date;
import ticket4u.sqlConnection.TicketEntity;
public class TicketsDto {

    String _userName;
    String showName;
    int row;
    int column;
    Date date;
    String theaterName;
    int price;
    String showHour;

    public TicketsDto(String userName, String showName, int row, int column, Date date, String theaterName, int price, String showHour) {
        this._userName = userName;
        this.showName = showName;
        this.row = row;
        this.column = column;
        this.date = date;
        this.theaterName = theaterName;
        this.price = price;
        this.showHour = showHour;
    }
    public TicketsDto(TicketEntity ticket){
        _userName =ticket.getUserName();
        showName =ticket.getShowName();
        row =ticket.getRow();
        column = ticket.getColumn();
        date =ticket.getDate();
        theaterName =  ticket.getTheaterName();
        price = ticket.getPrice();
        showHour = ticket.getShowHour();
    }

   public TicketEntity toEntity(){
        return new TicketEntity(_userName,showName,row,column,date,theaterName, price, showHour);
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
