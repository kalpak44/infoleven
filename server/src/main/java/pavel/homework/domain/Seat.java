package pavel.homework.domain;

import java.io.Serializable;

/**
 * Created on 14.04.17.
 */
public class Seat implements Serializable {

    private static final long serialVersionUID = -2541746827048300367L;
    private boolean isFree;
    private int seatId;

    public Seat(int seatId, boolean hasFree) {
        this.isFree = hasFree;
        this.seatId = seatId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }
}
