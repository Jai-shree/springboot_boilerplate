package com.sample.springboot_boilerplate.Db;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeHandler {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Object[]> getMeetingNotes(Integer id,String managerEmail, String empEmail){
        StringBuilder query = new StringBuilder();
        query.append("select id,emp_email,manager_email,meeting_notes,authored_by,meeting_mode,meeting_date from meeting_notes where org_id=:id and manager_email= :managerEmail and emp_email = :empEmail and authored_by = :managerEmail");
        Query nativeQuery = entityManager.createNativeQuery(query.toString());
        nativeQuery.setParameter("id",id);
        nativeQuery.setParameter("empEmail", empEmail);
        nativeQuery.setParameter("managerEmail",managerEmail);
        return nativeQuery.getResultList();
    }
}

