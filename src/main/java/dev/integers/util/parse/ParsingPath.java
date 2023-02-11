package dev.integers.util.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для парсинга путей к файлу
 * @version 1.0
 */
public class ParsingPath {
    /**
     * Получение пути без файла (в строке будут только директории)
     * @param path - строка пути к файлу
     * @return возвращает строку пути без файла
     */
    public static String getDirectoryInStringFormat(String path) {
        String regexFile = "(\\\\|/)*\\w+\\.\\w+";
        return getOptional(regexFile, path);
    }

    /**
     * Получение пути без директорий(-и) (в строке будет только наименование файла с расширением)
     * @param path - строка пути к файлу
     * @return возвращает наименование файла с расширением
     */
    public static String getFileInStringFormat(String path) {
        String regexDirectory = "(\\w+\\\\|\\w+/)*";
        return getOptional(regexDirectory, path);
    }

    /**
     * Метод для удаления из пути директории(-ий) или файла с помощью регулярного выражения
     * @param regex - регулярное выражение
     * @param path - строка пути к файлу
     * @return возвращает класс-оболочку Optional со строкой
     */
    private static String getOptional(String regex, String path) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(path);
        String result = "";
        if (matcher.find()) {
            result = matcher.replaceAll("");
        }
        return result;
    }
}
