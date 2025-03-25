package com.sihm.SIHMSystem.Service;

import java.util.Map;

import com.sihm.SIHMSystem.Model.Userdetails;

public interface Loginservice {

	Map<String, Object> loginapi(Userdetails usermodel) throws Exception;

}
