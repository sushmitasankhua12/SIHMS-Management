package com.sihm.SIHMSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sihm.SIHMSystem.Model.StudentInfo;

@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfo, Long> {

	@Query("from StudentInfo where classNo=:classNo and statusFlag = 0")
	List<StudentInfo> getstudentdata(Integer classNo);

}
