package com.bridgelabz.todoApplication.utilservice;
/******************************************************************************************************
 * Created By:Medini P.D
 * Date:- 11/07/2018
 * Purpose:Mail sender class for the login and registration
 *******************************************************************************************************/
import javax.mail.SendFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.bridgelabz.todoApplication.rabbitmq.Mailmodel;
public class MailSender 
{
	@Autowired
	JavaMailSender javaMailSender;
	
/*public void sendMail(String to, String subject, String text) throws SendFailedException 
	{
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(text);
		javaMailSender.send(simpleMailMessage);
	}*/

	public void sendMail(Mailmodel mail) throws SendFailedException 
	{
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setTo(mail.getTo());
		simpleMailMessage.setSubject(mail.getSubject());
		simpleMailMessage.setText(mail.getText());
		javaMailSender.send(simpleMailMessage);
	}
}
