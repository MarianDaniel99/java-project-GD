package trainingcenter;

import trainingcenter.model.Course;
import trainingcenter.model.Curriculum;
import trainingcenter.model.Student;
import trainingcenter.service.TrainingCenter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Course javaCourse = new Course("Java", 16);
        Course jdbcCourse = new Course("JDBC", 24);
        Course springCourse = new Course("Spring", 16);
        Course testDesignCourse = new Course("Test design", 10);
        Course pageObjectCourse = new Course("Page Object", 16);
        Course seleniumCourse = new Course("Selenium", 16);

        Curriculum javaDeveloperCurriculum = new Curriculum("Java Developer", Arrays.asList(javaCourse, jdbcCourse, springCourse));
        Curriculum aqeCurriculum = new Curriculum("AQE", Arrays.asList(testDesignCourse, pageObjectCourse, seleniumCourse));

        Student ivanovIvan = new Student("Ivanov Ivan", javaDeveloperCurriculum, LocalDate.of(2020, Month.JUNE, 1));
        Student sidorovIvan = new Student("Sidorov Ivan", aqeCurriculum, LocalDate.of(2020, Month.JUNE, 1));

        TrainingCenter trainingCenter = new TrainingCenter();

        LocalDateTime reportDate = LocalDateTime.of(2020, Month.JUNE, 8, 15, 0);

        System.out.println(trainingCenter.generateReport(ivanovIvan, reportDate, false));
        System.out.println(trainingCenter.generateReport(sidorovIvan, reportDate, true));
    }
}