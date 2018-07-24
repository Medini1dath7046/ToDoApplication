
package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created By:Medini P.D
 * Date:- 03/07/2018
 * Purpose:
 */
public class ToDo {

	  private MockMvc mockMvc;
		
		@Autowired
	    private WebApplicationContext wac;

		@Before
		public void setup() {
	        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		}
		
		 @Test
	        public void loginTest() throws Exception {
	            mockMvc.perform(MockMvcRequestBuilders.post("/userLogin").
	                    param("email","medinipd@gmail.com").
	                    contentType(MediaType.TEXT_PLAIN_VALUE).param("password", "123")
	                    .contentType(MediaType.TEXT_PLAIN_VALUE))
	                   .andExpect(jsonPath("$.message").value("Login Success"));
	            
		 }
	            
	      /*
			@Test
		   public void registerTest() throws Exception {
		       mockMvc.perform(MockMvcRequestBuilders.post("/save").contentType
		    		   (MediaType.APPLICATION_JSON).content(
		       		"{\"emailId\" : \"111chaitra@gmail.com\", \"password\" : \"chaitra111\",\"phoneNumber\":\"985546235\",\"userId\": \"1\",\"userName\":\"Chaitra\", }")
		               .accept(MediaType.APPLICATION_JSON))
		               .andExpect(jsonPath("$.message").value("Registration Successfull!!"))
		               .andExpect(jsonPath("$.status").value(200));
		   }*/
			/*@Test
		   public void  forgotPasswordTest() throws Exception {
		       mockMvc.perform(MockMvcRequestBuilders.post("/save").contentType(MediaType.APPLICATION_JSON).content(
		       		"{\"emailId\" : \"111chaitra@gmail.com\" }")
		               .accept(MediaType.APPLICATION_JSON))
		               .andExpect(jsonPath("$.message").value("send the user mailid to reset password"))
		               .andExpect(jsonPath("$.status").value(200));
		   }
			@Test
		   public void  resetPasswordTest() throws Exception {
		       mockMvc.perform(MockMvcRequestBuilders.post("/save").contentType(MediaType.APPLICATION_JSON).content(
		       		"{\"newPassword\" : \"111chaitra\",\"confirmPassword\": \"111chaitra\" }")
		               .accept(MediaType.APPLICATION_JSON))
		               .andExpect(jsonPath("$.message").value("password changed successfully!!!"))
		               .andExpect(jsonPath("$.status").value(200));
		   }*/
	}


