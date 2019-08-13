package br.com.bot.json;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Builder
public class MessageRequestJson {

	@NotNull(message = "O conversationId não poder ser nulo")
	@NotBlank(message = "O conversationId não poder ser em branco")
	@Getter
	private String conversationId;

	@Getter
	private LocalDateTime timestamp;

	@NotNull(message = "O id from não poder ser nulo")
	@NotBlank(message = "O id from não poder ser em branco")
	@Getter
	private String from;

	@NotNull(message = "O id to não poder ser nulo")
	@NotBlank(message = "O id to não poder ser em branco")
	@Getter
	private String to;

	@NotNull(message = "O texto não poder ser nulo")
	@NotBlank(message = "O texto não poder ser em branco")
	@Getter
	private String text;

}
