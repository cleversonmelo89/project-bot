package br.com.bot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bot.domain.Bot;
import br.com.bot.json.BotJson;
import br.com.bot.repository.BotRepository;
import br.com.bot.service.BotService;

@Service
public class BotServiceImpl implements BotService {
	
	@Autowired
	private BotRepository botRepository;

	public Bot createBot(BotJson botJson) {

		return Bot.builder().build();
	}

	public Bot getBotId(Object Id) {

		return Bot.builder().build();
	}

}
