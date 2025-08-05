package second;

import java.util.*;
import java.util.stream.Collectors;

class Book {
    private String title;
    private int pages;
    private int year;

    public Book(String title, int pages, int year) {
        this.title = title;
        this.pages = pages;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Книга{" +
                "Название='" + title + '\'' +
                ", страницы=" + pages +
                ", год=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pages == book.pages && year == book.year && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, pages, year);
    }
}

class Student {
    private String name;
    private List<Book> books;

    public Student(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Студент{" +
                "Имя='" + name + '\'' +
                ", книги=" + books +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Alice", Arrays.asList(
                        new Book("Властелин колец", 1178, 1954),
                        new Book("Перси Джексон и грех Олимпоса", 432, 1813),
                        new Book("1984", 328, 1949),
                        new Book("Маленькая страна", 112, 1945),
                        new Book("The Hobbit", 310, 1937)
                )),
                new Student("Bob", Arrays.asList(
                        new Book("Поездка на восток", 224, 1979),
                        new Book("Код да Винчи", 424, 2003),
                        new Book("Ангелы и демоны", 624, 2000),
                        new Book("Девушка с драконом", 464, 2005),
                        new Book("The Hitchhiker's Guide to the Galaxy", 224, 1979)
                )),
                new Student("Charlie", Arrays.asList(
                        new Book("Гарри Поттер и философский камень", 320, 1997),
                        new Book("Хроники Нарнии", 374, 2008),
                        new Book("Властелин колец", 391, 2009),
                        new Book("Властелин колец", 455, 2010),
                        new Book("Убить петуха", 281, 2960),
                        new Book("Убить петуха", 281, 2960) // Дубликат
                ))
        );

        students.stream()
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .sorted(Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                .map(Book::getYear)
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("Год выпуска найденной книги: " + year),
                        () -> System.out.println("Такая книга отсутствует")
                );
    }
}
