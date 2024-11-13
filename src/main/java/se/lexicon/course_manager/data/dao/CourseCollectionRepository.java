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

        return localCourse;
    }

    @Override
    public Course findById(int id) {

        Iterator<Course> itr = courses.iterator();

        while (itr.hasNext()) {
            if (id == (itr.next().getId())) {
                return itr.next();
            }
        }
        return null;
    }

    @Override
    public Collection<Course> findByNameContains(String name) {

        Iterator<Course> itr = courses.iterator();

        while (itr.hasNext()) {

            if (itr.next().getCourseName().equalsIgnoreCase(name)) {

                return courses;
            }
        }
        return null;
    }

    @Override
    public Collection<Course> findByDateBefore(LocalDate end) {

        Period period;

        Iterator<Course> itr = courses.iterator();

        while (itr.hasNext()) {

            period = Period.between((itr.next().getStartDate()), end);

            if (period.getDays() > 0) {

                return courses;
            }
        }
        return null;
    }

    @Override
    public Collection<Course> findByDateAfter(LocalDate start) {
        Period period;

        Iterator<Course> itr = courses.iterator();

        while (itr.hasNext()) {

            period = Period.between(start, itr.next().getStartDate());

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

        Iterator<Course> itr = courses.iterator();


        while (itr.hasNext()) {


            Iterator<Student> itr2 = itr.next().getStudents().iterator();

            while (itr2.hasNext()) {


                if (itr2.next().getId() == studentId) {

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
