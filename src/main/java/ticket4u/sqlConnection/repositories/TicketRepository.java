package ticket4u.sqlConnection.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ticket4u.sqlConnection.DbConnect;
import ticket4u.sqlConnection.NamedParamStatement;
import ticket4u.sqlConnection.TicketEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TicketRepository implements ITicketRepository{
    private DbConnect _dbConnect;

    @Autowired
    public TicketRepository(DbConnect dbConnect)
    {
        _dbConnect = dbConnect;
    }
    public void getListOfTickets(TicketEntity ticketEntity) throws SQLException,Exception
    {
        String query = "select * from tickets" + " where username = userName";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setString("@username@" ,ticketEntity.getUserName());
        ResultSet myRs = st.executeQuery();
        while (myRs.next()) {
           System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
        }
        _dbConnect.closeConnection();
    }

    public int setTicketTable(TicketEntity ticketEntity) throws SQLException,Exception
    {
        String query = "INSERT INTO tickets (username, showName, seatRow, seatColumn, date, theaterName, price, showHour) "
                +"VALUES (@username@, @showName@, @seatRow@, @seatColumn@, @date@, @theater@, @price@, @showHour@)";

        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        st.setString("@username@" ,ticketEntity.getUserName());
        st.setString("@showName@" ,ticketEntity.getShowName());
        st.setInt("@seatRow@",ticketEntity.getRow());
        st.setInt("@seatColumn@",ticketEntity.getColumn());
        st.setDate("@date@" ,ticketEntity.getDate());
        st.setString("@theater@" ,ticketEntity.getTheaterName());
        st.setInt("@price@",ticketEntity.getPrice());
        st.setString("@showHour@" ,ticketEntity.getShowHour());

        int myRs = st.executeUpdate();
        _dbConnect.closeConnection();
        return myRs;

    }

    public void createTicketsTable() throws SQLException,Exception
    {
        String query = "CREATE TABLE IF NOT EXISTS tickets (id INT(11) AUTO_INCREMENT PRIMARY KEY,username VARCHAR(45) PRIMARY KEY,showName VARCHAR(45) PRIMARY KEY,seatRow INT(11) PRIMARY KEY, seatColumn INT(11) PRIMARY KEY,date DATE, theaterName VARCHAR(45) PRIMARY KEY, price INT(11), showHour VARCHAR(45));";
        NamedParamStatement st = new NamedParamStatement(_dbConnect.connect(),query);
        int myRs = st.executeUpdate();
        _dbConnect.closeConnection();
    }


}
