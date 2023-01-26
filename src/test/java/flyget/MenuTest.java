package flyget;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;

import org.junit.Test;

/*
 * WARNING
 * RUN EACH TEST SEPARATE
 * VS CODE WILL INFINITE LOOP IF ALL TEST ARE RUN AT THE SAME TIME
 */
public class MenuTest {

    @Test
    public void shouldReturnValidSeatIndex() {
        InputStream sysInBackup = System.in; // backup System.in
        ByteArrayInputStream in = new ByteArrayInputStream("1 a".getBytes());
        System.setIn(in);

        int[] input = Menu.getSeatFromUser();
        int[] expected = { 0, 0 };
        assertArrayEquals(expected, input);

        System.setIn(sysInBackup); // restor System.in
    }

    @Test
    public void shouldNotCrashOnInvalidSeatIndex() {
        InputStream sysInBackup = System.in; // backup System.in
        String userInput = String.format("hello%s1 a",
                System.lineSeparator());
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        int[] input = Menu.getSeatFromUser();
        int[] expected = { 0, 0 };
        assertArrayEquals(expected, input);

        System.setIn(sysInBackup); // restor System.in
    }

    @Test
    public void shouldReturnStringArrayOfFullName() {
        InputStream sysInBackup = System.in; // backup System.in
        ByteArrayInputStream in = new ByteArrayInputStream("Adam Andersson".getBytes());
        System.setIn(in);

        String[] input = Menu.getFullNameFromUser();
        String[] expected = { "Adam", "Andersson" };
        assertArrayEquals(expected, input);

        System.setIn(sysInBackup); // restor System.in
    }

    @Test
    public void shouldNotCrashOnInvalidFullName() {
        InputStream sysInBackup = System.in; // backup System.in
        String userInput = String.format("HELLO%sAdam Andersson",
                System.lineSeparator());
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        String[] input = Menu.getFullNameFromUser();
        String[] expected = { "Adam", "Andersson" };
        assertArrayEquals(expected, input);

        System.setIn(sysInBackup); // restor System.in
    }

    @Test
    public void shouldReturnLocalDateOfBirthdate() {
        InputStream sysInBackup = System.in; // backup System.in
        ByteArrayInputStream in = new ByteArrayInputStream("1990-01-01".getBytes());
        System.setIn(in);

        LocalDate input = Menu.getBirDateFromUser();
        LocalDate expected = LocalDate.of(1990, 01, 01);
        assertEquals(expected, input);

        System.setIn(sysInBackup); // restor System.in
    }

    @Test
    public void shouldNotCrashOnInvalidBirthdate() {
        InputStream sysInBackup = System.in; // backup System.in
        String userInput = String.format("HELLO%s1990-01-01",
                System.lineSeparator());
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        LocalDate input = Menu.getBirDateFromUser();
        LocalDate expected = LocalDate.of(1990, 01, 01);
        assertEquals(expected, input);

        System.setIn(sysInBackup); // restor System.in
    }
}
