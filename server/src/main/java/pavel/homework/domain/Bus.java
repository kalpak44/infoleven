package pavel.homework.domain;

import java.io.Serializable;

/**
 * Created on 14.04.17.
 */
public class Bus implements Serializable {
    private static final long serialVersionUID = -7375160540121389039L;
    private Seat[] seats;
    private String busId;

    public Bus(String busId) {
        this.busId = busId;
        this.seats = new Seat[50];
        initSeats();
    }

    public Bus(String busId, int seatsCount) {
        this.busId = busId;
        this.seats = new Seat[seatsCount];
        initSeats();
    }

    private void initSeats() {
        for (int i = 0; seats.length > i; i++) {
            seats[i] = new Seat(i + 1, true);
        }
    }

    public Seat[] getSeats() {
        return seats;
    }

    public void setSeats(Seat[] seats) {
        this.seats = seats;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }
}
