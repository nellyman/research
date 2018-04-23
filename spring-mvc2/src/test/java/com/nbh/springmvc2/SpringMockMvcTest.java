package com.nbh.springmvc2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

/**
 * https://spring.io/blog/2014/05/23/preview-spring-security-test-web-security
 */

@RunWith(SpringRunner.class)
@WebMvcTest(HelloWorldController.class)
@AutoConfigureMockMvc()
public class SpringMockMvcTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	//@WithMockUser(username="bob", password = "pwd", roles="ADMIN")
	public void shouldUploadAndRespondWithOK()throws Exception {

		Payload payload = new Payload();
		payload.id=100L;
		payload.name = "payload";
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/upload")
				.with(user("jeff").roles("USER"))
				.contentType("application/json")
				.content(asJsonString(payload)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}


	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
