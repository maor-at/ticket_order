package ticket4u.logic;

import ticket4u.controllers.ticketStore.TicketsDto;
import ticket4u.sqlConnection.TicketEntity;

import java.sql.SQLException;
import java.util.List;

public interface ITicketService {

    TicketEntity getTicket(int id) throws SQLException,Exception;
    void addNewTicket(List<TicketsDto> tickets) throws SQLException,Exception;

}
