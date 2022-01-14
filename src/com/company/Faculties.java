package com.company;

import java.util.ArrayList;

public class Faculties {
    protected ArrayList<Student> students = new ArrayList<>();
    private int minimumGrid;
    private int size;
    protected Student[] facultySize = new Student[getSize()];
    private String facultyName;

    public int getMinimumGrid() {
        return minimumGrid;
    }

    public void setMinimumGrid(int minimumGrid) {
        this.minimumGrid = minimumGrid;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

}
