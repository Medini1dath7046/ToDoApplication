package com.bridgelabz.todoApplication.rabbitmq;
import com.bridgelabz.todoApplication.rabbitmq.Mailmodel;
/**
 * Created By:Medini P.D
 * Date:- 03/07/2018
 * Purpose:
 */

public interface Producer
{
	void produceMail(Mailmodel mail);
}