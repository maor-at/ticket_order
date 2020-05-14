package ticket4u.logic;

import javafx.scene.control.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ticket4u.controllers.ticketStore.CreateShowRequest;
import ticket4u.sqlConnection.SeatsEntity;
import ticket4u.sqlConnection.ShowsEntity;
import ticket4u.sqlConnection.TheaterEntity;
import ticket4u.sqlConnection.repositories.ISeatRepository;
import ticket4u.sqlConnection.repositories.IShowRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Service
public class ShowService implements IShowsService {

    private final IShowRepository _showRepository;
    private final ISeatService _seatService;
    private final ITheaterService _theaterService;

    @Autowired
    public ShowService(IShowRepository showRepository, ISeatService seatService, ITheaterService theaterService)//can add another repository
    {
        _showRepository = showRepository;
        _seatService = seatService;
        _theaterService = theaterService;

    }


    @Override
    public ShowsEntity getShow(int id) {
        return null;
    }

    @Override
    public List<ShowsEntity> getShowsByDate(Date date) throws SQLException, Exception {
        return _showRepository.getListOfShowsByDate(date);
    }

    @Override
    public List<ShowsEntity> getShowsByType(int type) throws SQLException, Exception {
        return _showRepository.getListOfShowsByType(type);
    }

    @Override
    public List<ShowsEntity> getShowsByName(String name) throws SQLException, Exception {
        return _showRepository.getListOfShowsByName(name);
    }

    @Override
    public List<ShowsEntity> getShowsByLocation(String location) throws SQLException, Exception {
        return _showRepository.getListOfShowsByLocation(location);
    }

    @Override
    public void addNewShow(CreateShowRequest CreateShowRequest) throws SQLException, Exception {
        _showRepository.setShowTable(new ShowsEntity(CreateShowRequest.getShowName(), CreateShowRequest.getShowType(), CreateShowRequest.getDate(), CreateShowRequest.getTheaterName(), CreateShowRequest.getDescription(),CreateShowRequest.getShowHour()));
        TheaterEntity theater = _theaterService.getTheater(CreateShowRequest.getTheaterName(),CreateShowRequest.getLocation());
        if( theater == null) {
            _theaterService.addNewTheater(new TheaterEntity(CreateShowRequest.getLocation(), CreateShowRequest.getTheaterName(), CreateShowRequest.getShowName(), CreateShowRequest.getSeatMap()));
            _theaterService.addNewSeatFromTheater(CreateShowRequest.getTheaterName(),CreateShowRequest.getDate(),CreateShowRequest.getSeatMap());
        }
        else
            _theaterService.addNewSeatFromTheater(CreateShowRequest.getTheaterName(),CreateShowRequest.getDate(),theater.getSeatMap());
    }

    @Override
    public ShowsEntity getShowDescription(String showName) throws SQLException, Exception {
        return _showRepository.getShowDescription(showName);
    }
}
