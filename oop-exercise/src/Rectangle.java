public class Rectangle extends Shape {

    public static final String NAME = "Rectangle";
    private final float width;
    private final float height;

    public Rectangle(float width, float height) {
        super(NAME);
        this.width = width;
        this.height = height;
    }

    @Override
    public float calculateArea() {
        return this.height * this.width;
    }

    @Override
    public float calculatePerimeter() {
        return 2 * (height + width);
    }

    @Override
    public String toString() {
        return "\n" + super.toString() + "\n"
                + "width=" + width + "\n"
                + "height=" + height;
    }
}
