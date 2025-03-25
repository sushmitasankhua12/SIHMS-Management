package com.sihm.SIHMSystem.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sihm.SIHMSystem.Model.Userdetails;
import com.sihm.SIHMSystem.Service.Loginservice;
import com.sihm.SIHMSystem.util.EncryptionUtils;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/login")
public class LoginController {
	
	@Autowired
	private Loginservice loginserv;

	@PostMapping("/loginapi")
	public ResponseEntity<?> loginapi(@RequestBody Userdetails usermodel) {
		Map<String,Object> response=new HashMap<>();
		try {
			usermodel.setUsername(EncryptionUtils.decryptCode(usermodel.getUsername()));
			usermodel.setPassWord(EncryptionUtils.decryptCode(usermodel.getPassWord()));
			response=loginserv.loginapi(usermodel);
		}catch (Exception e) {
			response.put("stratus", HttpStatus.BAD_REQUEST);
			response.put("message", "Something Went Wrong !");
			response.put("error", e.getMessage());
		}
		return ResponseEntity.ok(response);
	}
}
