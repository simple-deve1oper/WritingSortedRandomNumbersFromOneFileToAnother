package dev.integers.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для проверки строки
 * @version 1.0
 */
public class CheckingString {
    /**
     * Метод для роверки строки на соответствие регулярному выражения, чтобы строка была с числами, разделенными пробелом
     * @param stringOfNumbers - строка чисел
     * @return возвращает результат проверки строки на соответствие регулярному выражению
     */
    public static boolean checkingStringForMatchingRegularExpressionSoThatStringIsWithNumbersSeparatedBySpace(String stringOfNumbers) {
        Pattern patternStringOfNumbers = Pattern.compile("(\\d+\\s)+\\d+$");
        Matcher matcherStringOfNumbers = patternStringOfNumbers.matcher(stringOfNumbers);
        if (matcherStringOfNumbers.matches()) {
            return true;
        }
        return false;
    }

    /**
     * Метод для проверки пути к файлу на соответствие регулярному выражения
     * @param path - путь
     * @return возвращает результат проверки пути
     */
    public static boolean checkPath(String path) {
        String regexPath = "(\\w+\\\\|\\w+/)*(\\w+\\.\\w+)";
        Pattern patternPath = Pattern.compile(regexPath);
        Matcher matcherPath = patternPath.matcher(path);
        boolean result = matcherPath.matches();
        return result;
    }
}
