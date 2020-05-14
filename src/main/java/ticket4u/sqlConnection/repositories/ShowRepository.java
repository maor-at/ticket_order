package ticket4u.sqlConnection.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ticket4u.sqlConnection.DbConnect;
import ticket4u.sqlConnection.NamedParamStatement;
import ticket4u.sqlConnection.SeatsEntity;
import ticket4u.sqlConnection.ShowsEntity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShowRepository implements IShowRepository {
    private DbConnect _dbConnect;

    @Autowired
    public ShowRepository (DbConnect dbConnect){
        _dbConnect = dbConnect;
    }


    public  List<ShowsEntity> getListOfExistingShows(ShowsEntity showsEntity)  throws SQLException,Exception/* date by today */
    {
        List<ShowsEntity> showsList = new ArrayList<ShowsEntity>();
        String query = "select * from shows" + " where date >= @date@;";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setDate("@date@" ,showsEntity.getDate());
        ResultSet myRs = st.executeQuery();
        while (myRs.next()) {
            showsList.add(new ShowsEntity(myRs.getString("showName") , myRs.getInt("showType") , myRs.getDate("date") , myRs.getString("theaterName"), myRs.getString("theaterName"), myRs.getString("showHour")));
        }
        _dbConnect.closeConnection();
        return showsList;
    }

    public List<ShowsEntity> getListOfShowsByDate(Date date) throws SQLException,Exception
    {
        List<ShowsEntity> showsList = new ArrayList<ShowsEntity>();
        String query = "select * from shows" + " where date = @date@;";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setDate("@date@" ,date);
        ResultSet myRs = st.executeQuery();
        while (myRs.next()) {
            showsList.add(new ShowsEntity(myRs.getString("showName") , myRs.getInt("showType") , myRs.getDate("date") , myRs.getString("theaterName"),myRs.getString("description"), myRs.getString("showHour")));
        }
        _dbConnect.closeConnection();
        return showsList;

    }
    public List<ShowsEntity> getListOfShowsByType(int showType) throws SQLException,Exception
    {
        List<ShowsEntity> showsList = new ArrayList<ShowsEntity>();
        String query = "select * from shows" + " where showType = @showType@;";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setInt("@showType@" ,showType);
        ResultSet myRs = st.executeQuery();
        while (myRs.next()) {
            showsList.add(new ShowsEntity(myRs.getString("showName") , myRs.getInt("showType") , myRs.getDate("date") , myRs.getString("theaterName"),myRs.getString("description"), myRs.getString("showHour")));
        }
        _dbConnect.closeConnection();
        return showsList;
    }

    public void setShowTable(ShowsEntity showsEntity) throws SQLException,Exception
    {
        String query = "INSERT INTO shows (showName, showType, date, theaterName, description,showHour) "
                +"VALUES (@showName@, @showType@, @date@, @theater@, @description@,@showHour@)";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setInt("@showType@" ,showsEntity.getShowType());
        st.setString("@showName@" ,showsEntity.getShowName());
        st.setDate("@date@" ,showsEntity.getDate());
        st.setString("@theater@" ,showsEntity.getTheaterName());
        st.setString("@description@",showsEntity.getDescription());
        st.setString("@showHour@",showsEntity.getShowHour());
        int myRs = st.executeUpdate();
//        ResultSet myRs = st.executeQuery();
        _dbConnect.closeConnection();
    }

     public List<ShowsEntity> getListOfShowsByName(String name) throws SQLException,Exception
     {
         String [] splittedString = name.split(" ");
         String query = "SELECT * FROM shows WHERE ";
         List<ShowsEntity> showsList = new ArrayList<ShowsEntity>();
         for(int i=0; i<splittedString.length;i++)
         {
             query = query + "showName LIKE \'%"+splittedString[i]+"%\' OR ";
         }
         for(int i=0; i<splittedString.length-1;i++)
         {

             query = query + "showName LIKE \'%"+splittedString[i]+"%\' AND ";
         }
         query = query + "showName LIKE \'%"+splittedString[splittedString.length-1]+"%\' ";

         NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
         //st.setString("@showName@" ,name);
         ResultSet myRs = st.executeQuery();
         while (myRs.next()) {
             showsList.add(new ShowsEntity(myRs.getString("showName") , myRs.getInt("showType") , myRs.getDate("date") , myRs.getString("theaterName"), myRs.getString("description"), myRs.getString("showHour")));
         }
         _dbConnect.closeConnection();
         return showsList;
     }

    @Override
    public List<ShowsEntity> getListOfShowsByLocation(String location) throws SQLException,Exception
    {
        List<ShowsEntity> showsList = new ArrayList<ShowsEntity>();
        String query = "select s.showName,showType,s.theaterName,description,date,showHour from theater as t join shows as s  on s.showName = t.showName where location =@showLocation@;";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setString("@showLocation@" ,location);
        ResultSet myRs = st.executeQuery();
        while (myRs.next()) {
            showsList.add(new ShowsEntity(myRs.getString("showName") , myRs.getInt("showType") , myRs.getDate("date") , myRs.getString("theaterName"),myRs.getString("description"), myRs.getString("showHour")));
        }
        _dbConnect.closeConnection();
        return showsList;
    }

    @Override
    public ShowsEntity getShowDescription(String showName) throws SQLException, Exception {
        String description = "";
        ShowsEntity show = null;
        String query = "select * from shows " + " where showName= @showName@;";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setString("@showName@" ,showName);
        ResultSet myRs = st.executeQuery();
        while (myRs.next()) {
             show = new ShowsEntity(myRs.getString("showName") , myRs.getInt("showType") , myRs.getDate("date") , myRs.getString("theaterName"),myRs.getString("description"), myRs.getString("showHour"));
            //description = myRs.getString("description");
        }
        _dbConnect.closeConnection();
        return show;
    }


    public void createShowsTable() throws SQLException,Exception
    {
        String query = "CREATE TABLE IF NOT EXISTS shows (showName VARCHAR(45) PRIMARY KEY,showType INT(11) PRIMARY KEY, date DATE PRIMARY KEY, theaterName VARCHAR(45) PRIMARY KEY,description LONGTEXT, showHour VARCHAR(45) );";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        int myRs = st.executeUpdate();
        _dbConnect.closeConnection();
    }


}
