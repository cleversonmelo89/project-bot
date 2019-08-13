package br.com.bot.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bot.converter.BotConverter;
import br.com.bot.domain.Bot;
import br.com.bot.json.BotJson;
import br.com.bot.repository.BotRepository;
import br.com.bot.service.BotService;

@Service
public class BotServiceImpl implements BotService {

	@Autowired
	private BotRepository botRepository;

	@Autowired
	private BotConverter botConverter;

	public Bot createBot(BotJson botJson) {
		return botRepository.save(botConverter.convert(botJson));
	}

	public Bot getBotId(ObjectId id) {
		return botRepository.findBotById(id);
	}

}
