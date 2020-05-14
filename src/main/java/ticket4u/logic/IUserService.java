package ticket4u.logic;

import ticket4u.sqlConnection.UsersEntity;

import java.sql.SQLException;

public interface IUserService {

    UsersEntity getUser(String userName, String password, int type ) throws SQLException,Exception;
    void addNewUser(UsersEntity usersEntity) throws SQLException,Exception;


}
