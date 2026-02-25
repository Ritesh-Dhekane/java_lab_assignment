// Q10: Write a Java class Employee that demonstrates constructor overloading
// using this() constructor and display all details.

class Employee {

    int ecode;
    String ename;
    double salary;
    String desg;
    String mobile;
    String email;

    // Constructor 1
    Employee(int ecode, String ename) {
        this.ecode = ecode;
        this.ename = ename;
    }

    // Constructor 2
    Employee(int ecode, String ename, double salary) {
        this(ecode, ename);   // calling constructor 1
        this.salary = salary;
    }

    // Constructor 3
    Employee(int ecode, String ename, double salary, String desg) {
        this(ecode, ename, salary);  // calling constructor 2
        this.desg = desg;
    }

    // Constructor 4
    Employee(int ecode, String ename, double salary, String desg,
             String mobile, String email) {
        this(ecode, ename, salary, desg);  // calling constructor 3
        this.mobile = mobile;
        this.email = email;
    }

    void dispDetails() {
        System.out.println("Employee Code: " + ecode);
        System.out.println("Name: " + ename);
        System.out.println("Salary: " + salary);
        System.out.println("Designation: " + desg);
        System.out.println("Mobile: " + mobile);
        System.out.println("Email: " + email);
        System.out.println("--------------------------------");
    }

    public static void main(String[] args) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q10: Constructor Overloading using this()");

        Employee e1 = new Employee(101, "Amit");
        Employee e2 = new Employee(102, "Rahul", 40000);
        Employee e3 = new Employee(103, "Sneha", 50000, "Manager");
        Employee e4 = new Employee(104, "Priya", 60000, "HR",
                "9876543210", "priya@gmail.com");

        e1.dispDetails();
        e2.dispDetails();
        e3.dispDetails();
        e4.dispDetails();
    }
}