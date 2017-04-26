package com.thegame.server.engine.messages.input;

import com.thegame.server.engine.messages.IsMessageBean;
import java.util.function.Consumer;
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
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
public class RegisterPlayerMessageBean extends InputMessageBean<RegisterPlayerMessageBean>{

	@Setter
	@Getter
	private String session;

	@Setter
	@Getter
	private Consumer<IsMessageBean> channel;

	@Builder
	public RegisterPlayerMessageBean(final String session,final Consumer<IsMessageBean> channel,final String sender) {
		super(sender);
		this.session=session;
		this.channel=channel;
	}
}
