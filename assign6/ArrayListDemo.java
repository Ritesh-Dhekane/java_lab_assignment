// Q26: ArrayList operations - add, remove and sort

import java.util.*;

public class ArrayListDemo {

    public static void main(String[] args) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q26: ArrayList Operations");

        ArrayList<String> list = new ArrayList<>();

        // Add elements
        list.add("Banana");
        list.add("Apple");
        list.add("Mango");
        list.add("Orange");

        System.out.println("After Adding: " + list);

        // Remove element
        list.remove("Mango");
        System.out.println("After Removing Mango: " + list);

        // Sort list
        Collections.sort(list);
        System.out.println("After Sorting: " + list);
    }
}