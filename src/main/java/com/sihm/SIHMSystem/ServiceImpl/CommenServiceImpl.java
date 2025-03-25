/**
 * 
 */
package com.sihm.SIHMSystem.ServiceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sihm.SIHMSystem.Model.AssignHomework;
import com.sihm.SIHMSystem.Model.StudentInfo;
import com.sihm.SIHMSystem.Repository.StudentInfoRepository;
import com.sihm.SIHMSystem.Service.CommonService;
import com.sihm.SIHMSystem.util.CustomCheckedException;

/**
 * 
 */
@Service
public class CommenServiceImpl implements CommonService {
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
    private StudentInfoRepository studentinforepo;
	
	@Autowired
	private Environment env;

	@Override
	public void rqstforcontact() throws CustomCheckedException {
		try {
			//save in table			
			Thread Thread = new Thread(new MyRunnable());
			Thread.start();		
		}catch (Exception e) {
			throw new CustomCheckedException(e);
		}		
	}
	
	class MyRunnable implements Runnable{
			
			private String name;
			private String mobile;
			private String email;
			private String messsage;
	
//			public MyRunnable(String name, String mobile, String email, String messsage) {
//				this.name = name;
//				this.mobile = mobile;
//				this.email = email;
//				this.messsage = messsage;
//			}
			List<String> emailId=Arrays.asList(
					"rajendraprasadsahoo28@gmail.com"
					);	
	
			@Override
			public void run() {
				String subject="Manage Mosaic || Message from User";
				String textmessage="Thank You .";
				SimpleMailMessage message =null;
				
				//send mail to Customer
				message = new SimpleMailMessage();
		        message.setFrom(env.getProperty("spring.mail.username"));
		        message.setTo(email.trim());
				textmessage = "Dear, Student"+name+"\r\n" 
						+ "\r\n" + "Thank you for reaching out to Manage Mosaic. "
						+ "\r\n We have received your message and will get back to you shortly to assist with your request.\r\n"					
						+ "\r\n Should you need any further assistance in the meantime, please feel free to contact us at the following :"+"\r\n"
						+ "\r\n Mobile No: "+"6370178554"
						+ "\r\n Email: "+"managemosaic@gmail.com"
						+ "\r\n Address: "+"Hanspal , Bhubaneswar,\r\n"
								+ "          Odisha-752115 Near Balianta \r\n"
						+ "\r\n We appreciate your patience and look forward to helping you.\r\n"
						+ "\r\n Best regards,"
						+ "\r\n Manage Mosaic";
		        message.setSubject(subject);
		        message.setText(textmessage);
		        mailSender.send(message);		
			}			
		}

	public void sendscheduleClassMail(List<StudentInfo> list, String date, String time) throws CustomCheckedException {
		try {
			//save in table			
			Thread Thread = new Thread(new sendscheduleMail(list,date,time));
			Thread.start();		
		}catch (Exception e) {
			throw new CustomCheckedException(e);
		}		
	}
	
	class sendscheduleMail implements Runnable {
		private List<StudentInfo> list;
		private String date;
		private String time;
		
		public sendscheduleMail(List<StudentInfo> list, String date, String time) {
			this.list=list;
			this.date=date;
			this.time=time;
		}

		@Override
		public void run() {
			String subject="Scheduled Class || SIHM System";
			String textmessage="Thank You .";
			SimpleMailMessage message =null;
			
			for(StudentInfo data:list) {
				message = new SimpleMailMessage();
		        message.setFrom(env.getProperty("spring.mail.username"));		        
		        
				if(data.getMailId() != null) {
					message = new SimpleMailMessage();
			        message.setFrom(env.getProperty("spring.mail.username"));
					message.setTo(data.getMailId().trim());
					textmessage = "Dear Student,\r\n" 
							+ "\r\n" + "This is to inform you that your upcoming class has been scheduled as per the details below:" +"\r\n"
							+ "\r\n Date: "+date
							+ "\r\n Time: "+time +"\r\n"
							+ "\r\n Please ensure your availability and join the class on time. If you have any questions or concerns, feel free to reach out.\r\n"
							+ "\r\n Best regards,"
							+ "\r\n SIHM System";
					message.setSubject(subject);
			        message.setText(textmessage);
			        mailSender.send(message);
				}
				if(data.getParentMail() != null) {
					message = new SimpleMailMessage();
			        message.setFrom(env.getProperty("spring.mail.username"));
					message.setTo(data.getParentMail().trim());
					textmessage = "Dear, Parent\r\n" 
							+ "\r\n" + "This is to inform you that your child's class has been scheduled as follows:"+"\r\n"
							+ "\r\n Date: "+ date
							+ "\r\n Time: "+ time +"\r\n"
							+ "\r\n Kindly ensure that your child attends the class on time. If you have any questions or require further assistance, please feel free to contact us.\r\n"
							+ "\r\n Best regards,"
							+ "\r\n SIHM System";
					message.setSubject(subject);
			        message.setText(textmessage);
			        mailSender.send(message);
				}
			}
		}
	}

