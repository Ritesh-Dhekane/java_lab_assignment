// Q30: HashMap example with insertion, search and iteration

import java.util.*;

public class HashMapDemo {

    public static void main(String[] args) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q30: HashMap Operations");

        HashMap<Integer, String> map = new HashMap<>();

        // Insertion
        map.put(101, "Ritesh");
        map.put(102, "Amit");
        map.put(103, "Sneha");

        System.out.println("After Insertion: " + map);

        // Searching
        int key = 102;

        if (map.containsKey(key)) {
            System.out.println("Search Result: " + map.get(key));
        }

        // Iteration
        System.out.println("Iterating over entries:");

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}