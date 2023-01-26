package flyget;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Main class of the program
 * handels user interface and user input
 */
public class Menu {
    public static Scanner sc = new Scanner(System.in);
    public static Plane plane;
    public static File file;
    public static String SAVE_DIRECTORY = "src\\main\\java\\flyget\\save\\";
    public static String MENU_ART = """

            1: Creat Plane
            2: Load Plane
            3: Book Seat
            4: List passenger
            5: Save Plane to File
            """;

    /**
     * Asks the user for a seat in the form of a
     * number (row) and letter (column)
     * and converts it in to a int array with
     * array indexes for the coresponding seat
     * will prompt the user for another input if input is invalid
     * 
     * @return a int array with seat index for the seat array
     */
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
        return new Person(strArr[0], strArr[strArr.length - 1], birthDate);
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

    /**
     * Tries to book a seat on the plane
     * with user input on what seat
     * and to what person
     * 
     * @param plane the plane to book the seat on
     */
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

    static void menuChoices(int i) throws IOException, ClassNotFoundException {
        switch (i) {
            case 1:
                System.out.println("1: Creat BigPlane");
                System.out.println("2: Creat SmallPlane");
                if (sc.hasNextInt()) {
                    int choice = Integer.valueOf(sc.nextLine());
                    if (choice == 1) {
                        plane = new BigPlane();
                        System.out.println("New BigPlane has been created");
                    } else if (choice == 2) {
                        plane = new SmallPlane();
                        System.out.println("New SmallPlane has been created");
                    }
                }
                break;
            case 2:
                try {
                    System.out.print("Enter file to load: ");
                    String input = sc.nextLine();
                    file = Paths.get(SAVE_DIRECTORY + input).toFile();

                    plane = (Plane) Writer.readFromFile(file);
                    System.out.println("Plane has been loaded from file");
                    break;
                } catch (Exception e) {
                    System.out.println("File not found");
                }
                break;
            case 3:
                if (plane != null) {
                    System.out.println(plane.getSeatMap());
                    bookSeatOnPlane(plane);
                } else {
                    System.out.println("No Plane is loaded");
                }
                break;
            case 4:
                if (plane != null) {
                    System.out.println(plane.listPassengers());
                } else {
                    System.out.println("No Plane is loaded");
                }
                break;
            case 5:
                try {
                    System.out.print("Enter name to save as: ");
                    String input = sc.nextLine();
                    file = Paths.get(SAVE_DIRECTORY + input).toFile();

                    Writer.writeToFile(file, plane);
                    System.out.println("Successfully saved Plane to file " + input);
                    break;
                } catch (Exception e) {
                    System.out.println("Could not save to file");
                }
                break;
            default:
                System.out.println("Unknown selection");
        }
    }

    public static void main(String[] args) throws IOException {
        if (Files.notExists(Paths.get(SAVE_DIRECTORY))) {
            Files.createDirectory(Paths.get(SAVE_DIRECTORY));
        }

        try {
            while (true) {
                System.out.println(MENU_ART);
                System.out.print("Enter a int 1-4 to choice or 'exit' to quit: ");
                if (sc.hasNextInt()) {
                    int choice = Integer.valueOf(sc.nextLine());
                    menuChoices(choice);
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
