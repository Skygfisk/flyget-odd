package flyget;

import java.io.Serializable;

public class Seat implements Serializable {
    private boolean booked;
    private Person passenger;

    public boolean getBooked() {
        return this.booked;
    }

    public void book(Person passenger) {
        this.booked = true;
        this.passenger = passenger;
    }

    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }

    public Person getPassenger() {
        return passenger;
    }

    public String toString() {
        return this.booked ? "[X]" : "[ ]";
    }
}
