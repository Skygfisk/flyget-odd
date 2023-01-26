package flyget;

/**
 * Sub class of Plane with a specific Seat layout
 */
public class BigPlane extends Plane {
    private static int ROW_MAX = 15;
    private static int SEAT_MAX = 9;

    public BigPlane() {
        this.seats = generateSeats();
    }

    private Seat[][] generateSeats() {
        Seat[][] newSeats = new Seat[ROW_MAX][SEAT_MAX];
        for (int rowIndex = 0; rowIndex < newSeats.length; rowIndex++) {
            for (int seatIndex = 0; seatIndex < newSeats[rowIndex].length; seatIndex++) {
                newSeats[rowIndex][seatIndex] = new Seat();
            }
        }
        newSeats[0][0] = new DeadSeat();
        newSeats[0][8] = new DeadSeat();

        newSeats[ROW_MAX - 1][0] = new DeadSeat();
        newSeats[ROW_MAX - 1][1] = new DeadSeat();
        newSeats[ROW_MAX - 1][2] = new DeadSeat();

        newSeats[ROW_MAX - 1][6] = new DeadSeat();
        newSeats[ROW_MAX - 1][7] = new DeadSeat();
        newSeats[ROW_MAX - 1][8] = new DeadSeat();
        return newSeats;
    }

    @Override
    public String getSeatMap() {
        int rowIndex = 1;
        int seatIndex = 1;
        char[] seatLetter = Plane.acb.substring(0, this.seats[0].length).toCharArray();
        String seatMap = "    ";
        for (char c : seatLetter) {
            seatMap = seatMap + c + "  ";
            seatMap = seatMap + (seatIndex++ % 3 == 0 ? "   " : "");
        }
        seatIndex = 1;
        seatMap = seatMap + '\n';

        for (Seat[] row : this.seats) {
            if (rowIndex < 10) {
                seatMap = seatMap + " ";
            }
            seatMap = seatMap + rowIndex++ + " ";

            for (Seat seat : row) {
                seatMap = seatMap + seat.toString();
                seatMap = seatMap + (seatIndex++ % 3 == 0 ? "   " : "");
            }
            seatIndex = 1;
            seatMap = seatMap + '\n';
            seatMap = seatMap + ((rowIndex - 1) % 5 == 0 ? "\n" : "");

        }
        return seatMap;
    }
}
