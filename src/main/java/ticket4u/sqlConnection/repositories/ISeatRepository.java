package ticket4u.sqlConnection.repositories;

import ticket4u.sqlConnection.SeatsEntity;
import ticket4u.sqlConnection.TheaterEntity;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ISeatRepository
{
    //List<SeatsEntity> getListOfAvailableSeats(TheaterEntity theaterEntity) throws SQLException,Exception;
    void setSeatsTabel( SeatsEntity seat ) throws SQLException,Exception;
    void updateSeat(String theaterName, Date date, int row, int column, int taken) throws SQLException,Exception;

    List<SeatsEntity> getUnavailableSeat(String theaterName, Date date) throws SQLException,Exception;
}
