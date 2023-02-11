package dev.integers.util;

import dev.integers.util.parse.ParsingNumbers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Класс для сортировки чисел
 * @version 1.0
 */
public class SortingNumbers {
    /**
     * Метод для сортировки строки чисел
     * @param stringOfNumbers - строка чисел, разделенных пробелом
     * @return
     */
    public static Optional<List<Integer>> sortIntegersFromString(String stringOfNumbers) {
        Optional<List<Integer>> optionalListIntegers = ParsingNumbers.parseStringToListIntegers(stringOfNumbers);
        if (optionalListIntegers.isPresent()) {
            List<Integer> listIntegers = optionalListIntegers.get();
            Collections.sort(listIntegers);
            return Optional.of(listIntegers);
        }
        return Optional.empty();
    }
}
