import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String text = "(3 + 5) - 9 × 4";  // Корректное выражение
        //String text = "2 * 9 - 6 × 5";  // Некорректное выражение (содержит '*')

        checkExpression(text);
    }

    public static void checkExpression(String text) {
        if (containsInvalidSymbol(text)) {
            System.out.println("Задано некорректное выражение: найден символ '*'.");
        } else if (hasInvalidNumbers(text)) {
            System.out.println("Есть числа, за которыми не стоит \"+\".");
        } else {
            System.out.println("Все числа корректны.");
        }
    }

    public static boolean containsInvalidSymbol(String text) {
        return text.contains("*");
    }

    public static boolean hasInvalidNumbers(String text) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            int numberEndIndex = matcher.end();

            if (numberEndIndex < text.length()) {
                int nextCharIndex = numberEndIndex;
                while (nextCharIndex < text.length() && Character.isWhitespace(text.charAt(nextCharIndex))) {
                    nextCharIndex++;
                }

                if (nextCharIndex < text.length() && text.charAt(nextCharIndex) != '+') {
                    char nextChar = text.charAt(nextCharIndex);

                    if (nextChar != '*' && nextChar != '/' && nextChar != '-' && nextChar != ')' && nextChar != '(') {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
