// Q13: Student array sorting using static method based on percentage

import java.util.Scanner;

class Student {

    String name;
    int roll_no;
    String className;
    int marks[] = new int[6];
    double percentage;

    void accept(Scanner sc) {

        System.out.print("Name: ");
        name = sc.nextLine();

        System.out.print("Roll No: ");
        roll_no = sc.nextInt();
        sc.nextLine();

        System.out.print("Class: ");
        className = sc.nextLine();

        System.out.println("Enter marks of 6 subjects:");

        int total = 0;

        for (int i = 0; i < 6; i++) {
            marks[i] = sc.nextInt();
            total += marks[i];
        }

        percentage = total / 6.0;
    }

    static void sortStudent(Student s[]) {

        for (int i = 0; i < s.length - 1; i++) {
            for (int j = i + 1; j < s.length; j++) {

                if (s[i].percentage < s[j].percentage) {

                    Student temp = s[i];
                    s[i] = s[j];
                    s[j] = temp;
                }
            }
        }
    }

    void display() {

        System.out.println(name + " " + roll_no +
                " " + className + " Percentage: " + percentage);
    }

    public static void main(String[] args) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q13: Student sorting by percentage");

        Scanner sc = new Scanner(System.in);

        Student s[] = new Student[5];

        for (int i = 0; i < 5; i++) {
            s[i] = new Student();
            s[i].accept(sc);
        }

        Student.sortStudent(s);

        System.out.println("\nSorted Student List:");

        for (Student st : s) {
            st.display();
        }
    }
}