package flyget;

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
