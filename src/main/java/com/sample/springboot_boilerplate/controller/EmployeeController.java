package com.sample.springboot_boilerplate.controller;

import com.sample.springboot_boilerplate.dto.OrganizationDTO;
import com.sample.springboot_boilerplate.dto.ProductDTO;
import com.sample.springboot_boilerplate.dto.MeetingNotesDTO;
import com.sample.springboot_boilerplate.dto.FeedbackDTO;
import com.sample.springboot_boilerplate.exception.ResourceNotFoundException;
import com.sample.springboot_boilerplate.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;



import java.util.List;

@RestController
@RequestMapping("/api/org")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController{

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/get/{id}/manager/{managerEmail}/employee/{empEmail}/meeting_notes")
    public ResponseEntity<List<MeetingNotesDTO>> getMeetingNotes(@PathVariable("id") Integer id, @PathVariable("managerEmail") String managerEmail , @PathVariable("empEmail") String empEmail){
        List<MeetingNotesDTO> notes = employeeService.getMeetingNotes(id,managerEmail,empEmail);
        return ResponseEntity.ok(notes);
    }

    @PostMapping("/{org_id}/{empEmail}/enterFeedback")
    public ResponseEntity<?> employeeEnterNotes(@PathVariable("org_id") Integer org_id, @PathVariable("empEmail") String empEmail, @RequestBody FeedbackDTO feedback){
        employeeService.addNewMeetingRecord(org_id, empEmail, feedback.getReporteeEmail(), feedback.getMeetingDate(), feedback.getMode(), feedback.getFeedbackEntered());
        return ResponseEntity.status(200).body("Notes added successfully.");
    }

}
