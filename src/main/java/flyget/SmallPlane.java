package flyget;

public class SmallPlane extends Plane {

    private static int ROW_MAX = 10;
    private static int SEAT_MAX = 4;

    public SmallPlane() {
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
        newSeats[0][1] = new DeadSeat();
        return newSeats;
    }

    public String getSeatMap() {
        int rowIndex = 1;
        int seatIndex = 1;
        char[] seatLetter = Plane.acb.substring(0, this.seats[0].length).toCharArray();
        String seatMap = "    ";
        for (char c : seatLetter) {
            seatMap = seatMap + c + "  ";
            seatMap = seatMap + (seatIndex++ == 2 ? "   " : "");
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
                seatMap = seatMap + (seatIndex++ == 2 ? "   " : "");
            }
            seatIndex = 1;
            seatMap = seatMap + '\n';
            seatMap = seatMap + ((rowIndex - 1) % 5 == 0 ? "\n" : "");

        }
        return seatMap;
    }
}
