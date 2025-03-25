/**
 * 
 */
package com.sihm.SIHMSystem.Model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * 
 */
@Data
@Entity
@Table(name = "TBL_SIHMS_CLASSDETAILS")
public class ClassNo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLASSID")
    private Long classId;

    @Column(name = "CLASSNAME")
    private String className;

    @Column(name = "STATUSFLAG")
    private Integer statusFlag;

}
