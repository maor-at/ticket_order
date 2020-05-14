package ticket4u.controllers.ticketStore;

import ticket4u.sqlConnection.UsersEntity;

public class UsersDto {

    String _userName;
    String _password;
    int type;

    public UsersDto(String userName, String password, int type)
    {
        this._userName = userName;
        this._password = password;
        this.type = type;

    }
    public UsersDto(UsersEntity user){
        _userName = user.getUserName();
        _password = user.getPassword();
        type = user.getType();
    }

     public UsersEntity toEntity(){
        return new UsersEntity(_userName,_password,type);
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
