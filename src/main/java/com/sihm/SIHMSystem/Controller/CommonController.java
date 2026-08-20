package com.sihm.SIHMSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sihm.SIHMSystem.Service.CommonService;

/**
 * Rajendra
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api")
public class CommonController {
	
	@Autowired
	private CommonService commenserv;
	
	@PostMapping(value = "/mailtoparent")
	@ResponseBody
	public void rqstforcontact() {
		System.out.println("hi");
		try {
			commenserv.rqstforcontact();
		}catch (Exception e) {
			HttpStatus
			e.printStackTrace();
		}
	}	

}
