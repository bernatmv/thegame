package com.thegame.server.presentation.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author afarre
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(exclude={"stacktrace"})
public class ErrorMessage implements IsMessage<ErrorMessage>{

	@Setter
	@Getter
	private String code;

	@Setter
	@Getter
	private String message;

	@Setter
	@Getter
	private String stacktrace;
}