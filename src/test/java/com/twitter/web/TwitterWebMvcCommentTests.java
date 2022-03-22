package com.twitter.web;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.twitter.request.Credentials;
import com.twitter.request.UserAuthRes;
import com.twitter.service.AuthService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles("dev")
@AutoConfigureMockMvc
public class TwitterWebMvcCommentTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private AuthService authService;
	
	public String Auth() {
		Credentials credentials = new Credentials();
		credentials.setPassword("root");
		credentials.setUsername("user");
		UserAuthRes authRes = authService.authenticate(credentials);
		return authRes.getToken();
	}
	
	@Test
	public void createComment() throws Exception {
		String format = Auth();
		System.out.println("token: "+format);
		long postId = 2;
		mockMvc.perform(MockMvcRequestBuilders.post("/api/1.0/comment/"+postId)
				.header("Authorization", format)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"text\":\"aaa\"}")
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deleteComment() throws Exception {
		String format = Auth();
		long postId = 2;
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/1.0/comment/"+postId)
				.header("Authorization", format)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getAllComment() throws Exception {
		String format = Auth();
		long postId = 2;
		mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/comment/"+postId)
				.header("Authorization", format)
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
