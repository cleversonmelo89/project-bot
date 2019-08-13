package br.com.bot.converter;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;

import br.com.bot.domain.Message;
import br.com.bot.json.MessageJson;

public class MessageConverter implements Converter<MessageJson, Message> {

	@Override
	public Message convert(MessageJson messageJson) {

		return Message.builder().id(ObjectId.get()).conversationId(messageJson.getConversationId())
				.timestamp(messageJson.getTimestamp()).from(messageJson.getFrom()).to(messageJson.getTo())
				.text(messageJson.getText()).build();

	}

}
