package ticket4u.sqlConnection.repositories;

import ticket4u.sqlConnection.TicketEntity;

import java.sql.SQLException;

public interface ITicketRepository {
    void getListOfTickets(TicketEntity ticketEntity) throws SQLException,Exception;
    int setTicketTable(TicketEntity ticketEntity) throws SQLException,Exception;
}
