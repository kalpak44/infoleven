import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pavel.homework.Application;
import pavel.homework.domain.Bus;
import pavel.homework.domain.Seat;
import pavel.homework.service.BusesService;
import pavel.homework.service.SeatsService;
import pavel.homework.service.exception.NoBusFoundException;
import util.MultiThreadedTester;

/**
 * Created on 14.04.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class SimpleServicesTest {
    @Autowired
    private BusesService busesService;

    @Autowired
    private SeatsService seatsService;

    @Test(expected = NoBusFoundException.class)
    public void testNoAddAndGetOneBus() {
        busesService.getBus("noExistingBus");
    }

    @Test
    public void testAddAndGetOneBus() {
        busesService.addBus(new Bus("testBusIdentifier"));
        Assert.assertNotNull(busesService.getBus("testBusIdentifier"));
    }

    @Test
    public void testBusSeatsInitialization() {
        busesService.addBus(new Bus("busWithFreeSeats"));
        Seat[] busSeats = busesService.getBus("busWithFreeSeats").getSeats();
        Assert.assertEquals(busSeats.length, 50);
        for (Seat seat : busSeats) {
            Assert.assertTrue(seat.getIsFree());
        }
    }

    @Test
    public void testBusSeatsMultiThreading() throws InterruptedException {
        final int seatsCount = 25000;
        busesService.addBus(new Bus("multiThread", seatsCount));

        MultiThreadedTester stressTester = new MultiThreadedTester(12500);

        stressTester.stress(new Runnable() {
            public void run() {
                seatsService.getReservation("multiThread");
            }
        });

        Seat[] seats = busesService.getBus("multiThread").getSeats();
        for (Seat seat : seats) {
            Assert.assertFalse(seat.getIsFree());
        }

    }
}
