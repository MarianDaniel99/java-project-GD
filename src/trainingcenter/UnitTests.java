package trainingcenter;

import org.junit.Test;
import trainingcenter.model.Course;
import trainingcenter.model.Curriculum;
import trainingcenter.model.Student;
import trainingcenter.service.TrainingCenter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UnitTests {
    @Test
    public void testGenerateFullReport() {
        Student student = new Student("Ivanov Ivan", new Curriculum("Java Developer",
                List.of(new Course("Java", 16), new Course("JDBC", 24), new Course("Spring", 16))),
                LocalDate.of(2020, Month.JUNE, 1));
        TrainingCenter center = new TrainingCenter();
        String report = center.generateReport(student, LocalDateTime.of(2020, Month.JUNE, 8, 15, 0), true);

        String expectedReport = "Ivanov Ivan (Java Developer) - Training is not finished. 1 d 3 hours are left until the end.\n" +
                "Full report:\n" +
                "Name: Ivanov Ivan\n" +
                "Working hours: 10:00 - 18:00\n" +
                "Curriculum: Java Developer\n" +
                "Total Duration (hours): 56\n" +
                "Start Date: 2020-06-01\n" +
                "End Date: 2020-06-09\n" +
                "Time Remaining/Elapsed: 1 d 3 hours";

        assertEquals(expectedReport, report);
    }

    @Test
    public void testGenerateShortReport() {
        Student student = new Student("Ivanov Ivan", new Curriculum("Java Developer",
                List.of(new Course("Java", 16), new Course("JDBC", 24), new Course("Spring", 16))),
                LocalDate.of(2020, Month.JUNE, 1));
        TrainingCenter center = new TrainingCenter();
        String report = center.generateReport(student, LocalDateTime.of(2020, Month.JUNE, 8, 15, 0), false);

        assertEquals("Ivanov Ivan (Java Developer) - Training is not finished. 1 d 3 hours are left until the end.", report);
    }
}
