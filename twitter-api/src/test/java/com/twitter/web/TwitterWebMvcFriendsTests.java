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
@Transactional
public class TwitterWebMvcFriendsTests {

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
	public void friendRequest() throws Exception {
		long id = 2;
		String format = Auth();
		mockMvc.perform(MockMvcRequestBuilders.post("/api/1.0/friends")
				.header("Authorization",format)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id2\":"+id+"}")
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getConfigUsers() throws Exception {
		String format = Auth();
		mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/friends").header("Authorization",format))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getUsers() throws Exception {
		String user = "users";
		String format = Auth();
		mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/friends/"+user).header("Authorization",format))
			.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void acceptRequest() throws Exception {
		long id = 1;
		String format = Auth();
		mockMvc.perform(MockMvcRequestBuilders.post("/api/1.0/friends/accept")
				.header("Authorization",format)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id2\":"+id+"}")
				)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
