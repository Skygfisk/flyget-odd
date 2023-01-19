package flyget;

import java.io.Serializable;

public class Seat implements Serializable {
    private int rowNumber;
    private char seatChar;
    private boolean booked;

    public int getRow() {
        return this.rowNumber;
    }

    public char getSeat() {
        return this.seatChar;
    }

    public void setBooked(boolean x) {
        this.booked = x;
    }

    public boolean getBooked() {
        return this.booked;
    }
}
