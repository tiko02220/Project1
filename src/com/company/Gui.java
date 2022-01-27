package com.company;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;


public class Gui extends JFrame {


    protected JPanel panel;
    protected JPanel panel2;
    SpinnerModel value1 = new SpinnerNumberModel(100, 0, 100, 10);
    SpinnerModel value2 = new SpinnerNumberModel(1, 1, 20, 1);
    SpinnerModel value3 = new SpinnerNumberModel(100, 0, 100, 10);
    Student student;
    Universities yale = new Universities();
    Universities cambridge = new Universities();
    Universities oxford = new Universities();

    Faculties fac;
    AdminGui admin;
    Configurations setToSQL = new Configurations();

    private JTextField textField1Name;
    private JTextField textField1Surname;
    private JButton clearButton;
    private JButton enterButton;
    private JButton finalEnterButton1;
    private JButton printListButton;
    private JButton adminPanelButton;
    private JButton clearButton1;
    private JButton loginButton;
    private JTextArea textArea1;
    private JSpinner spinner1;
    private JComboBox comboBox1Universities;
    private JComboBox comboBox2Faculties;
    private JLabel nameJLabel;
    private JLabel surnameJLabel;
    private JLabel gridJLabel;
    private JLabel universitiesJLabel;
    private JLabel facultiesJLabel;
    private JTextField adminUserName;
    private JPasswordField passwordField1;
    private JRadioButton showPassword;
    private JDialog dialog;

    public void gInterface() {

        setSize(570, 450);
        setContentPane(panel);
        setTitle("Student Management");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        spinner1.setModel(value1);

    }


    void gInterface1() {

        dialog = new JDialog();
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setBackground(Color.getHSBColor(42, 44, 58));
        dialog.setSize(350, 250);
        dialog.setTitle("Admin");
        panel2.setSize(350, 250);
        panel2.setVisible(true);
        panel2.setBackground(new Color(42, 44, 58));
        dialog.add(panel2);
        dialog.setVisible(true);


    }

