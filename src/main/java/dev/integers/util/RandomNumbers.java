package dev.integers.util;

import dev.integers.util.parse.ParsingNumbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Класс для создания списка со случайными числами
 * @version 1.0
 */
public class RandomNumbers {
    /**
     * Метод для получения класса-оболочки строки целых чисел, разделенными пробелом
     * @param quantity - количество целых чисел в строке
     * @param maxNumber - максимальное число диапазона случайных чисел
     * @return возвращает класс-оболочку строки целых чисел, разделенными пробелом
     */
    public static Optional<String> getStringOfRandomIntegers(int quantity, int maxNumber) {
        List<Integer> listInt = getListOfRandomIntegers(quantity, maxNumber);
        if (!listInt.isEmpty()) {
            Optional<String> optionalParsed = ParsingNumbers.parseListOfIntegersToString(listInt);
            if (optionalParsed.isPresent()) {
                String parsed = optionalParsed.get();
                return Optional.of(parsed);
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * Метод для получения списка случайных целых чисел заданной длины и диапазона
     * @param quantity - количество чисел в списке
     * @param maxNumber - максимальное число диапазона случайных чисел
     * @return возвращает список случайных чисел заданной длины и диапазона
     */
    private static List<Integer> getListOfRandomIntegers(int quantity, int maxNumber) {
        Random random = new Random();
        List<Integer> listRandomIntegers = new ArrayList<>(quantity);
        if ((quantity > 1) && (maxNumber > 1)) {
            for (int i = 0; i < quantity; i++) {
                int randomNumber = random.nextInt(maxNumber);
                listRandomIntegers.add(randomNumber);
            }
        }
        return listRandomIntegers;
    }
}
