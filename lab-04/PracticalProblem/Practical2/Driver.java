import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {

        String[] logs = {
            "10:05 alice Hello there",
            "10:06 bob How are you?",
            "10:07 Charlie Good morning"
        };

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();

        String result = ChatFilter.filter(logs, keyword);

        System.out.println(result);

        sc.close();
    }
}