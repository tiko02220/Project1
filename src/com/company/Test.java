package com.company;

//public class Test {
////    Scanner in = new Scanner(System.in);
//  //  Universities yale = new Universities();
//    Universities cambridge = new Universities();
//    Student student = new Student();
//
//
//    public void yale() {
//        yale.setUniversityName("Yale");
//        yale.setFaculties(3);
//        yale.univFaculties = new ArrayList<>();
//        yale.univFaculties.set(0, yale.business = new Faculties());
//        yale.business.setFacultyName("Business");
//        yale.business.setMinimumGrid(65);
//        yale.business.setSize(3);
//        yale.business.facultySize = new Student[yale.business.getSize()];
//        yale.univFaculties.set(1, yale.cS = new Faculties());
//        yale.cS.setFacultyName("Computer Science");
//        yale.cS.setMinimumGrid(70);
//        yale.cS.setSize(2);
//        yale.cS.facultySize = new Student[yale.cS.getSize()];
//        yale.univFaculties.set(2, yale.math = new Faculties());
//        yale.math.setFacultyName("Math");
//        yale.math.setMinimumGrid(85);
//        yale.math.setSize(3);
//        yale.math.facultySize = new Student[yale.math.getSize()];
//        yale.setUniversitySize(yale.business.getSize() + yale.math.getSize() + yale.cS.getSize());
//    }
//
//
//    public void cambridge() {
//        cambridge.setUniversityName("Cambridge");
//        cambridge.setFaculties(3);
//        cambridge.univFaculties = new ArrayList<>();
//        cambridge.univFaculties.set(0, cambridge.business = new Faculties());
//        cambridge.business.setFacultyName("Business");
//        cambridge.business.setMinimumGrid(40);
//        cambridge.business.setSize(3);
//        cambridge.business.facultySize = new Student[cambridge.business.getSize()];
//        cambridge.univFaculties.set(1, cambridge.cS = new Faculties());
//        cambridge.cS.setFacultyName("Computer Science");
//        cambridge.cS.setMinimumGrid(55);
//        cambridge.cS.setSize(4);
//        cambridge.cS.facultySize = new Student[cambridge.cS.getSize()];
//        cambridge.univFaculties.set(2, cambridge.math = new Faculties());
//        cambridge.math.setFacultyName("Math");
//        cambridge.math.setMinimumGrid(35);
//        cambridge.math.setSize(4);
//        cambridge.math.facultySize = new Student[cambridge.math.getSize()];
//        cambridge.setUniversitySize(cambridge.business.getSize() + cambridge.cS.getSize() + cambridge.math.getSize());
//    }
//
//    public void lowGrid() {
//        if (student.getStudentGrid() < yale.univFaculties.get(0).getMinimumGrid()
//                && student.getStudentGrid() < yale.univFaculties.get(1).getMinimumGrid()
//                && student.getStudentGrid() < yale.univFaculties.get(2).getMinimumGrid()) {
//            yale.setLowGrid(false);
//        } else {
//            yale.setLowGrid(true);
//        }
//        if (student.getStudentGrid() < cambridge.univFaculties.get(0).getMinimumGrid()
//                && student.getStudentGrid() < cambridge.univFaculties.get(1).getMinimumGrid()
//                && student.getStudentGrid() < cambridge.univFaculties.get(2).getMinimumGrid()) {
//            cambridge.setLowGrid(false);
//        } else {
//            cambridge.setLowGrid(true);
//        }
//    }
//
//
//    public void yaleList() {
//        for (Faculties faculty : yale.univFaculties) {
//            if (faculty.getMinimumGrid() <= student.getStudentGrid()
//                    && faculty.getSize() != 0) {
//
//                System.out.println(faculty.getFacultyName());
//
//            }
//        }
//
//        System.out.print("Choose faculty-");
//        student.setChooseFaculty(in.next());
//
//
//    }
//
//    public void cambridgeList() {
//        for (Faculties faculty : cambridge.univFaculties) {
//
//            if (faculty.getMinimumGrid() <= student.getStudentGrid()
//                    && faculty.getSize() != 0) {
//                System.out.println(faculty.getFacultyName());
//            }
//        }
//
//        System.out.print("Choose faculty-");
//        student.setChooseFaculty(in.next());
//
//    }
//
//
//    public void yaleCases() {
//        switch (student.getChooseFaculty()) {
//
//            case "Business", "b":
//                yale.business.setSize(yale.business.getSize() - 1);
//                System.out.println("You entered-" + yale.business.getFacultyName());
//                yale.business.facultySize[yale.business.getSize()] = student;
//                if (0 == yale.business.getSize()) {
//                    System.out.println(Arrays.toString(yale.business.facultySize));
//                }
//                break;
//            case "Computer Science", "cs":
//                yale.cS.setSize(yale.cS.getSize() - 1);
//                System.out.println("You entered-" + yale.cS.getFacultyName());
//                yale.cS.facultySize[yale.cS.getSize()] = student;
//                if (0 == yale.cS.getSize()) {
//                    System.out.println(Arrays.toString(yale.cS.facultySize));
//                }
//                break;
//            case "Math", "m":
//                yale.math.setSize(yale.math.getSize() - 1);
//                System.out.println("You entered-" + yale.math.getFacultyName());
//                yale.math.facultySize[yale.math.getSize()] = student;
//                if (0 == yale.math.getSize()) {
//                    System.out.println(Arrays.toString(yale.math.facultySize));
//                }
//                break;
//            default:
//                System.out.println("Unexpected Value");
//                return;
//        }
//    }
//
//    public void cambridgeCases() {
//        switch (student.getChooseFaculty()) {
//            case "Business", "b":
//                cambridge.business.setSize(cambridge.business.getSize() - 1);
//                System.out.println("You entered-" + cambridge.business.getFacultyName());
//                cambridge.business.facultySize[cambridge.business.getSize()] = student;
//                if (0 == cambridge.business.getSize()) {
//                    System.out.println(Arrays.toString(cambridge.business.facultySize));
//                }
//                break;
//            case "Computer Science", "cs":
//                cambridge.cS.setSize(cambridge.cS.getSize() - 1);
//                System.out.println("You entered-" + cambridge.cS.getFacultyName());
//                cambridge.cS.facultySize[cambridge.cS.getSize()] = student;
//                if (0 == cambridge.cS.getSize()) {
//                    System.out.println(Arrays.toString(cambridge.cS.facultySize));
//                }
//                break;
//            case "Math", "m":
//                cambridge.math.setSize(cambridge.math.getSize() - 1);
//                System.out.println("You entered-" + cambridge.math.getFacultyName());
//                cambridge.math.facultySize[cambridge.math.getSize()] = student;
//                if (0 == cambridge.math.getSize()) {
//                    System.out.println(Arrays.toString(cambridge.math.facultySize));
//                }
//                break;
//            default:
//                System.out.println("Unexpected Value");
//                return;
//
//        }
//    }
//
//
//    public void cases() {
//        if (yale.isLowGrid() && yale.getUniversitySize() != 0) {
//            System.out.println(yale.getUniversityName());
//        }
//        if (cambridge.isLowGrid() && cambridge.getUniversitySize() != 0) {
//            System.out.println(cambridge.getUniversityName());
//        } else {
//            System.out.println("Your grid is low");
//            return;
//        }
//
//        System.out.print("Choose University-");
//        student.setStudentChoice(in.next());
//
//        if (student.getStudentChoice().equals(yale.getUniversityName())
//                || student.getStudentChoice().equals("y")) {
//            yale.setUniversitySize(yale.getUniversitySize() - 1);
//
//            yaleList();
//            yaleCases();
//
//        } else if (student.getStudentChoice().equals(cambridge.getUniversityName())
//                || student.getStudentChoice().equals("c")) {
//            cambridge.setUniversitySize(cambridge.getUniversitySize() - 1);
//
//            cambridgeList();
//            cambridgeCases();
//
//        } else {
//            System.out.println("Unexpected Value ");
//            return;
//        }
//    }
//
//    public void start() {
//
//        yale();
//        cambridge();
//        int totalSize = cambridge.getUniversitySize() + yale.getUniversitySize();
//
//        for (int i = 0; i <= totalSize; i++) {
//            System.out.println("Enter Student Name Surname Grid ");
//
//            student = new Student();
//            student.setStudentName(in.next());
//            student.setStudentSurname(in.next());
//            student.setStudentGrid(in.nextInt());
//
//            lowGrid();
//            if (!yale.isLowGrid() && !cambridge.isLowGrid()) {
//                System.out.println("Grid is too Low");
//                continue;
//            }
//            cases();
//        }
//    }
//}
