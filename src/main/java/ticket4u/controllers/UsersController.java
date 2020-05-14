package ticket4u.controllers;

import ticket4u.controllers.ticketStore.UsersDto;
import ticket4u.logic.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ticket4u.sqlConnection.UsersEntity;

import java.sql.SQLException;

@CrossOrigin
@RestController
@RequestMapping(value = "/TicketStore/v1/a")
public class UsersController {
    private IUserService userService;

    @Autowired
    public UsersController(IUserService service)
    {
        this.userService = service;
    }

    //checks if the admin userName is exists in the db
    @GetMapping(value = "/login")
    public UsersDto getUser(@RequestParam(name = "userName",required = true) String userName, @RequestParam(name = "password",required = true) String password , @RequestParam(name = "type",required = true) int type  ) throws SQLException,Exception
    {
        return new UsersDto(userService.getUser(userName,password, type));
    }

    //this function suppose to save a new user to the dataBase
    @PostMapping(value = "/registration")
    public void AddUserEntity(@RequestBody UsersDto newUser) throws SQLException,Exception
    {
        userService.addNewUser(newUser.toEntity());
    }

}
