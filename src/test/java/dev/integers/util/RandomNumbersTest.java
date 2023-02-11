package dev.integers.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class RandomNumbersTest {
    @Test
    @DisplayName("Получить строку с количеством элементов 0")
    public void getStringWithQuantityZero() {
        Optional<String> optional = RandomNumbers.getStringOfRandomIntegers(0, 50);
        Assertions.assertTrue(optional.isEmpty());
    }

    @Test
    @DisplayName("Получить строку с количеством элементов 1")
    public void getStringWithQuantityOne() {
        Optional<String> optional = RandomNumbers.getStringOfRandomIntegers(1, 50);
        Assertions.assertTrue(optional.isEmpty());
    }

    @Test
    @DisplayName("Получить строку с максимальным числом диапазона случайных чисел 0")
    public void getStringWithMaxNumberZero() {
        Optional<String> optional = RandomNumbers.getStringOfRandomIntegers(34, 0);
        Assertions.assertFalse(optional.isPresent());
    }

    @Test
    @DisplayName("Получить строку с максимальным числом диапазона случайных чисел 1")
    public void getStringWithMaxNumberOne() {
        Optional<String> optional = RandomNumbers.getStringOfRandomIntegers(34, 1);
        Assertions.assertFalse(optional.isPresent());
    }

    @Test
    @DisplayName("Получить строку с количеством элементов 20 и максимальным числом диапазона случайных чисел 156")
    public void getStringRandomNumbers() {
        Optional<String> optional = RandomNumbers.getStringOfRandomIntegers(20, 156);
        Assertions.assertTrue(optional.isPresent());
        String[] numbers = optional.get().split("\\s");
        Assertions.assertEquals(20, numbers.length);
    }
}
