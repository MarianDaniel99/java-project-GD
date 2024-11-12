package trainingcenter.model;

import java.time.LocalDate;

public class Student {
    private String name;
    private Curriculum  curriculum;
    private LocalDate startDate;

    public Student(String name, Curriculum curriculum, LocalDate startDate) {
        this.name = name;
        this.curriculum = curriculum;
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
