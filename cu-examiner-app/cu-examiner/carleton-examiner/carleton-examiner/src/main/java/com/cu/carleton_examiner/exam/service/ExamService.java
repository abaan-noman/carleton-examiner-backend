package com.cu.carleton_examiner.exam.service;

import com.cu.carleton_examiner.exam.repo.ExamRepository;
import com.cu.carleton_examiner.exam.model.Exam;
import com.cu.carleton_examiner.exam.model.ExamKey;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ExamService {
    private final ExamRepository examRepository;

    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public List<Exam> getExams(){
        return examRepository.findAll();
    }

    public List<Exam> getExamsByDepartment(String query) {
        return examRepository.findAll().stream()
                .filter(exam -> exam.getDepartment().toLowerCase().contains(query.toLowerCase())
                        || exam.getCourse().toLowerCase().equals(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Exam> getExamsByCourse(String course) {
        return examRepository.findAll().stream()
                .filter(exam -> exam.getCourse().toLowerCase().contains(course.toLowerCase())
                        || exam.getDepartment().toLowerCase().contains(course.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Exam addExam(Exam newExam) {
        return examRepository.save(newExam);
    }

    public Exam updateExam(ExamKey examKey, Exam updatedExam) {
        Optional<Exam> optionalExam = examRepository.findByDepartmentAndCourse(examKey.getDepartment(), examKey.getCourse());
        if (optionalExam.isPresent()) {
            Exam currExam = optionalExam.get();
//            currExam.setCourse(examKey.getCourse());
//            currExam.setSection(examKey.getSection());
            currExam.setStart_date(updatedExam.getStart_date());
            currExam.setStart_time(updatedExam.getStart_time());
            currExam.setDuration(updatedExam.getDuration());
            currExam.setEnd_date(updatedExam.getEnd_date());
            currExam.setEnd_time(updatedExam.getEnd_time());
            currExam.setLocation(updatedExam.getLocation());
            currExam.setAvailability(updatedExam.getAvailability());
            return examRepository.save(currExam);
        }
        return null;
    }
    @Transactional
    public void deleteExam(ExamKey examKey){
        Optional<Exam> deletedExam = examRepository.findByDepartmentAndCourse(examKey.getDepartment(), examKey.getCourse());
        if (deletedExam.isPresent()) {
            examRepository.deleteByDepartmentAndCourse(examKey.getDepartment(), examKey.getCourse());
        }
    }

    public List<Exam> getExamsByNames(List<String> examNames) {
        List<String> lowercaseExamNames = examNames.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        return examRepository.findAll().stream()
                .filter(exam -> lowercaseExamNames.stream()
                        .anyMatch(name ->
                                exam.getCourse().toLowerCase().contains(name) ||
                                        (exam.getDepartment() + " " + exam.getCourse()).toLowerCase().contains(name)))
                .collect(Collectors.toList());
    }

    public List<Exam> getExamsByQuery(String query) {
        return examRepository.findAll().stream()
                .filter(exam -> exam.getDepartment().toLowerCase().contains(query.toLowerCase())
                        || exam.getCourse().toLowerCase().contains(query.toLowerCase())
                        || (exam.getDepartment() + " " + exam.getCourse()).toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

}
