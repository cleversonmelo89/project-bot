package br.com.bot.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.bot.domain.Bot;
import br.com.bot.json.BotJson;

@Component
public class BotResponseConverter implements Converter<Bot, BotJson> {

	@Override
	public BotJson convert(Bot bot) {
		return BotJson.builder().id(bot.getId()).name(bot.getName()).build();
	}

}
