package com.thegame.server.engine.tasks;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.thegame.server.engine.messages.IsMessageBean;

/**
 * @author afarre
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Task {

	public Class<? extends IsMessageBean> value();
}
