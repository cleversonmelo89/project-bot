package br.com.bot.json;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;

public class BotJson {

	@NotNull(message = "O nome não poder ser nulo")
	@NotBlank(message = "O nome não poder ser em branco")
	@Getter
	private String name;

}
