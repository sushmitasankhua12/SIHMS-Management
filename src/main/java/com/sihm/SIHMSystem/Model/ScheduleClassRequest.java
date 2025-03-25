/**
 * 
 */
package com.sihm.SIHMSystem.Model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

/**
 * RAJENDRA
 */

@Entity
@Data
@Table(name = "TBL_SIHMS_SCHEDULESCLASS")
public class ScheduleClassRequest {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCHEDULESCLASS", nullable = false, updatable = false)
    private Long ScheduleId;

    @Column(name = "CLASSNO")
    private Integer classNo;

    @Column(name = "SCHEDULEDATE")
    private Date scheduledate;

    @Column(name = "FROMTIME")
    private String fromTime;

    @Column(name = "TOTIME")
    private String toTime;

    @Column(name = "STATUSFLAG")
    private Integer statusFlag;

}