	public void sendasignhomeworksMail(List<AssignHomework> list, String date) throws CustomCheckedException {
		try {
			//save in table			
			Thread Thread = new Thread(new sendasignhomeworksMail(list,date));
			Thread.start();		
		}catch (Exception e) {
			throw new CustomCheckedException(e);
		}	
	}
	
	class sendasignhomeworksMail implements Runnable {
		private List<AssignHomework> list;
		private String date;
		
		public sendasignhomeworksMail(List<AssignHomework> list, String date) {
			this.list=list;
			this.date=date;
		}

		@Override
		public void run() {
			String subject="Asign Homework || SIHM System";
			String textmessage="Thank You .";
			SimpleMailMessage message =null;
			
			for(AssignHomework asign:list) {
				StudentInfo data = studentinforepo.findById(asign.getStudentId()).get();
				message = new SimpleMailMessage();
		        message.setFrom(env.getProperty("spring.mail.username"));		        
		        
				if(data.getMailId() != null) {
					message = new SimpleMailMessage();
			        message.setFrom(env.getProperty("spring.mail.username"));
					message.setTo(data.getMailId().trim());
					textmessage = "Dear Student,\r\n" 
							+ "\r\n" + "Your homework has been assigned for "+date 
							+ ". Please log in to our website to view your task and complete it before the due date."+"\r\n"
							+ "\r\n If you have any questions, feel free to reach out.\r\n"
							+ "\r\n Best regards,"
							+ "\r\n SIHM System";
					message.setSubject(subject);
			        message.setText(textmessage);
			        mailSender.send(message);
				}
				if(data.getParentMail() != null) {
					message = new SimpleMailMessage();
			        message.setFrom(env.getProperty("spring.mail.username"));
					message.setTo(data.getParentMail().trim());
					textmessage = "Dear Parent,\r\n" 
							+ "\r\n" + "We would like to inform you that homework has been assigned to your child for "+date 
							+ ". Please ensure they log in to our website to view the task and complete it before the due date."+"\r\n"
							+ "\r\n Your support in keeping track of their assignments is greatly appreciated. If you have any questions, feel free to contact us.\r\n"
							+ "\r\n Best regards,"
							+ "\r\n SIHM System";
					message.setSubject(subject);
			        message.setText(textmessage);
			        mailSender.send(message);
				}
			}
			
		}
		
	}

	public void creditionalmail(String username, String password, StudentInfo studentinfo) {
		try {
			//save in table			
			Thread Thread = new Thread(new creditionalmail(username,password,studentinfo));
			Thread.start();		
		}catch (Exception e) {
			System.out.println(e);
		}			
	}

	class creditionalmail implements Runnable {
		private String username;
		private String password;
		private StudentInfo studentinfo;
		
		public creditionalmail(String username, String password, StudentInfo studentinfo) {
			this.username=username;
			this.password=password;
			this.studentinfo = studentinfo;
		}

		@Override
		public void run() {
			String subject="Credentials  || SIHM System";
			String textmessage="Thank You .";
			SimpleMailMessage message =null;
			
			message = new SimpleMailMessage();
	        message.setFrom(env.getProperty("spring.mail.username"));		        
	        
			if(studentinfo.getMailId() != null) {
				message = new SimpleMailMessage();
		        message.setFrom(env.getProperty("spring.mail.username"));
				message.setTo(studentinfo.getMailId().trim());
				textmessage = "Dear Student,\r\n" 
						+ "\r\n" + "Your account has been created successfully! Below are your login credentials:" +"\r\n"
						+ "\r\n Username: "+username
						+ "\r\n Password: "+password 
						+ "\r\n Website: SIHMS.com"+"\r\n"
						+ "\r\n For security reasons, please change your password after logging in. If you have any issues, feel free to contact support.\r\n"
						+ "\r\n Best regards,"
						+ "\r\n SIHM System";
				message.setSubject(subject);
		        message.setText(textmessage);
		        mailSender.send(message);
			}
			if(studentinfo.getParentMail() != null) {
				message = new SimpleMailMessage();
		        message.setFrom(env.getProperty("spring.mail.username"));
				message.setTo(studentinfo.getParentMail().trim());
				textmessage = "Dear Student,\r\n" 
						+ "\r\n" + "We have created an online account for your child to access assignments and important updates. Below are the login details:" +"\r\n"
						+ "\r\n Username: "+username
						+ "\r\n Password: "+password 
						+ "\r\n Website: SIHMS.com"+"\r\n"
						+ "\r\n For security reasons, please change your password after logging in. If you have any issues, feel free to contact support.\r\n"
						+ "\r\n Best regards,"
						+ "\r\n SIHM System";
				message.setSubject(subject);
		        message.setText(textmessage);
		        mailSender.send(message);
			}
			
		}
		
	}

	

}
