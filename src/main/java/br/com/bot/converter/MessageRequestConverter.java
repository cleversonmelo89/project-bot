package br.com.bot.converter;

import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.bot.domain.Message;
import br.com.bot.json.MessageRequestJson;

@Component
public class MessageRequestConverter implements Converter<MessageRequestJson, Message> {

	@Override
	public Message convert(MessageRequestJson messageJson) {

		return Message.builder()._id(ObjectId.get()).id(generateId()).conversationId(messageJson.getConversationId())
				.timestamp(messageJson.getTimestamp()).from(messageJson.getFrom()).to(messageJson.getTo())
				.text(messageJson.getText()).build();
	}

	private String generateId() {
		return UUID.randomUUID().toString();
	}

}
