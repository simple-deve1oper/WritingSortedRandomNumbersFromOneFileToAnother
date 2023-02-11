package dev.integers.util.parse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParsingPathTest {
    private final String PATH_UNIX = "data/ivanov/file.txt";
    private final String PATH_WINDOWS = "Pictures\\new_directory\\file.txt";

    @Test
    @DisplayName("Получить директорию в строковом формате из Unix пути")
    public void getDirectoryInStringFormatFromUnixPath() {
        String directory = ParsingPath.getDirectoryInStringFormat(PATH_UNIX);
        Assertions.assertFalse(directory.isBlank());
        Assertions.assertEquals("data/ivanov", directory);

        String otherPathUnix = "data/test.txt";
        directory = ParsingPath.getDirectoryInStringFormat(otherPathUnix);
        Assertions.assertFalse(directory.isBlank());
        Assertions.assertEquals("data", directory);

        otherPathUnix = "/file.txt";
        directory = ParsingPath.getDirectoryInStringFormat(otherPathUnix);
        Assertions.assertTrue(directory.isBlank());
        Assertions.assertEquals("", directory);

        otherPathUnix = "file.doc";
        directory = ParsingPath.getDirectoryInStringFormat(otherPathUnix);
        Assertions.assertTrue(directory.isBlank());
        Assertions.assertEquals("", directory);
    }

    @Test
    @DisplayName("Получить директорию в строковом формате из Windows пути")
    public void getDirectoryInStringFormatFromWindowsPath() {
        String directory = ParsingPath.getDirectoryInStringFormat(PATH_WINDOWS);
        Assertions.assertFalse(directory.isBlank());
        Assertions.assertEquals("Pictures\\new_directory", directory);

        String otherPathWindows = "Documents\\test.txt";
        directory = ParsingPath.getDirectoryInStringFormat(otherPathWindows);
        Assertions.assertFalse(directory.isBlank());
        Assertions.assertEquals("Documents", directory);

        otherPathWindows = "\\file.txt";
        directory = ParsingPath.getDirectoryInStringFormat(otherPathWindows);
        Assertions.assertTrue(directory.isBlank());
        Assertions.assertEquals("", directory);

        otherPathWindows = "file.doc";
        directory = ParsingPath.getDirectoryInStringFormat(otherPathWindows);
        Assertions.assertTrue(directory.isBlank());
        Assertions.assertEquals("", directory);
    }

    @Test
    @DisplayName("Получить файл в строковом формате из Unix пути")
    public void getFileInStringFormatFromUnixPath() {
        String file = ParsingPath.getFileInStringFormat(PATH_UNIX);
        Assertions.assertFalse(file.isBlank());
        Assertions.assertEquals("file.txt", file);

        String otherPathUnix = "data/test.txt";
        file = ParsingPath.getFileInStringFormat(otherPathUnix);
        Assertions.assertFalse(file.isBlank());
        Assertions.assertEquals("test.txt", file);

        otherPathUnix = "test.mp3";
        file = ParsingPath.getFileInStringFormat(otherPathUnix);
        Assertions.assertFalse(file.isBlank());
        Assertions.assertEquals("test.mp3", file);

        otherPathUnix = "data/";
        file = ParsingPath.getFileInStringFormat(otherPathUnix);
        Assertions.assertTrue(file.isBlank());
        Assertions.assertEquals("", file);
    }

    @Test
    @DisplayName("Получить файл в строковом формате из Windows пути")
    public void getFileInStringFormatFromWindowsPath() {
        String file = ParsingPath.getFileInStringFormat(PATH_WINDOWS);
        Assertions.assertFalse(file.isBlank());
        Assertions.assertEquals("file.txt", file);

        String otherPathUnix = "Documents\\data\\test.txt";
        file = ParsingPath.getFileInStringFormat(otherPathUnix);
        Assertions.assertFalse(file.isBlank());
        Assertions.assertEquals("test.txt", file);

        otherPathUnix = "test.mp3";
        file = ParsingPath.getFileInStringFormat(otherPathUnix);
        Assertions.assertFalse(file.isBlank());
        Assertions.assertEquals("test.mp3", file);

        otherPathUnix = "new_directory\\";
        file = ParsingPath.getFileInStringFormat(otherPathUnix);
        Assertions.assertTrue(file.isBlank());
        Assertions.assertEquals("", file);
    }
}
