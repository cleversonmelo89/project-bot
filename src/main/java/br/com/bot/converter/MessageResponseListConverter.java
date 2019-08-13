package br.com.bot.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.bot.domain.Message;
import br.com.bot.json.MessageResponseJson;

@Component
public class MessageResponseListConverter implements Converter<List<Message>, List<MessageResponseJson>> {

	@Override
	public List<MessageResponseJson> convert(List<Message> listMessage) {
		List<MessageResponseJson> messageResponseJsonList = new ArrayList<>();

		for (Message message : listMessage) {
			messageResponseJsonList.add(MessageResponseJson.builder().id(message.getId())
					.conversationId(message.getConversationId()).timestamp(message.getTimestamp())
					.from(message.getFrom()).to(message.getTo()).text(message.getText()).build());
		}

		return messageResponseJsonList;
	}

}
