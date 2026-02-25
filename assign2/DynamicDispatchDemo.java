// Q8: Write a Java program to explain Dynamic Method Dispatch.

class Animal {
    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}

public class DynamicDispatchDemo {

    public static void main(String[] args) {
        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q8: Dynamic Method Dispatch");

        Animal a = new Dog();   // Parent reference, child object
        a.sound();              // Runtime polymorphism
    }
}
