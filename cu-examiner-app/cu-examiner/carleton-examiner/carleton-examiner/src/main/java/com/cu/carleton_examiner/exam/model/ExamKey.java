package com.cu.carleton_examiner.exam.model;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;

public class ExamKey implements Serializable {
    @Column(name = "department_name")
    private String department;
    @Column(name = "course_code")
    private String course;

    // Constructors
    public ExamKey() {
    }

    public ExamKey(String department, String course) {
        this.department = department;
        this.course = course;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamKey examKey = (ExamKey) o;
        return Objects.equals(department, examKey.department) &&
                Objects.equals(course, examKey.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, course);
    }
}