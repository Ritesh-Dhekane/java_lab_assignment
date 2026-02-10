class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    double add(int a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println("qNum: Q2");
        System.out.println("devName: Ritesh Dhekane");
        Calculator c = new Calculator();

        System.out.println("Addition of 2 ints: " + c.add(10, 20));
        System.out.println("Addition of 2 doubles: " + c.add(10.5, 20.5));
        System.out.println("Addition of 3 ints: " + c.add(1, 2, 3));
        System.out.println("Addition of int and double: " + c.add(5, 2.5));
    }
}
