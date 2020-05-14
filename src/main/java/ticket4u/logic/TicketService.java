package ticket4u.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticket4u.controllers.ticketStore.TicketsDto;
import ticket4u.sqlConnection.SeatsEntity;
import ticket4u.sqlConnection.TicketEntity;
import ticket4u.sqlConnection.repositories.ITicketRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService implements ITicketService
{
    private final ITicketRepository _ticketRepository;
    private final ISeatService _seatService;

    @Autowired
    public TicketService(ITicketRepository ticketRepository, ISeatService seatService)//can add another repository
    {
        _ticketRepository = ticketRepository;
        _seatService = seatService;
    }

    @Override
    public TicketEntity getTicket(int id) {
        return null;
    }

    @Override
    public void addNewTicket(List<TicketsDto> tickets) throws SQLException,Exception
    {
        List<TicketEntity> ticketEntityList = new ArrayList<TicketEntity>();
        for(int i = 0; i<tickets.size(); i++)
        {
            ticketEntityList.add(tickets.get(i).toEntity());
            _ticketRepository.setTicketTable(ticketEntityList.get(i));
            _seatService.updateSeat(ticketEntityList.get(i).getTheaterName(),ticketEntityList.get(i).getDate(),ticketEntityList.get(i).getRow(),ticketEntityList.get(i).getColumn(),1);
        }
    }
}
