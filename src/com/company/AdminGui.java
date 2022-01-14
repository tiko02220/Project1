package com.company;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class AdminGui extends Gui {

    DefaultTableModel model = new DefaultTableModel();
    ArrayList<Faculties> allFaculties = new ArrayList<>();
    Gui exp = new Gui();
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JPanel panel3;
    private JComboBox comboBox;
    private JTextField facultyName;
    private JSpinner spinerMinGrid;
    private JSpinner spinnerStudents;
    private JButton addButton;
    private JTextArea admintextArea2;
    private JButton logOut;
    private JLabel facultyNameLabel;
    private JLabel minGridLabel;
    private JLabel universityLabel;
    private JPanel panel4;
    private JLabel Students;
    private JComboBox adminUniversity;
    private JComboBox adminFaculty;
    private JButton deleteButton;
    private JButton infoButton;
    private JTable table1;
    private JRadioButton selectAllRadioButton;

    AdminGui() {
        spinerMinGrid.setModel(value3);
        spinnerStudents.setModel(value2);
        adminFaculty.setSelectedItem(null);

        adminFaculty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean enabeled = adminFaculty.getSelectedItem() != null;
                infoButton.setEnabled(enabeled);
            }
        });
        selectAllRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectAllRadioButton.isSelected()) {
                    table1.selectAll();
                } else if (!selectAllRadioButton.isSelected()) {
                    table1.clearSelection();
                }
            }
        });

    }


    void deleteFromSQL(Configurations setToSQL, Universities universities) {
        int row = table1.getSelectedRow();
        while (table1.getSelectedRows().length != 0) {
            setToSQL.setFacultyTable(adminUniversity.getSelectedItem().toString().toLowerCase() +
                    "_" + table1.getValueAt(row, 5).toString().toLowerCase().replaceAll(" ", "_"));
            String delete = "DELETE FROM " + setToSQL.getFacultyTable().toLowerCase() + " WHERE " + " id=" + table1.getValueAt(row, 0);

            try {
                Statement ps = setToSQL.getConnection().createStatement();
                ps.executeUpdate(delete);
                model.removeRow(row);


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    void addFacluty(Universities universities) {

        for (Faculties faculti : universities.univFaculties) {
            adminFaculty.addItem(faculti.getFacultyName());

        }
        adminFaculty.addItem("All");
        adminFaculty.setSelectedItem(null);
    }

    void removeTableInfo() {
        int rows = model.getRowCount();
        if (rows != 0) {
            for (int i = rows - 1; i >= 0; i--) {
                model.removeRow(i);
            }
        }
    }


    void addStudnet(Universities universities) {
        if (getFromSQL.getConnection() != null) {
            try {
                Statement stat = getFromSQL.getConnection().createStatement();

                for (Faculties faculties : universities.univFaculties) {
                    if (adminFaculty.getSelectedItem().equals(faculties.getFacultyName()) || adminFaculty.getSelectedItem().equals("All")) {
                        setToSQL.setFacultyTable(universities.getUniversityName().toLowerCase() +
                                "_" + faculties.getFacultyName().replaceAll(" ", "_"));
                        String selectStudent = " select * from " + setToSQL.getFacultyTable();
                        DatabaseMetaData data = getFromSQL.getConnection().getMetaData();
                        ResultSet resultSet1 = data.getTables("Student_Management", null, setToSQL.getFacultyTable(), new String[]{"TABLE"});
                        if (resultSet1.next()) {
                            ResultSet resultSet = stat.executeQuery(selectStudent);
                            while (resultSet.next()) {

                                Student stud = new Student();
                                stud.setId(resultSet.getInt("id"));
                                stud.setStudentName(resultSet.getString("studentName"));
                                stud.setStudentSurname(resultSet.getString("studentSurname"));
                                stud.setStudentGrid(resultSet.getInt("studentGrid"));
                                stud.setStudentUniversity(resultSet.getString("studentUniversity"));
                                stud.setStudentFaculty(resultSet.getString("studentFaculty"));


                                faculties.students.clear();
                                String[] tb = {String.valueOf(stud.getId()), stud.getStudentName(), stud.getStudentSurname(),
                                        String.valueOf(stud.getStudentGrid()), stud.getStudentUniversity(), stud.getStudentFaculty()};
                                model.addRow(tb);
                            }
                        }
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    void comboboxs(Universities yale1, Universities cambridge1, Universities oxfprd1) {
        adminUniversity.removeAllItems();
        adminUniversity.addItem(yale1.getUniversityName());
        adminUniversity.addItem(cambridge1.getUniversityName());
        adminUniversity.addItem(oxfprd1.getUniversityName());
        allFaculties.addAll(yale1.univFaculties);
        allFaculties.addAll(cambridge1.univFaculties);
        allFaculties.addAll(oxfprd1.univFaculties);

        adminUniversity.setSelectedItem(null);

        adminUniversity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (adminUniversity.getSelectedItem() != null) {
                    if (adminUniversity.getSelectedItem().equals(yale1.getUniversityName())) {
                        adminFaculty.removeAllItems();
                        addFacluty(yale1);

                    }
                    if (adminUniversity.getSelectedItem().equals(cambridge1.getUniversityName())) {
                        adminFaculty.removeAllItems();
                        addFacluty(cambridge1);
                    }
                    if (adminUniversity.getSelectedItem().equals(oxfprd1.getUniversityName())) {
                        adminFaculty.removeAllItems();
                        addFacluty(oxfprd1);
                    }

                }
            }
        });
        adminFaculty.setSelectedItem(null);
    }

    void interface2() {
        Object[] title = {"Id", "Name", "Surname", "Grid", "University", "Faculty"};
        model.addColumn(title[0]);
        table1.sizeColumnsToFit(0);
        DefaultTableColumnModel model1 = new DefaultTableColumnModel();
        model.addColumn(title[1]);
        model.addColumn(title[2]);
        model.addColumn(title[3]);
        model.addColumn(title[4]);
        model.addColumn(title[5]);
        table1.setColumnModel(model1);
        table1.setModel(model);
        model1.getColumn(0).setPreferredWidth(35);
        model1.getColumn(3).setPreferredWidth(35);
        setSize(460, 350);
        setContentPane(panel1);
        setVisible(true);
        tabbedPane1.setSize(350, 270);
        tabbedPane1.add("Admin", panel3);
        tabbedPane1.add("Sis-Admin", panel4);
        panel3.setSize(350, 270);
        admintextArea2.setText("");
        addButton.setEnabled(false);

    }

    void adminPanel(Universities yale1, Universities cambridge1, Universities oxford1, Configurations setToSQL) {
        comboBox3Add(yale1, cambridge1, oxford1);
        addButton(yale1, cambridge1, oxford1);
        facultyName();
        logOut();
        comboboxs(yale1, cambridge1, oxford1);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                table1.getSelectedRows();
                if (adminUniversity.getSelectedItem().equals(yale1.getUniversityName())) {
                    deleteFromSQL(setToSQL, yale1);
                }
                if (adminUniversity.getSelectedItem().equals(cambridge1.getUniversityName())) {
                    deleteFromSQL(setToSQL, cambridge1);
                }
                if (adminUniversity.getSelectedItem().equals(oxford1.getUniversityName())) {
                    deleteFromSQL(setToSQL, oxford1);
                }

                selectAllRadioButton.setSelected(false);


            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTableInfo();
                setToSQL.setFacultyTable(adminUniversity.getSelectedItem().toString()
                        + "_" + adminFaculty.getSelectedItem().toString().replaceAll(" ", "_"));
                if (adminFaculty.getSelectedItem() != null && adminUniversity.getSelectedItem() != null) {

                    if (adminUniversity.getSelectedItem().equals(yale1.getUniversityName())) {
                        addStudnet(yale1);
                    }
                    if (adminUniversity.getSelectedItem().equals(cambridge1.getUniversityName())) {
                        addStudnet(cambridge1);
                    }
                    if (adminUniversity.getSelectedItem().equals(oxford1.getUniversityName())) {
                        addStudnet(oxford1);
                    }


                    infoButton.setEnabled(false);


                }

            }
        });


    }

    void comboBox3Add(Universities yale1, Universities cambridge1, Universities oxford1) {


        if (facultyName.getText() != null) {
            addButton.setEnabled(true);

        }
        comboBox.removeAllItems();

        comboBox.addItem(yale1.getUniversityName());
        comboBox.addItem(oxford1.getUniversityName());
        comboBox.addItem(cambridge1.getUniversityName());

        comboBox.setSelectedItem(null);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent s) {

                if (
                        comboBox.getSelectedItem() != null) {
                    if (comboBox.getSelectedItem().equals(yale1.getUniversityName())) {
                        yale1.setUniversitySize(yale1.getUniversitySize() + 1);
                    } else if (comboBox.getSelectedItem().equals(cambridge1.getUniversityName())) {
                        cambridge1.setUniversitySize(cambridge1.getUniversitySize() + 1);
                    } else if (comboBox.getSelectedItem().equals(oxford1.getUniversityName())) {
                        oxford1.setUniversitySize(oxford1.getUniversitySize() + 1);
                    }
                }
            }
        });


    }

    void addButtonCheck() {
        addButton.setEnabled(comboBox.getSelectedItem() != null && !facultyName.getText().isEmpty());
    }

    void facultyNameCheck(Universities yale1, Universities cambridge1, Universities oxford1) throws IOException {
        if (facultyName.getText().length() >= 10) {
            throw new IOException();
        }
        if (comboBox.getSelectedItem() != null) {
            Faculties faculti = new Faculties();
            faculti.students = new ArrayList<>();
            faculti.setSize((int) spinnerStudents.getValue());
            faculti.facultySize = new Student[faculti.getSize()];
            faculti.setFacultyName(facultyName.getText());
            faculti.setMinimumGrid((int) spinerMinGrid.getValue());
            admintextArea2.setText("");
            admintextArea2.setText("You Successfully added" + "\n" + faculti.getFacultyName());
            if (comboBox.getSelectedItem().equals(yale1.getUniversityName())) {
                yale1.univFaculties.add(faculti);
                setToSQL.setTable(yale1.getUniversityName().toLowerCase() + "_faculties");
            } else if (comboBox.getSelectedItem().equals(cambridge1.getUniversityName())) {
                cambridge1.univFaculties.add(faculti);
                setToSQL.setTable(cambridge1.getUniversityName().toLowerCase() + "_faculties");
            } else if (comboBox.getSelectedItem().equals(oxford1.getUniversityName())) {
                oxford1.univFaculties.add(faculti);
                setToSQL.setTable(oxford1.getUniversityName().toLowerCase() + "_faculties");
            }


            setToSQL.addFacultySQL(facultyName.getText(), (int) spinerMinGrid.getValue(), (int) spinnerStudents.getValue());
            setToSQL.setFacultyTable(comboBox.getSelectedItem().toString().toLowerCase()
                    + "_" + facultyName.getText().toLowerCase().replaceAll(" ", "_"));
            setToSQL.addTable(setToSQL);
            ubdateFromSQL(yale1, cambridge1, oxford1);
            spinerMinGrid.setValue(0);
            spinnerStudents.setValue(1);
            facultyName.setText(null);
        }

    }

    void addButton(Universities yale1, Universities cambridge1, Universities oxford1) {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent s) {
                try {

                    facultyNameCheck(yale1, cambridge1, oxford1);

                } catch (IOException exp) {
                    JOptionPane.showMessageDialog(null,
                            "  Faculty name is too long" + "\n" + "(Maximum 10 characters)",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }

        });


    }

    void logOut() {
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                facultyName.setText(null);
                spinerMinGrid.setValue(100);
                spinnerStudents.setValue(1);
                comboBox.setSelectedItem(null);
            }
        });
    }

    void facultyName() {
        facultyName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char ch = e.getKeyChar();
                facultyName.setEditable(Character.isAlphabetic(ch) || Character.isISOControl(ch));
            }
        });

        facultyName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                addButtonCheck();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                addButtonCheck();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                addButtonCheck();
            }
        });
    }
}
