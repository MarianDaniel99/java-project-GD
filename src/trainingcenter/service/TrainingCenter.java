package trainingcenter.service;

import trainingcenter.model.Student;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.EnumSet;
import java.util.Set;

public class TrainingCenter {
    private static final int WORKING_HOURS_PER_DAY = 8;
    private static final LocalTime WORK_START_TIME = LocalTime.of(10, 0);
    private static final LocalTime WORK_END_TIME = LocalTime.of(18, 0);
    private static final Set<DayOfWeek> WORKING_DAYS = EnumSet.range(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);

    public LocalDateTime calculateEndDateTime(Student student) {
        LocalDate startDate = student.getStartDate();
        int totalHours = student.getCurriculum().getTotalDurationHours();
        LocalDateTime endDateTime = LocalDateTime.of(startDate, WORK_START_TIME);

        while (totalHours > 0) {
            if (WORKING_DAYS.contains(endDateTime.getDayOfWeek())) {
                int hoursLeftInDay = (int) ChronoUnit.HOURS.between(endDateTime.toLocalTime(), WORK_END_TIME);
                int hoursToUse = Math.min(totalHours, hoursLeftInDay);
                endDateTime = endDateTime.plusHours(hoursToUse);
                totalHours -= hoursToUse;

                if (totalHours > 0) {
                    endDateTime = endDateTime.plusDays(1).with(WORK_START_TIME);
                }
            } else {
                endDateTime = endDateTime.plusDays(1).with(WORK_START_TIME);
            }
        }
        return endDateTime;
    }

    public String generateReport(Student student, LocalDateTime reportDate, boolean fullReport) {
        LocalDateTime endDateTime = calculateEndDateTime(student);
        StringBuilder report = new StringBuilder();
        report.append(student.getName()).append(" (").append(student.getCurriculum().getName()).append(") - ");
        Duration duration = Duration.between(reportDate, endDateTime);
        if (reportDate.isBefore(endDateTime)) {
            report.append("Training is not finished. ");
            report.append(formatDuration(duration)).append(" are left until the end.");
        } else {
            report.append("Training completed. ");
            report.append(formatDuration(duration.negated())).append(" have passed since the end.");
        }

        if (fullReport) {
            report.append("\nFull report:\n");
            report.append("Name: ").append(student.getName()).append("\n");
            report.append("Working hours: ").append(WORK_START_TIME).append(" - ").append(WORK_END_TIME).append("\n");
            report.append("Curriculum: ").append(student.getCurriculum().getName()).append("\n");
            report.append("Total Duration (hours): ").append(student.getCurriculum().getTotalDurationHours()).append("\n");
            report.append("Start Date: ").append(student.getStartDate()).append("\n");
            report.append("End Date: ").append(endDateTime.toLocalDate()).append("\n");
            report.append("Time Remaining/Elapsed: ").append(formatDuration(duration));
        }
        return report.toString();
    }

    private String formatDuration(Duration duration) {
        long days = duration.toDays();
        long hours = duration.toHoursPart();
        return days + " d " + hours + " hours";
    }
}
