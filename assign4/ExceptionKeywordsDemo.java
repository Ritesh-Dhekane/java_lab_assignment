// Q18: Demonstrate try, catch, finally, throw and throws

public class ExceptionKeywordsDemo {

    static void checkNumber(int num) throws Exception {

        if (num < 0) {
            throw new Exception("Negative number not allowed");
        }

        System.out.println("Number: " + num);
    }

    public static void main(String args[]) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q18: Exception Handling Keywords");

        try {

            checkNumber(-5);

        } catch (Exception e) {

            System.out.println("Exception caught: " + e.getMessage());

        } finally {

            System.out.println("Finally block executed");
        }
    }
}