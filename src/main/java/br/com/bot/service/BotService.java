package br.com.bot.service;

import br.com.bot.domain.Bot;
import br.com.bot.json.BotJson;

public interface BotService {

	Bot createBot(BotJson botJson);
	
	Bot getBotId(Object Id);
	
}
