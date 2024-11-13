package se.lexicon.course_manager.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

// TODO implement model
public class Course implements Serializable {

    private Integer id;
    private String courseName;

    private LocalDate startDate;

    private Integer weekDuration;

    private Collection<Student> students;

    public Course() {

        this.students = new HashSet<>();
    }

    public Course(Integer id) {

        this();
        this.id = id;
    }

    public Course(Integer id, String courseName) {

        this(id);
        this.courseName = courseName;
    }

    public Course(Integer id, String courseName, LocalDate startDate) {

        this(id, courseName);
        this.startDate = startDate;
    }


    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getCourseName() {

        return courseName;
    }

    public void setCourseName(String courseName) {

        this.courseName = courseName;
    }

    public LocalDate getStartDate() {

        return startDate;
    }

    public void setStartDate(LocalDate startDate) {

        this.startDate = startDate;
    }

    public Integer getWeekDuration() {

        return weekDuration;
    }

    public void setWeekDuration(Integer weekDuration) {

        this.weekDuration = weekDuration;
    }

    public Collection<Student> getStudents() {

        return students;
    }

    public void setStudents(Collection<Student> students) {

        this.students = students;
    }

    //Methods

    public boolean enrollStudent(Student student) {

        if (student == null) {
            return false;
        }

        return students.add(student);
    }

    public boolean unrollStudent(Student student) {

        if (student == null) {
            return false;
        }

        return students.remove(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) || Objects.equals(courseName, course.courseName) || Objects.equals(startDate, course.startDate) || Objects.equals(weekDuration, course.weekDuration) || Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, startDate, weekDuration, students);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", weekDuration=" + weekDuration +
                ", students=" + students +
                '}';
    }


}


