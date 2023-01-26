package flyget;

import java.io.Serializable;

/**
 * Represents a seat in a plane
 * can be unbooked or booked by a Person
 */
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

    /**
     * Returns a visual representation of the
     * seats booking status
     * 
     * @param booking status as a String
     */
    public String toString() {
        return this.booked ? "[X]" : "[ ]";
    }
}
