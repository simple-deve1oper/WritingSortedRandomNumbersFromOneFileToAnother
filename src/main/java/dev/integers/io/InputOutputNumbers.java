package dev.integers.io;

import dev.integers.util.CheckingString;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Класс для записи/чтения строки чисел в файл
 * @version 1.0
 */
public class InputOutputNumbers {
    /**
     * Метод для записи строки чисел в файл
     * @param directory - наименование директории
     * @param filename - наименование файла
     * @param stringOfNumbers - строка чисел
     * @return возвращает результат записи строки чисел в файл
     * @throws IOException - ошибка ввода-вывода
     */
    public boolean writeStringOfNumbersToFile(String directory, String filename, String stringOfNumbers) throws IOException {
        if (filename.isBlank()) {
            throw new IOException("Имя файла для записи не передано");
        }

        if (!CheckingString.checkingStringForMatchingRegularExpressionSoThatStringIsWithNumbersSeparatedBySpace(stringOfNumbers)) {
            return false;
        }

        String path;
        if (!directory.isBlank()) {
            path = directory + File.separator + filename;
        } else {
            path = filename;
        }
        if (!Files.exists(Paths.get(path))) {
            throw new IOException("Файл для записи не найден");
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
        bufferedWriter.write(stringOfNumbers);
        bufferedWriter.close();
        return true;
    }

    /**
     * Метод для чтения строки чисел из файла
     * @param directory - наименование директории
     * @param filename - наименование файла
     * @return возвращает класс-оболочку Optional со строкой
     * @throws IOException - ошибка ввода-вывода
     */
    public Optional<String> readStringOfNumbersFromFile(String directory, String filename) throws IOException {
        if (filename.isBlank()) {
            throw new IOException("Имя файла для чтения не передано");
        }
        String path;
        if (!directory.isBlank()) {
            path = directory + File.separator + filename;
        } else {
            path = filename;
        }
        if (!Files.exists(Paths.get(path))) {
            throw new IOException("Файл для чтения не найден");
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String stringOfNumbersFromFile = "";
        String temp = "";
        while ((temp = bufferedReader.readLine()) != null) {
            stringOfNumbersFromFile += temp;
        }
        bufferedReader.close();

        if (stringOfNumbersFromFile.isBlank() ||
                !CheckingString.checkingStringForMatchingRegularExpressionSoThatStringIsWithNumbersSeparatedBySpace(stringOfNumbersFromFile)) {
            return Optional.empty();
        } else {
            return Optional.of(stringOfNumbersFromFile);
        }

    }
}
