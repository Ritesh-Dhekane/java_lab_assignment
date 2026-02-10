class Person {
    String name;
    int age;

    void setPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Manager extends Person {
    double salary;

    void setManager(double salary) {
        this.salary = salary;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: " + salary);
    }

    public static void main(String[] args) {
        System.out.println("qNum: Q3");
        System.out.println("devName: Ritesh Dhekane");
        Manager m = new Manager();
        m.setPerson("Ramesh", 35);
        m.setManager(50000);
        m.display();
    }
}
