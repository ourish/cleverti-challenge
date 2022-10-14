package com.ticketmachine;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TrainStationApplicationTests {

	public static final String BASE_URL = "/api/v1/trainticketmachine/";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void whenGet_BaseUrl_returnAllStations() throws Exception {
		mockMvc.perform(get(BASE_URL))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", Matchers.hasSize(100)));
	}

	@Test
	void whenGet_partialStationName_returnMatchingStations() throws Exception {
		mockMvc.perform(get(BASE_URL + "Ave"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(""
						+ "{\"nextPossibleCharacters\":[\"i\",\"l\"],\"matchingStations\":[\"Aveiro\",\"Aveleda\"]}"));
	}

	@Test
	void whenGet_fullStationName_onlyReturnMatchedStation() throws Exception {
		mockMvc.perform(get(BASE_URL + "Aveiro"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(""
						+ "{\"matchingStations\":[\"Aveiro\"]}"));
	}

}
