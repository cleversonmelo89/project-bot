package br.com.bot.controller;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bot.domain.Message;
import br.com.bot.json.MessageJson;
import br.com.bot.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@PostMapping
	public ResponseEntity<Message> createMessage(@Valid @RequestBody MessageJson messageJson) {
		return ResponseEntity.status(HttpStatus.CREATED).body(messageService.createMessage(messageJson));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Message> getMessage(@PathVariable("id") ObjectId id) {
		return ResponseEntity.status(HttpStatus.OK).body(messageService.getMessageId(id));
	}

	@GetMapping
	public ResponseEntity<List<Message>> getMessageByConversationId(@RequestParam Long conversationId) {
		return ResponseEntity.status(HttpStatus.OK).body(messageService.getListMessage(conversationId));
	}

}
