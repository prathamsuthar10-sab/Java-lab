public class Driver {

    public static void main(String[] args) {

        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 4);
        Fraction f3 = new Fraction(3, 6);
        Fraction f4 = new Fraction(10, 2);

        System.out.println("Fractions:");
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);
        System.out.println(f4);

        System.out.println();

        System.out.println("Equality:");
        System.out.println("f1 equals f2: " + f1.equals(f2));
        System.out.println("f2 equals f3: " + f2.equals(f3));
        System.out.println("f1 equals f3: " + f1.equals(f3));
    }
}
