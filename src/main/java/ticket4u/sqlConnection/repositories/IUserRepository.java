package ticket4u.sqlConnection.repositories;

import ticket4u.sqlConnection.UsersEntity;

import java.sql.SQLException;

public interface IUserRepository {

    UsersEntity getUser(String username,String password, int type) throws SQLException,Exception;

    void insertUser(UsersEntity user) throws SQLException,Exception;


}
