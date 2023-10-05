package com.consumer.controller;

import com.consumer.ReportRepository;
import com.consumer.entities.ReportEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
public class MongoDBController {

    private final ReportRepository reportRepository;

    public MongoDBController(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @GetMapping("/get")
    public ResponseEntity<List<ReportEntity>> fetchedReports() {
        try {
            List<ReportEntity> reports = reportRepository.findAll();
            return ResponseEntity.ok(reports);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
