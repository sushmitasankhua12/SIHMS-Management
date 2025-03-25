/**
 * 
 */
package com.sihm.SIHMSystem.Bean;

import lombok.Data;

/**
 * Rajendra
 */

@Data
public class Response {
	
	private Integer status;
	
	private String message;
	
	private Object data;
	
	private String error;

}
