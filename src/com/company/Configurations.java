package com.company;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Configurations {
    protected String dbHost = "87.241.141.101";
    protected String dbPort = "1433";
    protected String dbUser = "sa";
    protected String dbPass = "admin01";
    protected String dbName = "Student_Management";
    private String table;
    private String facultyTable;
    private final String facultiName  = "facultiesName";
    private final String facultiMinGrid = "facultiesMInGrid";
    private final String facultiStudent = "facultiesStudents";
    private Connection Connection;

    public Configurations() {
        String connectionString = "jdbc:sqlserver://" + dbHost + ":" + dbPort + ";" + "databaseName=" + dbName;

        try {

            Connection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Base is Missing!",
                    "Warning", JOptionPane.WARNING_MESSAGE);

        }


    }

    public java.sql.Connection getConnection() {
        return Connection;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getFacultyTable() {
        return facultyTable;
    }

    public void setFacultyTable(String facultyTable) {
        this.facultyTable = facultyTable;
    }

    public void addTable(Configurations configurations) {

        if (Connection != null) {
            try {

                DatabaseMetaData dbm = Connection.getMetaData();
                ResultSet tables = dbm.getTables(null, null, configurations.getFacultyTable(), null);
                if (!tables.next()) {
                    String create = "CREATE TABLE " + getFacultyTable().toLowerCase() + "(" + "id integer identity," +
                            "studentName VARCHAR(50) NOT NULL,"
                            + "studentSurname VARCHAR(50) NOT NULL,"
                            + "studentGrid integer  NOT NULL,"
                            + "studentUniversity VARCHAR(50) NOT NULL,"
                            + "studentFaculty VARCHAR(50) NOT NULL)";
                    Statement statement = Connection.createStatement();
                    statement.executeUpdate(create);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Base is Missing!",
                        "Warning", JOptionPane.WARNING_MESSAGE);

            }
        }


    }

    public void addStudent(Student student) {
        String insert = "INSERT " + getFacultyTable().toLowerCase() + "(" + "studentName" + "," + "studentSurname" + ","
                + "studentGrid" + "," + "studentUniversity" + "," + "studentFaculty" + ")" + "VALUES(?,?,?,?,?)";
        if (Connection != null) {

            try {
                PreparedStatement ps = Connection.prepareStatement(insert);

                ps.setString(1, student.getStudentName());
                ps.setString(2, student.getStudentSurname());
                ps.setInt(3, student.getStudentGrid());
                ps.setString(4, student.getStudentUniversity());
                ps.setString(5, student.getStudentFaculty());
                ps.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Base is Missing!",
                        "Warning", JOptionPane.WARNING_MESSAGE);

            }
        }
    }

    public void addFacultySQL(String facultyName, int facultyMinGrid, int facultyStudent) {
        String insert = "INSERT " + getTable() + "(" + facultiName + "," + facultiMinGrid + "," + facultiStudent + ")" + "VALUES(?,?,?)";
        if (Connection != null) {

            try {
                PreparedStatement ps = Connection.prepareStatement(insert);
                ps.setString(1, facultyName);
                ps.setInt(2, facultyMinGrid);
                ps.setInt(3, facultyStudent);
                ps.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Base is Missing!",
                        "Warning", JOptionPane.WARNING_MESSAGE);

            }

        }
    }

    public void getFaculty(Universities universities, Faculties faculties, Configurations setToSQL, Configurations getFromSQL) {
        setToSQL.setTable(universities.getUniversityName().toLowerCase() + "_faculties");

        String select = "select * from " + setToSQL.getTable();
        if (getFromSQL.getConnection() != null) {

            try {
                Statement statement = getFromSQL.getConnection().createStatement();
                DatabaseMetaData data = getFromSQL.getConnection().getMetaData();
                ResultSet resultSet1 = data.getTables("Student_Management", null, setToSQL.getFacultyTable(), new String[]{"TABLE"});
                if (resultSet1.next()) {
                    ResultSet resultSet = statement.executeQuery(select);
                    while (resultSet.next()) {

                        faculties = new Faculties();

                        faculties.setFacultyName(resultSet.getString("facultiesName"));

                        faculties.setMinimumGrid(resultSet.getInt("facultiesMinGrid"));

                        faculties.setSize(resultSet.getInt("facultiesStudents"));

                        faculties.facultySize = new Student[faculties.getSize()];

                        faculties.students = new ArrayList<>();

                        universities.univFaculties.add(faculties);


                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Base is Missing!",
                        "Warning", JOptionPane.WARNING_MESSAGE);

            }

        }
    }
}


