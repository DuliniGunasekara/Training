package example1;

public class MyClass {

    public int calculateSquare(int number){
        return number * number;
    }

    public static void main(String[] args){
        MyClass myClass = new MyClass();
        System.out.println(myClass.calculateSquare(5));
    }
}
