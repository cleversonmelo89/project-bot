package br.com.bot.service.impl;

import java.text.MessageFormat;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private MessageRequestConverter messageRequestConverter;

	@Autowired
	private MessageResponseConverter messageResponseConverter;

	@Autowired
	private BotService botService;

	@Autowired
	private MessageResponseListConverter messageResponseListConverter;

	private ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	public MessageResponseJson createMessage(MessageRequestJson messageJson) {
		try {
			botService.getBotId(messageJson.getFrom());
			botService.getBotId(messageJson.getTo());
		} catch (ResponseStatusException rse) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					MessageFormat.format(resourceBundle.getString("message_not_sending"), rse.getReason()));
		}

		return messageResponseConverter.convert(messageRepository.save(messageRequestConverter.convert(messageJson)));
	}

	public MessageResponseJson getMessageId(String id) {
		Message message = messageRepository.findMessageById(id);
		if (message == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					MessageFormat.format(resourceBundle.getString("message_not_found"), id));
		}
		return messageResponseConverter.convert(message);
	}

	public List<MessageResponseJson> getListMessage(String conversationId) {
		List<Message> listMessage = messageRepository.findMessagesByConversationId(conversationId);
		if (listMessage.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					MessageFormat.format(resourceBundle.getString("conversation_not_found"), conversationId));
		}
		return messageResponseListConverter.convert(listMessage);
	}

}
