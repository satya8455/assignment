
package com.watsoo.expense.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.watsoo.expense.service.EmailService;

import jakarta.mail.internet.MimeMessage;
 

 
@Service
public class EmailServiceImpl implements EmailService {
 
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendBudgetExceededMail(String email, String name, String categoryName) {
	    Runnable emailTask = () -> {
	        try {
	            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
 
	            String subject = "⚠️ Budget Limit Exceeded for " + categoryName;
 
	            String content = String.format(
	                "Dear %s,<br><br>" +
	                "We noticed that you've exceeded your budget limit for the <b>%s</b> category.<br><br>" +
 
	                "Keeping track of your expenses is crucial for maintaining financial control. " +
	                "Please review your recent spending and adjust your budget if needed.<br><br>" +
 
 
	                "If you have any questions or need assistance, feel free to reach out.<br><br>" +
 
	                "Stay on top of your finances! 💰<br><br>" +
	                "Warm regards,<br>" +
	                "<b>Expense Tracker Team</b>",
 
	                name, categoryName
	            );
 
	            helper.setTo(email);
	            helper.setSubject(subject);
	            helper.setText(content, true);
 
	            javaMailSender.send(mimeMessage);
 
	            System.out.println("Budget exceed email sent successfully to: " + email);
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.err.println("Failed to send budget exceed email to: " + email);
	        }
	    };
 
	    Thread emailThread = new Thread(emailTask);
	    emailThread.setPriority(Thread.NORM_PRIORITY);
	    emailThread.start();
	}
 
 
}
 
 