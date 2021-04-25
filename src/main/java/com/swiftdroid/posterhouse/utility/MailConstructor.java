package com.swiftdroid.posterhouse.utility;

import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.swiftdroid.posterhouse.model.Order;
import com.swiftdroid.posterhouse.model.User;

@Component
public class MailConstructor {
	@Autowired
	private Environment env;
	
	@Autowired
	private TemplateEngine templateEngine;
	/*
	public SimpleMailMessage constructResetTokenEmail(
			String contextPath, Locale locale, String token, User user, String password
			) {
		
		String url = contextPath + "/newUser?token="+token;
		String message = "\nPlease click on this link to verify your email and edit your personal information. Your password is: \n"+password;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("Le's Bookstore - New User");
		email.setText(url+message);
		email.setFrom(env.getProperty("support.email"));
		return email;
		
	}
	*/
	
	
	
	public MimeMessagePreparator constructOrderConfirmationEmail (User user, Order order, Locale locale) {
		Context context = new Context();
		context.setVariable("order", order);
		context.setVariable("user", user);
		context.setVariable("cartItemList", order.getCartItemList());
		String text = templateEngine.process("orderConfirmationEmailTemplate", context);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(user.getEmail());
				email.setSubject("Order Confirmation - "+order.getId());
				email.setText(text, true);
				email.setFrom(new InternetAddress("vaibhavbhadeaai@gmail.com"));
			}
		};
		
		return messagePreparator;
	}
	
	

	public MimeMessagePreparator constructPasswordEmail (User user, String password, Locale locale) {
		Context context = new Context();
		context.setVariable("password", password);
		context.setVariable("user", user);
		String text = templateEngine.process("passwordemilTemplate", context);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(user.getEmail());
				email.setSubject("Reset password");
				email.setText(text, true);
				email.setFrom(new InternetAddress("ray.deng83@gmail.com"));
			}
		};
		
		return messagePreparator;
	}
	
	public MimeMessagePreparator constructWelcomeMail (String firstName, String emailAddress,Locale locale) {
		Context context = new Context();
		context.setVariable("firstName", firstName);
		String text = templateEngine.process("welcomeMail", context);
		
		MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
				email.setTo(emailAddress);
				email.setSubject("Welcome From Posterhouse");
				email.setText(text, true);
				email.setFrom(new InternetAddress("ray.deng83@gmail.com"));
			}
		};
		System.out.println(messagePreparator.toString());
		return messagePreparator;
	}
}
