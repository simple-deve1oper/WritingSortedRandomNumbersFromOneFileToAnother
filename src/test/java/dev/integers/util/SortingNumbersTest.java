package dev.integers.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class SortingNumbersTest {
    @Test
    @DisplayName("Сортировка строки из 10 чисел в строке, разделенных пробелом")
    public void sortTenIntegersFromString() {
        String stringOfIntegers = "73 133 3 78 19 38 111 123 122 1";
        Optional<List<Integer>> optionalListInteger = SortingNumbers.sortIntegersFromString(stringOfIntegers);
        Assertions.assertTrue(optionalListInteger.isPresent());
        List<Integer> listInteger = optionalListInteger.get();
        Assertions.assertEquals(10, listInteger.size());
        Assertions.assertEquals("[1, 3, 19, 38, 73, 78, 111, 122, 123, 133]", listInteger.toString());
    }

    @Test
    @DisplayName("Сортировка одной строки с одним числом")
    public void sortStringOfOneInteger() {
        String oneNumber = "56";
        Optional<List<Integer>> optionalListInteger = SortingNumbers.sortIntegersFromString(oneNumber);
        Assertions.assertFalse(optionalListInteger.isPresent());
    }

    @Test
    @DisplayName("Сортировка пустой строки")
    public void sortEmptyString() {
        String emptyString = "";
        Optional<List<Integer>> optionalListInteger = SortingNumbers.sortIntegersFromString(emptyString);
        Assertions.assertFalse(optionalListInteger.isPresent());
    }

    @Test
    @DisplayName("Сортировка строки текста")
    public void sortStringOfText() {
        String stringOfText = "Аааааааааааа";
        Optional<List<Integer>> optionalListInteger = SortingNumbers.sortIntegersFromString(stringOfText);
        Assertions.assertFalse(optionalListInteger.isPresent());
    }
}
