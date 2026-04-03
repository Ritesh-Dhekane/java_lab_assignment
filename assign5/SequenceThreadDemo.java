// Q25: Use synchronized to control execution order

class PrintNumbers {

    synchronized void printFiveSeries() {

        for (int i = 1; i <= 5; i++) {
            System.out.println(i * 5);
        }
    }

    synchronized void printHundredSeries() {

        for (int i = 1; i <= 5; i++) {
            System.out.println(i * 100);
        }
    }
}

class ThreadA extends Thread {
    PrintNumbers p;

    ThreadA(PrintNumbers p) {
        this.p = p;
    }

    public void run() {
        p.printFiveSeries();
    }
}

class ThreadB extends Thread {
    PrintNumbers p;

    ThreadB(PrintNumbers p) {
        this.p = p;
    }

    public void run() {
        p.printHundredSeries();
    }
}

public class SequenceThreadDemo {

    public static void main(String[] args) throws Exception {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q25: Synchronized Sequence Control");

        PrintNumbers obj = new PrintNumbers();

        ThreadA t1 = new ThreadA(obj);
        ThreadB t2 = new ThreadB(obj);

        t1.start();
        t1.join();  // ensures order

        t2.start();
    }
}