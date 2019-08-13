package br.com.bot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bot.converter.BotRequestConverter;
import br.com.bot.converter.BotResponseConverter;
import br.com.bot.json.BotJson;
import br.com.bot.repository.BotRepository;
import br.com.bot.service.BotService;

@Service
public class BotServiceImpl implements BotService {

	@Autowired
	private BotRepository botRepository;

	@Autowired
	private BotRequestConverter botConverter;

	@Autowired
	private BotResponseConverter botResponseConverter;

	public BotJson createBot(BotJson botJson) {
		return botResponseConverter.convert(botRepository.save(botConverter.convert(botJson)));
	}

	public BotJson getBotId(String id) {
		return botResponseConverter.convert(botRepository.findBotById(id));
	}

}
