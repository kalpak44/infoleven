package pavel.homework.service;

import org.springframework.stereotype.Service;
import pavel.homework.domain.Bus;
import pavel.homework.service.exception.NoBusFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 14.04.17.
 */
@Service
public class BusesService {

    private volatile Map<String, Bus> buses = new HashMap<String, Bus>();

    public void addBus(final Bus bus) {
        this.buses.put(bus.getBusId(), bus);
    }

    public Bus getBus(final String busId) {
        final Bus bus = this.buses.get(busId);
        if (bus == null) {
            throw new NoBusFoundException();
        }
        return bus;
    }
}
