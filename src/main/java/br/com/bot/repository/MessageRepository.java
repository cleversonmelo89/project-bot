package br.com.bot.repository;

import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bot.domain.Message;

public interface MessageRepository extends JpaRepository<Message, ObjectId> {

}
