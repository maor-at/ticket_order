package ticket4u.logic;

import ticket4u.controllers.ticketStore.CreateShowRequest;
import ticket4u.sqlConnection.ShowsEntity;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface IShowsService {
    ShowsEntity getShow(int id);
    List<ShowsEntity> getShowsByDate(Date date) throws SQLException,Exception;
    List<ShowsEntity> getShowsByType(int type) throws SQLException,Exception;
    List<ShowsEntity> getShowsByName(String name) throws SQLException,Exception;
    List<ShowsEntity> getShowsByLocation(String location) throws SQLException,Exception;
    void addNewShow(CreateShowRequest createShowRequest) throws SQLException,Exception;
    ShowsEntity getShowDescription(String showName) throws SQLException,Exception;





}
