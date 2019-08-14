package br.com.bot.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bot.json.BotJson;
import br.com.bot.service.BotService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class BotControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private BotController botController;

	@MockBean
	private BotService botService;

	private static final String PATH_BOT = "/bots";

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(botController).build();
	}

	@Test
	public void testPostCreateBotSucess() throws Exception {

		BotJson botJson = getBotJson();

		Mockito.when(botService.createBot(botJson)).thenReturn(botJson);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post(PATH_BOT).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(new ObjectMapper().writeValueAsString(botJson)))
				.andExpect(MockMvcResultMatchers.status().isCreated());

	}

	@Test
	public void testPostCreateBotConflict() throws Exception {

		ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatus.CONFLICT,
				"Id já existe");

		Mockito.when(botService.createBot(Mockito.any(BotJson.class))).thenThrow(responseStatusException);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post(PATH_BOT).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(new ObjectMapper().writeValueAsString(getBotJson())))
				.andExpect(MockMvcResultMatchers.status().isConflict());

	}

	@Test
	public void testGetBotByIdSuccess() throws Exception {

		BotJson botJson = getBotJson();

		Mockito.when(botService.getBotId("16edd3b3-3f75-40df-af07-2a3813a79ce9")).thenReturn(botJson);

		this.mockMvc.perform(MockMvcRequestBuilders.get(PATH_BOT.concat("/16edd3b3-3f75-40df-af07-2a3813a79ce9")))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGetBotByIdNotFound() throws Exception {

		ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Id não encontrado");

		Mockito.when(botService.getBotId("16edd3b3")).thenThrow(responseStatusException);

		this.mockMvc.perform(MockMvcRequestBuilders.get(PATH_BOT.concat("/16edd3b3")))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	private BotJson getBotJson() {
		return BotJson.builder().id("16edd3b3-3f75-40df-af07-2a3813a79ce9").name("Marley").build();
	}

}
