package flyget;

import static org.junit.Assert.*;
import org.junit.Test;

import java.time.LocalDate;

public class PlaneTest {

    @Test
    public void shouldReturnA() {
        String c = Plane.seatIndexToLetter(0);
        assertEquals("A", c);
    }

    @Test
    public void shouldReturnEmptyStringOnOutOfBoundIndex() {
        String c = Plane.seatIndexToLetter(100);
        assertEquals("", c);
    }

    @Test
    public void shouldReturnLetterAtAbsolutIndex() {
        String c = Plane.seatIndexToLetter(-3);
        assertEquals("D", c);
    }

    @Test
    public void shouldReturn2() {
        int x = Plane.seatLetterToIndex("c");
        assertEquals(2, x);
    }

    @Test
    public void shouldReturnNegativOneOnNonLetterInput() {
        int x = Plane.seatLetterToIndex("%");
        assertEquals(-1, x);
    }

    @Test
    public void shouldReturnJustifyedStringOfPassengers() {
        Person a = new Person("Adam", "Andersson", LocalDate.of(1991, 01, 01));
        Person b = new Person("Bertil", "Bengtsson", LocalDate.of(1992, 02, 02));
        Person c = new Person("Caesar", "Julius", LocalDate.of(1993, 03, 03));
        Plane plane = new Plane(3);
        plane.bookSeat(0, 0, a);
        plane.bookSeat(0, 1, b);
        plane.bookSeat(0, 2, c);
        String passengerList = plane.listPassengers();
        String expectedList = """
                Adam Andersson      1991-01-01
                Bertil Bengtsson    1992-02-02
                Caesar Julius       1993-03-03
                """;
        assertEquals(expectedList, passengerList);
    }

    @Test
    public void shouldReturnEmptyStringOnNOPassengers() {
        Plane plane = new Plane(3);
        String passengerList = plane.listPassengers();
        assertEquals("", passengerList);
    }

    @Test
    public void shouldReturnFalseOnEmptySeat() throws Exception {
        Plane plane = new Plane(3);
        assertFalse("", plane.isSeatBooked(0, 0));
    }

    @Test
    public void shouldReturnTrueOnBookedSeat() throws Exception {
        Plane plane = new Plane(3);
        Person a = new Person("Adam", "Andersson", LocalDate.of(1991, 01, 01));
        plane.bookSeat(0, 0, a);
        assertTrue("", plane.isSeatBooked(0, 0));
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionOnOutOfBoundsSeat() throws Exception {
        Plane plane = new Plane(3);
        plane.isSeatBooked(5, 5);
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionOnDeadSeat() throws Exception {
        Plane plane = new SmallPlane();
        plane.isSeatBooked(0, 0);
    }
}
