package br.com.bot.json;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class MessageResponseJson {

	@Setter
	@Getter
	private String id;

	@Setter
	@Getter
	private String conversationId;

	@Setter
	@Getter
	private LocalDateTime timestamp;

	@Setter
	@Getter
	private String from;

	@Setter
	@Getter
	private String to;

	@Setter
	@Getter
	private String text;

}
