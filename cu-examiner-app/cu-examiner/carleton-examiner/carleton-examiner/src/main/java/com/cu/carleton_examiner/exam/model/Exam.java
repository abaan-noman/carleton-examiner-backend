package com.cu.carleton_examiner.exam.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "exam_schedule")
@IdClass(ExamKey.class)
public class Exam implements Serializable {
    @Id
    @Column(name = "department_name")
    private String department;
    @Id
    @Column(name = "course_code")
    private String course;
    @Column(name = "start_date")
    private LocalDate start_date;
    @Column(name = "start_time")
    private LocalTime start_time;
    @Column(name = "duration")
    private Integer duration;
    @Column(name = "end_date")
    private LocalDate end_date;
    @Column(name = "end_time")
    private LocalTime end_time;
    @Column(name = "location")
    private String location;
    @Column(name = "availability")
    private String availability;

    public Exam() {
    }

    public Exam(String department, String course, LocalDate start_date, LocalTime start_time, int duration, LocalDate end_date, LocalTime end_time, String location, String availability) {
        this.department = department;
        this.course = course;
        this.start_date = start_date;
        this.start_time = start_time;
        this.duration = duration;
        this.end_date = end_date;
        this.end_time = end_time;
        this.location = location;
        this.availability = availability;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public Integer getDuration() {
        return duration != null ? duration : 0;
    } // Return 0 or any default value if duration is null

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "department='" + department + '\'' +
                ", course='" + course + '\'' +
                ", start_date=" + start_date +
                ", start_time=" + start_time +
                ", duration=" + duration +
                ", end_date=" + end_date +
                ", end_time=" + end_time +
                ", location='" + location + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }

    public ExamKey getExamKey() {
        return new ExamKey(this.department, this.course);
    }

    public void setExamKey(ExamKey examKey) {
        this.department = examKey.getDepartment();
        this.course = examKey.getCourse();
    }
}