public class Square extends Shape {
    public static final String NAME = "Square";
    private final float sideLength;

    public Square(float sideLength) {
        super(NAME);
        this.sideLength = sideLength;
    }

    @Override
    public float calculateArea() {
        return sideLength * sideLength;
    }

    @Override
    public float calculatePerimeter() {
        return 4 * sideLength;
    }

    @Override
    public String toString() {
        return "\n" + super.toString() + "\n"
                + "Side length=" + sideLength;
    }
}
