import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private int rollNumber;
    private String name;
    private transient String password;

    Student(int rollNumber, String name, String password) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.password = password;
    }

    void display() {
        System.out.println("Roll number: " + rollNumber);
        System.out.println("Name: " + name);
        System.out.println("Password: " + password);
    }
}

public class SerializationPractical {
    public static void main(String[] args) {
        Student[] students = {
            new Student(1, "Asha", "pass123"),
            new Student(2, "Ravi", "pass456")
        };

        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream("students.dat"))) {
            output.writeObject(students);
            System.out.println("Student objects saved.");
        } catch (IOException e) {
            System.out.println("Error saving objects: " + e.getMessage());
            return;
        }

        try (ObjectInputStream input = new ObjectInputStream(
                new FileInputStream("students.dat"))) {
            Student[] loadedStudents = (Student[]) input.readObject();

            System.out.println("\nStudent objects loaded:");
            for (Student student : loadedStudents) {
                student.display();
                System.out.println();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading objects: " + e.getMessage());
        }
    }
}