    public void studentUpdate(Universities universities) {
        if (setToSQL.getConnection() != null) {
            try {

                Statement stat =
                        setToSQL.getConnection().createStatement();
                for (Faculties faculties : universities.univFaculties) {

                    setToSQL.setFacultyTable(universities.getUniversityName().toLowerCase() +
                            "_" + faculties.getFacultyName().toLowerCase().replaceAll(" ", "_"));
                    String selectStudent = " select * from " + setToSQL.getFacultyTable();
                    DatabaseMetaData data =
                            setToSQL.getConnection().getMetaData();
                    ResultSet resultSet1 = data.getTables("Student_Management", null,
                            setToSQL.getFacultyTable(), new String[]{"TABLE"});

                    if (resultSet1.next()) {
                        ResultSet resultSet = stat.executeQuery(selectStudent);

                        while (resultSet.next()) {
                            Student stud = new Student();
                            stud.setStudentName(resultSet.getString("studentName"));
                            stud.setStudentSurname(resultSet.getString("studentSurname"));
                            stud.setStudentGrid(resultSet.getInt("studentGrid"));
                            stud.setStudentUniversity(resultSet.getString("studentUniversity"));
                            stud.setStudentFaculty(resultSet.getString("studentFaculty"));
                            faculties.facultySize[faculties.getSize() - 1] = stud;
                            faculties.setSize(faculties.getSize() - 1);
                            faculties.students.clear();
                            for (int i = 0; i < faculties.facultySize.length; i++) {
                                if (faculties.facultySize[i] != null) {

                                    faculties.students.add(faculties.facultySize[i]);
                                }
                            }

                        }
                    }
                }


            } catch (SQLException throwables) {
                JOptionPane.showMessageDialog(null, "Data Base is Missing!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }


    void updateFromSQL(Universities yale, Universities oxford, Universities cambridge) {
        setToSQL.getFaculty(yale, fac, setToSQL);
        setToSQL.getFaculty(cambridge, fac, setToSQL);
        setToSQL.getFaculty(oxford, fac, setToSQL);


    }

    void pirntList(Universities universities) {
        for (Faculties yaleFaculty : universities.univFaculties) {
            if (comboBox2Faculties.getSelectedItem().equals(yaleFaculty.getFacultyName())) {
                textArea1.setText(yaleFaculty.students.toString().replaceAll("(^\\[|\\]$)", ""));
            }
        }
    }

    void comboxobUniversity(Universities universities) {


        for (Faculties faculties : universities.univFaculties) {

            if (faculties.getMinimumGrid() <= (int) spinner1.getValue()
                    && faculties.getSize() != 0) {

                comboBox2Faculties.addItem(faculties.getFacultyName());
                continue;
            }
        }
        comboBox2Faculties.setSelectedItem(null);
    }


    void fieldCheck() {

        enterButton.setEnabled(!textField1Name.getText().isEmpty() && !textField1Surname.getText().isEmpty());
    }


    void addStudent() throws IOException {
        if (textField1Name.getText().length() >= 10 || textField1Surname.getText().length() >= 12) {
            throw new IOException();
        }
        student = new Student();
        student.studentChoice = new HashSet<String>();
        student.studentChoice.clear();
        student.setStudentName(textField1Name.getText());
        student.setStudentSurname(textField1Surname.getText());
        student.setStudentGrid((int) spinner1.getValue());
        textArea1.setText("");
        comboBox1Universities.setSelectedItem("");

        for (Faculties yaleFaculties : yale.univFaculties) {
            if ((int) spinner1.getValue() >= yaleFaculties.getMinimumGrid()) {
                comboBox1Universities.addItem(yale.getUniversityName());
                break;
            }
        }
        for (Faculties cambridgeFaculties : cambridge.univFaculties) {
            if ((int) spinner1.getValue() >= cambridgeFaculties.getMinimumGrid()) {
                comboBox1Universities.addItem(cambridge.getUniversityName());
                break;
            }
        }
        for (Faculties oxfordFaculties : oxford.univFaculties) {
            if ((int) spinner1.getValue() >= oxfordFaculties.getMinimumGrid()) {
                comboBox1Universities.addItem(oxford.getUniversityName());
                break;
            }

        }
    }


    void lengthCheck() {
        String massage = null;
        if (textField1Name.getText().length() >= 10 && textField1Surname.getText().length() >= 12) {
            massage = "Name & Surname are too long!";
        } else if (textField1Surname.getText().length() >= 12) {
            massage = "Surname is too long!";
        } else if (textField1Name.getText().length() >= 10) {
            massage = "Name is too long!";
        }
        JOptionPane.showMessageDialog(null, massage, "Warning", JOptionPane.WARNING_MESSAGE);

    }


    void admin() {
        adminPanelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gInterface1();

            }
        });

        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passwordField1.setEchoChar((char) 0);
                } else {
                    passwordField1.setEchoChar('*');
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (adminUserName.getText().equals("admin") && passwordField1.getText().equals("admin")) {

                    panel2.setVisible(false);
                    dialog.remove(panel2);
                    dialog.setVisible(false);
                    admin = new AdminGui();
                    admin.adminPanel(yale, cambridge, oxford, setToSQL);
                    admin.interface2();
                    adminUserName.setText(null);
                    passwordField1.setText(null);

                } else {
                    JOptionPane.showMessageDialog(null, "Usernaem and/or Password is wrong!",
                            "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        clearButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminUserName.setText(null);
                passwordField1.setText(null);
            }
        });


    }  // Username-admin Password-admina

