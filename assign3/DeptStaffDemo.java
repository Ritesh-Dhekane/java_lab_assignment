// Q14: Staff, TeachingStaff and NonTeachingStaff using inheritance

class Staff {

    int id;
    String name;
    String DOB;
    String joining_date;
    double salary;

    void displayStaff() {

        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("DOB: " + DOB);
        System.out.println("Joining Date: " + joining_date);
        System.out.println("Salary: " + salary);
    }
}

class TeachingStaff extends Staff {

    String subjects[];
    int experience;

    TeachingStaff(int id, String name, String DOB,
                  String joining_date, double salary,
                  String subjects[], int experience) {

        this.id = id;
        this.name = name;
        this.DOB = DOB;
        this.joining_date = joining_date;
        this.salary = salary;

        this.subjects = subjects;
        this.experience = experience;
    }

    void display() {

        displayStaff();

        System.out.print("Subjects: ");

        for (String s : subjects) {
            System.out.print(s + " ");
        }

        System.out.println("\nExperience: " + experience);
    }
}

class NonTeachingStaff extends Staff {

    String department;
    String shift;

    NonTeachingStaff(int id, String name, String DOB,
                     String joining_date, double salary,
                     String department, String shift) {

        this.id = id;
        this.name = name;
        this.DOB = DOB;
        this.joining_date = joining_date;
        this.salary = salary;

        this.department = department;
        this.shift = shift;
    }

    void display() {

        displayStaff();

        System.out.println("Department: " + department);
        System.out.println("Shift: " + shift);
    }
}

public class DeptStaffDemo {

    public static void main(String[] args) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q14: Department Staff Details");

        Staff DeptStaff[] = new Staff[2];

        DeptStaff[0] = new TeachingStaff(
                1, "Ravi", "01-01-1980",
                "2010", 50000,
                new String[]{"Java", "DBMS"}, 10
        );

        DeptStaff[1] = new NonTeachingStaff(
                2, "Amit", "05-05-1985",
                "2015", 30000,
                "Admin", "Morning"
        );

        for (Staff s : DeptStaff) {

            if (s instanceof TeachingStaff)
                ((TeachingStaff) s).display();

            else
                ((NonTeachingStaff) s).display();

            System.out.println("------------------");
        }
    }
}