package br.com.bot.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Entity
@Document(collection = "messages")
public class Message {

	@Id
	private ObjectId id;

	private Long conversationId;

	private LocalDateTime timestamp;

	private ObjectId from;

	private ObjectId to;

	private String text;

}
