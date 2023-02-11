package dev.integers;

import dev.integers.io.FilePath;
import dev.integers.io.InputOutputNumbers;
import dev.integers.util.CheckingString;
import dev.integers.util.RandomNumbers;
import dev.integers.util.SortingNumbers;
import dev.integers.util.parse.ParsingNumbers;
import dev.integers.util.parse.ParsingPath;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для запуска приложения
 * @version 1.0
 */
public class App {
    public static void main(String[] args) {
        App.run(args);
    }

    /**
     * Метод для запуска приложения
     * @param arguments - аргументы приложения
     */
    private static void run(String[] arguments) {
        if (checkArguments(arguments)) {
            FilePath filePath = getFilePath(arguments);
            try {
                filePath.createDirectories();
                filePath.createFiles();
            } catch (IOException ex) {
                System.err.println("Ошибка создания директории/файла: " + ex.getMessage());
            }
            Optional<String> optionalResult = writeAndReadRandomNumbers(filePath.inputDirectory(), filePath.inputFilename());
            if (optionalResult.isPresent()) {
                String readString = optionalResult.get();
                boolean result = writeAndReadSortNumbers(filePath.outputDirectory(), filePath.outputFilename(), readString);
                if (!result) {
                    System.out.println("Произошла ошибка записи/чтения отсортированных чисел");
                }
            } else {
                System.out.println("Произошла ошибка записи/чтения случайных чисел");
            }
        }
        System.out.println("Завершение работы программы");
    }

    /**
     * Метод для проверки путей из передаваемых аргументов
     * @param arguments - аргументы приложения
     * @return возвращает результат проверки
     */
    private static boolean checkArguments(String[] arguments) {
        if (arguments.length == 2) {
            if (arguments[0].isBlank() || arguments[1].isBlank()) {
                System.out.println("Аргументы входного и выходного путей не могут быть пустыми");
                return false;
            } else {
                String pathInput = arguments[0];
                String pathOutput = arguments[1];
                if (CheckingString.checkPath(pathInput) && CheckingString.checkPath(pathOutput)) {
                    return true;
                } else {
                    System.out.println("Входные и выходные пути должны соответствовать маске '[имя_директории/]имя_файла.расширение'");
                    return false;
                }
            }
        } else {
            System.out.println("Передаваемых входных аргументов должно быть два: входной и выходной пути");
            return false;
        }
    }

    /**
     * Метод для заполнения названиями директорий и файлов объекта типа FilePath из передаваемых аргументов
     * @param arguments - аргументы приложения
     * @return возвращает объект типа FilePath
     */
    private static FilePath getFilePath(String[] arguments) {
        String pathInput = arguments[0];
        String pathOutput = arguments[1];
        String directoryInput = ParsingPath.getDirectoryInStringFormat(pathInput);
        String fileInput = ParsingPath.getFileInStringFormat(pathInput);
        String directoryOutput = ParsingPath.getDirectoryInStringFormat(pathOutput);
        String fileOutput = ParsingPath.getFileInStringFormat(pathOutput);
        FilePath filePath = new FilePath(directoryInput, fileInput, directoryOutput, fileOutput);
        return filePath;
    }

    /**
     * Метод для записи и чтения строки случайных чисел
     * @param directory - наименование входной дректории
     * @param filename - наименование входного файла
     * @return возвращает класс-оболочку Optional со строкой
     */
    private static Optional<String> writeAndReadRandomNumbers(String directory, String filename) {
        System.out.println("Введите ожидаемое количество целых чисел в строке: ");
        int quantity = inputNumbers();
        System.out.println("Введите ожидаемое максимальное число диапазона случайных чисел: ");
        int maxNumber = inputNumbers();

        Optional<String> optionalStringOfRandomIntegers = RandomNumbers.getStringOfRandomIntegers(quantity, maxNumber);
        if (optionalStringOfRandomIntegers.isPresent()) {
            String stringOfRandomIntegers = optionalStringOfRandomIntegers.get();
            boolean resultWrite = writeString(directory, filename, stringOfRandomIntegers);
            if (resultWrite) {
                Optional<String> optionalReadString = readString(directory, filename);
                if (optionalReadString.isPresent()) {
                    String readString = optionalReadString.get();
                    String path = formatPath(directory, filename);
                    printResult("Исходная строка", path, stringOfRandomIntegers);
                    return Optional.of(readString);
                } else {
                    System.out.println("Полученная строка для чтения не соответствует строке целых чисел");
                    return Optional.empty();
                }
            } else {
                System.out.println("Передаваемая строка для записи не соответствует строке целых чисел");
                return Optional.empty();
            }
        } else {
            System.out.println("Ошибка получения списка случайных чисел");
            return Optional.empty();
        }
    }

