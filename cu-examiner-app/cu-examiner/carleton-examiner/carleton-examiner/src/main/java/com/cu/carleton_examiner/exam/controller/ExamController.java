package com.cu.carleton_examiner.exam.controller;

import com.cu.carleton_examiner.exam.service.ExamService;
import com.cu.carleton_examiner.exam.model.Exam;
import com.cu.carleton_examiner.exam.model.ExamKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/exam")
public class ExamController {
    private final ExamService examService;

    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public List<Exam> getExams(@RequestParam(required = false) String query) {
        if (query != null) {
            return examService.getExamsByQuery(query);
        } else {
            return examService.getExams();
        }
    }


    @GetMapping("/multiple")
    public List<Exam> getExamsByNames(@RequestParam("names") String names) {
        List<String> examNames = List.of(names.split(","));
        return examService.getExamsByNames(examNames);
    }

    @PostMapping
    public ResponseEntity<Exam> addExam(@RequestBody Exam newExam) {
        Exam createdExam = examService.addExam(newExam);
        return new ResponseEntity<>(createdExam, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Exam> updateExam(@RequestParam String department, @RequestParam String course, @RequestBody Exam updatedExam) {
        // Create an ExamKey object based on the course and section
        ExamKey examKey = new ExamKey(department, course);

        // Pass the composite key and the updated exam details to the service method
        Exam resultExam = examService.updateExam(examKey, updatedExam);

        if (resultExam != null) {
            return new ResponseEntity<>(resultExam, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteExamsByClassName(@RequestParam(required = false) String department, @RequestParam(required = false) String course) {
        if (department != null && course != null) {
            ExamKey examKey = new ExamKey(department, course);
            examService.deleteExam(examKey);
            return ResponseEntity.noContent().build(); // Return 204 No Content on successful deletion
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
