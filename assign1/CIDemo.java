interface CompoundInterest {
    double calculate(double p, double r, double t);
}

public class CIDemo {
    public static void main(String[] args) {
        System.out.println("qNum: Q5");
        System.out.println("devName: Ritesh Dhekane");

        CompoundInterest ci =
                (p, r, t) -> p * Math.pow((1 + r / 100), t);

        System.out.println("Compound Interest: " + ci.calculate(10000, 5, 2));
    }
}
