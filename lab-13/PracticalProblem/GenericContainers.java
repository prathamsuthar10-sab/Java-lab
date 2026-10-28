class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

class Pair<A, B> {
    private final A first;
    private final B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}

public class GenericContainers {
    public static <T> T firstElement(T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }
        return array[0];
    }

    public static void main(String[] args) {
        Box<String> textBox = new Box<>();
        textBox.set("Hello");

        Box<Integer> numberBox = new Box<>();
        numberBox.set(42);

        Pair<String, Integer> student = new Pair<>("Asha", 20);

        String[] names = {"Maya", "Ravi", "Noah"};
        Integer[] numbers = {10, 20, 30};

        String firstName = firstElement(names);
        Integer firstNumber = firstElement(numbers);

        System.out.println("Text box: " + textBox.get());
        System.out.println("Number box: " + numberBox.get());
        System.out.println("Pair: " + student.getFirst() + ", " + student.getSecond());
        System.out.println("First name: " + firstName);
        System.out.println("First number: " + firstNumber);
    }
}