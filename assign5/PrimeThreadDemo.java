// Q21: Thread to display prime numbers between 1 to 500 after every 3 seconds

class PrimeThread extends Thread {

    boolean isPrime(int n) {
        if (n < 2) return false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public void run() {

        for (int i = 1; i <= 500; i++) {

            if (isPrime(i)) {
                System.out.println(i);

                try {
                    Thread.sleep(3000); // 3 seconds
                } catch (Exception e) { }
            }
        }
    }
}

public class PrimeThreadDemo {

    public static void main(String[] args) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q21: Prime Numbers Thread");

        PrimeThread t = new PrimeThread();
        t.start();
    }
}