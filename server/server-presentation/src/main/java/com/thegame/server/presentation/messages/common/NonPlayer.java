package com.thegame.server.presentation.messages.common;

import com.thegame.server.engine.messages.IsMessageBean;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

/**
 * @author afarre
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NonPlayer implements IsMessageBean{

	private String id;
	private Profile profile;
	@Singular("chat")
	private Set<String> chatter;
}
