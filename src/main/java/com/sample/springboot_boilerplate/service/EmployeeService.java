package com.sample.springboot_boilerplate.service;

import com.sample.springboot_boilerplate.dto.OrganizationDTO;
import com.sample.springboot_boilerplate.dto.ProductDTO;
import com.sample.springboot_boilerplate.dto.MeetingNotesDTO;

import java.util.List;

public interface EmployeeService {
    List<MeetingNotesDTO> getMeetingNotes(Integer id, String managerEmail,String empEmail);
}

