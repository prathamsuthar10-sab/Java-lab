public class Driver {

    public static void main(String[] args) {

        String template = "Dear {name}, order {id} ships {date}.";

        String[] names = {
                "name",
                "id"
        };

        String[] values = {
                "Rishit",
                "089"
        };

        String output = TemplateFilter.FTemplate(template, names, values);

        System.out.println(output);
    }
}
