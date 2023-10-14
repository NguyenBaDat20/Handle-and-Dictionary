package common;

import java.util.Scanner;

public class Validation {
    Scanner sc = new Scanner(System.in);
    public boolean checkInputYN() {
        System.out.print("Do you want to replace(Y/N)? ");
        //loop until user input correct
        while (true) {
            String result = sc.nextLine().trim();
            //return true if user input y/Y
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            //return false if user input n/N
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }
}
