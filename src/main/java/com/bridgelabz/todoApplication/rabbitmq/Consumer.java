package com.bridgelabz.todoApplication.rabbitmq;

/**
 * Created By:Medini P.D
 * Date:- 03/07/2018
 * Purpose:
 */
import javax.mail.SendFailedException;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.todoApplication.rabbitmq.Mailmodel;
import com.bridgelabz.todoApplication.utilservice.MailSender;

@Service
public class Consumer 
{
	@Autowired
	MailSender javaMailSender;
	@RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void recievedMessage(Mailmodel mail) throws SendFailedException 
	{  
    javaMailSender.sendMail(mail);
    }
}
