package br.com.bot.json;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BotJson {

	@NotNull(message = "O id não poder ser nulo")
	@NotBlank(message = "O id não poder ser em branco")
	@Getter
	private String id;

	@NotNull(message = "O nome não poder ser nulo")
	@NotBlank(message = "O nome não poder ser em branco")
	@Getter
	private String name;

}
