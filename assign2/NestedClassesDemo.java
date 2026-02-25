// Q9: Write programs to explain all types of nested and inner classes.

class Outer {

    // 1. Static Nested Class
    static class StaticNested {
        void display() {
            System.out.println("Static Nested Class");
        }
    }

    // 2. Non-static Inner Class
    class Inner {
        void display() {
            System.out.println("Non-static Inner Class");
        }
    }

    void showLocal() {

        // 3. Local Inner Class
        class LocalInner {
            void display() {
                System.out.println("Local Inner Class");
            }
        }

        LocalInner li = new LocalInner();
        li.display();
    }

    public static void main(String[] args) {

        System.out.println("devName: Ritesh Dhekane");
        System.out.println("Q9: Nested and Inner Classes");

        // Static Nested
        Outer.StaticNested sn = new Outer.StaticNested();
        sn.display();

        // Non-static Inner
        Outer o = new Outer();
        Outer.Inner in = o.new Inner();
        in.display();

        // Local Inner
        o.showLocal();

        // 4. Anonymous Inner Class
        Runnable r = new Runnable() {
            public void run() {
                System.out.println("Anonymous Inner Class");
            }
        };
        r.run();
    }
}