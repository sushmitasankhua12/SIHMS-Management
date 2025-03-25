package com.sihm.SIHMSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sihm.SIHMSystem.Model.AssignHomework;

@Repository
public interface AssignHomeworkRepository extends JpaRepository<AssignHomework, Long> {

	@Query(value = "SELECT SC.CLASSNAME, SI.STUDENTNAME ,AH.HOMEWORK,\r\n"
			+ "TO_CHAR(AH.DUE_DATE,'DD-MON-YYYY'),TO_CHAR(AH.CREATED_ON,'DD-MON-YYYY')\r\n"
			+ "FROM TBL_SIHMS_ASIGN_HOMEWORK AH\r\n"
			+ "LEFT JOIN TBL_SIHMS_CLASSDETAILS SC ON AH.CLASSNO = SC.CLASSID\r\n"
			+ "LEFT JOIN TBL_SIHMS_STUDENTINFO SI ON AH.STUDENT_ID = SI.STUDENTID\r\n"
			+ "WHERE AH.STATUSFLAG = 0 AND SC.STATUSFLAG = 0 AND SI.STATUSFLAG = 0 \r\n"
			+ "AND AH.CLASSNO = DECODE(?1,NULL,AH.CLASSNO,?1)\r\n"
			+ "AND AH.STUDENT_ID = DECODE(?2,NULL,AH.STUDENT_ID,?2)\r\n"
			+ "AND AH.DUE_DATE >= TRUNC(SYSDATE)\r\n"
			+ "ORDER BY AH.HOMEWORK_ID DESC" , nativeQuery = true)
	List<Object[]> getasignhomework(Integer classNo, Long studentId);

}
