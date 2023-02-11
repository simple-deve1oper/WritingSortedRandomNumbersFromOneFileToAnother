package dev.integers.io;

import java.io.File;
import java.io.IOException;

/**
 * Неизменяемый класс данных для хранения информации о директориях с наименованиями входных и выходных файлов
 * @version 1.0
 * @param inputDirectory - входная директория
 * @param inputFilename - входной файл
 * @param outputDirectory - выходная директория
 * @param outputFilename - выходной файл
 */
public record FilePath(String inputDirectory, String inputFilename, String outputDirectory, String outputFilename) {
    /**
     * Метод для создания директорий
     */
    public void createDirectories() {
        if (!inputDirectory.isBlank()) {
            File directoryInput = new File(inputDirectory);
            createDirectory(directoryInput);
        }
        if (!outputDirectory.isBlank()) {
            File directoryOutput = new File(outputDirectory);
            createDirectory(directoryOutput);
        }
    }

    /**
     * Метод для создания файлов
     * @throws IOException - ошибка ввода-вывода
     */
    public void createFiles() throws IOException {
        String pathToFileInput;
        if (inputDirectory.isBlank()) {
            pathToFileInput = inputFilename;
        } else {
            pathToFileInput = inputDirectory + File.separator + inputFilename;
        }
        File fileInput = new File(pathToFileInput);
        createFile(fileInput);

        String pathToFileOutput;
        if (inputDirectory.isBlank()) {
            pathToFileOutput = outputFilename;
        } else {
            pathToFileOutput = outputDirectory + File.separator + outputFilename;
        }
        File fileOutput = new File(pathToFileOutput);
        createFile(fileOutput);
    }

    /**
     * Метод для создания директории (если такой не существует)
     * @param directory - директория
     */
    private void createDirectory(File directory) {
        if (!checkDirectory(directory)) {
            directory.mkdirs();
        }
    }

    /**
     * Метод для создания файла (если такой не существует)
     * @param file - файл
     * @throws IOException - ошибка ввода/вывода
     */
    private void createFile(File file) throws IOException {
        if (!checkFile(file)) {
            file.createNewFile();
        }
    }

    /**
     * Проверка на существование директории
     * @param directory - директория
     * @return возвращает результат существования директории
     */
    private boolean checkDirectory(File directory) {
        if (!directory.exists()) {
            return false;
        }
        return true;
    }

    /**
     * Проверка на существование файла
     * @param file - файл
     * @return возвращает результат существования файла
     */
    private boolean checkFile(File file) {
        if (!file.exists()) {
            return false;
        }
        return true;
    }
}
