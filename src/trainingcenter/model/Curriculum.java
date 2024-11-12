package trainingcenter.model;

import java.util.List;

public class Curriculum {
    private String name;
    private List<Course> courses;

    public int getTotalDurationHours() {
        return courses.stream().mapToInt(Course::getDuration).sum();
    }

    public Curriculum(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

}
