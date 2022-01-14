package com.company;

import javax.swing.*;
import java.util.ArrayList;

public class Universities {
    protected Faculties business;
    protected Faculties cS;
    protected Faculties math;
    protected Faculties chemistry;
    protected Faculties physics;
    protected Faculties englishCommunications;
    protected ArrayList<Faculties> univFaculties = new ArrayList<>();
    private int faculties;
    private int universitySize;
    private String universityName;


    public int getFaculties() {
        return faculties;
    }

    public void setFaculties(int faculties) {
        this.faculties = faculties;
    }

    public int getUniversitySize() {
        return universitySize;
    }

    public void setUniversitySize(int universitySize) {
        this.universitySize = universitySize;
    }


    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }


    void cambridge(Universities cambridge) {
        cambridge.setUniversitySize(3);
        cambridge.setUniversityName("Cambridge");
        cambridge.setFaculties(cambridge.getUniversitySize());
        cambridge.univFaculties = new ArrayList<>();
        cambridge.univFaculties.add(0, cambridge.business = new Faculties());
        cambridge.business.setFacultyName("Business ");
        cambridge.business.setMinimumGrid(40);
        cambridge.business.setSize(3);
        cambridge.business.facultySize = new Student[cambridge.business.getSize()];
        cambridge.business.students = new ArrayList<>();
        cambridge.univFaculties.add(1, cambridge.chemistry = new Faculties());
        cambridge.chemistry.setFacultyName("Chemistry");
        cambridge.chemistry.setMinimumGrid(55);
        cambridge.chemistry.setSize(3);
        cambridge.chemistry.facultySize = new Student[cambridge.chemistry.getSize()];
        cambridge.chemistry.students = new ArrayList<>();
        cambridge.univFaculties.add(2, cambridge.physics = new Faculties());
        cambridge.physics.setFacultyName("Physics");
        cambridge.physics.setMinimumGrid(35);
        cambridge.physics.setSize(4);
        cambridge.physics.facultySize = new Student[cambridge.physics.getSize()];
        cambridge.physics.students = new ArrayList<>();

    }

    void yale(Universities yale) {
        yale.setUniversitySize(3);
        yale.setUniversityName("Yale");
        yale.setFaculties(yale.getUniversitySize());
        yale.univFaculties = new ArrayList<>();
        yale.univFaculties.add(0, yale.business = new Faculties());
        yale.business.setFacultyName("Business");
        yale.business.setMinimumGrid(65);
        yale.business.setSize(3);
        yale.business.facultySize = new Student[yale.business.getSize()];
        yale.business.students = new ArrayList<>();
        yale.univFaculties.add(1, yale.cS = new Faculties());
        yale.cS.setFacultyName("Computer Science");
        yale.cS.setMinimumGrid(70);
        yale.cS.setSize(2);
        yale.cS.facultySize = new Student[yale.cS.getSize()];
        yale.cS.students = new ArrayList<>();
        yale.univFaculties.add(2, yale.math = new Faculties());
        yale.math.setFacultyName("Math");
        yale.math.setMinimumGrid(85);
        yale.math.setSize(3);
        yale.math.facultySize = new Student[yale.math.getSize()];
        yale.math.students = new ArrayList<>();

    }

    void oxford(Universities oxford) {
        oxford.setUniversitySize(2);
        oxford.setUniversityName("Oxford");
        oxford.setFaculties(oxford.getUniversitySize());
        oxford.univFaculties = new ArrayList<>();
        oxford.univFaculties.add(0, oxford.englishCommunications = new Faculties());
        oxford.englishCommunications.setFacultyName("English Communications");
        oxford.englishCommunications.setMinimumGrid(65);
        oxford.englishCommunications.setSize(3);
        oxford.englishCommunications.facultySize = new Student[oxford.englishCommunications.getSize()];
        oxford.englishCommunications.students = new ArrayList<>();
        oxford.univFaculties.add(1, oxford.math = new Faculties());
        oxford.math.setFacultyName("Math");
        oxford.math.setMinimumGrid(85);
        oxford.math.setSize(3);
        oxford.math.facultySize = new Student[oxford.math.getSize()];
        oxford.math.students = new ArrayList<>();

    }

    void universityCases(Universities universities, Student student,
                         JComboBox comboBox2Faculties, JTextArea textArea1, Configurations setToSQL) {
        for (Faculties faculty : universities.univFaculties) {

            if (faculty.getSize() <= 0) {
                textArea1.setText("Faculty is full");
                comboBox2Faculties.removeItem(faculty.getFacultyName());
            }
            if (comboBox2Faculties.getSelectedItem().equals(faculty.getFacultyName())) {

                if (faculty.getSize() > 0) {
                    textArea1.setText("You entered-" + faculty.getFacultyName());
                    faculty.facultySize[faculty.getSize() - 1] = student;
                    student.setStudentFaculty(faculty.getFacultyName());
                    student.setStudentUniversity(universities.getUniversityName());
                    setToSQL.addStudent(student);

                    faculty.students.clear();
                    for (int i = 0; i < faculty.facultySize.length; i++) {
                        if (faculty.facultySize[i] != null) {
                            faculty.students.add(faculty.facultySize[i]);
                        }
                    }

                }
            }
            if (comboBox2Faculties.getSelectedItem().equals(faculty.getFacultyName())) {
                faculty.setSize(faculty.getSize() - 1);

                return;
            }


        }


    }


}
