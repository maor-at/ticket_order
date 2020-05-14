package ticket4u.controllers;

import org.springframework.web.bind.annotation.*;
import ticket4u.controllers.ticketStore.ShowsDto;
import ticket4u.controllers.ticketStore.TheaterDto;
import ticket4u.logic.ITheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import ticket4u.sqlConnection.ShowsEntity;
import ticket4u.sqlConnection.TheaterEntity;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/TicketStore/v1/r")
public class TheaterController {
    private ITheaterService service;

    @Autowired
    public TheaterController(ITheaterService service) {
        this.service = service;
    }

    @GetMapping(value = "/getTheater")
    public TheaterDto getTheater(@RequestParam(name = "theaterName", required = true)String theaterName,@RequestParam(name = "location", required = true)String location) throws SQLException, Exception {
        return new TheaterDto(service.getTheater(theaterName,location));
    }

    @GetMapping(value = "/seatMap")
    public TheaterDto getTheaterSeatMap(@RequestParam(name = "theaterName", required = true) String theaterName, @RequestParam(name = "location", required = true) String location) throws SQLException, Exception {
        return (new TheaterDto(service.getTheaterSeatMap(theaterName, location)));
    }

    @GetMapping(value = "/theaterList")
    public List<TheaterDto> getListOfTheaters() {
        try {
            List<TheaterEntity> listOfTheaters = new ArrayList<TheaterEntity>(service.getListOfTheaters());
            List<TheaterDto> listOfTheatersDto = new ArrayList<TheaterDto>();
            for (int i = 0; i < listOfTheaters.size(); i++) {
                listOfTheatersDto.add(new TheaterDto(listOfTheaters.get(i)));
            }
            return listOfTheatersDto;

        } catch (Exception e) {
            throw new RuntimeException("can't add new entity to the table \n"+e.getMessage());
        }
    }
}