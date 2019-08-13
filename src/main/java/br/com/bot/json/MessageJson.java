package br.com.bot.json;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;

import lombok.Getter;

public class MessageJson {

	@NotNull(message = "O conversationId não poder ser nulo")
	@NotBlank(message = "O conversationId não poder ser em branco")
	@Getter
	private Long conversationId;

	@NotNull(message = "A data não poder ser nula")
	@NotBlank(message = "A data não poder ser em branco")
	@Getter
	private LocalDateTime timestamp;

	@NotNull(message = "O id from não poder ser nulo")
	@NotBlank(message = "O id from não poder ser em branco")
	@Getter
	private ObjectId from;

	@NotNull(message = "O id to não poder ser nulo")
	@NotBlank(message = "O id to não poder ser em branco")
	@Getter
	private ObjectId to;

	@NotNull(message = "O texto não poder ser nulo")
	@NotBlank(message = "O texto não poder ser em branco")
	@Getter
	private String text;

}
