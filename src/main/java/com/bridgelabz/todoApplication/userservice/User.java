package com.bridgelabz.todoApplication.userservice;

import java.util.concurrent.atomic.AtomicLong;
/********************************************************************************************************
 * Created By:Medini P.D
 * Date:- 11/07/2018
 * Purpose:User pojo class for the login and registration
 *******************************************************************************************************/
import javax.persistence.Column;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user")
public class User {

@Id
@Column(name = "Id")
	private Long id;
	private static AtomicLong  COUNTER= new AtomicLong();
	@Column(name = "First_Name", length = 15, nullable = false)
	private String firstName;
	@Column(name = "Last_Name", length = 15, nullable = false)
	private String lastName;
	@Column(name = "Email_Id", length = 30, nullable = false, unique = true)
	private String email;
	@Column(name = "Password", length = 250, nullable = false)
	private String password;
	@Column(name = "Verified_Account", length = 5, nullable = false)
	private boolean isVerified;

	public User()
	{
		this.id=COUNTER.incrementAndGet();
	}
	
	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long Id) {
		this.id = Id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
