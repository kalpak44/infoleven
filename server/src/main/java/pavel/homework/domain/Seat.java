package pavel.homework.domain;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created on 14.04.17.
 */
public class Seat implements Serializable {

    private static final long serialVersionUID = -2541746827048300367L;
    private AtomicBoolean isFree = new AtomicBoolean();
    private int seatId;

    public Seat(int seatId, boolean hasFree) {
        this.isFree.set(hasFree);
        this.seatId = seatId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public boolean isFree() {
        return isFree.get();
    }

    public boolean reserve() {
        return this.isFree.compareAndSet(true, false);
    }

}
