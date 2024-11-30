package com.sample.springboot_boilerplate.service;

import com.sample.springboot_boilerplate.dto.OrganizationDTO;
import com.sample.springboot_boilerplate.dto.ProductDTO;
import com.sample.springboot_boilerplate.dto.MeetingNotesDTO;

import java.util.List;
import java.util.Date;

public interface EmployeeService {
    List<MeetingNotesDTO> getMeetingNotes(Integer id, String managerEmail,String empEmail);

    void addNewMeetingRecord(Integer oid, String reporterEmail, String reporteeEmail, Date meet_date, String mode, String notes);
}


