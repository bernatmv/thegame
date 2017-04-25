package com.thegame.server.presentation.messages;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author afarre
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude={"stacktrace"})
public class ErrorMessage implements IsMessage<ErrorMessage>{

	private LocalDateTime time;	
	private String code;
	private String message;
	private String stacktrace;
}