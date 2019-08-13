package br.com.bot.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bot.domain.Message;

public interface MessageRepository extends JpaRepository<Message, ObjectId> {

	Message findMessageById(ObjectId id);
	
	List<Message> findMessagesByConversationId(Long conversationId);
	
}
