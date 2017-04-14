package pavel.homework.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pavel.homework.domain.Bus;
import pavel.homework.domain.Seat;
import pavel.homework.service.BusesService;
import pavel.homework.service.SeatsService;
import pavel.homework.service.exception.NoBusFoundException;
import pavel.homework.service.exception.NoSeatsFreeFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created on 14.04.17.
 */
@RestController
class BusController {

    private final BusesService busesService;
    private final SeatsService seatsService;

    @Autowired
    public BusController(BusesService busesService, SeatsService seatsService) {
        this.busesService = busesService;
        this.seatsService = seatsService;
    }

    @GetMapping("/get-bus/{busId}")
    public @ResponseBody
    Bus getBuses(@PathVariable String busId) {
        return busesService.getBus(busId);
    }

    @GetMapping("/get-bus/{busId}/reserve")
    public @ResponseBody
    Seat getBusReservation(@PathVariable String busId) {
        return seatsService.getReservation(busId);
    }

    @ExceptionHandler
    void handleNoSeatsFreeFoundException(NoSeatsFreeFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ExceptionHandler
    void handleNoBusFoundException(NoBusFoundException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_ACCEPTABLE.value());
    }
}