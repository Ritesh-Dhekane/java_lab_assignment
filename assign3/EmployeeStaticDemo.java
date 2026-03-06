// Q15: Demonstrate static object counter and toString()

class Employee {

    String name;
    String designation;
    double salary;

    static int count = 0;

    Employee() {
        name = "Unknown";
        designation = "Unknown";
        salary = 0;
        count++;
    }

    Employee(String name, String designation, double salary) {

        this.name = name;
        this.designation = designation;
        this.salary = salary;
        count++;
    }

    public String toString() {

        return "Name: " + name +
                ", Designation: " + designation +
                ", Salary: " + salary;
    }

    static void displayCount() {

        System.out.println("Total Employees Created: " + count);
    }

    public static void main(String[] args) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q15: Static Object Counter");

        Employee e1 = new Employee("Ravi", "Manager", 50000);
        System.out.println(e1);
        Employee.displayCount();

        Employee e2 = new Employee("Amit", "Developer", 40000);
        System.out.println(e2);
        Employee.displayCount();

        Employee e3 = new Employee("Sneha", "HR", 35000);
        System.out.println(e3);
        Employee.displayCount();
    }
}