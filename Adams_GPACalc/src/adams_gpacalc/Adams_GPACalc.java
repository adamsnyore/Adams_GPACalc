/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package adams_gpacalc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Adams
 */
public class Adams_GPACalc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the GPA Calculator!");

        List<Course> courses = new ArrayList<>();

        // Accept course details from the user
        while (true) {
            Course course = acceptCourseDetails(scanner);
            if (course == null) {
                break;
            }
            courses.add(course);
        }

        // Display course details in tabular form
        displayTable(courses);

        // Calculate and display GPA
        double gpa = calculateGPA(courses);
        System.out.printf("Your GPA is = %.2f  to 2 decimal places.\n", gpa);

        scanner.close();
    }

    // Function to accept course details from the user
    static Course acceptCourseDetails(Scanner scanner) {
        System.out.print("Enter Course Name and Code (or 'done' to finish): ");
        String courseNameAndCode = scanner.nextLine();

        if (courseNameAndCode.equalsIgnoreCase("done")) {
            return null;
        }

        System.out.print("Enter Course Unit: ");
        int courseUnit = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter Course Score: ");
        int courseScore = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Calculate grade and grade unit
        String grade = calculateGrade(courseScore);
        int gradeUnit = calculateGradeUnit(grade);

        return new Course(courseNameAndCode, courseUnit, grade, gradeUnit);
    }

    // Function to calculate the grade based on the provided grading system
    static String calculateGrade(int score) {
        if (score >= 70 && score <= 100) {
            return "A";
        } else if (score >= 60 && score <= 69) {
            return "B";
        } else if (score >= 50 && score <= 59) {
            return "C";
        } else if (score >= 45 && score <= 49) {
            return "D";
        } else if (score >= 40 && score <= 44) {
            return "E";
        } else if (score >= 0 && score <= 39) {
            return "F";
        } else {
            return "Invalid Score";
        }
    }

    // Function to calculate the grade unit based on the grade
    static int calculateGradeUnit(String grade) {
        switch (grade) {
            case "A":
                return 5;
            case "B":
                return 4;
            case "C":
                return 3;
            case "D":
                return 2;
            case "E":
                return 1;
            default:
                return 0; // For invalid or unknown grades
        }
    }

    // Function to calculate the GPA using the given formula
    static double calculateGPA(List<Course> courses) {
        int totalQualityPoints = 0;
        int totalGradeUnits = 0;

        for (Course course : courses) {
            totalQualityPoints += course.getGradeUnit() * course.getCourseUnit();
            totalGradeUnits += course.getCourseUnit();
        }

        if (totalGradeUnits == 0) {
            return 0.0;
        }

        return (double) totalQualityPoints / totalGradeUnits;
    }

    // Function to display course details in tabular form
    static void displayTable(List<Course> courses) {
        System.out.println("|----------------------------|-----------------------|------------|---------------------|");
        System.out.println("| COURSE & CODE              | COURSE UNIT           | GRADE      | GRADE-UNIT          |");
        System.out.println("|----------------------------|-----------------------|------------|---------------------|");

        for (Course course : courses) {
            System.out.printf("| %-26s | %-21d | %-10s | %-19d |\n",
                    course.getCourseNameAndCode(), course.getCourseUnit(), course.getGrade(), course.getGradeUnit());
        }

        System.out.println("|---------------------------------------------------------------------------------------|");
    }
}

class Course {
    private String courseNameAndCode;
    private int courseUnit;
    private String grade;
    private int gradeUnit;

    public Course(String courseNameAndCode, int courseUnit, String grade, int gradeUnit) {
        this.courseNameAndCode = courseNameAndCode;
        this.courseUnit = courseUnit;
        this.grade = grade;
        this.gradeUnit = gradeUnit;
    }

    public String getCourseNameAndCode() {
        return courseNameAndCode;
    }

    public int getCourseUnit() {
        return courseUnit;
    }

    public String getGrade() {
        return grade;
    }

    public int getGradeUnit() {
        return gradeUnit;
    }
    
}
