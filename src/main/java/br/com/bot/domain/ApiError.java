package br.com.bot.domain;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

	private HttpStatus status;
	private String message;
	private List<String> detail;
	private Integer code;

}