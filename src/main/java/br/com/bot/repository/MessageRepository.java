package br.com.bot.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.bot.domain.Message;

public interface MessageRepository extends MongoRepository<Message, ObjectId> {

	Message findMessageById(String id);

	List<Message> findMessagesByConversationId(String conversationId);

}
