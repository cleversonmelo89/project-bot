package br.com.bot.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.bot.domain.Bot;

public interface BotRepository extends MongoRepository<Bot, ObjectId> {

	Bot findBotById(String id);
	
}
