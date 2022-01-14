package com.company;

import java.util.Set;

public class Student {

    protected Set<String> studentChoice;
    private String chooseFaculty;
    private int studentGrid;
    private String studentName;
    private String studentSurname;

    public int getId() {
        return id;
    }

    public void setId(int  id) {
        this.id = id;
    }

    private int id;

    public String getStudentUniversity() {
        return studentUniversity;
    }

    public void setStudentUniversity(String studentUniversity) {
        this.studentUniversity = studentUniversity;
    }

    public String getStudentFaculty() {
        return studentFaculty;
    }

    public void setStudentFaculty(String studentFaculty) {
        this.studentFaculty = studentFaculty;
    }

    private String studentUniversity;
    private String studentFaculty;

    public String toString() {


        return "\n" + getStudentName() + " " + getStudentSurname() + "-" + getStudentGrid();
    }


    public String getChooseFaculty() {
        return chooseFaculty;
    }

    public void setChooseFaculty(String chooseFaculty) {

        this.chooseFaculty = chooseFaculty;
    }

    public int getStudentGrid() {
        return studentGrid;
    }

    public void setStudentGrid(int studentGrid) {
        this.studentGrid = studentGrid;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

}
