package fifth;

interface Shape {
    void draw(int x, int y, int width, int height);
}

class LegacyRectangle {
    public void display(int x1, int y1, int x2, int y2) {
        System.out.println("LegacyRectangle: отображение от (" + x1 + "," + y1 + ") до (" + x2 + "," + y2 + ")");
    }
}

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

public class Adapter {
    public static void main(String[] args) {

        LegacyRectangle legacyRectangle = new LegacyRectangle();
        Shape rectangle = new RectangleAdapter(legacyRectangle);

        rectangle.draw(10, 20, 30, 40); // LegacyRectangle отображение от (10,20) до (40,60)
    }
}
