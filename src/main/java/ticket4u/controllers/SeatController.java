package ticket4u.controllers;


import org.springframework.web.bind.annotation.*;
import ticket4u.controllers.ticketStore.SeatsDto;
import ticket4u.logic.ISeatService;
import ticket4u.logic.ITheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ticket4u.sqlConnection.SeatsEntity;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/TicketStore/v1/s")
public class SeatController  {
    private ISeatService seatService;
    private ITheaterService theaterService;

@Autowired
public SeatController(ISeatService seatService , ITheaterService theaterService )
{
    this.seatService = seatService;
    this.theaterService = theaterService;
}
//returns a list of seatsDTO of the theater in order to display seats availability
   public List<SeatsDto> getSeatListOfTheater(String TheaterName) throws SQLException,Exception
   {
       List<SeatsDto> listOfDtoSeats = new ArrayList<SeatsDto>();
       List<SeatsEntity> listOfSeats= theaterService.getAllSeatsInTheater(TheaterName);
       for ( int i = 0; i<listOfSeats.size();i++)
       {
           listOfDtoSeats.add(new SeatsDto(listOfSeats.get(i)));
       }
      return  listOfDtoSeats;
    }

    @GetMapping(value = "/unavailableSeat")
    public ArrayList<String> getUnavailableSeatListOfTheater(@RequestParam(name = "theaterName",required = true) String theaterName, @RequestParam(name = "date",required = true)Date date)  throws SQLException,Exception
    {
        ArrayList<String> listOfUnavailableSeats = new ArrayList<String>();
        String takenSeatRepresentInString;
        int row,column;
        List<SeatsEntity> listOfSeats= seatService.getUnavailableSeat(theaterName,date);
        for ( int i = 0; i<listOfSeats.size();i++)
        {
            row = listOfSeats.get(i).getRow();
            column = listOfSeats.get(i).getColumn();
            takenSeatRepresentInString = row +"_"+ column;
            listOfUnavailableSeats.add(takenSeatRepresentInString);
        }
        return  listOfUnavailableSeats;
    }

    @GetMapping(value = "/updateSeat")
    public void updateSeat(@RequestParam(name = "taken",required = true) int taken,@RequestParam(name = "theaterName",required = true) String theaterName, @RequestParam(name = "date",required = true)Date date,@RequestParam(name = "row",required = true) int row,@RequestParam(name = "column",required = true) int column)  throws SQLException,Exception
    {
        seatService.updateSeat(theaterName,date,row,column,taken);
    }

}
