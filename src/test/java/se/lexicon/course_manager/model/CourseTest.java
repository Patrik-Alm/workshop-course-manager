package se.lexicon.course_manager.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseTest {
    @BeforeEach
    void setUp() {
        Course course = new Course(12);
        Student student = new Student(10);

    }

    @Test
    void enrollStudent() {
        Course course = new Course(12);
        Student student = new Student(10);


       assertTrue (course.enrollStudent(student));

    }

    @Test
    void unrollStudent() {

        Course course = new Course(12);
        Student student = new Student(10);

        // Test depends on enrollStudent method as well. But tested prior to this test.
        course.enrollStudent(student);

        assertTrue(course.unrollStudent(student));

    }










}
