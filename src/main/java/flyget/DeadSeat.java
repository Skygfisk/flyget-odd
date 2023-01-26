package flyget;

/**
 * Helper class to be able to print seatMaps with
 * missing seats
 */
public class DeadSeat extends Seat {

    @Override
    public String toString() {
        return "   ";
    }
}
