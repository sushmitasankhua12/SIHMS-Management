/**
 * 
 */
package com.sihm.SIHMSystem.Model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 
 */
@Entity
@Data
@Table(name = "TBL_SIHMS_ASIGN_HOMEWORK")
public class AssignHomework {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOMEWORK_ID")
    private Long homeworkId;

    @Column(name = "CLASSNO")
    private Integer classNo;

    @Column(name = "STUDENT_ID")
    private Long studentId;

    @Column(name = "HOMEWORK")
    private String homework;

    @Column(name = "DUE_DATE")
    private Date dueDate;

    @Column(name = "CREATED_ON")
    private Date createdOn;

    @Column(name = "STATUSFLAG")
    private Integer statusFlag;
}
