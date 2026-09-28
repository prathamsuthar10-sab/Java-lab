import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

class TestCases {
    @Run
    public void loginTest() {
        System.out.println("Login test completed");
    }

    @Run
    public void paymentTest() {
        System.out.println("Payment test completed");
    }

    public void normalMethod() {
        System.out.println("Normal method completed");
    }

    @Run
    public void logoutTest() {
        System.out.println("Logout test completed");
    }
}

public class MiniTestRunner {
    public static void main(String[] args) {
        TestCases testObject = new TestCases();
        Method[] methods = TestCases.class.getDeclaredMethods();
        int count = 0;

        for (Method method : methods) {
            if (method.isAnnotationPresent(Run.class)
                    && method.getParameterCount() == 0) {
                try {
                    System.out.println("Running: " + method.getName());
                    method.invoke(testObject);
                    count++;
                } catch (Exception e) {
                    System.out.println(method.getName() + " failed");
                }
            }
        }

        System.out.println("Total methods run: " + count);
    }
}