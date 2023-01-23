package flyget;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    public static Scanner sc = new Scanner(System.in);

    public static String getSeatFromUser() {
        String str;
        System.out.print("Enter seat to book: ");
        String[] strArr = sc.nextLine().trim().split("\\s");
        if (strArr.length > 1) {
            str = strArr[0] + strArr[strArr.length - 1];
        } else {
            str = strArr[0];
        }
        return str;
    }

    public static Person getPersonFromUser() {
        String[] strArr = getFullNameFromUser();
        LocalDate birthDate = getBirDateFromUser();
        Person person = new Person(strArr[0], strArr[1], birthDate);
        System.out.println(person.getFirstName() + '\n' + person.getLastName() + '\n' + person.getBirthDate());
        return person;
    }

    private static String[] getFullNameFromUser() {
        System.out.print("Enter full name: ");
        String[] strArr = sc.nextLine().trim().split("\\s");
        return strArr;
    }

    public static LocalDate getBirDateFromUser() {
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
            String input = Menu.getSeatFromUser();
            int row = Integer.parseInt(input.substring(0, 1)) - 1;
            int seat = Plane.acb.indexOf(input.toUpperCase().charAt(1));
            if (row >= plane.getSize() || seat >= plane.getSize()) {
                System.out.println("Seat dosen't exist on plane");
                continue;
            }
            if (plane.bookSeat(row, seat)) {
                System.out.println("Seat: " + input + " is now booked");
                break;
            }
            System.out.println("Seat is already booked");
        }
    }

    public static void main(String[] args) {

    }
}
