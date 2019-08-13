package br.com.bot.service;

import br.com.bot.json.BotJson;

public interface BotService {

	BotJson createBot(BotJson botJson);

	BotJson getBotId(String id);

}
