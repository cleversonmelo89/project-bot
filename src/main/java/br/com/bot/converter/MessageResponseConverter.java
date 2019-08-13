package br.com.bot.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.bot.domain.Message;
import br.com.bot.json.MessageResponseJson;

@Component
public class MessageResponseConverter implements Converter<Message, MessageResponseJson> {

	@Override
	public MessageResponseJson convert(Message message) {

		return MessageResponseJson.builder().id(message.getId()).conversationId(message.getConversationId())
				.timestamp(message.getTimestamp()).from(message.getFrom()).to(message.getTo()).text(message.getText())
				.build();
	}
}
