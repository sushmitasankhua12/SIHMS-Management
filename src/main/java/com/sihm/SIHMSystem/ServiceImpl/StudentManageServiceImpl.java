/**
 * 
 */
package com.sihm.SIHMSystem.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sihm.SIHMSystem.Bean.BeanClass;
import com.sihm.SIHMSystem.Bean.Response;
import com.sihm.SIHMSystem.Model.AssignHomework;
import com.sihm.SIHMSystem.Model.ScheduleClassRequest;
import com.sihm.SIHMSystem.Model.StudentInfo;
import com.sihm.SIHMSystem.Model.Userdetails;
import com.sihm.SIHMSystem.Repository.AssignHomeworkRepository;
import com.sihm.SIHMSystem.Repository.ClassNoRepository;
import com.sihm.SIHMSystem.Repository.ScheduleClassRequestRepository;
import com.sihm.SIHMSystem.Repository.StudentInfoRepository;
import com.sihm.SIHMSystem.Repository.UserdetailsRepository;
import com.sihm.SIHMSystem.Service.StudentManageService;

/**
 * Rajendra
 */
@Service
public class StudentManageServiceImpl implements StudentManageService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserdetailsRepository userdetailsrepo;
	
	@Autowired
	private StudentInfoRepository studentinforepo;
	
	@Autowired
	private ScheduleClassRequestRepository scheduleClassrepo;
	
	@Autowired
	private AssignHomeworkRepository assignhomeworkrepo;
	
	@Autowired
	private CommenServiceImpl commenserv;
	
	@Autowired
	private ClassNoRepository classnorep;
	
	@Transactional
	@Override
	public Response addstudent(StudentInfo studentinfo) throws Exception {
		Response response = new Response();
		try {
			Userdetails userdetails = new Userdetails();			
			userdetails.setUsername(null);
			userdetails.setEmail(studentinfo.getMailId());
			userdetails.setFullName(studentinfo.getStudentName());
			userdetails.setGroupId(2);
			userdetails.setPassWord(passwordEncoder.encode("SIHMS@123"));
			userdetails.setMobile(studentinfo.getPhoneNo());
			userdetails.setCreatedOn(new Date());
			userdetails.setStatusFlag(0);
			userdetails = userdetailsrepo.save(userdetails);
			
			studentinfo.setStatusFlag(0);
			studentinfo.setCreatedOn(new Date());
			studentinfo.setUserId(userdetails.getUserId());
			studentinforepo.save(studentinfo);
			
			commenserv.creditionalmail(userdetails.getUsername(),"SIHMS@123",studentinfo);
			
			response.setStatus(200);
			response.setMessage("success");
		}catch (Exception e) {
			throw new Exception(e);
		}
		return response;
	}

	@Override
	public Response getClassroom() throws Exception {
		Response response = new Response();
		try {
			response.setStatus(200);
			response.setMessage("Success");
			response.setData(classnorep.findAllactivedata());			;
		}catch (Exception e) {
			throw new Exception(e);
		}
		return response;
	}

	@Override
	public Response getstudentdata(Integer classNo) throws Exception {
		Response response = new Response();
		try {
			response.setStatus(200);
			response.setMessage("Success");
			response.setData(studentinforepo.getstudentdata(classNo));			;
		}catch (Exception e) {
			throw new Exception(e);
		}
		return response;
	}

	@Override
	public Response getClassroomdetails() throws Exception {
		Response response = new Response();
		try {
			List<Object[]> objarr = classnorep.getClassroomdetails();
			List<Object> objlist = new ArrayList<>();
			for(Object[] obj : objarr) {
				Map<String,Object> map = new HashMap<>();
				map.put("classId", obj[0]);
				map.put("className", obj[1]);
				map.put("count", obj[2]);
				objlist.add(map);
			}
			response.setStatus(200);
			response.setMessage("Success");
			response.setData(objlist);			;
		}catch (Exception e) {
			throw new Exception(e);
		}
		return response;
	}

	@Transactional
	@Override
	public Response scheduleclass(BeanClass beanclass) throws Exception {
		Response response = new Response();
		try {
			ScheduleClassRequest schdelerqst = new ScheduleClassRequest();			
			schdelerqst.setClassNo(Integer.parseInt(beanclass.getClassNo()));
			schdelerqst.setScheduledate(new SimpleDateFormat("yyyy-MM-dd").parse(beanclass.getDate()));
			schdelerqst.setFromTime(beanclass.getFromtime());
			schdelerqst.setToTime(beanclass.getTotime());
			schdelerqst.setStatusFlag(0);
			scheduleClassrepo.save(schdelerqst);
			
			sendscheduleClassMail(schdelerqst);
			
			response.setStatus(200);
			response.setMessage("success");
		}catch (Exception e) {
			throw new Exception(e);
		}
		return response;
	}

	private void sendscheduleClassMail(ScheduleClassRequest schdelerqst) {
		List<String> maillist = new ArrayList<>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm a");
			String date = new SimpleDateFormat("dd-MMM-yyyy").format(schdelerqst.getScheduledate());
			Date formdate = sdf.parse(schdelerqst.getFromTime());
			Date todate = sdf.parse(schdelerqst.getToTime());
			String time = sdf1.format(formdate) + " To " + sdf1.format(todate);
			List<StudentInfo> list = studentinforepo.getstudentdata(schdelerqst.getClassNo());
//			for(StudentInfo data:list) {
//				if(data.getParentMail() != null) {
//					maillist.add(data.getParentMail());
//				}
//				if(data.getMailId() != null) {
//					maillist.add(data.getMailId());
//				}				
//			}
			commenserv.sendscheduleClassMail(list,date,time);
		}catch (Exception e) {
			System.out.println(e);
		}		
	}

	@Override
	public Response getscheduledclass(String fromdate, String todate) throws Exception {
		Response response = new Response();
		try{
			List<Object[]> objarr = new ArrayList<>();
			if(fromdate == null || todate == null || fromdate == "" || todate == "") {
				objarr = scheduleClassrepo.get10schedulelist();
			}else {
				objarr = scheduleClassrepo.getfilterschedulelist(fromdate,todate);
			}
			List<Object> objlist = new ArrayList<>();
			for(Object[] obj : objarr) {
				Map<String,Object> map = new HashMap<>();
				map.put("scheduleId", obj[0]);
				map.put("className", obj[1]);
				map.put("date", obj[2]);
				map.put("fromtime", obj[3]);
				map.put("tptime", obj[4]);
				objlist.add(map);
			}
			response.setStatus(200);
			response.setMessage("Success");
			response.setData(objlist);	
		}catch (Exception e) {
			throw new Exception(e);
		}
		return response;
	}

	@Override
	public Response asignhomework(AssignHomework assignhomework) throws Exception {
		Response response = new Response();
		try {
			List<StudentInfo> studentlist=studentinforepo.getstudentdata(assignhomework.getClassNo());
			List<AssignHomework> assignhomeworklist = new ArrayList<>();
			for(StudentInfo student:studentlist){
				AssignHomework assignhomework1 = new AssignHomework();
				assignhomework1.setClassNo(assignhomework.getClassNo());
				assignhomework1.setStudentId(student.getStudentId());
				assignhomework1.setHomework(assignhomework.getHomework());
				assignhomework1.setDueDate(assignhomework.getDueDate());
				assignhomework1.setCreatedOn(new Date());
				assignhomework1.setStatusFlag(0);
				assignhomeworklist.add(assignhomework1);
			}
			assignhomeworkrepo.saveAll(assignhomeworklist);
			
			sendasignhomeworksMail(assignhomeworklist,assignhomework.getDueDate());
			
			response.setStatus(200);
			response.setMessage("success");
		}catch (Exception e) {
			throw new Exception(e);
		}
		return response;
	}

	@Override
	public Response asignindividulahomework(AssignHomework assignhomework) throws Exception {
		Response response = new Response();
		try {
			assignhomework.setCreatedOn(new Date());
			assignhomework.setStatusFlag(0);
			assignhomeworkrepo.save(assignhomework);
			
			List<AssignHomework> list = Arrays.asList(assignhomework);
			sendasignhomeworksMail(list,assignhomework.getDueDate());
			
			response.setStatus(200);
			response.setMessage("success");
		}catch (Exception e) {
			throw new Exception(e);
		}
		return response;
	}

	private void sendasignhomeworksMail(List<AssignHomework> list, Date date2) {
		try {
			String date = new SimpleDateFormat("dd-MMM-yyyy").format(date2);			
			commenserv.sendasignhomeworksMail(list,date);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	@Override
	public Response getasignhomework(AssignHomework assignhomework) throws Exception {
		Response response = new Response();
		try{
			List<Object[]> objarr = assignhomeworkrepo.getasignhomework(assignhomework.getClassNo(),assignhomework.getStudentId());
			List<Object> objlist = new ArrayList<>();
			for(Object[] obj : objarr) {
				Map<String,Object> map = new HashMap<>();
				map.put("class", obj[0]);
				map.put("student", obj[1]);
				map.put("homework", obj[2]);
				map.put("duedate", obj[3]);
				map.put("submitdate", obj[4]);
				objlist.add(map);
			}
			response.setStatus(200);
			response.setMessage("Success");
			response.setData(objlist);	
		}catch (Exception e) {
			throw new Exception(e);
		}
		return response;
	}

}
