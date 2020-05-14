package ticket4u.logic;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticket4u.sqlConnection.UsersEntity;
import ticket4u.sqlConnection.repositories.IUserRepository;

import java.sql.SQLException;

@Service
public class UserService implements IUserService {
    private final IUserRepository _userRepository;
    @Autowired
    public UserService(IUserRepository userRepository)//can add another repository
    {
        _userRepository = userRepository;
    }



    @Override
    public UsersEntity getUser(String userName, String password, int type) throws SQLException,Exception{
        UsersEntity user =_userRepository.getUser(userName,password,type);
        if(user == null )
            throw new IllegalArgumentException("user doesn't exist");
        return user;
    }

    @Override
    public void addNewUser(UsersEntity usersEntity) throws SQLException,Exception{
        _userRepository.insertUser(usersEntity);
    }
}
