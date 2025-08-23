package fifth;

// 1. Target (Цель)
interface Shape {
    void draw(int x, int y, int width, int height);
}

// 2. Adaptee (Адаптируемый)
class LegacyRectangle {
    public void display(int x1, int y1, int x2, int y2) {
        System.out.println("LegacyRectangle: отображение от (" + x1 + "," + y1 + ") до (" + x2 + "," + y2 + ")");
    }
}

// 3. Adapter (Адаптер) - Object Adapter
class RectangleAdapter implements Shape {
    private LegacyRectangle legacyRectangle;

    public RectangleAdapter(LegacyRectangle legacyRectangle) {
        this.legacyRectangle = legacyRectangle;
    }

    @Override
    public void draw(int x, int y, int width, int height) {
        legacyRectangle.display(x, y, x + width, y + height);
    }
}

// 4. Client (Клиент)
public class Adapter {
    public static void main(String[] args) {
        // Используем LegacyRectangle через адаптер
        LegacyRectangle legacyRectangle = new LegacyRectangle();
        Shape rectangle = new RectangleAdapter(legacyRectangle);

        rectangle.draw(10, 20, 30, 40); // Вывод: LegacyRectangle: отображение от (10,20) до (40,60)

        // Без адаптера мы бы не могли напрямую использовать LegacyRectangle как Shape
    }
}
