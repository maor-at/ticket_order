package ticket4u.controllers;

import org.springframework.web.bind.annotation.*;
import ticket4u.controllers.ticketStore.TicketsDto;
import ticket4u.controllers.ticketStore.UsersDto;
import ticket4u.logic.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/TicketStore/v1/b")
public class TicketsController {

    private ITicketService service;

    @Autowired
    public TicketsController(ITicketService service)
    {
        this.service = service;
    }

    public TicketsDto demoGetTicket(int sn) throws SQLException,Exception {

        return new TicketsDto(service.getTicket(sn));
    }

    //this function suppose to save a new purchase to the dataBase(add tickets entity)
    @PostMapping(value = "/purchase")
    public void AddTicketEntity(@RequestBody List<TicketsDto> ticketsDtoList) throws SQLException,Exception
    {
        service.addNewTicket(ticketsDtoList);
    }

    }

