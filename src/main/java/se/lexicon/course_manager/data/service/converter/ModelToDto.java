package se.lexicon.course_manager.data.service.converter;

import org.springframework.stereotype.Component;
import se.lexicon.course_manager.data.dao.CourseCollectionRepository;
import se.lexicon.course_manager.dto.views.CourseView;
import se.lexicon.course_manager.dto.views.StudentView;
import se.lexicon.course_manager.model.Course;
import se.lexicon.course_manager.model.Student;


import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;



@Component
public class ModelToDto implements Converters {
    @Override
    public StudentView studentToStudentView(Student student) {

        return new StudentView(student.getId(), student.getName(), student.getEmail(), student.getAddress());
    }

    @Override
    public CourseView courseToCourseView(Course course) {

        return new CourseView(course.getId(), course.getCourseName(), course.getStartDate(), course.getWeekDuration(), studentsToStudentViews(course.getStudents()));
    }

    @Override
    public List<CourseView> coursesToCourseViews(Collection<Course> courses) {

        List<CourseView> courseViews = null;

        Iterator<Course> itr = courses.iterator();

        while (itr.hasNext()) {


            if (itr.next() != null) {
                courseViews.add(new CourseView(itr.next().getId(), itr.next().getCourseName(), itr.next().getStartDate(), itr.next().getWeekDuration(), studentsToStudentViews(itr.next().getStudents())));
            }
        }
        return courseViews;
    }

    @Override
    public List<StudentView> studentsToStudentViews(Collection<Student> students) {

        List<StudentView> studentViews = null;

        Iterator<Student> itr = students.iterator();

        while (itr.hasNext()) {

            if (itr.next() != null) {
                studentViews.add(new StudentView(itr.next().getId(), itr.next().getName(), itr.next().getEmail(), itr.next().getAddress()));
            }

        }
        return studentViews;
    }
}
