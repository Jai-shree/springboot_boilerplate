package com.sample.springboot_boilerplate.service.impl;

import com.sample.springboot_boilerplate.Db.EmployeeHandler;
import com.sample.springboot_boilerplate.dto.MeetingNotesDTO;

import com.sample.springboot_boilerplate.util.CryptoUtils;
import com.sample.springboot_boilerplate.exception.ResourceNotFoundException;
import com.sample.springboot_boilerplate.mapper.OrganizationMapper;
import com.sample.springboot_boilerplate.repository.OrganizationRepository;
import com.sample.springboot_boilerplate.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeHandler employeeHandler;

    public EmployeeServiceImpl(EmployeeHandler employeeHandler) {
        this.employeeHandler = employeeHandler;
    }
 
    @Override
    public List<MeetingNotesDTO> getMeetingNotes(Integer id, String managerEmail, String empEmail){
        List<Object[]> org2 = employeeHandler.getMeetingNotes(id,managerEmail,empEmail);
        List<MeetingNotesDTO> notes = new ArrayList<>();

        for  (Object[] org : org2) {
            MeetingNotesDTO dto = new MeetingNotesDTO();
            String decryptNotes = CryptoUtils.decrypt((String) org[3]);
            dto.setId(Integer.parseInt(Objects.toString(org[0])));
            dto.setEmpEmail((String) org[1]);
            dto.setManagerEmail((String) org[2]);
            dto.setMeetingNotes((String) decryptNotes);
            dto.setAuthoredBy((String) org[4]);
            dto.setMeetingMode((String) org[5]);
            dto.setMeetingDate((Date) org[6]);
            notes.add(dto);
        }
        return notes;
    }

    @Override
    public void addNewMeetingRecord(Integer oid, String reporterEmail, String reporteeEmail, java.util.Date meet_date, String mode, String notes){
        employeeHandler.addNewMeetingRecord(oid, reporterEmail, reporteeEmail, meet_date, mode, notes);
    }    
}
