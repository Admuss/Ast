package fifth;

// 1. Subject (Интерфейс Субъекта)
interface Image {
    void display();
}

// 2. RealSubject (Реальный Субъект)
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Загрузка изображения: " + filename);
    }

    @Override
    public void display() {
        System.out.println("Отображение изображения: " + filename);
    }
}

// 3. Proxy (Прокси)
class ProxyImage implements Image {
    private String filename;
    private RealImage realImage;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename); // Ленивая инициализация
        }
        realImage.display();
    }
}

// 4. Client (Клиент)
public class Proxy {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("image1.jpg");
        Image image2 = new ProxyImage("image2.png");

        // Изображения не загружаются сразу при создании ProxyImage
        System.out.println("Изображения созданы (но еще не загружены).");

        // Отображаем первое изображение
        image1.display(); // Загрузка изображения: image1.jpg
                            // Отображение изображения: image1.jpg

        // Отображаем второе изображение
        image2.display(); // Загрузка изображения: image2.png
                            // Отображение изображения: image2.png

        // Отображаем первое изображение снова (не загружается повторно)
        image1.display(); // Отображение изображения: image1.jpg
    }
}
