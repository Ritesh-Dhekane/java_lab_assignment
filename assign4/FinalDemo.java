// Q16: Demonstration of final variable, method and class

final class FinalClass {

    final int number = 10;   // final data member

    final void display() {   // final method
        System.out.println("Final method executed");
        System.out.println("Final variable value: " + number);
    }
}

public class FinalDemo {

    public static void main(String[] args) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q16: Demonstration of final keyword");

        FinalClass obj = new FinalClass();
        obj.display();
    }
}