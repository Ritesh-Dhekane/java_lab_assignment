// Q17: Throw InvalidAgeException if age < 60

class InvalidAgeException extends Exception {

    InvalidAgeException(String message) {
        super(message);
    }
}

public class InvalidAgeExceptionDemo {

    public static void main(String args[]) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q17: InvalidAgeException Example");

        try {

            String name = args[0];
            int age = Integer.parseInt(args[1]);

            if (age < 60) {
                throw new InvalidAgeException("Age below 60 is not allowed");
            }

            System.out.println("Name: " + name);
            System.out.println("Age: " + age);

        } catch (InvalidAgeException e) {

            System.out.println("Exception: " + e.getMessage());

        }
    }
}