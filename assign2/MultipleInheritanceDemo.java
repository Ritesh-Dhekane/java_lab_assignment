// Q6: Explain multiple inheritance using interface with example.

interface Father {
    void showFather();
}

interface Mother {
    void showMother();
}

class Child implements Father, Mother {

    public void showFather() {
        System.out.println("Father's qualities inherited");
    }

    public void showMother() {
        System.out.println("Mother's qualities inherited");
    }

    public static void main(String[] args) {
        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q6: Multiple Inheritance using Interface");

        Child c = new Child();
        c.showFather();
        c.showMother();
    }
}