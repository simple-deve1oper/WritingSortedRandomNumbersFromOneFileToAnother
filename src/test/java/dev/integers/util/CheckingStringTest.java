package dev.integers.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CheckingStringTest {
    @Test
    @DisplayName("Проверка пустой строки")
    public void checkEmptyString() {
        String emptyString = "";
        boolean result = CheckingString.checkingStringForMatchingRegularExpressionSoThatStringIsWithNumbersSeparatedBySpace(emptyString);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Проверка строки с текстом")
    public void checkStringOfText() {
        String str = "abcde";
        boolean result = CheckingString.checkingStringForMatchingRegularExpressionSoThatStringIsWithNumbersSeparatedBySpace(str);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Проверка строки с одним числом в строке")
    public void checkOneNumberInString() {
        String str = "1";
        boolean result = CheckingString.checkingStringForMatchingRegularExpressionSoThatStringIsWithNumbersSeparatedBySpace(str);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Проверка строки с одним числом и пробелом в строке")
    public void checkOneNumberAndSpaceInString() {
        String str = "78 ";
        boolean result = CheckingString.checkingStringForMatchingRegularExpressionSoThatStringIsWithNumbersSeparatedBySpace(str);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("Проверка строки с числами")
    public void checkStringOfNumbers() {
        String stringOfNumbers = "56 123 7 89 3 67";
        boolean result = CheckingString.checkingStringForMatchingRegularExpressionSoThatStringIsWithNumbersSeparatedBySpace(stringOfNumbers);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Проверка пути Unix, состоящего из директории и файла")
    public void checkPathDirectoryAndFileUnix() {
        String path = "home/anton/data/file.doc";
        boolean result = CheckingString.checkPath(path);
        Assertions.assertTrue(result);

        path = "data/music.mp3";
        result = CheckingString.checkPath(path);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Проверка пути Windows, состоящего из директории и файла")
    public void checkPathDirectoryAndFileWindows() {
        String path = "Anton\\Documents\\new_directory\\file.doc";
        boolean result = CheckingString.checkPath(path);
        Assertions.assertTrue(result);

        path = "Pictures\\image.png";
        result = CheckingString.checkPath(path);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Проверка пути Unix, состоящего из файла")
    public void checkPathFileUnix() {
        String path = "/file.doc";
        boolean result = CheckingString.checkPath(path);
        Assertions.assertFalse(result);

        path = "music.mp3";
        result = CheckingString.checkPath(path);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Проверка пути Windows, состоящего из файла")
    public void checkPathFileWindows() {
        String path = "\\file.doc";
        boolean result = CheckingString.checkPath(path);
        Assertions.assertFalse(result);

        path = "image.png";
        result = CheckingString.checkPath(path);
        Assertions.assertTrue(result);
    }
}
