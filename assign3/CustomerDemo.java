// Q11: Person class with constructors and Customer subclass demonstrating super()

class Person {

    String aadhar;
    String name;
    String address;

    // Constructor 1
    Person() {
        aadhar = "Unknown";
        name = "Unknown";
        address = "Unknown";
    }

    // Constructor 2
    Person(String name) {
        this.name = name;
    }

    // Constructor 3
    Person(String aadhar, String name, String address) {
        this.aadhar = aadhar;
        this.name = name;
        this.address = address;
    }

    void displayPerson() {
        System.out.println("Aadhar: " + aadhar);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
    }
}

class Customer extends Person {

    int cust_code;
    String username;
    String password;
    String mobile;
    String email;

    Customer(String aadhar, String name, String address,
             int cust_code, String username, String password,
             String mobile, String email) {

        super(aadhar, name, address); // calling parent constructor

        this.cust_code = cust_code;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
    }

    void displayCustomer() {

        super.displayPerson(); // calling parent method

        System.out.println("Customer Code: " + cust_code);
        System.out.println("Username: " + username);
        System.out.println("Mobile: " + mobile);
        System.out.println("Email: " + email);
    }

    public static void main(String[] args) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q11: Demonstration of super() constructor");

        Customer c = new Customer(
                "123456789012",
                "Ritesh",
                "Pune",
                101,
                "ritesh123",
                "pass",
                "9876543210",
                "ritesh@email.com"
        );

        c.displayCustomer();
    }
}