    void gui() {
        setToSQL.WakeOnLan();
        gInterface();
        yale.yale(yale);
        cambridge.cambridge(cambridge);
        oxford.oxford(oxford);
        admin();


        textField1Surname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char ch = e.getKeyChar();
                textField1Surname.setEditable(Character.isAlphabetic(ch) || Character.isISOControl(ch));
            }
        });


        textField1Surname.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                fieldCheck();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fieldCheck();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                fieldCheck();
            }
        });

        textField1Name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char ch = e.getKeyChar();
                textField1Name.setEditable(Character.isAlphabetic(ch) || Character.isISOControl(ch));
            }


        });

        textField1Name.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void changedUpdate(DocumentEvent e) {
                fieldCheck();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                fieldCheck();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                fieldCheck();
            }


        });

        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterButton.setEnabled(false);
                textField1Name.setEnabled(false);
                textField1Surname.setEnabled(false);
                spinner1.setEnabled(false);

                try {

                    addStudent();

                } catch (IOException ex) {
                    lengthCheck();
                }
            }

        });
        setToSQL.getConnection();
        updateFromSQL(yale, cambridge, oxford);
        studentUpdate(yale);
        studentUpdate(cambridge);
        studentUpdate(oxford);
        comboBox2Faculties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText(null);


                boolean enabeled = comboBox2Faculties.getSelectedItem() != null;

                finalEnterButton1.setEnabled(enabeled);
                printListButton.setEnabled(enabeled);
                if (student != null) {
                    for (String studentchoose : student.studentChoice) {

                        if (comboBox2Faculties.getSelectedItem() == studentchoose) {
                            finalEnterButton1.setEnabled(false);
                        }
                    }
                }
            }
        });

        finalEnterButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalEnterButton1.setEnabled(false);
                setToSQL.setFacultyTable(comboBox1Universities.getSelectedItem().toString().toLowerCase()
                        + "_" + comboBox2Faculties.getSelectedItem().toString().toLowerCase().replaceAll(" ", "_"));
                setToSQL.addTable(setToSQL);
                student.studentChoice.add((String) comboBox2Faculties.getSelectedItem());

                if (comboBox1Universities.getSelectedItem().equals(oxford.getUniversityName())) {
                    oxford.universityCases(oxford, student, comboBox2Faculties, textArea1, setToSQL);
                }
                if (comboBox1Universities.getSelectedItem().equals(yale.getUniversityName())) {
                    yale.universityCases(yale, student, comboBox2Faculties, textArea1, setToSQL);
                }
                if (comboBox1Universities.getSelectedItem().equals(cambridge.getUniversityName())) {
                    cambridge.universityCases(cambridge, student, comboBox2Faculties, textArea1, setToSQL);

                }
            }
        });


        comboBox1Universities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                comboBox1Universities.setEditable(true);
                if (comboBox1Universities.getSelectedItem() != null) {


                    if (comboBox1Universities.getSelectedItem().equals(yale.getUniversityName())) {
                        comboBox2Faculties.removeAllItems();
                        comboxobUniversity(yale);

                    }
                    if (comboBox1Universities.getSelectedItem().equals(oxford.getUniversityName())) {
                        comboBox2Faculties.removeAllItems();
                        comboxobUniversity(oxford);

                    }
                    if (comboBox1Universities.getSelectedItem().equals(cambridge.getUniversityName())) {
                        comboBox2Faculties.removeAllItems();
                        comboxobUniversity(cambridge);

                    }
                }

            }
        });

        printListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox1Universities.getSelectedItem().equals(yale.getUniversityName())) {
                    pirntList(yale);
                }
                if (comboBox1Universities.getSelectedItem().equals(oxford.getUniversityName())) {
                    pirntList(oxford);
                }
                if (comboBox1Universities.getSelectedItem().equals(cambridge.getUniversityName())) {
                    pirntList(cambridge);
                }
            }

        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1Name.setText("");
                textField1Surname.setText("");
                textField1Name.setEnabled(true);
                textField1Surname.setEnabled(true);
                spinner1.setEnabled(true);
                spinner1.setValue(100);
                textArea1.setText("");
                comboBox1Universities.removeAllItems();
                comboBox2Faculties.setSelectedItem(null);
                comboBox1Universities.setSelectedItem(null);
                comboBox2Faculties.removeAllItems();
            }
        });
    }
}



