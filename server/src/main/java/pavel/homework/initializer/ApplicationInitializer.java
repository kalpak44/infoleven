package pavel.homework.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pavel.homework.domain.Bus;
import pavel.homework.service.BusesService;

/**
 * Created on 14.04.17.
 * It is temporary class that initialize data.
 */
@Component
public class ApplicationInitializer implements ApplicationRunner {
    private final BusesService busesService;

    @Autowired
    public ApplicationInitializer(BusesService busesService) {
        this.busesService = busesService;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        //init data
        busesService.addBus(new Bus("coolBus"));
    }
}