// Q24: Thread to display perfect numbers between 1 to 1000 after every 5 seconds

class PerfectThread extends Thread {

    boolean isPerfect(int n) {

        int sum = 0;

        for (int i = 1; i < n; i++) {
            if (n % i == 0)
                sum += i;
        }

        return sum == n;
    }

    public void run() {

        for (int i = 1; i <= 1000; i++) {

            if (isPerfect(i)) {

                System.out.println("Perfect Number: " + i);

                try {
                    Thread.sleep(5000); // 5 sec
                } catch (Exception e) { }
            }
        }
    }
}

public class PerfectNumberThreadDemo {

    public static void main(String[] args) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q24: Perfect Numbers Thread");

        PerfectThread t = new PerfectThread();
        t.start();
    }
}