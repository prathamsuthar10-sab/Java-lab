import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.Scanner;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class Student {
    @Column(name = "student_id")
    private int id;

    @Column(name = "student_name")
    private String name;

    @Column(name = "marks")
    private double marks;

    public String toString() {
        return "Student ID: " + id
                + "\nStudent Name: " + name
                + "\nMarks: " + marks;
    }
}

class ObjectMapper {
    public static <T> T createObject(
            Class<T> className,
            String[] headers,
            String[] data) throws Exception {

        T object = className.getDeclaredConstructor().newInstance();
        Field[] fields = className.getDeclaredFields();

        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);

            if (column == null) {
                continue;
            }

            int columnIndex = -1;

            for (int i = 0; i < headers.length; i++) {
                if (headers[i].trim().equalsIgnoreCase(column.name())) {
                    columnIndex = i;
                    break;
                }
            }

            if (columnIndex == -1 || columnIndex >= data.length) {
                System.out.println(
                    "Missing column: " + column.name()
                    + ". Default value is used."
                );
                continue;
            }

            String value = data[columnIndex].trim();
            field.setAccessible(true);

            if (field.getType() == int.class) {
                field.setInt(object, Integer.parseInt(value));
            } else if (field.getType() == double.class) {
                field.setDouble(object, Double.parseDouble(value));
            } else if (field.getType() == String.class) {
                field.set(object, value);
            }
        }

        return object;
    }
}

public class ColumnMapper {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter column names separated by commas:");
        String[] headers = sc.nextLine().split(",");

        System.out.println("Enter data values separated by commas:");
        String[] data = sc.nextLine().split(",");

        Student student = ObjectMapper.createObject(
                Student.class, headers, data
        );

        System.out.println("\nMapped object:");
        System.out.println(student);

        sc.close();
    }
}