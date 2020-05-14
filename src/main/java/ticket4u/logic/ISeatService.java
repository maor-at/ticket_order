package ticket4u.logic;

import ticket4u.sqlConnection.SeatsEntity;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ISeatService {

 SeatsEntity getSeat(int id );
 void addNewSeat(SeatsEntity seatsEntity) throws SQLException,Exception;
 void updateSeat(String theaterName, Date date, int row, int column, int taken) throws SQLException, Exception;
 List<SeatsEntity> getUnavailableSeat(String theaterName, Date date) throws SQLException,Exception;
}
