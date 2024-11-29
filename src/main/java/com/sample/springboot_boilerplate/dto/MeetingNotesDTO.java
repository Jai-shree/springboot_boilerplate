package com.sample.springboot_boilerplate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MeetingNotesDTO {
    private int id;
    private String empEmail;
    private String managerEmail;
    private String meetingNotes;
    private String authoredBy;
    private String meetingMode;
    private Date meetingDate;
}

