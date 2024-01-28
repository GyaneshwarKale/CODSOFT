import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class StudentManagementSystem {
    public static void main(String[] args) {
                StudentManagementSystemGUI system = new StudentManagementSystemGUI();
                system.display();
            }
    }

class Student {
    private String name;
    private int rollNo;
    private char grade;

    public Student(String name, int rollNo, char grade) {
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public char getGrade() {
        return grade;
    }

    public Student(String data) {
        String[] parts = data.split(",");
        this.name = parts[0];
        this.rollNo = Integer.parseInt(parts[1]);
        this.grade = parts[2].charAt(0);
    }

    public String toFileString() {
        return name + "," + rollNo + "," + grade;
    }
}

class StudentManagementSystemGUI {
    Scanner scanner=new Scanner(System.in);
    private ArrayList<Student> students;
    private static final String FILE_NAME = "students.txt";
    private JTextArea displayTextArea;

    public StudentManagementSystemGUI() {
        this.students = new ArrayList<>();
        loadStudentsFromFile();
    }

    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(new Student(line));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.write(student.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void addStudent() {
        String name = JOptionPane.showInputDialog("Enter name:");
        int rollNo = Integer.parseInt(JOptionPane.showInputDialog("Enter roll no:"));
        char grade = JOptionPane.showInputDialog("Enter grade:").charAt(0);
        students.add(new Student(name, rollNo, grade));
        saveStudentsToFile();
        JOptionPane.showMessageDialog(null, "Student added successfully.");
    }

    private void removeStudent() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(null,"No students in the system.");
            return;
        }
        int rollNo = Integer.parseInt(JOptionPane.showInputDialog("Enter roll no of student to remove:"));
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getRollNo() == rollNo) {
                students.remove(i);
                saveStudentsToFile();
                JOptionPane.showMessageDialog(null, "Student removed successfully.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Student with Roll No. " + rollNo + " not found.");
    }

    private void searchStudent() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(null,"No students in the system.");
            return;
        }
        int rollNo = Integer.parseInt(JOptionPane.showInputDialog("Enter roll no to search for a student:"));
        for (Student student : students) {
            if (student.getRollNo() == rollNo) {
                JOptionPane.showMessageDialog(null,"Student found:" +
                        "\nName: " + student.getName() +
                        "\nRoll No: " + student.getRollNo() +
                        "\nGrade: " + student.getGrade());
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"Student with Roll No. " + rollNo + " not found.");
    }

    private void displayStudents() {
        displayTextArea.setText("");
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(null,"No students in the system.");
            return;
        }
        for (Student student : students) {
            displayTextArea.append(" Name: " + student.getName() +
                    "\n Roll No: " + student.getRollNo() +
                    "\n Grade: " + student.getGrade() +
                    "\n------------------------\n");
        }
    }

    public void display() {
        JFrame frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,800);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 170, 330, 500);
        panel.add(scrollPane);

        displayTextArea = new JTextArea();
        displayTextArea.setBounds(20, 170, 330, 500);
        scrollPane.setViewportView(displayTextArea);
        displayTextArea.setEditable(false);

        frame.setVisible(true);
    }
    
    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton addButton = new JButton("Add Student");
        addButton.setBounds(20, 20, 150, 30);
        panel.add(addButton);

        JButton removeButton = new JButton("Remove Student");
        removeButton.setBounds(200, 20, 150, 30);
        panel.add(removeButton);

        JButton searchButton = new JButton("Search Student");
        searchButton.setBounds(20, 70, 150, 30);
        panel.add(searchButton);

        JButton displayButton = new JButton("Display All Students");
        displayButton.setBounds(200, 70, 150, 30);
        panel.add(displayButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(20, 120, 330, 30);
        panel.add(exitButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayStudents();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}