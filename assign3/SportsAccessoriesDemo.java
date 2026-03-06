// Q12: Store accessories in array of objects and display cricket accessories

import java.util.Scanner;

class sports_accessories {

    int accessory_id;
    String description;
    int quantity;
    double rate;
    String used_in_game;

    void accept(Scanner sc) {

        System.out.print("Accessory ID: ");
        accessory_id = sc.nextInt();
        sc.nextLine();

        System.out.print("Description: ");
        description = sc.nextLine();

        System.out.print("Quantity: ");
        quantity = sc.nextInt();

        System.out.print("Rate: ");
        rate = sc.nextDouble();
        sc.nextLine();

        System.out.print("Used in Game: ");
        used_in_game = sc.nextLine();
    }

    void display() {
        System.out.println(accessory_id + " " + description + " " + quantity +
                " " + rate + " " + used_in_game);
    }

    public static void main(String[] args) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q12: Accessories used in Cricket");

        Scanner sc = new Scanner(System.in);

        sports_accessories[] arr = new sports_accessories[5];

        for (int i = 0; i < 5; i++) {
            arr[i] = new sports_accessories();
            System.out.println("\nEnter Accessory " + (i + 1));
            arr[i].accept(sc);
        }

        System.out.println("\nAccessories used in Cricket:");

        for (sports_accessories s : arr) {

            if (s.used_in_game.equalsIgnoreCase("cricket")) {
                s.display();
            }
        }
    }
}