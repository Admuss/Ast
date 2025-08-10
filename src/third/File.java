package third;

import java.io.*;
import java.nio.file.*;
import java.util.*;

class FileOperationException extends RuntimeException {
    public FileOperationException(String message) {
        super(message);
    }

    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class File {
    public static void writeToFile(String filename, String content) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            writer.write(content);
            System.out.println("Данные успешно записаны в файл: " + filename);
        } catch (IOException e) {
            throw new FileOperationException("Ошибка при записи в файл: " + filename, e);
        }
    }

    public static String readFromFile(String filename) {
        try {
            return Files.readString(Paths.get(filename));
        } catch (NoSuchFileException e) {
            throw new FileOperationException("Файл не найден: " + filename, e);
        } catch (IOException e) {
            throw new FileOperationException("Ошибка при чтении файла: " + filename, e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exitRequested = false;

        while (!exitRequested) {
            System.out.println("\nМеню:");
            System.out.println("1. Записать в файл");
            System.out.println("2. Прочитать из файла");
            System.out.println("3. Выход");
            System.out.print("Выберите действие (1-3): ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка: введите число от 1 до 3");
                scanner.nextLine(); // Очистка неверного ввода
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите имя файла: ");
                    String writeFilename = scanner.nextLine();
                    System.out.print("Введите текст для записи: ");
                    String content = scanner.nextLine();
                    writeToFile(writeFilename, content);
                    break;
                case 2:
                    System.out.print("Введите имя файла: ");
                    String readFilename = scanner.nextLine();
                    try {
                        String fileContent = readFromFile(readFilename);
                        System.out.println("\nСодержимое файла:");
                        System.out.println(fileContent);
                    } catch (FileOperationException e) {
                        System.err.println("Ошибка: " + e.getMessage());
                    }
                    break;
                case 3:
                    exitRequested = true;
                    System.out.println("Выход из программы...");
                    break;
                default:
                    System.out.println("Неверный выбор. Введите число от 1 до 3");
            }
        }
        scanner.close();
    }
}