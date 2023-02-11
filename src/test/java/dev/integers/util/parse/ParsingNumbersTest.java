package dev.integers.util.parse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParsingNumbersTest {
    @Test
    @DisplayName("Распарсить список целых чисел десяти единиц")
    public void parseOfListTenNumericUnits() {
        List<Integer> list = List.of(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        Assertions.assertFalse(list.isEmpty());
        Optional<String> optionalParsedString = ParsingNumbers.parseListOfIntegersToString(list);
        String parsedString = optionalParsedString.get();
        Assertions.assertFalse(parsedString.isBlank());
        Assertions.assertEquals("1 1 1 1 1 1 1 1 1 1", parsedString);
    }

    @Test
    @DisplayName("Распарсить 5 чисел в списке")
    public void parseOfListFiveIntegers() {
        List<Integer> list = List.of(67, 112, 59, 23, 245);
        Assertions.assertFalse(list.isEmpty());
        Optional<String> optionalParsedString = ParsingNumbers.parseListOfIntegersToString(list);
        String parsedString = optionalParsedString.get();
        Assertions.assertFalse(parsedString.isBlank());
        Assertions.assertEquals("67 112 59 23 245", parsedString);
    }

    @Test
    @DisplayName("Распарсить список целых чисел с одним элементом")
    public void parsingListOfSingleInteger() {
        List<Integer> list = List.of(56);
        Assertions.assertFalse(list.isEmpty());
        Optional<String> optionalParsedString = ParsingNumbers.parseListOfIntegersToString(list);
        Assertions.assertTrue(optionalParsedString.isEmpty());
    }

    @Test
    @DisplayName("Распарсить пустой список")
    public void parseOfEmptyList() {
        List<Integer> list = new ArrayList<>();
        Assertions.assertTrue(list.isEmpty());
        Optional<String> optionalParsedString = ParsingNumbers.parseListOfIntegersToString(list);
        Assertions.assertTrue(optionalParsedString.isEmpty());
    }

    @Test
    @DisplayName("Распарсить числовую строку из десяти единиц")
    public void parsingNumericStringOfTenNumericUnits() {
        String transmittedStringOfNumbers = "1 1 1 1 1 1 1 1 1 1";
        Assertions.assertFalse(transmittedStringOfNumbers.isBlank());
        Optional<List<Integer>> optionalParsedListIntegers = ParsingNumbers.parseStringToListIntegers(transmittedStringOfNumbers);
        List<Integer> parsedListIntegers = optionalParsedListIntegers.get();
        Assertions.assertNotNull(parsedListIntegers);
        Assertions.assertFalse(parsedListIntegers.isEmpty());
        Assertions.assertEquals(10, parsedListIntegers.size());
        for (int i = 0; i < parsedListIntegers.size(); i++) {
            Assertions.assertEquals(1, parsedListIntegers.get(i));
        }
    }

    @Test
    @DisplayName("Распарсить числовую строку из пяти чисел")
    public void parsingNumericStringOfListFiveIntegers() {
        String transmittedStringOfNumbers = "67 112 59 23 245";
        Assertions.assertFalse(transmittedStringOfNumbers.isBlank());
        Optional<List<Integer>> optionalParsedListIntegers = ParsingNumbers.parseStringToListIntegers(transmittedStringOfNumbers);
        List<Integer> parsedListIntegers = optionalParsedListIntegers.get();
        Assertions.assertNotNull(parsedListIntegers);
        Assertions.assertFalse(parsedListIntegers.isEmpty());
        Assertions.assertEquals(5, parsedListIntegers.size());

        Assertions.assertEquals(67, parsedListIntegers.get(0));
        Assertions.assertEquals(112, parsedListIntegers.get(1));
        Assertions.assertEquals(59, parsedListIntegers.get(2));
        Assertions.assertEquals(23, parsedListIntegers.get(3));
        Assertions.assertEquals(245, parsedListIntegers.get(4));
    }

    @Test
    @DisplayName("Распарсить чтроку с одним числом")
    public void parsingStringOfOneInteger() {
        String transmittedStringOfNumbers = "56";
        Assertions.assertFalse(transmittedStringOfNumbers.isBlank());
        Optional<List<Integer>> optionalParsedListIntegers = ParsingNumbers.parseStringToListIntegers(transmittedStringOfNumbers);
        Assertions.assertTrue(optionalParsedListIntegers.isEmpty());
    }

    @Test
    @DisplayName("Распарсить пустую строку")
    public void parsingOfEmptyString() {
        String transmittedStringOfNumbers = "";
        Assertions.assertTrue(transmittedStringOfNumbers.isBlank());
        Optional<List<Integer>> optionalParsedListIntegers = ParsingNumbers.parseStringToListIntegers(transmittedStringOfNumbers);
        Assertions.assertTrue(optionalParsedListIntegers.isEmpty());
    }

    @Test
    @DisplayName("Распарсить строку с текстом там, где необходима строка с числами")
    public void parsingStringOfText() {
        String transmittedStringOfNumbers = "Ааааааааааааааа";
        Assertions.assertFalse(transmittedStringOfNumbers.isBlank());
        Optional<List<Integer>> optionalParsedListIntegers = ParsingNumbers.parseStringToListIntegers(transmittedStringOfNumbers);
        Assertions.assertTrue(optionalParsedListIntegers.isEmpty());
    }
}
