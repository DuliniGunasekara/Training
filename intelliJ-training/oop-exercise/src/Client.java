import java.util.Scanner;

public class Client {
    float height;
    float length;
    float width;
    float radius;
    int shapeNumber;
    Shape shape;
    Scanner scanner;

    Shape s;

    public Client() {
        this.shape = null;
        this.scanner = new Scanner(System.in);

    }

    public void getShapeInput() {
        System.out.print("Please enter a number for the shape (Rectangle=1, Square=2, Circle=3) :");
        shapeNumber = scanner.nextInt();

        if (shapeNumber == 1 || shapeNumber == 2 || shapeNumber == 3) {
            getShapeProperties();
        } else {
            System.out.println("Please enter a valid shape number");
        }
    }

    public void getShapeProperties() {
        switch (shapeNumber) {

            case 1 -> {
                System.out.print("Enter Height :");
                height = scanner.nextInt();
                System.out.print("Enter Width :");
                width = scanner.nextInt();
                shape = new Rectangle(width, height);

            }
            case 2 -> {
                System.out.print("Enter Side Length :");
                length = scanner.nextInt();
                shape = new Square(length);

            }
            case 3 -> {
                System.out.print("Enter Radius :");
                radius = scanner.nextInt();
                shape = new Circle(radius);
            }

        }
        Utils.printResults(shape);
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.getShapeInput();
    }
}
