class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;       // refers to instance variable
        this.name = name;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
    }

    public static void main(String[] args) {
        System.out.println("qNum: Q1");
        System.out.println("devName: Ritesh Dhekane");
        Student s = new Student(101, "Snehal");
        s.display();
    }
}
