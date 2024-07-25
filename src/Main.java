import java.util.Scanner;

public class Main {

    public static final String OPERANDS = "[+\\-*/]";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите значение:");
            String input = scanner.nextLine();
            try {
                System.out.println(calc(input));
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    public static String calc(String input) throws Exception {
        String[] operands = input.split(OPERANDS);
        if (operands.length != 2) {
            throw new Exception("Минимум два операнда");
        }

        String operation = detectOperation(input);
        if (operation == null) {
            throw new Exception("Неверная операция");
        }

        boolean isRoman = Roman.isRoman(operands[0]) && Roman.isRoman(operands[1]);
        int firstNumber = isRoman ? Roman.convertToArabic(operands[0]) : Integer.parseInt(operands[0]);
        int secondNumber = isRoman ? Roman.convertToArabic(operands[1]) : Integer.parseInt(operands[1]);

        if (firstNumber > 99 || secondNumber > 99) {
            throw new Exception("Числа должны быть от 1 до 10");
        }

        int result = calc(firstNumber, secondNumber, operation);
        if (isRoman) {
            if (result <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            return Roman.convertToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }

    static String detectOperation(String expres) {
        if (expres.contains("+"))
            return "+";
        if (expres.contains("-"))
            return "-";
        if (expres.contains("*"))
            return "*";
        if (expres.contains("/"))
            return "/";
        return null;
    }

    static int calc(int a, int b, String operator) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalArgumentException("Неверный оператор: " + operator);
        };
    }
}

class Roman {
    static String[] romanNumerals = { "0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX" };

    public static boolean isRoman(String value) {
        for (String romanNumeral : romanNumerals) {
            if (value.equals(romanNumeral)) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabic(String roman) {
        for (int i = 0; i < romanNumerals.length; i++) {
            if (roman.equals(romanNumerals[i])) {
                return i;
            }
        }
        throw new IllegalArgumentException("Неверное римское число: " + roman);
    }

    public static String convertToRoman(int arabic) {
        if (arabic < 0 || arabic >= romanNumerals.length) {
            throw new IllegalArgumentException("Неверное арабское число: " + arabic);
        }
        return romanNumerals[arabic];
    }
}