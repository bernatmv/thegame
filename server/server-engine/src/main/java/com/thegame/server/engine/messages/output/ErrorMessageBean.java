package com.thegame.server.engine.messages.output;

import com.thegame.server.engine.messages.IsMessageBean;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author afarre
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageBean implements IsMessageBean{

	@NonNull
	private Throwable exception;
}
