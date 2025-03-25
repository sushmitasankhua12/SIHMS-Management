/**
 * 
 */
package com.sihm.SIHMSystem.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * Rajendra
 */

@Entity
@Data
@Table(name = "TBL_SIHMS_USERDETAILS")
public class Userdetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USERID")
    private Long userId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASS_WORD")
    private String passWord;

    @Column(name = "GROUPID")
    private Integer groupId;

    @Column(name = "FULLNAME")
    private String fullName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "CREATED_ON")
    private Date createdOn;

    @Column(name = "STATUSFLAG")
    private Integer statusFlag;

}
