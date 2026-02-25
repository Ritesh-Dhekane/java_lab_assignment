// Q7: Create an abstract class Shape with method area(). 
// Inherit this class creating Square class. Display area of square.

abstract class Shape {
    abstract double area();
}

class Square extends Shape {
    double side;

    Square(double side) {
        this.side = side;
    }

    double area() {
        return side * side;
    }

    public static void main(String[] args) {
        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q7: Abstract Class - Shape and Square");

        Square s = new Square(4);
        System.out.println("Area of Square: " + s.area());
    }
}