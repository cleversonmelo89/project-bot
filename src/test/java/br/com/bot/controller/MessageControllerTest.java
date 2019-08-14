package br.com.bot.controller;

import java.util.ArrayList;
import java.util.List;

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

import br.com.bot.json.MessageRequestJson;
import br.com.bot.json.MessageResponseJson;
import br.com.bot.service.MessageService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class MessageControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private MessageController messageController;

	@MockBean
	private MessageService messageService;

	private static final String PATH_MESSAGE = "/messages";

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
	}

	@Test
	public void testPostCreateMessageSucess() throws Exception {

		Mockito.when(messageService.createMessage(Mockito.any(MessageRequestJson.class)))
				.thenReturn(getMessageResponse());

		this.mockMvc.perform(MockMvcRequestBuilders.post(PATH_MESSAGE).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(getMessageRequest())).andExpect(MockMvcResultMatchers.status().isCreated());

	}

	@Test
	public void testPostMessageUnprocessable() throws Exception {

		ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
				"Id usuario não encontrado mensagem não enviada");

		Mockito.when(messageService.createMessage(Mockito.any(MessageRequestJson.class)))
				.thenThrow(responseStatusException);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post(PATH_MESSAGE).contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(getMessageRequest()))
				.andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());

	}

	@Test
	public void testGetMessageByIdSuccess() throws Exception {

		Mockito.when(messageService.getMessageId("16edd3b3-3f75-40df-af07-2a3813a79ce9"))
				.thenReturn(getMessageResponse());

		this.mockMvc.perform(MockMvcRequestBuilders.get(PATH_MESSAGE.concat("/16edd3b3-3f75-40df-af07-2a3813a79ce9")))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void testGetMessageByIdNotFound() throws Exception {

		ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Id mensagem não encontrado");

		Mockito.when(messageService.getMessageId("16edd3b3")).thenThrow(responseStatusException);

		this.mockMvc.perform(MockMvcRequestBuilders.get(PATH_MESSAGE.concat("/16edd3b3")))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	@Test
	public void testGetMessagesByConversationIdSuccess() throws Exception {

		Mockito.when(messageService.getListMessage("16edd3b3-3f75-40df-af07-2a3813a79ce9"))
				.thenReturn(getListMessageResponse());

		this.mockMvc
				.perform(MockMvcRequestBuilders
						.get(PATH_MESSAGE.concat("?conversationId=16edd3b3-3f75-40df-af07-2a3813a79ce9")))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void testGetMessagesByConversationIdNotFound() throws Exception {

		ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Id da conversa não encontrado");

		Mockito.when(messageService.getListMessage("16edd3b3")).thenThrow(responseStatusException);

		this.mockMvc.perform(MockMvcRequestBuilders.get(PATH_MESSAGE.concat("?conversationId=16edd3b3")))
				.andExpect(MockMvcResultMatchers.status().isNotFound());

	}

	private MessageResponseJson getMessageResponse() {
		return MessageResponseJson.builder().id("16edd3b3-3f75-40df-af07-2a3813a79ce9")
				.conversationId("7665ada8-3448-4acd-a1b7-d688e68fe9a1").from("16edd3b3-3f75-40df-af07-2a3813a79ce9")
				.to("16edd3b3-3f75-40df-af07-2a3813a79ce8").text("Oi !Como posso te ajudar?").build();
	}

	private List<MessageResponseJson> getListMessageResponse() {
		List<MessageResponseJson> listResponseJson = new ArrayList<MessageResponseJson>();
		listResponseJson.add(getMessageResponse());
		return listResponseJson;
	}

	private String getMessageRequest() {
		return "{\"conversationId\":\"7665ada8-3448-4acd-a1b7-d688e68fe9a1\",\"timestamp\":\"2018-11-16T23:30:52.6917722Z\",\"from\":\"16edd3b3-3f75-40df-af07-2a3813a79ce9\",\"to\":\"36b9f842-ee97-11e8-9443-0242ac120002\",\"text\":\"Gostaria de saber meu saldo do cartão?\"}";
	}

}
