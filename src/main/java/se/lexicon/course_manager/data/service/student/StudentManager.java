package se.lexicon.course_manager.data.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager.data.dao.CourseDao;
import se.lexicon.course_manager.data.dao.StudentDao;
import se.lexicon.course_manager.data.service.converter.Converters;
import se.lexicon.course_manager.dto.forms.CreateStudentForm;
import se.lexicon.course_manager.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager.dto.views.StudentView;
import se.lexicon.course_manager.model.Course;
import se.lexicon.course_manager.model.Student;


import javax.swing.text.html.HTMLDocument;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final Converters converters;

    @Autowired
    public StudentManager(StudentDao studentDao, CourseDao courseDao, Converters converters) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.converters = converters;
    }

    @Override
    public StudentView create(CreateStudentForm form) {

        Student localStudent = studentDao.createStudent(form.getName(), form.getEmail(),form.getAddress());

        return converters.studentToStudentView(localStudent);
    }

    @Override
    public StudentView update(UpdateStudentForm form) {

        Student localStudent = studentDao.findById(form.getId());

        if (localStudent != null) {

            localStudent.setName(form.getName());
            localStudent.setEmail(form.getEmail());
            localStudent.setAddress(form.getAddress());
        }

        return converters.studentToStudentView(localStudent);
    }

    @Override
    public StudentView findById(int id) {
        return converters.studentToStudentView(studentDao.findById(id));
    }

    @Override
    public StudentView searchByEmail(String email) {
        return converters.studentToStudentView(studentDao.findByEmailIgnoreCase(email));
    }

    @Override
    public List<StudentView> searchByName(String name) {
        return converters.studentsToStudentViews(studentDao.findByNameContains(name));
    }

    @Override
    public List<StudentView> findAll() {
        return converters.studentsToStudentViews(studentDao.findAll());
    }

    @Override
    public boolean deleteStudent(int id) {

        if (studentDao.findById(id) == null) {
            return false;
        }
        Student localStudent = studentDao.findById(id);

        if (courseDao.findByStudentId(localStudent.getId()) != null) {

            for (Course course : courseDao.findByStudentId(localStudent.getId())) {

                course.unrollStudent(localStudent);
            }
        }

        return studentDao.removeStudent(localStudent);
    }
}
