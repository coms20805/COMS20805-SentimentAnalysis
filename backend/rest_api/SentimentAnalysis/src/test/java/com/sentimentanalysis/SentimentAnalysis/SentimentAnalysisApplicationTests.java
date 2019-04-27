package com.sentimentanalysis.SentimentAnalysis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SentimentAnalysisApplicationTests {

	@Autowired
	private QueryController queryController;
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
		assertThat(queryController).isNotNull();
		assertThat(mockMvc).isNotNull();
	}

	@Test
	public void returnSearchJSON() throws Exception {
		this.mockMvc.perform(get("/api/search?query=a")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void returnMediansJSON() throws Exception {
		this.mockMvc.perform(get("/api/median?query=a")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void returnValidListOfPosts() throws Exception{
		this.mockMvc.perform(get("/api/search?query=java")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.rating", is(notNullValue())))
				.andExpect(jsonPath("$.rating", greaterThanOrEqualTo(Double.valueOf(-1.0))))
				.andExpect(jsonPath("$.rating", lessThanOrEqualTo(Double.valueOf(1.0))))
				.andExpect(jsonPath("$.rating", is(notNullValue())))
				.andExpect(jsonPath("$.posts").isArray())
				.andExpect(jsonPath("$.posts[0].*", hasSize(4)))
				.andExpect(jsonPath("$.posts[0].content", not(isEmptyOrNullString())))
				.andExpect(jsonPath("$.posts[0].timestamp", not(isEmptyOrNullString())))
				.andExpect(jsonPath("$.posts[0].url", not(isEmptyOrNullString())))
				.andExpect(jsonPath("$.posts[0].score", is(notNullValue())))
				.andExpect(jsonPath("$.posts[0].score", greaterThanOrEqualTo(Double.valueOf(-1.0))))
				.andExpect(jsonPath("$.posts[0].score", lessThanOrEqualTo(Double.valueOf(1.0))));
	}

	@Test
	public void returnValidListOfMedians() throws Exception{
		this.mockMvc.perform(get("/api/median?query=java")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.timestamps").isArray())
				.andExpect(jsonPath("$.timestamps[0]", not(isEmptyOrNullString())))
				.andExpect(jsonPath("$.medians").isArray())
				.andExpect(jsonPath("$.medians[0]", greaterThanOrEqualTo(Double.valueOf(-1.0))))
				.andExpect(jsonPath("$.medians[0]", lessThanOrEqualTo(Double.valueOf(1.0))));
	}

}
