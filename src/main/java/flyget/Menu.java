package flyget;

import java.util.Scanner;

public class Menu {

    public static String getSeatFromUser() {
        try (Scanner sc = new Scanner(System.in);) {
            System.out.print("Enter seat to book: ");
            String[] strArr = sc.nextLine().trim().split("\\s");
            String str;
            if (strArr.length > 1) {
                str = strArr[0] + strArr[strArr.length - 1];
            } else {
                str = strArr[0];
            }
            return str;
        }

    }

    public static void main(String[] args) {

    }
}
