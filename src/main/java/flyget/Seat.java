package flyget;

public class Seat {
    private int rowNumber;
    private char seatChar;
    private boolean booked;

    public int getRow() {
        return this.rowNumber;
    }

    public char getSeat() {
        return this.seatChar;
    }

    public boolean isBooked() {
        return this.booked;
    }
}
