import java.util.HashMap;
import java.util.Map;

public class NotationConverter {

    private final static String exceptionMessage = "String must contain only valid roman numerals [I, V, X, L, C, D, M].";
    private final static String regexPattern = "[IVXLCDM]*";

    private final static Map<Character, Integer> notationMap = new HashMap<>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    public int toArabic(String romanNotation) throws InvalidValueException {
        if (romanNotation == null || romanNotation.isEmpty() || !romanNotation.matches(regexPattern)) {
            throw new InvalidValueException(exceptionMessage);
        }

        final int length = romanNotation.length() - 1;

        int result = notationMap.get(romanNotation.charAt(length));

        for (int i = 0; i < length;) {

            int left = notationMap.get(romanNotation.charAt(i));
            int right = notationMap.get(romanNotation.charAt(++i));

            if (right > left) {
                result -= left;
            } else {
                result += left;
            }

        }

        return result;
    }
}