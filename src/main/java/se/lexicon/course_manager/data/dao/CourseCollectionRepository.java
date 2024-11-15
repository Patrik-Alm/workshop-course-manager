package se.lexicon.course_manager.data.dao;


import se.lexicon.course_manager.data.sequencers.CourseSequencer;
import se.lexicon.course_manager.model.Course;
import se.lexicon.course_manager.model.Student;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


public class CourseCollectionRepository implements CourseDao {

    private Collection<Course> courses;


    public CourseCollectionRepository(Collection<Course> courses) {

        this.courses = courses;
    }

    @Override
    public Course createCourse(String courseName, LocalDate startDate, int weekDuration) {

        int localId = CourseSequencer.nextCourseId();

        Course localCourse = new Course(localId, courseName, startDate);
        localCourse.setWeekDuration(weekDuration);

        return courses.add(localCourse) ? localCourse : null;
    }

    @Override
    public Course findById(int id) {

        for (Course course : courses){
            if (id == course.getId()) {
                return course;
            }
        }
        return null;
    }

    @Override
    public Collection<Course> findByNameContains(String name) {



       for (Course course : courses) {

            if (course.getCourseName().equalsIgnoreCase(name)) {

                return courses;
            }
        }
        return null;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {

        Period period;

        Iterator<Course> itr = courses.iterator();

        for (Course course : courses) {

            period = Period.between((course.getStartDate()), end);

            if (period.getDays() > 0) {

                return courses;
            }
        }
        return null;
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        Period period;

        for (Course course : courses) {

            period = Period.between(start, course.getStartDate());

            if (period.getDays() > 0) {

                return courses;
            }
        }
        return null;
    }

    @Override
    public Collection<Course> findAll() {

        return courses;
    }

    @Override
    public Collection<Course> findByStudentId(int studentId) {

        for (Course course : courses) {

            for (Student student : course.getStudents()) {

                if (student.getId() == studentId) {

                    return courses;
                }
            }
        }

        return null;
    }

    @Override
    public boolean removeCourse(Course course) {

        return courses.remove(course);
    }

    @Override
    public void clear() {

        courses = new HashSet<>();
    }
}
