package flyget;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    public static Scanner sc = new Scanner(System.in);
    public static Plane plane;
    public static String MENU_ART = """
            1: Creat Plane
            2: Load Plane
            3: Book Seat
            4: List passenger
            """;

    public static int[] getSeatFromUser() {
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

    public static String[] getFullNameFromUser() {
        while (true) {
            try {
                System.out.print("Enter full name: ");
                String[] strArr = sc.nextLine().trim().split("\\s");
                if (strArr.length < 2) {
                    throw new Exception();
                }
                return strArr;
            } catch (Exception e) {
                System.out.println("Invalid name");
                continue;
            }

        }
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
            try {
                int[] input = Menu.getSeatFromUser();
                int row = input[0];
                int seat = input[1];
                if (!plane.isSeatBooked(row, seat)) {
                    Person user = getPersonFromUser();
                    plane.bookSeat(row, seat, user);
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

    static void menu_choices(int i) throws IOException, ClassNotFoundException {
        switch (i) {
            case 1:
                System.out.println("Generating new Plane");
                plane = new SmallPlane();
                break;
            case 2:
                System.out.println("Load Plane from file");
                System.out.println("TODO");
                // App.mainHotel = (MyHotel) Writer.readFromFile(file);
                break;
            case 3:
                if (plane != null) {
                    bookSeatOnPlane(plane);
                }
                break;
            case 4:
                if (plane != null) {
                    System.out.println(plane.listPassengers());
                }
                break;
            default:
                System.out.println("Unknown selection");
        }
    }

    public static void main(String[] args) {
        try {
            while (true) {
                System.out.println(MENU_ART);
                System.out.print("Enter a int 1-4 to choice or 'exit' to quit: ");
                if (sc.hasNextInt()) {
                    int choice = Integer.valueOf(sc.nextLine());
                    menu_choices(choice);
                } else {
                    if (sc.nextLine().equals("exit")) {
                        break;
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}
