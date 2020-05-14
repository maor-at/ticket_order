package ticket4u.controllers;

import ticket4u.controllers.ticketStore.CreateShowRequest;
import ticket4u.logic.IShowsService;
import ticket4u.controllers.ticketStore.ShowsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ticket4u.sqlConnection.ShowsEntity;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/TicketStore/v1/t")
public class ShowsController {

    private IShowsService service;

    @Autowired
    public ShowsController(IShowsService service ){
        this.service = service;
    }

    //returns the list of all shows that occurs in the specific date
@GetMapping(value = "/SearchByDate")
    public List<ShowsDto> getShowByDate(@RequestParam(name = "showDate",required = true) Date showDate) throws SQLException,Exception
{
    List<ShowsEntity> listOfShows = new ArrayList<ShowsEntity>(service.getShowsByDate(showDate));
    List<ShowsDto> listOfShowsDto = new ArrayList<ShowsDto>();
    for( int i = 0; i<listOfShows.size(); i++)
    {
        listOfShowsDto.add(new ShowsDto(listOfShows.get(i)));
    }
    return listOfShowsDto;
    }

    //returns the list of all shows of specific type
    @GetMapping(value = "/SearchByType")
    public List<ShowsDto> getShowByType(@RequestParam(name = "showType",required = true) int showType) throws SQLException,Exception
    {
        List<ShowsEntity> listOfShows = new ArrayList<ShowsEntity>(service.getShowsByType(showType));
        List<ShowsDto> listOfShowsDto = new ArrayList<ShowsDto>();
        for( int i = 0; i<listOfShows.size(); i++)
        {
            listOfShowsDto.add(new ShowsDto(listOfShows.get(i)));
        }
        return listOfShowsDto;

    }

    //returns the list of all shows that contains the name
    @GetMapping(value = "/SearchByName")
    public List<ShowsDto> getShowsByName(@RequestParam(name = "showName",required = true) String showName) throws SQLException,Exception
    {
        List<ShowsEntity> listOfShows = new ArrayList<ShowsEntity>(service.getShowsByName (showName));
        List<ShowsDto> listOfShowsDto = new ArrayList<ShowsDto>();
        for( int i = 0; i<listOfShows.size(); i++)
        {
            listOfShowsDto.add(new ShowsDto(listOfShows.get(i)));
        }
        return listOfShowsDto;
    }

    //returns the list of all shows that contains the name
    @GetMapping(value = "/SearchByLocation")
    public List<ShowsDto> getShowsByLocation(@RequestParam(name = "location",required = true) String location) throws SQLException,Exception
    {
        List<ShowsEntity> listOfShows = new ArrayList<ShowsEntity>(service.getShowsByLocation(location));
        List<ShowsDto> listOfShowsDto = new ArrayList<ShowsDto>();
        for( int i = 0; i<listOfShows.size(); i++)
        {
            listOfShowsDto.add(new ShowsDto(listOfShows.get(i)));
        }
        return listOfShowsDto;
    }
    //adds new show to the DB -shows entity
    @PostMapping(value = "/NewShow")
    public void AddShowEntity(@RequestBody CreateShowRequest newShow) throws SQLException,Exception
    {
        service.addNewShow(newShow);
    }

    @GetMapping(value = "/showDescription")
    public ShowsDto getShowDescription(@RequestParam(name = "showName",required = true) String showName) throws SQLException,Exception
    {
        return(new ShowsDto(service.getShowDescription(showName)));
    }
}
