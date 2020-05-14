package ticket4u.sqlConnection.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ticket4u.sqlConnection.DbConnect;
import ticket4u.sqlConnection.NamedParamStatement;
import ticket4u.sqlConnection.UsersEntity;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository implements IUserRepository {
    private DbConnect _dbConnect;

    @Autowired
    public UserRepository(DbConnect dbConnect){
        _dbConnect = dbConnect;
    }

    @Override
    public UsersEntity getUser(String username,String password,int type) throws SQLException,Exception
    {
        String query =" SELECT * FROM users WHERE username=@username@ AND password=@password@ AND type = @type@ ";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setString("@username@", username);
        st.setString("@password@", password);
        st.setInt("@type@",type);
        ResultSet myRs = st.executeQuery();

        UsersEntity user = null;
        while (myRs.next()) {
            System.out.println(myRs.getString("username") + ", " + myRs.getString("password"));
            user = new UsersEntity(myRs.getString("username"),myRs.getString("password"),myRs.getInt("type"));
        }
        _dbConnect.closeConnection();
        return user;
    }

    @Override
    public void insertUser(UsersEntity user)  throws SQLException,Exception
    {
           /* String query = "INSERT INTO users (username, password) SELECT * FROM (SELECT @username@, @password@) as tmp " +
               */   //  "WHERE NOT EXISTS ( SELECT username FROM users WHERE username = @username@) LIMIT 1";


               String query =  " INSERT INTO users (username, password,type) SELECT @username@,@password@,@type@ FROM DUAL WHERE NOT EXISTS (SELECT username FROM users WHERE username=@username@)";


            NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
            st.setString("@username@" ,user.getUserName());
            st.setString("@password@" ,user.getPassword());
            st.setInt("@type@" ,user.getType());

            int myRs = st.executeUpdate();
            if(myRs != 0)
            {
                      //return user;
            }
            _dbConnect.closeConnection();

    }

    public void createUsersTable() throws SQLException,Exception
    {

        String query = "CREATE TABLE IF NOT EXISTS users (username VARCHAR(45) PRIMARY KEY,password VARCHAR(45) ,type INT(11) );";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        int myRs = st.executeUpdate();
        _dbConnect.closeConnection();
    }



}
