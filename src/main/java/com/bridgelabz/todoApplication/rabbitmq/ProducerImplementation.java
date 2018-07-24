package com.bridgelabz.todoApplication.rabbitmq;

/**
 * Created By:Medini P.D
 * Date:- 03/07/2018
 * Purpose:
 */

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.bridgelabz.todoApplication.rabbitmq.Mailmodel;
@Service
public  class ProducerImplementation implements Producer
{


	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${jsa.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${jsa.rabbitmq.routingkey}")
	private String routingKey;
	
	@Override
	public void produceMail(Mailmodel model)
	{
		amqpTemplate.convertAndSend(exchange, routingKey,model);
		System.out.println("Send msg = " + model);
	}
}