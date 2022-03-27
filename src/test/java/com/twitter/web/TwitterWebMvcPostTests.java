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

import com.twitter.dto.Credentials;
import com.twitter.dto.UserAuthRes;
import com.twitter.service.AuthService;
import com.twitter.service.PostService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles("dev")
@AutoConfigureMockMvc
public class TwitterWebMvcPostTests {
	
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
	public void getUserPosts() throws Exception {
		String format = Auth();
		String username = "user";
		mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/"+username).header("Authorization",format))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getAuthPost() throws Exception {
		String format = Auth();
		mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/post").header("Authorization", format))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void createPost() throws Exception {
		String format = Auth();
		mockMvc.perform(MockMvcRequestBuilders.post("/api/1.0/post")
				.header("Authorization", format)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"title\": \"deneme\",\"text\": \"deneme\"}")
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deletePost() throws Exception {
		String format =  Auth();
		long id = 4;
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/1.0/post/"+id).header("Authorization", format))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getFriendsPost() throws Exception{
		String format = Auth();
		mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/post/friends").header("Authorization", format))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	

}
