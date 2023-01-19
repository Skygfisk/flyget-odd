package flyget;

import java.io.Serializable;

public class Plane implements Serializable {
    private String name;
    private Seat[][] seats;

    public Plane(int size) {
        this.seats = generateSeats(size);
    }

    private Seat[][] generateSeats(int size) {
        Seat[][] newSeats = new Seat[size][size];
        for (int rowIndex = 0; rowIndex < newSeats.length; rowIndex++) {
            for (int seatIndex = 0; seatIndex < newSeats[rowIndex].length; seatIndex++) {
                newSeats[rowIndex][seatIndex] = new Seat();
            }
        }
        return newSeats;
    }

    public void bookSeat(int row, int seat) {
        this.seats[row][seat].setBooked(true);
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public String getName() {
        return name;
    }
}
