package com.bridgelabz.todoApplication.userservice;

/************************************************************************************************
 * Created By:Medini P.D
 * Date:- 11/07/2018
 * Purpose:User controller class for the login and registration
 ***********************************************************************************************/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.todoApplication.utilservice.Response;

@RestController
public class UserController {
	@Autowired
	UserServiceImpl userService;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	   private ResponseEntity<Response> register(
		@RequestBody UserRegistration userRegistrationDTO) {
		userService.userRegister(userRegistrationDTO);
		return new ResponseEntity<>(new Response("Registration Success", HttpStatus.CREATED), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	   public ResponseEntity<Response> login(@RequestBody UserLogin userLogin) {
		userService.userLogin(userLogin);
		return new ResponseEntity<>(new Response("Login Success", HttpStatus.OK), HttpStatus.OK);
	}

	@RequestMapping(value = "/activate/{token}", method = RequestMethod.GET)
	    public ResponseEntity<Response> activation(@RequestParam String token) {
		userService.activate(token);
		return new ResponseEntity<>(new Response("Account Activated", HttpStatus.OK), HttpStatus.OK);
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	   public ResponseEntity<Response> forgetpassword(@RequestParam String email) {
		userService.forgotPassword(email);
		return new ResponseEntity<>(new Response("Password Reset Link Sent", HttpStatus.OK), HttpStatus.OK);
	}

	@RequestMapping(value = "/reset-password/{token}", method = RequestMethod.POST)
	    public ResponseEntity<Response> newPassword(@RequestBody String token, @RequestBody String password) {
		userService.resetPassword(token, password);
		return new ResponseEntity<>(new Response("Password Changed Succesfully", HttpStatus.OK), HttpStatus.OK);
	}
}