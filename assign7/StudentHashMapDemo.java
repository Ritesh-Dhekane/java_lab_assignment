// Q35: HashMap to store student roll number and name

import java.util.*;

public class StudentHashMapDemo {

    public static void main(String[] args) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q35: HashMap Student Data");

        HashMap<Integer, String> map = new HashMap<>();

        // Insert data
        map.put(101, "Ritesh");
        map.put(102, "Amit");
        map.put(103, "Sneha");

        System.out.println("Student Details:");

        // Display data
        for (Map.Entry<Integer, String> entry : map.entrySet()) {

            System.out.println("Roll No: " + entry.getKey() +
                               " Name: " + entry.getValue());
        }
    }
}