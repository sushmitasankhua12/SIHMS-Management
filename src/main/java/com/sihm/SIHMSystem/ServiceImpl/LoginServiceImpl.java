package com.sihm.SIHMSystem.ServiceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sihm.SIHMSystem.Model.Userdetails;
import com.sihm.SIHMSystem.Repository.UserdetailsRepository;
import com.sihm.SIHMSystem.Service.Loginservice;
import com.sihm.SIHMSystem.util.JwtUtil;

@Service
public class LoginServiceImpl implements Loginservice {
	
	@Autowired
	private AuthenticationManager authenticationManager = null;
	
	@Autowired
	private UserdetailsRepository userrepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
    private JwtUtil jwtUtil;

	@Override
	public Map<String, Object> loginapi(Userdetails usermodel) throws Exception {
		Map<String, Object> map=new HashMap<>();
		try {
			Userdetails usermodel1=null;
				usermodel1=userrepo.findByusernameIgnoreCase(usermodel.getUsername());
			if(usermodel1==null) {
				usermodel1=userrepo.findByEmail(usermodel.getUsername());
			}
			if(usermodel1==null) {
				usermodel1=userrepo.findBymobile(usermodel.getUsername());
			}
			if(usermodel1!=null) {
				if(usermodel1.getStatusFlag()==0) {
					Authentication auth = null;
					try {
						auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
								usermodel1.getUsername(), usermodel.getPassWord()));
						if(auth!=null) {
							Map<String,Object> data=new HashMap<>();
							data.put("userId", usermodel1.getUserId());
							data.put("userName", usermodel1.getUsername() != null ? usermodel1.getUsername() : "");
							data.put("phoneNo", usermodel1.getMobile() != null ? usermodel1.getMobile() : "");
							data.put("fullName", usermodel1.getFullName() != null ? usermodel1.getFullName() : "");
							data.put("groupId", usermodel1.getGroupId() != null ? usermodel1.getGroupId() : "");
							data.put("email", usermodel1.getEmail() != null ? usermodel1.getEmail() : "");
						
						map.put("userdata", data);
						map.put("token", "Bearer " + jwtUtil.generateToken(usermodel1.getUsername()));
						map.put("status", HttpStatus.OK.value());
						map.put("message", "Login Successful");
						}else {
							map.put("status", HttpStatus.BAD_REQUEST.value());
							map.put("message", "Authentication Failed");
						}
					}catch (Exception e) {
						e.printStackTrace();						
						map.put("status", HttpStatus.BAD_REQUEST.value());
						map.put("message", "Authentication Failed");
					}					
				}else {
					map.put("status", HttpStatus.BAD_REQUEST.value());
					map.put("message", "Your Account was Deactivated ! Please Contact Support Team !");
				}
			}else {
				map.put("status", HttpStatus.NOT_FOUND.value());
				map.put("message", "User Not Found ! / UnAuthorized Access");
			}		
			
		}catch (Exception e) {
			throw new Exception(e);
		}
		return map;
	}

}
