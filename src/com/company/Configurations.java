package com.company;

import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.*;
import java.util.ArrayList;

public class Configurations {

    private final String facultiName = "facultiesName";
    private final String facultiMinGrid = "facultiesMInGrid";
    private final String facultiStudent = "facultiesStudents";
    protected String dbHost = "87.241.141.101";
    protected String dbPort = "1433";
    protected String dbUser = "sa";
    protected String dbPass = "admin01";
    protected String dbName = "Student_Management";
    private String macStr = "04:D4:C4:92:A8:3F";
    final int PORT = 9;
    private String table;
    private String facultyTable;
    private Connection Connection;

    private static byte[] getMacBytes(String macStr) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        String[] hex = macStr.split("(\\:|\\-)");
        if (hex.length != 6) {
            throw new IllegalArgumentException("Invalid MAC address.");
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hex digit in MAC address.");
        }
        return bytes;
    }

    public void WakeOnLan() {


        try {
            byte[] macBytes = getMacBytes(macStr);
            byte[] bytes = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) 0xff;
            }
            for (int i = 6; i < bytes.length; i += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
            }

            InetAddress address = InetAddress.getByName(dbHost);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();


        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Failed to send Wake-on-LAN packet!",
                    "Warning", JOptionPane.WARNING_MESSAGE);

        }


    }


    public java.sql.Connection getConnection() {
        String connectionString = "jdbc:sqlserver://" + dbHost + ":" + dbPort + ";" + "databaseName=" + dbName;


        try {

            Connection = DriverManager.getConnection(connectionString, dbUser, dbPass);

            } catch(SQLException e){


        }
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

    public void getFaculty(Universities universities, Faculties faculties, Configurations setToSQL) {
        setToSQL.setTable(universities.getUniversityName().toLowerCase() + "_faculties");
        String select = "select * from " + setToSQL.getTable();
        if (setToSQL.getConnection() != null) {

            try {
                Statement statement = setToSQL.getConnection().createStatement();
                DatabaseMetaData data = setToSQL.getConnection().getMetaData();
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


