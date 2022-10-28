public abstract class Shape {

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
