package com.thegame.server.presentation.messages;

import java.time.LocalDateTime;
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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class MoveMessage implements IsMessage<MoveMessage>{

	@Setter
	@Getter
	public LocalDateTime time;	

	@Setter
	@Getter
	public String sender;
	
	@Setter
	@Getter
	public String direction;
}
