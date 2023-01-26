package flyget;

import java.io.Serializable;

/**
 * Class to represent a Plane with rows of seats
 */
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

    /**
     * Books a seat on the plane to a person
     * 
     * @param row       the row index if the seat in the plane
     * @param seat      the seat index if the seat in the plane
     * @param passenger the person to book the seat to
     * @return true if seat booked successfully and false if seat is
     *         already booked
     */
    public boolean bookSeat(int row, int seat, Person passenger) {
        if (this.seats[row][seat].getBooked()) {
            return false;
        } else {
            this.seats[row][seat].book(passenger);
            return true;
        }
    }

    /**
     * Check of a given seat is booked
     * 
     * @param row  the row index if the seat in the plane
     * @param seat the seat index if the seat in the plane
     * @return true if seat is booked and false if it is not
     * @throws Exception will throw exeption if seat or row index is out
     *                   of bounds or is the seat is a DeadSeat
     */
    public boolean isSeatBooked(int row, int seat) throws Exception {
        if (this.seats[row][seat] instanceof DeadSeat) {
            throw new Exception();
        }
        return this.seats[row][seat].getBooked();
    }

    /**
     * Generates a visual representation of all the seats
     * and there booking status
     * 
     * @return a String of all seats booking status
     */
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

    /**
     * Generastes a String to be printed of all passengers on the plane
     * with there full name and birthdate
     * 
     * @return a String of all passengers
     */
    public String listPassengers() {
        String passengerList = "";
        for (Seat[] row : this.seats) {
            for (Seat seat : row) {
                Person passenger = seat.getPassenger();
                if (passenger != null) {
                    passengerList = passengerList + normalizRowLenght(passenger);
                }
            }
        }
        return passengerList;
    }

    private static String normalizRowLenght(Person p) {
        String str = "                    ";
        String name = p.getFullName();
        String birthDate = p.getBirthDate().toString();
        str = name + str.substring(name.length()) + birthDate + "\n";
        return str;
    }

    /**
     * Convert the visual letter representation of a seat
     * to its actual index in the seat row
     * 
     * @param s the letter of a seat in a row
     * @return index in the seat row array
     */
    public static int seatLetterToIndex(String s) {
        return Plane.acb.indexOf(s.toUpperCase());
    }

    /**
     * Convert the actual index of a seat to
     * the letter representation of the seat
     * 
     * @param x index in the seat row array
     * @return the letter representation of a seat in a row
     */
    public static String seatIndexToLetter(int x) {
        int index = Math.abs(x);
        try {
            return Plane.acb.substring(index, index + 1);
        } catch (Exception e) {
            return "";
        }
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
