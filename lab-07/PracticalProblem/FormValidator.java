import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {
    @NotBlank
    @MaxLength(15)
    private String username;

    @NotBlank
    @MaxLength(30)
    private String email;

    @NotBlank
    @MaxLength(12)
    private String password;

    SignupForm(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

class Checker {
    public static List<String> validate(Object object) {
        List<String> errors = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object value = field.get(object);

                if (field.isAnnotationPresent(NotBlank.class)) {
                    if (value == null || value.toString().trim().isEmpty()) {
                        errors.add(field.getName() + " cannot be blank");
                    }
                }

                MaxLength max = field.getAnnotation(MaxLength.class);

                if (max != null && value != null) {
                    if (value.toString().length() > max.value()) {
                        errors.add(
                            field.getName() + " cannot exceed "
                            + max.value() + " characters"
                        );
                    }
                }
            } catch (IllegalAccessException e) {
                errors.add("Cannot access " + field.getName());
            }
        }

        return errors;
    }
}

public class FormValidator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        SignupForm form = new SignupForm(username, email, password);
        List<String> errors = Checker.validate(form);

        if (errors.isEmpty()) {
            System.out.println("Form submitted successfully");
        } else {
            System.out.println("Validation errors:");

            for (String error : errors) {
                System.out.println(error);
            }
        }

        sc.close();
    }
}