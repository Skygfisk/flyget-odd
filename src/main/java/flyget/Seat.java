package flyget;

import java.io.Serializable;

public class Seat implements Serializable {
    private int rowNumber;
    private char seatChar;
    private boolean booked;
    private Person passenger;

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

    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }

    public Person getPassenger() {
        return passenger;
    }

    public void book(Person passenger) {
        this.booked = true;
        this.passenger = passenger;
    }
}
