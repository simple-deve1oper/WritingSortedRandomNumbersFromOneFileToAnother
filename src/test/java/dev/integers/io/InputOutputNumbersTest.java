package dev.integers.io;

import dev.integers.util.RandomNumbers;
import dev.integers.util.SortingNumbers;
import dev.integers.util.parse.ParsingNumbers;
import dev.integers.util.parse.ParsingPath;
import org.junit.jupiter.api.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class InputOutputNumbersTest {
    private final InputOutputNumbers io = new InputOutputNumbers();
    private final static String pathToNewInputFile = "data/input/in.txt";
    private final static String pathToNewOutputFile = "data/output/out.txt";

    @BeforeAll
    public static void initAll() {
        try {
            String inputDirectory = ParsingPath.getDirectoryInStringFormat(pathToNewInputFile);
            Path inputPathDirectory = FileSystems.getDefault().getPath(inputDirectory);
            if (!Files.exists(inputPathDirectory)) {
                Files.createDirectories(inputPathDirectory);
            }
            String outputDirectory = ParsingPath.getDirectoryInStringFormat(pathToNewOutputFile);
            Path outputPathDirectory = FileSystems.getDefault().getPath(outputDirectory);
            if (!Files.exists(outputPathDirectory)) {
                Files.createDirectories(outputPathDirectory);
            }
            Files.createFile(Paths.get(pathToNewInputFile));
            Files.createFile(Paths.get(pathToNewOutputFile));

            Files.createFile(Paths.get("empty.txt"));

            Files.createFile(Paths.get("randomText.txt"));
            try(BufferedWriter writer = new BufferedWriter(
                    new FileWriter("randomText.txt")
            )) {
                writer.write("Аааааааааа");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            System.err.println("Ошибка создания файла/директории: " + ex.getMessage());
        }
    }

    @Test
    @DisplayName("Запись в файл без имени файла")
    public void writingStringToFileWithoutFilename() {
        String stringOfNumbers = "45 56 67 87 12";
        String directory = "test";
        String file = "";
        Assertions.assertThrows(IOException.class, () -> io.writeStringOfNumbersToFile(directory, file, stringOfNumbers),
                "Имя файла для записи не передано");
    }

    @Test
    @DisplayName("Запись строки в файл по несуществующему пути")
    public void writingStringToFileUsingNonExistentPath() {
        String stringOfNumbers = "45 56 67 87 12";
        String directory = "test";
        String file = "abc.doc";
        Assertions.assertThrows(IOException.class, () -> io.writeStringOfNumbersToFile(directory, file, stringOfNumbers),
                "Файл для записи не найден");
    }

    @Test
    @DisplayName("Запись в файл пустую строку")
    public void writingEmptyStringToFile() {
        String emptyString = "";
        String directory = ParsingPath.getDirectoryInStringFormat(pathToNewInputFile);
        String file = ParsingPath.getFileInStringFormat(pathToNewInputFile);
        try {
            boolean result = io.writeStringOfNumbersToFile(directory, file, emptyString);
            Assertions.assertFalse(result);
        } catch (IOException ex) {
            System.err.println("Ошибка записи в файл: " + ex.getMessage());
        }
    }

    @Test
    @DisplayName("Запись в файл строку текста")
    public void writingStringOfTextToFile() {
        String stringOfText = "Aaaaaaaaaa";
        String directory = ParsingPath.getDirectoryInStringFormat(pathToNewInputFile);
        String file = ParsingPath.getFileInStringFormat(pathToNewInputFile);
        try {
            boolean result = io.writeStringOfNumbersToFile(directory, file, stringOfText);
            Assertions.assertFalse(result);
        } catch (IOException ex) {
            System.err.println("Ошибка записи в файл: " + ex.getMessage());
        }
    }

    @Test
    @DisplayName("Запись в файл строку с одним число")
    public void writingStringOfOneIntegerToFile() {
        String stringOfOneInteger = "90";
        String directory = ParsingPath.getDirectoryInStringFormat(pathToNewInputFile);
        String file = ParsingPath.getFileInStringFormat(pathToNewInputFile);
        try {
            boolean result = io.writeStringOfNumbersToFile(directory, file, stringOfOneInteger);
            Assertions.assertFalse(result);
        } catch (IOException ex) {
            System.err.println("Ошибка записи в файл: " + ex.getMessage());
        }
    }

    @Test
    @DisplayName("Запись в файл строку с одним число и пробелом")
    public void writingStringOfOneIntegerAndSpaceToFile() {
        String stringOfOneInteger = "78 ";
        String directory = ParsingPath.getDirectoryInStringFormat(pathToNewInputFile);
        String file = ParsingPath.getFileInStringFormat(pathToNewInputFile);
        try {
            boolean result = io.writeStringOfNumbersToFile(directory, file, stringOfOneInteger);
            Assertions.assertFalse(result);
        } catch (IOException ex) {
            System.err.println("Ошибка записи в файл: " + ex.getMessage());
        }
    }

    @Test
    @DisplayName("Чтение файла без имени файла")
    public void readingStringFromFileWithoutFilename() {
        String directory = "test";
        String file = "";
        Assertions.assertThrows(IOException.class, () -> io.readStringOfNumbersFromFile(directory, file),
                "Имя файла для чтения не передано");
    }

    @Test
    @DisplayName("Чтение строки из файла по несуществующему пути")
    public void readingStringFromFileUsingNonExistentPath() {
        String directory = "test";
        String file = "abc.doc";
        Assertions.assertThrows(IOException.class, () -> io.readStringOfNumbersFromFile(directory, file),
                "Файл для чтения не найден");
    }

    @Test
    @DisplayName("Чтение пустого файла")
    public void readingEmptyFile() {
        try {
            Optional<String> optional = io.readStringOfNumbersFromFile("", "empty.txt");
            Assertions.assertFalse(optional.isPresent());
        } catch (IOException ex) {
            System.err.println("Ошибка записи в файл: " + ex.getMessage());
        }
    }

    @Test
    @DisplayName("Чтение строки текста")
    public void readingStringOfText() {
        try {
            Optional<String> optional = io.readStringOfNumbersFromFile("", "randomText.txt");
            Assertions.assertFalse(optional.isPresent());
        } catch (IOException ex) {
            System.err.println("Ошибка записи в файл: " + ex.getMessage());
        }
    }

    @Test
    @DisplayName("Запись чисел по существующему пути во входной файл, его чтение, сортировка полученных чисел, " +
            "запись в выходной файл отсортированных чисел и чтение их оттуда")
    public void fullTest() {
        Optional<String> optionalStringOfIntegers = RandomNumbers.getStringOfRandomIntegers(20, 999);
        Assertions.assertTrue(optionalStringOfIntegers.isPresent());
        String stringOfNumbers = optionalStringOfIntegers.get();
        Assertions.assertFalse(stringOfNumbers.isBlank());
        String directory = ParsingPath.getDirectoryInStringFormat(pathToNewInputFile);
        String file = ParsingPath.getFileInStringFormat(pathToNewInputFile);
        try {
            boolean result = io.writeStringOfNumbersToFile(directory, file, stringOfNumbers);
            Assertions.assertTrue(result);
            Optional<String> optionalReadStringOfIntegers = io.readStringOfNumbersFromFile(directory, file);
            Assertions.assertTrue(optionalReadStringOfIntegers.isPresent());
            String readStringOfIntegers = optionalReadStringOfIntegers.get();
            Assertions.assertFalse(readStringOfIntegers.isBlank());
            Optional<List<Integer>> optionalSortListInteger = SortingNumbers.sortIntegersFromString(readStringOfIntegers);
            Assertions.assertTrue(optionalSortListInteger.isPresent());
            List<Integer> sortListInteger = optionalSortListInteger.get();
            Assertions.assertFalse(sortListInteger.isEmpty());
            Optional<String> optionalParsedStringFromList = ParsingNumbers.parseListOfIntegersToString(sortListInteger);
            Assertions.assertTrue(optionalParsedStringFromList.isPresent());
            String parsedStringFromList = optionalParsedStringFromList.get();
            Assertions.assertFalse(parsedStringFromList.isBlank());

            directory = ParsingPath.getDirectoryInStringFormat(pathToNewOutputFile);
            file = ParsingPath.getFileInStringFormat(pathToNewOutputFile);
            result = io.writeStringOfNumbersToFile(directory, file, parsedStringFromList);
            Assertions.assertTrue(result);
            optionalReadStringOfIntegers = io.readStringOfNumbersFromFile(directory, file);
            Assertions.assertTrue(optionalReadStringOfIntegers.isPresent());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @AfterAll
    public static void completeAll() {
        Path path = Paths.get(pathToNewInputFile);
        deletePath(path);
        path = Paths.get(ParsingPath.getDirectoryInStringFormat(pathToNewInputFile));
        deletePath(path);

        path = Paths.get(pathToNewOutputFile);
        deletePath(path);
        path = Paths.get(ParsingPath.getDirectoryInStringFormat(pathToNewOutputFile));
        deletePath(path);

        path = Paths.get("data");
        deletePath(path);

        path = Paths.get("empty.txt");
        deletePath(path);

        path = Paths.get("randomText.txt");
        deletePath(path);
    }

    private static void deletePath(Path path) {
        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
