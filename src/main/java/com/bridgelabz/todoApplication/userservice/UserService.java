package com.bridgelabz.todoApplication.userservice;

/*******************************************************************************************
 * Created By:Medini P.D 
 * Date:- 11/07/2018 
 * Purpose: User Service class for the
 * login and registration
 ********************************************************************************************/
public interface UserService {

	public void userRegister(UserRegistration userRegistrationDTO);
    public void userLogin(UserLogin userLogin);
	public void activate(String token);
	public void forgotPassword(String email);
	public void resetPassword(String token, String pass);

}