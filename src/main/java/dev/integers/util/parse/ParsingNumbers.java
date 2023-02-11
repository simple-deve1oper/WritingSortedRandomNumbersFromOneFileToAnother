package dev.integers.util.parse;

import dev.integers.util.CheckingString;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Класс для парсинга чисел
 * @version 1.0
 */
public class ParsingNumbers {
    /**
     * Метод для парсинга списка целых чисел в строку целых чисел, разделенными пробелом
     * @param list - список целых чисел
     * @return возвращает класс-оболочку Optional со строкой
     */
    public static Optional<String> parseListOfIntegersToString(List<Integer> list) {
        if (!list.isEmpty() && (list.size() > 1)) {
            String parsedListIntoString = list.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));
            return Optional.of(parsedListIntoString);
        }
        return Optional.empty();
    }

    /**
     * Метод для парсинга строки целых чисел, разделенными пробелом, в список чисел
     * @param stringOfNumbers - строка целых чисел, разделенными пробелом
     * @return возвращает класс-оболочку Optional со списком целых чисел
     */
    public static Optional<List<Integer>> parseStringToListIntegers(String stringOfNumbers) {
        if (stringOfNumbers.isBlank()) {
            return Optional.empty();
        }

        if (!CheckingString.checkingStringForMatchingRegularExpressionSoThatStringIsWithNumbersSeparatedBySpace(stringOfNumbers)) {
            return Optional.empty();
        }

        String[] arrayOfStringNumbers = stringOfNumbers.split("\\s");
        List<Integer> listIntegers = Arrays.stream(arrayOfStringNumbers)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        return Optional.of(listIntegers);
    }
}
