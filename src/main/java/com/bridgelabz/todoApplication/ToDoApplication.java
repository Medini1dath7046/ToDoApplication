package com.bridgelabz.todoApplication;
/**********************************************************************************************************
 * Created By:Medini P.D
 * Date:- 16/07/2018
 * Purpose: Main class of the ToDo application
 **********************************************************************************************************/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class ToDoApplication {
	public static void main(String[] args) {
		SpringApplication.run(ToDoApplication.class, args);
	}
}
