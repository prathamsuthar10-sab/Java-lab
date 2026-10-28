import java.util.Arrays;
import java.util.EmptyStackException;

class GenericStack<T> {
    private Object[] elements;
    private int size;

    public GenericStack() {
        elements = new Object[10];
    }

    public void push(T item) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T item = (T) elements[--size];
        elements[size] = null;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}

public class GenericStackDemo {
    public static void main(String[] args) {
        GenericStack<String> names = new GenericStack<>();
        names.push("Maya");
        names.push("Ravi");

        GenericStack<Integer> numbers = new GenericStack<>();
        numbers.push(10);
        numbers.push(20);

        System.out.println("Name stack: " + names.pop() + ", " + names.pop());
        System.out.println("Number stack: " + numbers.pop() + ", " + numbers.pop());

        if (names.isEmpty()) {
            System.out.println("Name stack is empty.");
        }
    }
}