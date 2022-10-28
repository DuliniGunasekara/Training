public abstract class Shape {
    // shape
    private String shapeName;

    protected Shape(String shapeName) {
        this.shapeName = shapeName;
    }

    public abstract float calculateArea();

    public abstract float calculatePerimeter();

    @Override
    public String toString() {
        return "Shape: " + shapeName;
    }
}
