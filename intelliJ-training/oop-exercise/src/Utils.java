import static java.lang.String.format;

public class Utils {

    private Utils() {

    }

    public static void printResults(Shape shape) {
        System.out.println(shape.toString());
        System.out.println();
        System.out.println("Area:" + format("%.2f", shape.calculateArea()));
        System.out.println("Perimeter:" + format("%.2f", shape.calculatePerimeter()));
    }
}
