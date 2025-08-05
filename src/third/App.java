package third;

import java.util.Comparator;
import java.util.List;

class Book {
    private String title;
    private String author;
    private int year;
    private int pages;

    public Book(String title, String author, int year, int pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year && pages == book.pages &&
                title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(title, author, year, pages);
    }

    @Override
    public String toString() {
        return title + " by " + author + " (" + year + "), " + pages + " pages";
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
        return "Student: " + name;
    }
}

public class App {
    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Alice", List.of(
                        new Book("Book1", "Author1", 1999, 200),
                        new Book("Book2", "Author2", 2005, 150),
                        new Book("Book3", "Author3", 2010, 300),
                        new Book("Book4", "Author4", 2001, 250),
                        new Book("Book5", "Author5", 1998, 180)
                )),
                new Student("Bob", List.of(
                        new Book("Book6", "Author6", 2003, 220),
                        new Book("Book7", "Author7", 2007, 190),
                        new Book("Book3", "Author3", 2010, 300),
                        new Book("Book8", "Author8", 2002, 210),
                        new Book("Book9", "Author9", 1995, 170)
                )),
                new Student("Charlie", List.of(
                        new Book("Book10", "Author10", 2004, 230),
                        new Book("Book11", "Author11", 2008, 240),
                        new Book("Book12", "Author12", 2012, 260),
                        new Book("Book13", "Author13", 2006, 270),
                        new Book("Book3", "Author3", 2010, 300)
                )),
                new Student("David", List.of(
                        new Book("Book14", "Author14", 2009, 280),
                        new Book("Book15", "Author15", 2011, 290),
                        new Book("Book16", "Author16", 2000, 310),
                        new Book("Book17", "Author17", 1997, 320),
                        new Book("Book18", "Author18", 2003, 330)
                )),
                new Student("Eve", List.of(
                        new Book("Book19", "Author19", 2005, 340),
                        new Book("Book20", "Author20", 2007, 350),
                        new Book("Book21", "Author21", 2013, 360),
                        new Book("Book22", "Author22", 2001, 370),
                        new Book("Book23", "Author23", 1999, 380)
                ))
        );

        students.stream()
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .sorted(Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                .findFirst()
                .map(Book::getYear)
                .ifPresentOrElse(
                        year -> System.out.println("Год выпуска найденной книги: " + year),
                        () -> System.out.println("Такая книга отсутствует")
                );
    }
}
