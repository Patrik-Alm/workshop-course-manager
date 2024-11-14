package se.lexicon.course_manager.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StudentTest {
    // TODO Implement your tests here


    @BeforeEach
    void setUp() {

        Student student1 = new Student(10);
        Student student2 = new Student(11);
        Student student3 = new Student(12);
        Student student4 = new Student(13);

        student1.setName("Jakob Svensson");
    }

        @Test
        void setName () {
            Student student5 = new Student(14);
            student5.setName("Karl Svensson");

            assert (student5.getName() == "Karl Svensson");
        }



        @Test
        void testEquals () {

        Student student = new Student(10);
        Student studentCopy = new Student(10);
        Student studentNotACopy = new Student(11);

        student.setName("Karl Svensson");
        studentCopy.setName("Karl Svensson");
        studentNotACopy.setName("Sven Karlsson");

        assertEquals(student, studentCopy);
        assertNotEquals(student, studentNotACopy);

        }

}