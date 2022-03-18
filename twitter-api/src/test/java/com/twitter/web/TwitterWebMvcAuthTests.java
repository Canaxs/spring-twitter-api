package com.twitter.web;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.twitter.request.Credentials;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles("dev")
@AutoConfigureMockMvc
public class TwitterWebMvcAuthTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testLogin() throws Exception {
		Credentials credentials = new Credentials();
		credentials.setPassword("root");
		credentials.setUsername("user");
		System.out.println("{\"username\": \"user\",\"password\": \"user\"}");
		mockMvc.perform(MockMvcRequestBuilders.post("/api/1.0/auth")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\": \""+credentials.getUsername()+"\",\"password\": \""+credentials.getPassword()+"\"}")
				)
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testLogout() throws Exception {
		// If you write a valid token to the format variable, there will be no problem.
		
		String format = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIn0.4nWQOrqwQXCH0XfC51FgtfH_gbylQ1PdGG7GZiEkvrG-z8O9Iyq71gKWGzepXNN3qz7F3-fgWh0SfcyAKKmTuQ";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/1.0/auth/logout").header("Authorization",format))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
