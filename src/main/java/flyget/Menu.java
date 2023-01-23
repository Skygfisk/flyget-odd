package flyget;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    public static Scanner sc = new Scanner(System.in);

    private static int[] getSeatFromUser() {
        while (true) {
            try {
                System.out.print("Enter seat to book: ");
                String[] input = sc.nextLine().trim().split("\s");
                int row = Integer.parseInt(input[0]) - 1;
                int seat = Plane.seatLetterToIndex(input[1]);
                return new int[] { row, seat };
            } catch (Exception e) {
                System.out.println("Invalid Seat");
                continue;
            }

        }

    }

    public static Person getPersonFromUser() {
        String[] strArr = getFullNameFromUser();
        LocalDate birthDate = getBirDateFromUser();
        Person person = new Person(strArr[0], strArr[strArr.length - 1], birthDate);
        System.out.println(person.getFirstName() + '\n' + person.getLastName() + '\n' + person.getBirthDate());
        return person;
    }

    private static String[] getFullNameFromUser() {
        while (true) {
            try {
                System.out.print("Enter full name: ");
                String[] strArr = sc.nextLine().trim().split("\\s");
                if (strArr.length < 2) {
                    throw new Exception();
                }
                return strArr;
            } catch (Exception e) {
                System.out.println("Invalit input");
                continue;
            }

        }
    }

    private static LocalDate getBirDateFromUser() {
        while (true) {
            try {
                System.out.print("Enter birthdate (yyyy/mm/dd): ");
                String str = sc.nextLine().trim().replaceAll("[^0-9]", "\s");
                String[] strArr = str.split("\s");
                int year = Integer.parseInt(strArr[0]);
                int month = Integer.parseInt(strArr[1]);
                int day = Integer.parseInt(strArr[2]);
                return LocalDate.of(year, month, day);
            } catch (Exception e) {
                System.out.println("Invalid birthdate");
                continue;
            }

        }
    }

    public static void bookSeatOnPlane(Plane plane) {
        while (true) {
            try {
                int[] input = Menu.getSeatFromUser();
                int row = input[0];
                int seat = input[1];
                if (plane.bookSeat(row, seat)) {
                    System.out.println(
                            String.format("Seat: %s %S is now booked", row + 1, Plane.seatIndexToLetter(seat)));
                    break;
                }
                System.out.println("Seat is already booked");
            } catch (Exception e) {
                System.out.println("Seat dosen't exist on this plane");
                continue;
            }
        }
    }

    public static void main(String[] args) {

    }
}
