// Q19: User defined exception InsufficientFundException

import java.util.Scanner;

class InsufficientFundException extends Exception {

    InsufficientFundException(String message) {
        super(message);
    }
}

public class InsufficientFundDemo {

    public static void main(String args[]) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q19: InsufficientFundException Example");

        Scanner sc = new Scanner(System.in);

        double balance = 5000;

        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();

        try {

            if (amount > balance) {
                throw new InsufficientFundException("Insufficient Balance");
            }

            balance -= amount;
            System.out.println("Withdrawal successful");
            System.out.println("Remaining Balance: " + balance);

        } catch (InsufficientFundException e) {

            System.out.println("Exception: " + e.getMessage());
            System.out.println("Available balance: " + balance);
        }
    }
}