package br.com.bot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bot.converter.MessageRequestConverter;
import br.com.bot.converter.MessageResponseConverter;
import br.com.bot.converter.MessageResponseListConverter;
import br.com.bot.json.MessageRequestJson;
import br.com.bot.json.MessageResponseJson;
import br.com.bot.repository.MessageRepository;
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
	private MessageResponseListConverter messageResponseListConverter;

	public MessageResponseJson createMessage(MessageRequestJson messageJson) {
		return messageResponseConverter.convert(messageRepository.save(messageRequestConverter.convert(messageJson)));
	}

	public MessageResponseJson getMessageId(String id) {
		return messageResponseConverter.convert(messageRepository.findMessageById(id));
	}

	public List<MessageResponseJson> getListMessage(String conversationId) {
		return messageResponseListConverter.convert(messageRepository.findMessagesByConversationId(conversationId));
	}

}
