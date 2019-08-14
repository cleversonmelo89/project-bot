package br.com.bot.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import br.com.bot.converter.MessageRequestConverter;
import br.com.bot.converter.MessageResponseConverter;
import br.com.bot.converter.MessageResponseListConverter;
import br.com.bot.domain.Message;
import br.com.bot.json.MessageRequestJson;
import br.com.bot.json.MessageResponseJson;
import br.com.bot.repository.MessageRepository;
import br.com.bot.service.BotService;
import br.com.bot.service.MessageService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MessateServiceTest {

	@Autowired
	private MessageService messageService;

	@MockBean
	private MessageRepository messageRepository;

	@MockBean
	private MessageRequestConverter messageRequestConverter;

	@MockBean
	private MessageResponseConverter messageResponseConverter;

	@MockBean
	private MessageResponseListConverter messageResponseListConverter;

	@MockBean
	private BotService botService;

	@Test
	public void testSaveMessageSuccess() {
		MessageRequestJson requestJson = getMessageRequestJson();
		MessageResponseJson responseJson = getMessageResponse();
		responseJson.setTimestamp(requestJson.getTimestamp());
		Message message = getMessage();
		message.setId(responseJson.getId());
		message.setTimestamp(requestJson.getTimestamp());

		Mockito.when(messageRequestConverter.convert(requestJson)).thenReturn(message);
		Mockito.when(messageRepository.save(message)).thenReturn(message);
		Mockito.when(messageResponseConverter.convert(message)).thenReturn(responseJson);

		MessageResponseJson returnMessageResponseJson = messageService.createMessage(requestJson);
		assertEquals(requestJson.getConversationId(), returnMessageResponseJson.getConversationId());
		assertEquals(requestJson.getFrom(), returnMessageResponseJson.getFrom());
		assertEquals(requestJson.getTo(), returnMessageResponseJson.getTo());
		assertEquals(requestJson.getText(), returnMessageResponseJson.getText());

	}

	@Test(expected = ResponseStatusException.class)
	public void testSaveMessageUnprocessable() {

		ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatus.NOT_FOUND,
				"id não existe");

		Mockito.when(botService.getBotId(Mockito.anyString())).thenThrow(responseStatusException);

		messageService.createMessage(getMessageRequestJson());

	}

	@Test
	public void testGetMessageByIdSuccess() {
		MessageResponseJson responseJson = getMessageResponse();
		Message message = getMessage();
		message.setId(responseJson.getId());

		Mockito.when(messageRepository.findMessageById("16edd3b3-3f75-40df-af07-2a3813a79ce7")).thenReturn(message);
		Mockito.when(messageResponseConverter.convert(message)).thenReturn(responseJson);

		MessageResponseJson returnMessageResponseJson = messageService
				.getMessageId("16edd3b3-3f75-40df-af07-2a3813a79ce7");

		assertEquals(responseJson.getConversationId(), returnMessageResponseJson.getConversationId());
		assertEquals(responseJson.getFrom(), returnMessageResponseJson.getFrom());
		assertEquals(responseJson.getTo(), returnMessageResponseJson.getTo());
		assertEquals(responseJson.getText(), returnMessageResponseJson.getText());

	}

	@Test(expected = ResponseStatusException.class)
	public void testGetMessageByIdNotFound() {
		MessageResponseJson responseJson = getMessageResponse();
		Message message = getMessage();
		message.setId(responseJson.getId());

		Mockito.when(messageRepository.findMessageById("16edd3b3-3f75-40df-af07-2a3813a79ce7")).thenReturn(null);

		messageService.getMessageId("16edd3b3-3f75-40df-af07-2a3813a79ce7");

	}

	@Test
	public void testGetConversationMessageByConversationIdSuccess() {
		List<MessageResponseJson> responseListJson = getListMessageResponse();
		List<Message> listMessage = getListMessage();
		listMessage.get(0).setId(responseListJson.get(0).getId());

		Mockito.when(messageRepository.findMessagesByConversationId("16edd3b3-3f75-40df-af07-2a3813a79ce7"))
				.thenReturn(listMessage);
		Mockito.when(messageResponseListConverter.convert(listMessage)).thenReturn(responseListJson);

		List<MessageResponseJson> returnMessageResponseJson = messageService
				.getListMessage("16edd3b3-3f75-40df-af07-2a3813a79ce7");

		assertEquals(responseListJson.get(0).getConversationId(), returnMessageResponseJson.get(0).getConversationId());
		assertEquals(responseListJson.get(0).getFrom(), returnMessageResponseJson.get(0).getFrom());
		assertEquals(responseListJson.get(0).getTo(), returnMessageResponseJson.get(0).getTo());
		assertEquals(responseListJson.get(0).getText(), returnMessageResponseJson.get(0).getText());

	}

	@Test(expected = ResponseStatusException.class)
	public void testGetConversationMessageByConversationIdIsEmpty() {

		Mockito.when(messageRepository.findMessagesByConversationId("16edd3b3-3f75-40df-af07-2a3813a79ce7"))
				.thenReturn(Collections.emptyList());

		messageService.getListMessage("16edd3b3-3f75-40df-af07-2a3813a79ce7");

	}

	private MessageResponseJson getMessageResponse() {
		return MessageResponseJson.builder().id("16edd3b3-3f75-40df-af07-2a3813a79ce7")
				.conversationId("7665ada8-3448-4acd-a1b7-d688e68fe9a1").from("16edd3b3-3f75-40df-af07-2a3813a79ce9")
				.to("16edd3b3-3f75-40df-af07-2a3813a79ce8").text("Oi !Como posso te ajudar?").build();
	}

	private List<MessageResponseJson> getListMessageResponse() {
		List<MessageResponseJson> listResponseJson = new ArrayList<MessageResponseJson>();
		listResponseJson.add(getMessageResponse());
		return listResponseJson;
	}

	private MessageRequestJson getMessageRequestJson() {
		return MessageRequestJson.builder().conversationId("7665ada8-3448-4acd-a1b7-d688e68fe9a1")
				.timestamp(LocalDateTime.now()).from("16edd3b3-3f75-40df-af07-2a3813a79ce9")
				.to("16edd3b3-3f75-40df-af07-2a3813a79ce8").text("Oi !Como posso te ajudar?").build();
	}

	private Message getMessage() {
		return Message.builder()._id(ObjectId.get()).id(UUID.randomUUID().toString())
				.conversationId("7665ada8-3448-4acd-a1b7-d688e68fe9a1").from("16edd3b3-3f75-40df-af07-2a3813a79ce9")
				.to("16edd3b3-3f75-40df-af07-2a3813a79ce8").text("Oi !Como posso te ajudar?").build();

	}

	private List<Message> getListMessage() {
		List<Message> listMessage = new ArrayList<Message>();
		listMessage.add(getMessage());
		return listMessage;
	}

}
