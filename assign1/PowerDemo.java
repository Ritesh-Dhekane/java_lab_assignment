interface Power {
    double calculate(double x, double y);
}

public class PowerDemo {
    public static void main(String[] args) {
        System.out.println("qNum: Q4");
        System.out.println("devName: Ritesh Dhekane");

        Power p = (x, y) -> Math.pow(x, y);
        System.out.println("Power: " + p.calculate(2, 3));
    }
}
