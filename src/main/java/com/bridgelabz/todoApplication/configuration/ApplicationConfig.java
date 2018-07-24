package com.bridgelabz.todoApplication.configuration;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.bridgelabz.todoApplication.utilservice.JWToken;
import com.bridgelabz.todoApplication.utilservice.MailSender;

/********************************************************************************************************
 * Created By:Medini P.D 
 * Date:- 11/07/2018 
 * Purpose: Application config class for  the login and registration application
 **********************************************************************************************************/
@Configuration
public class ApplicationConfig {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
@Bean
public MailSender email()
{
	return new MailSender();
}
	@Bean
	public JWToken jwToken() {
		return new JWToken();
	}
}
