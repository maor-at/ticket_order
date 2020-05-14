package ticket4u.sqlConnection.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ticket4u.sqlConnection.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SeatRepository implements ISeatRepository {
    private DbConnect _dbConnect;

    @Autowired
    public SeatRepository(DbConnect dbConnect)
    {
        _dbConnect = dbConnect;
    }

    public void setSeatsTabel( SeatsEntity seat ) throws SQLException,Exception
    {
        String query = "INSERT INTO seats (taken, theaterName, date, seatRow, seatColumn)"
                +"VALUES ( @taken@, @theaterName@, @date@, @row@, @column@);";

        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setInt("@taken@" ,seat.isTaken());
        st.setString("@theaterName@" ,seat.getTheaterName());
        st.setDate("@date@" ,seat.getDate());
        st.setInt("@row@" ,seat.getRow());
        st.setInt("@column@" ,seat.getColumn());
        int myRs = st.executeUpdate();
        _dbConnect.closeConnection();
    }

    @Override
    public void updateSeat(String theaterName, Date date, int row, int column, int taken) throws SQLException,Exception
    {
        String query = "UPDATE seats SET taken = @taken@  WHERE (`theaterName` = @theaterName@) AND (`date` = @date@) AND (`seatRow` = @row@) AND (`seatColumn` = @column@);";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setInt("@taken@" ,taken);
        st.setString("@theaterName@" ,theaterName);
        st.setDate("@date@" ,date);
        st.setInt("@row@" ,row);
        st.setInt("@column@" ,column);
        int myRs = st.executeUpdate();
        _dbConnect.closeConnection();
    }

    public List<SeatsEntity> getUnavailableSeat(String theaterName, Date date) throws SQLException,Exception
    {
        List<SeatsEntity> seatList = new ArrayList<SeatsEntity>();
        String query ="select * from seats where  date = @date@ AND (taken = 1 OR taken = 2) AND theaterName = @theaterName@"; //"select * from seats where taken = true & theaterName = @theaterName@ & date = @date@;";

        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setString("@theaterName@" , theaterName);
        st.setDate("@date@",date);
        ResultSet myRs = st.executeQuery();

        while (myRs.next()) {
            seatList.add(new SeatsEntity(myRs.getInt("taken") ,myRs.getString("theaterName"), myRs.getDate("date") ,myRs.getInt("seatRow"),myRs.getInt("seatColumn")));
        }
        _dbConnect.closeConnection();
        return seatList;
    }
    public void createSeatsTable() throws SQLException,Exception
    {
        String query = "CREATE TABLE IF NOT EXISTS seats (taken TINYINT(4),theaterName VARCHAR(45) PRIMARY KEY,date DATE PRIMARY KEY, seatRow INT(11) PRIMARY KEY, seatColumn INT(11) PRIMARY KEY);";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        int myRs = st.executeUpdate();
        _dbConnect.closeConnection();
    }

}
