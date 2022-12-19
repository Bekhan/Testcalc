import java.util.Scanner;

public class Main {

    public static final String OPERANDS = "[+\\-*/]";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значение:");
        String input = scanner.nextLine();
        System.out.println(calc(input));

    }
    public static String calc(String input) throws Exception {
        int firstNumber, secondNumber;
        String operation;
        String result;
        boolean isRoman;
        String[] operands = input.split(OPERANDS);
        if (operands.length != 2)
            throw new Exception("Минимум два операнда");
        operation = detectOperation(input);
        if (operation == null)
            throw new Exception("Неверная операция");
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            firstNumber = Roman.convertToArabic(operands[0]);
            secondNumber = Roman.convertToArabic(operands[1]);
            isRoman = true;
        }

        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])){
            firstNumber = Integer.parseInt(operands[0]);
            secondNumber = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        else {
            throw new Exception("Числа должны быть одного формата");
        }
        if (firstNumber > 10 || secondNumber > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabic = calc(firstNumber, secondNumber, operation);
        if (isRoman){
            if (arabic <= 0){
                throw new Exception("Римское число должны быть больше нуля");
            }
            result = Roman.convertToRoman(arabic);
        } else {
            result = String.valueOf(arabic);
        }
        return result;

    }

    static String detectOperation(String expres){
        if (expres.contains("+")) return "+";
        else if (expres.contains("-")) return "-";
        else if (expres.contains("*")) return "*";
        else if (expres.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String operator){
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }
}
class Roman {
    static String[] romanNumerals = new String[]{"0","I","II","III","IV","V","VI","VII","VIII","IX","X"};

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
            return -1;
        }

        public static String convertToRoman(int arabic) {
            return romanNumerals[arabic];
        }
}