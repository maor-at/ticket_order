package ticket4u.sqlConnection.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ticket4u.controllers.ticketStore.TheaterDto;
import ticket4u.sqlConnection.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TheaterRepository implements  ITheaterRepository {
    private DbConnect _dbConnect;

    @Autowired
    public TheaterRepository(DbConnect dbConnect) {
        _dbConnect = dbConnect;
    }

    public void setTheaterTable(TheaterEntity theaterEntity) throws SQLException,Exception
    {
        String query ="INSERT INTO theater (location, theaterName, showName,seatMap) "
                +"VALUES ( @location@, @theaterName@, @showName@,@seatMap@)";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setString("@location@" ,theaterEntity.getLocation());
        st.setString("@theaterName@" ,theaterEntity.getTheaterName());
        st.setString("@showName@" ,theaterEntity.getShowName());
        st.setString("@seatMap@" ,theaterEntity.getSeatMap());

        int myRs = st.executeUpdate();
        _dbConnect.closeConnection();
    }


    public void addNewSeatFromTheater(String theaterName, Date date , String theaterSeatMap) throws SQLException,Exception
    {
        //take the amount of seats in theater and if there is non it adds seats based on the seatMap
        String query = "select count(*)  AS total from seats where theaterName = @theaterName@ AND date = @date@ ";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setDate("@date@" ,date);
        st.setString("@theaterName@" ,theaterName);
        ResultSet myResult = st.executeQuery();
        int a = -1;
        while(myResult.next())
        {
            a = myResult.getInt("total");
        }

        if(a == 0){
        SeatRepository seat = new SeatRepository(_dbConnect);
        int row = 1;
        int column = 1;
        int seatNumber = 1;
        String seatMap = theaterSeatMap;
        for (int i = 0; i < seatMap.length(); i++) {
            if (seatMap.charAt(i) >= 'a' && seatMap.charAt(i) <= 'z') {
                seat.setSeatsTabel(new SeatsEntity(0, theaterName, date, row, column));
                column++;
                seatNumber++;
            } else if (seatMap.charAt(i) == '/') {
                i = i + 1;
                row++;
                column = 1;
            } else //the seat is '_' - means there is no seat
            {
                column++;
                if (seatMap.charAt(i + 1) == '/') {
                    row++;
                    i= i+2;//jumps to the next seat
                    column = 1;
                }
            }
        }
    }
        _dbConnect.closeConnection();
    }

    @Override
    public List<SeatsEntity> getAllSeatsInTheater(String theaterName)  throws SQLException,Exception {

        List<SeatsEntity> seatList = new ArrayList<SeatsEntity>();
        String query ="SELECT * FROM seats WHERE theaterName=@theater@";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setString("@theater@" ,theaterName);
        ResultSet myRs = st.executeQuery();
        while (myRs.next()) {
            System.out.println(myRs.getInt("NofSeats"));
            seatList.add(new SeatsEntity(myRs.getInt("taken"),myRs.getString("theaterName"),myRs.getDate("date"),1,1));
        }
        _dbConnect.closeConnection();
        return seatList;
    }

    @Override
    public TheaterEntity getTheater(String theaterName, String location) throws SQLException,Exception
    {
        TheaterEntity theater = null;
        String query ="SELECT * FROM theater "
                +"WHERE  theaterName=@theater@ AND location = @location@";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setString("@theater@" ,theaterName);
        st.setString("@location@" ,location);
        ResultSet myRs = st.executeQuery();
        while (myRs.next()) {
                theater = new TheaterEntity(myRs.getString("location"),myRs.getString("theaterName"), myRs.getString("showName"), myRs.getString("seatMap"));
          }
        _dbConnect.closeConnection();
        return theater;
    }



    @Override
    public TheaterEntity getTheaterSeatMap(String theaterName, String location) throws SQLException, Exception {
       String seatMap = "";
       TheaterEntity theater = null;
        String query ="SELECT * FROM theater WHERE theaterName=@theater@ AND location = @location@ ";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setString("@theater@" ,theaterName);
        st.setString("@location@" ,location);
        ResultSet myRs = st.executeQuery();
        while (myRs.next()) {
            theater = new TheaterEntity(myRs.getString("location"),myRs.getString("theaterName"), myRs.getString("showName") ,myRs.getString("seatMap"));
        }
        _dbConnect.closeConnection();
        return theater;
        //return myRs;
    }
  public List<TheaterEntity> getListOfTheaters() throws SQLException,Exception
  {
      List<TheaterEntity> theaterList = new ArrayList<TheaterEntity>();
      String query = "select * from theater;";
      NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
      ResultSet myRs = st.executeQuery();
      while (myRs.next()) {
          theaterList.add(new TheaterEntity(myRs.getString("location") , myRs.getString("theaterName") , myRs.getString("showName") , myRs.getString("seatMap")));
      }
      _dbConnect.closeConnection();
      return theaterList;
  }


    public void createTheaterTable() throws SQLException,Exception
    {
        String query = "CREATE TABLE IF NOT EXISTS theater (location VARCHAR(45) PRIMARY KEY,theaterName VARCHAR(45) PRIMARY KEY,showName VARCHAR(45), seatMap LONGTEXT);";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        int myRs = st.executeUpdate();
        _dbConnect.closeConnection();
    }




}
