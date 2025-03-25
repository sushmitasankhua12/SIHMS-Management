package com.sihm.SIHMSystem.Service;

import com.sihm.SIHMSystem.Bean.BeanClass;
import com.sihm.SIHMSystem.Bean.Response;
import com.sihm.SIHMSystem.Model.AssignHomework;
import com.sihm.SIHMSystem.Model.StudentInfo;

public interface StudentManageService {

	Response addstudent(StudentInfo studentinfo) throws Exception;

	Response getClassroom() throws Exception;

	Response getstudentdata(Integer classNo) throws Exception;

	Response getClassroomdetails() throws Exception;

	Response scheduleclass(BeanClass beanclass) throws Exception;

	Response getscheduledclass(String fromdate, String todate) throws Exception;

	Response asignhomework(AssignHomework assignhomework) throws Exception;

	Response asignindividulahomework(AssignHomework assignhomework) throws Exception;

	Response getasignhomework(AssignHomework assignhomework) throws Exception;

}
