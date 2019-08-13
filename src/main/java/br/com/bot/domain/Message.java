package br.com.bot.domain;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "messages")
public class Message {

	@Id
	private ObjectId _id;
	
	private String id;

	private String conversationId;

	private LocalDateTime timestamp;

	private String from;

	private String to;

	private String text;

}
