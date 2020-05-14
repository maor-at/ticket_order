package ticket4u.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticket4u.controllers.ticketStore.TheaterDto;
import ticket4u.sqlConnection.SeatsEntity;
import ticket4u.sqlConnection.TheaterEntity;
import ticket4u.sqlConnection.repositories.ITheaterRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Service
public class TheaterService implements ITheaterService {

    private final ITheaterRepository _theaterRepository;

        @Autowired
        public TheaterService(ITheaterRepository theaterRepository)//can add another repository
        {
            _theaterRepository = theaterRepository;
        }

    @Override
    public TheaterEntity getTheater(String theaterName, String location) throws SQLException,Exception{
        return _theaterRepository.getTheater(theaterName,location);
    }

    @Override
    public void addNewTheater(TheaterEntity theaterEntity) throws SQLException,Exception
    {
        _theaterRepository.setTheaterTable(theaterEntity);
    }

    @Override
    public List<SeatsEntity> getAllSeatsInTheater(String TheaterName) throws SQLException,Exception{
        _theaterRepository.getAllSeatsInTheater(TheaterName);
            return null;
    }

    @Override
    public TheaterEntity getTheaterSeatMap(String theaterName, String location) throws SQLException, Exception {
        return _theaterRepository.getTheaterSeatMap(theaterName, location);
    }

    public List<TheaterEntity> getListOfTheaters() throws SQLException,Exception
    {
        return _theaterRepository.getListOfTheaters();
    }

    @Override
    public void addNewSeatFromTheater(String theaterName, Date date, String theaterSeatMap) throws SQLException, Exception {

                _theaterRepository.addNewSeatFromTheater(theaterName,date,theaterSeatMap);
    }
}
