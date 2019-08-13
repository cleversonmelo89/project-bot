package br.com.bot.service;

import java.util.List;

import org.bson.types.ObjectId;

import br.com.bot.domain.Message;
import br.com.bot.json.MessageJson;

public interface MessageService {
	
	Message createMessage(MessageJson messageJson);
	
	Message getMessageId(ObjectId id);
	
	List<Message> getListMessage(Long conversationId);

}