    /**
     * Метод для записи и чтения строки отсортированных чисел
     * @param directory - наименование выходной директории
     * @param filename - наимнование выходного файла
     * @param readString - строка, которую необходимо отсортировать
     * @return возвращает результат чтения и записи строки отсортированных чисел
     */
    private static boolean writeAndReadSortNumbers(String directory, String filename, String readString) {
        Optional<String> optionalResultSortInteger = sortInteger(readString);
        if (optionalResultSortInteger.isPresent()) {
            String resultSortString = optionalResultSortInteger.get();
            boolean resultWrite = writeString(directory, filename, resultSortString);
            if (resultWrite) {
                Optional<String> optionalReadString = readString(directory, filename);
                if (optionalReadString.isPresent()) {
                    readString = optionalReadString.get();
                    String path = formatPath(directory, filename);
                    printResult("Исходная строка", path, readString);
                    return true;
                } else {
                    System.out.println("Полученная строка для чтения не соответствует строке целых чисел");
                    return false;
                }
            } else {
                System.out.println("Передаваемая строка для записи не соответствует строке целых чисел");
                return false;
            }
        } else {
            System.out.println("Передаваемая строка для сортировки не соответствует строке целых чисел");
            return false;
        }
    }

    /**
     * Метод для ввода целого числа с консоли
     * @return возвращает введенное целое число
     */
    private static int inputNumbers() {
        Pattern patternNumber = Pattern.compile("\\d+");
        int number = 0;
        Scanner scanner = new Scanner(System.in);
        String stringOfInteger = scanner.next();
        stringOfInteger = stringOfInteger.strip();
        while(true) {
            Matcher matcherNumber = patternNumber.matcher(stringOfInteger);
            if (matcherNumber.matches()) {
                number = Integer.valueOf(stringOfInteger);
                break;
            } else {
                System.out.println("Ошибка! Вы ввели не целое число\nПопробуйте снова: ");
                stringOfInteger = scanner.nextLine();
                stringOfInteger = stringOfInteger.strip();
            }
        }
        return number;
    }

    /**
     * Метод для записи строки чисел в файл
     * @param directory - наименование директории
     * @param filename - наименование файла
     * @param stringOfNumbers - строка чисел
     * @return возвращает результат записи строки чисел в файл
     */
    private static boolean writeString(String directory, String filename, String stringOfNumbers) {
        final InputOutputNumbers io = new InputOutputNumbers();
        boolean result = false;
        try {
            result = io.writeStringOfNumbersToFile(directory, filename, stringOfNumbers);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Метод для чтения строки чисел из файла
     * @param directory - наименование директории
     * @param filename - наименование файла
     * @return возвращает класс-оболочку Optional со строкой
     */
    private static Optional<String> readString(String directory, String filename) {
        final InputOutputNumbers io = new InputOutputNumbers();
        try {
            Optional<String> optionalResultString = io.readStringOfNumbersFromFile(directory, filename);
            return optionalResultString;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Метод для сортировки строки чисел
     * @param stringOfIntegers - строка целых чисел
     * @return возвращает класс-оболочку Optional со строкой
     */
    private static Optional<String> sortInteger(String stringOfIntegers) {
        Optional<List<Integer>> optionalListInteger = SortingNumbers.sortIntegersFromString(stringOfIntegers);
        if (optionalListInteger.isPresent()) {
            List<Integer> listInteger = optionalListInteger.get();
            Optional<String> optionalParsedString = ParsingNumbers.parseListOfIntegersToString(listInteger);
            if (optionalParsedString.isPresent()) {
                return optionalParsedString;
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * Метод для вывода строки чисел
     * @param message - сообщение
     * @param path - строковый путь к файлу
     * @param stringOfIntegers - сортировка целых чисел
     */
    private static void printResult(String message, String path, String stringOfIntegers) {
        System.out.printf("%s из файла %s: %s%n", message, path, stringOfIntegers);
    }

    /**
     * Метод для форматирования пути из названия директории и названия файла
     * @param directory - наименование директории
     * @param filename - наименование файла
     * @return возвращает отформатированный путь в виде строки
     */
    private static String formatPath(String directory, String filename) {
        String path;
        if (directory.isBlank()) {
            path = filename;
        } else {
            path = directory + File.separator + filename;
        }
        return path;
    }
}
