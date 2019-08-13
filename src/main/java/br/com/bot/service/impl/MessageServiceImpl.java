package br.com.bot.service.impl;

import java.util.Collections;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bot.domain.Message;
import br.com.bot.json.MessageJson;
import br.com.bot.repository.MessageRepository;
import br.com.bot.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository messageRepository;

	public Message createMessage(MessageJson messageJson) {

		return Message.builder().build();
	}

	public Message getMessageId(ObjectId id) {

		return Message.builder().build();
	}

	public List<Message> getListMessage(Long conversationId) {

		return Collections.emptyList();
	}

}
