package ticket4u.sqlConnection.repositories;

import ticket4u.controllers.ticketStore.TheaterDto;
import ticket4u.sqlConnection.SeatsEntity;
import ticket4u.sqlConnection.TheaterEntity;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ITheaterRepository {
    void setTheaterTable(TheaterEntity theaterEntity) throws SQLException,Exception;
    List<SeatsEntity> getAllSeatsInTheater(String theaterName) throws SQLException,Exception;
    TheaterEntity getTheater(String theaterName, String location) throws SQLException,Exception;
    TheaterEntity  getTheaterSeatMap(String theaterName, String location) throws SQLException,Exception;
    List<TheaterEntity> getListOfTheaters() throws SQLException,Exception;
    void addNewSeatFromTheater(String theaterName, Date date , String theaterSeatMap) throws SQLException,Exception;


}
