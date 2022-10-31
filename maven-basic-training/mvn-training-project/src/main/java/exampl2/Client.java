package exampl2;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        List<String> subjectList = new ArrayList<>();
        subjectList.add("Maths");
        subjectList.add("English");
        subjectList.add("History");

        Student student = new Student();
        student.setId(1);
        student.setName("John");
        student.setAge(20);
        student.setSubjects(subjectList);

        System.out.println("id: " + student.getId() + "\n"
                + "name: " + student.getName() + "\n"
                + "age: " + student.getAge());
        System.out.print("subjects:" + subjectList);


    }
}
