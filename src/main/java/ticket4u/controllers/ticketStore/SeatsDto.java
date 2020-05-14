package ticket4u.controllers.ticketStore;

import ticket4u.sqlConnection.SeatsEntity;

import java.sql.Date;

public class SeatsDto {

    int taken;
    String theaterName;
    Date date;
    int row;
    int column;

    public SeatsDto(int seatNumber, int taken, String theaterName, Date date, int row, int column) {
        this.taken = taken;
        this.theaterName = theaterName;
        this.date = date;
        this.row = row;
        this.column = column;
    }
    public SeatsDto(SeatsEntity seat){
        taken = seat.isTaken();
        theaterName =seat.getTheaterName();
        date = seat.getDate();
        row = seat.getRow();
        column = seat.getColumn();

    }

   public SeatsEntity toEntity(){
        return new SeatsEntity(taken,theaterName,date,row,column);
    }

    public int isTaken() {
        return taken;
    }

    public void setTaken(int taken) {
        this.taken = taken;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
