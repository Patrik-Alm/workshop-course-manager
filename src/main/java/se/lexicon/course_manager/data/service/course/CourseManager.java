package se.lexicon.course_manager.data.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager.data.dao.CourseDao;
import se.lexicon.course_manager.data.dao.StudentDao;
import se.lexicon.course_manager.data.service.converter.Converters;
import se.lexicon.course_manager.dto.forms.CreateCourseForm;
import se.lexicon.course_manager.dto.forms.UpdateCourseForm;
import se.lexicon.course_manager.dto.views.CourseView;
import se.lexicon.course_manager.model.Course;
import se.lexicon.course_manager.model.Student;


import java.time.LocalDate;
import java.util.List;


@Service
public class CourseManager implements CourseService {

    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final Converters converters;

    @Autowired
    public CourseManager(CourseDao courseDao, StudentDao studentDao, Converters converters) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.converters = converters;
    }

    @Override
    public CourseView create(CreateCourseForm form) {

        return converters.courseToCourseView(courseDao.createCourse(form.getCourseName(),form.getStartDate(), form.getWeekDuration()));
    }

    @Override
    public CourseView update(UpdateCourseForm form) {

        Course localCourse = courseDao.findById(form.getId());

        if (localCourse != null) {

            localCourse.setCourseName(form.getCourseName());
            localCourse.setStartDate(form.getStartDate());
            localCourse.setWeekDuration(form.getWeekDuration());
        }

        return converters.courseToCourseView(localCourse);
    }

    @Override
    public List<CourseView> searchByCourseName(String courseName) {
        return converters.coursesToCourseViews(courseDao.findByNameContains(courseName));
    }

    @Override
    public List<CourseView> searchByDateBefore(LocalDate end) {
        return converters.coursesToCourseViews(courseDao.findByDateBefore(end));
    }

    @Override
    public List<CourseView> searchByDateAfter(LocalDate start) {
        return converters.coursesToCourseViews(courseDao.findByDateAfter(start));
    }

    @Override
    public boolean addStudentToCourse(int courseId, int studentId) {

        return courseDao.findById(courseId).enrollStudent(studentDao.findById(studentId));

    }

    @Override
    public boolean removeStudentFromCourse(int courseId, int studentId) {
        return courseDao.findById(courseId).unrollStudent(studentDao.findById(studentId));
    }

    @Override
    public CourseView findById(int id) {
        return converters.courseToCourseView(courseDao.findById(id));
    }

    @Override
    public List<CourseView> findAll() {
        return converters.coursesToCourseViews(courseDao.findAll());
    }

    @Override
    public List<CourseView> findByStudentId(int studentId) {
        return converters.coursesToCourseViews(courseDao.findByStudentId(studentId));
    }

    @Override
    public boolean deleteCourse(int id) {

        Course localCourse = courseDao.findById(id);

        if (localCourse != null) {

            for (Student student : localCourse.getStudents()) {

                localCourse.unrollStudent(student);
            }
        }
        return courseDao.removeCourse(localCourse);
    }
}
