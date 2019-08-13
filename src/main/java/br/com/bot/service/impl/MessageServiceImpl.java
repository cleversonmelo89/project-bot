package br.com.bot.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bot.converter.MessageConverter;
import br.com.bot.domain.Message;
import br.com.bot.json.MessageJson;
import br.com.bot.repository.MessageRepository;
import br.com.bot.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private MessageConverter messageConverter;
	
	public Message createMessage(MessageJson messageJson) {
		return messageRepository.save(messageConverter.convert(messageJson));
	}

	public Message getMessageId(ObjectId id) {
		return messageRepository.findMessageById(id); 
	}

	public List<Message> getListMessage(Long conversationId) {
		return messageRepository.findMessagesByConversationId(conversationId);
	}

}
