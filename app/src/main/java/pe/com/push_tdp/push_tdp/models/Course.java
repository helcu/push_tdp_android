package pe.com.push_tdp.push_tdp.models;

/**
 * Created by Angelo-pooh on 23/10/2017.
 */

public class Course {

    private int idCourse;
    private String nameCourse;
    private int numberOfStudents;
    private int capacity;
    private String imageCourse;

    public Course(int idCourse, String nameCourse, int numberOfStudents, String imageCourse) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.numberOfStudents = numberOfStudents;
        this.imageCourse = imageCourse;
    }

    public Course() {
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getNumberOfStudentsAsString(){
        return String.valueOf(capacity);
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public String getImageCourse() {
        return imageCourse;
    }

    public void setImageCourse(String imageCourse) {
        this.imageCourse = imageCourse;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getCapacityAsString(){
        return String.valueOf(capacity);
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
