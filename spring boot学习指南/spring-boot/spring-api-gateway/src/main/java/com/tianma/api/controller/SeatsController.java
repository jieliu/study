package com.tianma.api.controller;

import com.tianma.api.pojo.Greeting;
import com.tianma.api.util.ResponseEntity;
import com.tianma.api.pojo.Seats;
import com.tianma.api.support.SeatsDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Franco on 2016/2/26.
 */
@RestController
@Api
public class SeatsController {
    private SeatsDao seatsDao;

    @Autowired
    public void  setSeatsDao(SeatsDao seatsDao)
    {
        this.seatsDao = seatsDao;
    }

    @Autowired
    public SeatsDao getSeatsDao() {
        return seatsDao;
    }

    //@RequestMapping(value = "/seats/login")
    @ApiOperation(value = "getSeats", nickname = "getSeats")
    @RequestMapping(method = RequestMethod.GET, path="/seats", produces = "application/json")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "User's name", required = false, dataType = "string", paramType = "query", defaultValue="Franco")
    })*/
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Greeting.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    ResponseEntity register(@RequestParam("Id") int Id)/*,@RequestParam("PhoneNumber") String PhoneNumber, @RequestParam("Password") String Password*/
    {
        Seats seats = new Seats();
        seats.setId(Id);
        Seats apply = seatsDao.getById(seats);
        if (apply != null) {
            return new ResponseEntity(apply.toString(), HttpStatus.OK);
        }
        else return new ResponseEntity("Seats with this id is not found!",HttpStatus.NOT_FOUND);
        //return new ResponseEntity("Seats with this id is not found!",HttpStatus.NOT_FOUND);
    }
}