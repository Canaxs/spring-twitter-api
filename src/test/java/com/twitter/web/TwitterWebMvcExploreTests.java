package com.twitter.web;

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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@ActiveProfiles("dev")
@AutoConfigureMockMvc
public class TwitterWebMvcExploreTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void getHashTag() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/explore"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void getHashTagNamePosts() throws Exception {
		String hashTagName = "deneme";
		mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/explore/"+hashTagName))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
