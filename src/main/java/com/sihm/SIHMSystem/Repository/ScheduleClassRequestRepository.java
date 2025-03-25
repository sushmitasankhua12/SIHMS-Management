package com.sihm.SIHMSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sihm.SIHMSystem.Model.ScheduleClassRequest;

@Repository
public interface ScheduleClassRequestRepository extends JpaRepository<ScheduleClassRequest, Long> {

	@Query(value = "SELECT SC.SCHEDULESCLASS,SI.CLASSNAME, TO_CHAR(SCHEDULEDATE,'DD-MON-YYYY'),\r\n"
			+ "    TO_CHAR(TO_DATE(FROMTIME,'HH24:MI'),'HH:MI AM') FROMDATE,\r\n"
			+ "    TO_CHAR(TO_DATE(TOTIME,'HH24:MI'),'HH:MI AM') TODATE\r\n"
			+ "FROM TBL_SIHMS_SCHEDULESCLASS SC\r\n"
			+ "LEFT JOIN TBL_SIHMS_CLASSDETAILS SI ON SC.CLASSNO = SI.CLASSID\r\n"
			+ "ORDER BY SC.SCHEDULESCLASS DESC\r\n"
			+ "FETCH FIRST 10 ROWS ONLY" , nativeQuery = true)
	List<Object[]> get10schedulelist();
	
	@Query(value = "SELECT SC.SCHEDULESCLASS,SI.CLASSNAME, TO_CHAR(SCHEDULEDATE,'DD-MON-YYYY'),\r\n"
			+ "    TO_CHAR(TO_DATE(FROMTIME,'HH24:MI'),'HH:MI AM') FROMDATE,\r\n"
			+ "    TO_CHAR(TO_DATE(TOTIME,'HH24:MI'),'HH:MI AM') TODATE\r\n"
			+ "FROM TBL_SIHMS_SCHEDULESCLASS SC\r\n"
			+ "LEFT JOIN TBL_SIHMS_CLASSDETAILS SI ON SC.CLASSNO = SI.CLASSID\r\n"
			+ "WHERE SCHEDULEDATE BETWEEN TO_DATE(?1,'YYYY-MM-DD') AND TO_DATE(?2,'YYYY-MM-DD')\r\n"
			+ "ORDER BY SC.SCHEDULESCLASS DESC" , nativeQuery = true)
	List<Object[]> getfilterschedulelist(String fromdate, String todate);
}
