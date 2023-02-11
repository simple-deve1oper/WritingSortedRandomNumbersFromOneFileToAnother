package dev.integers.io;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePathTest {
    private FilePath filePath;

    @BeforeEach
    public void init() {
        filePath = new FilePath("data/input", "in.txt",
                "data/output", "out.txt");
    }

    @Test
    @DisplayName("Создание директорий и файлов")
    public void createDirectoriesAndFiles() {
        try {
            filePath.createDirectories();
            filePath.createFiles();
        } catch (IOException ex) {
            System.err.println("Ошибка создания файлов/директорий: " + ex.getMessage());
        }

        String pathToFileInput;
        if (filePath.inputDirectory().isBlank()) {
            pathToFileInput = filePath.inputFilename();
        } else {
            pathToFileInput = filePath.inputDirectory() + File.separator + filePath.inputFilename();
        }
        Assertions.assertTrue(Files.exists(Paths.get(pathToFileInput)));

        String pathToFileOutput;
        if (filePath.inputDirectory().isBlank()) {
            pathToFileOutput = filePath.outputFilename();
        } else {
            pathToFileOutput = filePath.outputDirectory() + File.separator + filePath.outputFilename();
        }
        Assertions.assertTrue(Files.exists(Paths.get(pathToFileOutput)));
    }

    @Test
    @DisplayName("Создание файлов без директорий")
    public void createFilesWithoutDirectories() {
        FilePath filePathObject = new FilePath("", "abc.txt", "", "black car");

        String pathToFileInput = filePath.inputFilename();
        Path pathInput = Paths.get(pathToFileInput);
        if (Files.exists(pathInput)) {
            
        }
    }


    @AfterEach
    public void complete() {
        String pathToFileInput;
        if (filePath.inputDirectory().isBlank()) {
            pathToFileInput = filePath.inputFilename();
        } else {
            pathToFileInput = filePath.inputDirectory() + File.separator + filePath.inputFilename();
        }
        Path pathInput = Paths.get(pathToFileInput);
        if (Files.exists(pathInput)) {
            try {
                Files.delete(pathInput);
                if (!filePath.inputDirectory().isBlank()) {
                    pathInput = Paths.get(filePath.inputDirectory());
                    Files.delete(pathInput);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        String pathToFileOutput;
        if (filePath.outputDirectory().isBlank()) {
            pathToFileOutput = filePath.outputFilename();
        } else {
            pathToFileOutput = filePath.outputDirectory() + File.separator + filePath.outputFilename();
        }
        Path pathOutput = Paths.get(pathToFileOutput);
        if (Files.exists(pathOutput)) {
            try {
                Files.delete(pathOutput);
                if (!filePath.outputDirectory().isBlank()) {
                    pathOutput = Paths.get(filePath.outputDirectory());
                    Files.delete(pathOutput);
                }
                Files.delete(Paths.get("data"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
