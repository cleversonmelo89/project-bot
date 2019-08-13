package br.com.bot.service;

import java.util.List;

import br.com.bot.json.MessageRequestJson;
import br.com.bot.json.MessageResponseJson;

public interface MessageService {
	
	MessageResponseJson createMessage(MessageRequestJson messageJson);
	
	MessageResponseJson getMessageId(String id);
	
	List<MessageResponseJson> getListMessage(String conversationId);

}
