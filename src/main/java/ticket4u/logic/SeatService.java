package ticket4u.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticket4u.sqlConnection.SeatsEntity;
import ticket4u.sqlConnection.repositories.ISeatRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Service
public class SeatService implements ISeatService
{
    private final ISeatRepository _seatRepository;

    @Autowired
    public SeatService(ISeatRepository seatRepository)//can add another repository
    {
        _seatRepository = seatRepository;
    }

    @Override
    public SeatsEntity getSeat(int id) {
        return null;
    }

    @Override
    public void addNewSeat(SeatsEntity seatsEntity)
    {

    }

    @Override
    public void updateSeat(String theaterName, Date date, int row, int column, int taken) throws SQLException, Exception {
        _seatRepository.updateSeat(theaterName,date,row,column,taken);
    }

    @Override
    public List<SeatsEntity> getUnavailableSeat(String theaterName, Date date) throws SQLException, Exception {
        return(_seatRepository.getUnavailableSeat(theaterName,date));
    }
}
