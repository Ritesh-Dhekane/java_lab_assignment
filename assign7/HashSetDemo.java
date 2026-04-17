// Q31: HashSet to store unique student roll numbers

import java.util.*;

public class HashSetDemo {

    public static void main(String[] args) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q31: HashSet Unique Roll Numbers");

        HashSet<Integer> set = new HashSet<>();

        // Adding elements (duplicates included)
        set.add(101);
        set.add(102);
        set.add(103);
        set.add(101); // duplicate
        set.add(102); // duplicate

        System.out.println("Unique Roll Numbers:");
        System.out.println(set);
    }
}