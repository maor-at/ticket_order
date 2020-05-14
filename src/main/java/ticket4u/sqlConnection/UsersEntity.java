package ticket4u.sqlConnection;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersEntity extends DbConnect {

    String _userName;
    String _password;
    int type;

    public UsersEntity(String userName, String password , int type)
    {
        super();
        this._userName = userName;
        this._password = password;
        this.type = type;
    }

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String userName) {
        this._userName = userName;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
