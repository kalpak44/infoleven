package pavel.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pavel.homework.domain.Bus;
import pavel.homework.domain.Seat;
import pavel.homework.service.exception.NoSeatsFreeFoundException;

/**
 * Created on 14.04.17.
 */
@Service
public class SeatsService {

    private BusesService busesService;

    @Autowired
    public SeatsService(BusesService busesService) {
        this.busesService = busesService;
    }

    public Seat getReservation(final String busId) {
        final Bus bus = busesService.getBus(busId);
        return this.getReservation(bus);
    }

    public Seat getReservation(final Bus bus) {
        // without synchronized block realized in  with_atomic branch
        synchronized (this) {
            for (Seat currentSeat : bus.getSeats()) {
                if (currentSeat.getIsFree()) {
                    currentSeat.setIsFree(false);
                    return currentSeat;
                }
            }
            throw new NoSeatsFreeFoundException();
        }
    }
}
