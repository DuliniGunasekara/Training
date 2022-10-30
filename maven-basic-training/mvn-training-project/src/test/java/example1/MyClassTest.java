package example1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {

    MyClass myClass;

    @BeforeEach
    void setMyClass(){
        this.myClass = new MyClass();
    }

    @Test
    void testCalculateSquare(){
        assertEquals(16,myClass.calculateSquare(4));
    }
}