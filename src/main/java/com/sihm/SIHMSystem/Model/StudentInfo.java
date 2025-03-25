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
@Table(name = "TBL_SIHMS_STUDENTINFO")
public class StudentInfo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENTID")
    private Long studentId;

    @Column(name = "STUDENTNAME")
    private String studentName;

    @Column(name = "PHONENO")
    private String phoneNo;

    @Column(name = "MAILID")
    private String mailId;

    @Column(name = "PARENTNAME")
    private String parentName;

    @Column(name = "PARENTMOBILE")
    private String parentMobile;

    @Column(name = "PARENTMAIL")
    private String parentMail;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CLASSNO")
    private Integer classNo;
    
    @Column(name = "USERID")
    private Long userId;

    @Column(name = "CREATED_ON")
    private Date createdOn;

    @Column(name = "STATUSFLAG")
    private Integer statusFlag;

}
