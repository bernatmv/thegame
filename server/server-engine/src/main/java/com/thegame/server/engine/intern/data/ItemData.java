package com.thegame.server.engine.intern.data;

import com.thegame.server.engine.messages.common.Gender;
import java.util.List;
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
public class ItemData {

	private String id;
	private String name;
	private String description;
	private boolean alive;
	private Gender gender;
	private String singular;
	private String plural;
	@Singular("chat")
	private List<String> chatter;
}
