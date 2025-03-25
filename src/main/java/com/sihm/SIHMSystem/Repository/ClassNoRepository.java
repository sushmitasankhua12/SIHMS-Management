package com.sihm.SIHMSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sihm.SIHMSystem.Model.ClassNo;

@Repository
public interface ClassNoRepository extends JpaRepository<ClassNo, Long> {

	@Query("from ClassNo where statusFlag=0")
	List<ClassNo> findAllactivedata();

	@Query(value = "SELECT CD.CLASSID , CD.CLASSNAME ,COUNT(SI.STUDENTID)\r\n"
			+ "FROM TBL_SIHMS_CLASSDETAILS CD\r\n"
			+ "LEFT JOIN TBL_SIHMS_STUDENTINFO SI ON CD.CLASSID = SI.CLASSNO\r\n"
			+ "WHERE CD.STATUSFLAG = 0\r\n"
			+ "GROUP BY CD.CLASSID , CD.CLASSNAME\r\n"
			+ "ORDER BY CD.CLASSID" , nativeQuery = true)
	List<Object[]> getClassroomdetails();

}
