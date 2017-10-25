package pe.com.push_tdp.push_tdp.models;

/**
 * Created by Angelo-pooh on 23/10/2017.
 */

public class Course {

    private int idCourse;
    private String nameCourse;
    private int countOfStudents;
    private int imageCourse;

    public Course(int idCourse, String nameCourse, int countOfStudents,int imageCourse) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.countOfStudents = countOfStudents;
        this.imageCourse=imageCourse;
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

    public int getCountOfStudents() {
        return countOfStudents;
    }

    public void setCountOfStudents(int countOfStudents) {
        this.countOfStudents = countOfStudents;
    }

    public int getImageCourse() {
        return imageCourse;
    }

    public void setImageCourse(int imageCourse) {
        this.imageCourse = imageCourse;
    }
}
