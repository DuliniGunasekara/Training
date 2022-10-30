public class Circle extends Shape {
    public static final String NAME = "Circle";
    private static final float PI = (float) Math.PI;
    private final float radius;

    public Circle(float radius) {
        super(NAME);
        this.radius = radius;
    }

    @Override
    public float calculateArea() {
        return PI * radius * radius;
    }

    @Override
    public float calculatePerimeter() {
        return 2 * PI * radius;
    }

    @Override
    public String toString() {
        return "\n" + super.toString() + "\n"
                + "Radius=" + radius;
    }
}
