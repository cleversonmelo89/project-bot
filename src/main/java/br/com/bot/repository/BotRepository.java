package br.com.bot.repository;

import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bot.domain.Bot;

public interface BotRepository extends JpaRepository<Bot, ObjectId>{

}
