package com.sample.springboot_boilerplate.Db;
//package com.example.util;

import com.sample.springboot_boilerplate.util.CryptoUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Date;

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

   @Transactional
   public void addNewMeetingRecord(Integer oid, String reporterEmail, String reporteeEmail, Date meet_date, String mode, String empNotes){
      StringBuilder query = new StringBuilder();
      query.append("insert into meeting_notes(org_id, emp_email, manager_Email, meeting_notes, authored_by, meeting_mode, meeting_date) " +
      "values (:oid, :reporteeEmail, :reporterEmail, :empNotes, :reporterEmail, :mode, :meet_date)");
      Query nativeQuery = entityManager.createNativeQuery(query.toString());
      String notes = CryptoUtils.encrypt(empNotes);
      System.out.println(notes);
      nativeQuery.setParameter("oid", oid);
      nativeQuery.setParameter("reporterEmail", reporterEmail);
      nativeQuery.setParameter("reporteeEmail", reporteeEmail);
      nativeQuery.setParameter("empNotes", notes);
      nativeQuery.setParameter("mode", mode);
      nativeQuery.setParameter("meet_date", meet_date);
      nativeQuery.executeUpdate();
      System.out.println(nativeQuery);
   }


/*public List<Object[]> addNewManagerMeetingRecord(Integer oid, String empMail, String mgrMail, Date meet_date, String mode, String mgrNote){
   StringBuilder query = new StringBuilder();
   query.append("insert into t_notes (org_id, emp_mail, man_mail, date_of_meet, meeting_mode, emp_notes, man_notes) values (:oid, :empMail, :mgrMail, :meet_date, :mode, null, :mgrNote");


   Query nativeQuery = entityManager.createNativeQuery(query.toString());
   nativeQuery.setParameter("oid", oid);
   nativeQuery.setParameter("empMail", empMail);
   nativeQuery.setParameter("mgrMail", mgrMail);
   nativeQuery.setParameter("meet_date", meet_date);
   nativeQuery.setParameter("mode", mode);
   nativeQuery.setParameter("mgrNote", mgrNote);
   return nativeQuery.getResultList();
}*/

}

