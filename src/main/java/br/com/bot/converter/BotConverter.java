package br.com.bot.converter;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.bot.domain.Bot;
import br.com.bot.json.BotJson;

@Component
public class BotConverter implements Converter<BotJson, Bot> {

	@Override
	public Bot convert(BotJson botJson) {
		return Bot.builder().id(ObjectId.get()).name(botJson.getName()).build();
	}

}
