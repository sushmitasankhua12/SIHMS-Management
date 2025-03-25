/**
 * 
 */
package com.sihm.SIHMSystem.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sihm.SIHMSystem.Bean.BeanClass;
import com.sihm.SIHMSystem.Bean.Response;
import com.sihm.SIHMSystem.Model.AssignHomework;
import com.sihm.SIHMSystem.Model.StudentInfo;
import com.sihm.SIHMSystem.Service.CommonService;
import com.sihm.SIHMSystem.Service.StudentManageService;

/**
 * Rajendra
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
public class StudentManageController {
	
	@Autowired
	private StudentManageService studentmanageserv;
	
	@PostMapping(value = "/addstudent")
	@ResponseBody
	public Response addstudent(StudentInfo studentinfo) {
		Response response = new Response();
		try {
			response = studentmanageserv.addstudent(studentinfo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@GetMapping(value = "/getClassroom")
	@ResponseBody
	public Response getClassroom() {
		Response response = new Response();
		try {
			response = studentmanageserv.getClassroom();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@GetMapping(value = "/getClassroomdetails")
	@ResponseBody
	public Response getClassroomdetails() {
		Response response = new Response();
		try {
			response = studentmanageserv.getClassroomdetails();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@GetMapping(value = "/getstudentdata")
	@ResponseBody
	public Response getstudentdata(@RequestParam(value = "classNo" , required = false) Integer classNo) {
		Response response = new Response();
		try {
			if(classNo == null) classNo=0;
			response = studentmanageserv.getstudentdata(classNo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping(value = "/scheduleclass")
	public Response scheduleclass(@RequestBody BeanClass beanclass) {
		Response response = new Response();
		try {
			response = studentmanageserv.scheduleclass(beanclass);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@GetMapping(value = "/getscheduledclass")
	public Response getscheduledclass(
			@RequestParam(value = "fromdate" , required = false) String fromdate,
			@RequestParam(value = "todate" , required = false) String todate) {
		Response response = new Response();
		try {
			response = studentmanageserv.getscheduledclass(fromdate,todate);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@PostMapping(value = "/asignhomework")
	public Response asignhomework(@RequestBody AssignHomework assignhomework) {
		Response response = new Response();
		try {
			response = studentmanageserv.asignhomework(assignhomework);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@PostMapping(value = "/asignindividulahomework")
	public Response asignindividulahomework(@RequestBody AssignHomework assignhomework) {
		Response response = new Response();
		try {
			response = studentmanageserv.asignindividulahomework(assignhomework);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@PostMapping(value = "/getasignhomework")
	public Response getasignhomework(@RequestBody AssignHomework assignhomework) {
		Response response = new Response();
		try {
			response = studentmanageserv.getasignhomework(assignhomework);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
}
