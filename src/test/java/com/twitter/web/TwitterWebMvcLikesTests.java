package com.twitter.web;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.twitter.dto.Credentials;
import com.twitter.dto.UserAuthRes;
import com.twitter.model.User;
import com.twitter.service.AuthService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles("dev")
@AutoConfigureMockMvc
public class TwitterWebMvcLikesTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private AuthService authService;
	
	public String Auth() {
		Credentials credentials = new Credentials();
		credentials.setPassword("root");
		credentials.setUsername("users");
		UserAuthRes authRes = authService.authenticate(credentials);
		return authRes.getToken();
	}
	
	@Test
	public void likePost() throws Exception {
		String format = Auth();
		int id = 2; 
		mockMvc.perform(MockMvcRequestBuilders.post("/api/1.0/likes/"+id).header("Authorization", format))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void existLikePost() throws Exception {
		String format = Auth();
		int id = 2; 
		mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/likes/"+id).header("Authorization", format))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deleteLikePost() throws Exception {
		String format = Auth();
		int id = 2; 
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/1.0/likes/"+id).header("Authorization", format))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getAllLikes() throws Exception {
		String format = Auth();
		int id = 1; 
		mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/likes/all/"+id).header("Authorization", format))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
