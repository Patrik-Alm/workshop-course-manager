package se.lexicon.course_manager.data.dao;



import se.lexicon.course_manager.data.sequencers.StudentSequencer;
import se.lexicon.course_manager.model.Student;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


public class StudentCollectionRepository implements StudentDao {

    private Collection<Student> students;

    public StudentCollectionRepository(Collection<Student> students) {

        this.students = students;
    }

    @Override
    public Student createStudent(String name, String email, String address) {

        int localId = StudentSequencer.nextStudentId();

        Student localStudent = new Student(localId);

        localStudent.setName(name);
        localStudent.setEmail(email);
        localStudent.setAddress(address);

        return students.add(localStudent) ? localStudent : null;
    }

    @Override
    public Student findByEmailIgnoreCase(String email) {


        for (Student student : students) {
        if (email.equalsIgnoreCase(student.getEmail())) {
            return student;
            }
        }
        return null;
    }

    @Override
    public Collection<Student> findByNameContains(String name) {

        for (Student student : students) {

            if (student.getName().equalsIgnoreCase(name)) {

                return students;
            }
        }
        return null;
    }

    @Override
    public Student findById(int id) {

        Iterator<Student> itr = students.iterator();

        for (Student student : students) {
            if (id == (student.getId())) {
                return student;
            }
        }return null;
    }

    @Override
    public Collection<Student> findAll() {

        return students;
    }

    @Override
    public boolean removeStudent(Student student) {

        return students.remove(student);
    }

    @Override
    public void clear() {
        students = new HashSet<>();
    }
}
