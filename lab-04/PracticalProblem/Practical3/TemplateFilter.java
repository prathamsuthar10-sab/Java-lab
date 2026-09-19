import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateFilter{

    public static String FTemplate(String template, String[] names, String[] values) {

        Pattern pattern = Pattern.compile("\\{(\\w+)\\}");
        Matcher matcher = pattern.matcher(template);

        StringBuilder result = new StringBuilder();

        int lastIndex = 0;

        while (matcher.find()) {

            result.append(template.substring(lastIndex, matcher.start()));

            String key = matcher.group(1);

            String replacement = "[?]";

            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(key)) {
                    replacement = values[i];
                    break;
                }
            }

            result.append(replacement);

            lastIndex = matcher.end();
        }

        result.append(template.substring(lastIndex));

        return result.toString();
    }
}

