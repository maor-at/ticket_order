package ticket4u.sqlConnection.repositories;

import ticket4u.sqlConnection.ShowsEntity;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface IShowRepository {
    List<ShowsEntity> getListOfExistingShows(ShowsEntity showsEntity) throws SQLException,Exception;
    List<ShowsEntity> getListOfShowsByDate(Date date) throws SQLException,Exception;
    List<ShowsEntity> getListOfShowsByType(int type) throws SQLException,Exception;
    void setShowTable(ShowsEntity showsEntity) throws SQLException,Exception;
    List<ShowsEntity> getListOfShowsByName(String name) throws SQLException,Exception;
    List<ShowsEntity> getListOfShowsByLocation(String location) throws SQLException,Exception;
    ShowsEntity getShowDescription(String showName) throws SQLException,Exception;
}
