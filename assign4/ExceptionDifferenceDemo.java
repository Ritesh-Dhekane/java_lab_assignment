// Q20: Checked vs Unchecked Exception Example

import java.io.*;

public class ExceptionDifferenceDemo {

    public static void main(String args[]) {

        System.out.println("devName: Aakanksha Mane");
        System.out.println("Q20: Checked vs Unchecked Exception");

        try {

            int a = 10 / 0; // Unchecked exception

        } catch (ArithmeticException e) {

            System.out.println("Unchecked Exception: " + e);
        }
    }
}