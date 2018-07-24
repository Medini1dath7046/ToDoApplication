package com.bridgelabz.todoApplication.userservice;

import javax.validation.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.todoApplication.rabbitmq.Mailmodel;
import com.bridgelabz.todoApplication.rabbitmq.Producer;
import com.bridgelabz.todoApplication.utilservice.JWToken;
import com.bridgelabz.todoApplication.utilservice.PreConditions;
import com.bridgelabz.todoApplication.utilservice.UserExceptionHandler;

@Service
@PropertySource("classpath:messages.properties")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	Mailmodel model;
	@Autowired
	Producer producer;
	@Autowired
	JWToken jwToken;
	@Autowired
	Environment environment;

	@Override
	public void userRegister(UserRegistration userRegistrationDTO) {
		try {
			User user = userRepository.findByEmail(userRegistrationDTO.getEmail());
			PreConditions.CheckNotNull(user);
			user = modelMapper.map(userRegistrationDTO, User.class);
			user.setVerified(false);
			user.setPassword(bCryptPasswordEncoder.encode(userRegistrationDTO.getPassword()));
			userRepository.save(user);
			String token = jwToken.createJWT("Admin", user.getEmail());
			// mailSender.sendMail(user.getEmail(), "Activation Link",
			// environment.getProperty("activation.link") + token);
			model.setTo(user.getEmail());
			model.setSubject("Activation Link");
			model.setText(environment.getProperty("activation.link") + token);
			producer.produceMail(model);
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new UserExceptionHandler(environment.getProperty("email.registered"));
		}
	}

	@Override
	public void userLogin(UserLogin userLoginDTO) {
		User user = userRepository.findByEmail(userLoginDTO.getEmail());
		PreConditions.CheckNull(user);
		if (user.isVerified()) {
			String password = userLoginDTO.getPassword();
			if (!bCryptPasswordEncoder.matches(password, user.getPassword()))
				throw new UserExceptionHandler("Incorrect Password");
		} else {
			throw new UserExceptionHandler("Please Activate Account First");
		}
		jwToken.createJWT("Admin", user.getEmail());
	}

	@Override
	public void activate(String token) {
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		PreConditions.CheckNull(user);
		user.setVerified(true);
		try {
			userRepository.save(user);
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new UserExceptionHandler("Account activation failed, request for new Link");
		}
	}

	@Override
	public void forgotPassword(String email) {
		User user = userRepository.findByEmail(email);
		PreConditions.CheckNull(user);
		String token = jwToken.createJWT("Admin", email);
		model.setSubject("Password Reset Link");
		model.setText(environment.getProperty("new.password.link") + token);
		producer.produceMail(model);
	}

	@Override
	public void resetPassword(String token, String pass) {
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		PreConditions.CheckNull(user);
		user.setPassword(bCryptPasswordEncoder.encode(pass));
		try {
			userRepository.save(user);
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new UserExceptionHandler("Error in changing password, please try again later");
		}
	}
}