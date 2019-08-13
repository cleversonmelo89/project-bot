package br.com.bot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bot.json.BotJson;
import br.com.bot.service.BotService;

@RestController
@RequestMapping("/bot")
public class BotController {

	@Autowired
	private BotService botService;

	@PostMapping
	public ResponseEntity<BotJson> createBot(@Valid @RequestBody BotJson botJson) {
		return ResponseEntity.status(HttpStatus.CREATED).body(botService.createBot(botJson));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<BotJson> getBotId(@PathVariable("id") String id) {
		return ResponseEntity.status(HttpStatus.OK).body(botService.getBotId(id));
	}

}
