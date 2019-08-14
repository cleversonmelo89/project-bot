package br.com.bot.service.impl;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.bot.converter.BotRequestConverter;
import br.com.bot.converter.BotResponseConverter;
import br.com.bot.domain.Bot;
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

	private ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	public BotJson createBot(BotJson botJson) {
		Bot bot = botRepository.findBotById(botJson.getId());
		if (bot != null) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,
					MessageFormat.format(resourceBundle.getString("id_user_already_exists"), botJson.getId()));
		}
		return botResponseConverter.convert(botRepository.save(botConverter.convert(botJson)));
	}

	public BotJson getBotId(String id) {
		Bot bot = botRepository.findBotById(id);
		if (bot == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					MessageFormat.format(resourceBundle.getString("bot_user_not_found"), id));
		}
		return botResponseConverter.convert(bot);
	}

}
