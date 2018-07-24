package com.bridgelabz.todoApplication.userservice;

/****************************************************************************************************
 * Created By:Medini P.D
 * Date:- 11/07/2018
 * Purpose: User repository class for the login and registration
 *******************************************************************************************************/
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
	public User findByEmail(String email);
}