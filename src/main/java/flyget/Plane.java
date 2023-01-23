package flyget;

import java.io.Serializable;

public class Plane implements Serializable {
    private String name;
    private Seat[][] seats;
    public static final String acb = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

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

    public void printPlane() {
        int rowIndex = 1;
        char[] seatIndex = Plane.acb.substring(0, this.seats.length).toCharArray();
        String str = "   ";
        for (char c : seatIndex) {
            str = str + c + "  ";
        }
        System.out.println(str);

        for (Seat[] row : this.seats) {
            System.out.print(rowIndex++ + " ");
            for (Seat seat : row) {
                char c = ((seat.getBooked()) ? 'X' : ' ');
                System.out.print("[" + c + "]");
            }
            System.out.println();
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
