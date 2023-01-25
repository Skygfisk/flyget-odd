package flyget;

import java.io.Serializable;

public class Plane implements Serializable {
    protected String name;
    protected Seat[][] seats;
    protected static final String acb = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Plane() {

    }

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

    public boolean bookSeat(int row, int seat, Person passenger) {
        if (this.seats[row][seat].getBooked()) {
            return false;
        } else {
            this.seats[row][seat].book(passenger);
            return true;
        }
    }

    public boolean isSeatBooked(int row, int seat) {
        return this.seats[row][seat].getBooked();
    }

    public boolean isSeatDead(int row, int seat) {
        return this.seats[row][seat] instanceof DeadSeat;
    }

    public String getSeatMap() {
        int rowIndex = 1;
        char[] seatIndex = Plane.acb.substring(0, this.seats[0].length).toCharArray();
        String seatMap = "    ";
        for (char c : seatIndex) {
            seatMap = seatMap + c + "  ";
        }
        seatMap = seatMap + '\n';

        for (Seat[] row : this.seats) {
            if (rowIndex < 10) {
                seatMap = seatMap + " ";
            }
            seatMap = seatMap + rowIndex++ + " ";
            for (Seat seat : row) {
                seatMap = seatMap + seat.toString();
            }
            seatMap = seatMap + '\n';
        }
        return seatMap;
    }

    public void listPassengers() {
        for (Seat[] row : this.seats) {
            for (Seat seat : row) {
                Person passenger = seat.getPassenger();
                if (passenger != null) {
                    System.out.println(passenger.getFullName() + " " + passenger.getBirthDate());
                }
            }
        }
    }

    public static int seatLetterToIndex(String s) {
        return Plane.acb.indexOf(s.toUpperCase());
    }

    public static String seatIndexToLetter(int x) {
        return Plane.acb.substring(x, x + 1);
    }

    public Seat[][] getSeats() {
        return seats;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return this.seats.length;
    }
}
