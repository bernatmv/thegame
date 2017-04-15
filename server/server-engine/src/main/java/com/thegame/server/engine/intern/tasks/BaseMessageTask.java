package com.thegame.server.engine.intern.tasks;

import com.thegame.server.common.logging.LogStream;
import lombok.Getter;
import com.thegame.server.engine.messages.IsMessageBean;

/**
 * @author afarre
 * @param <T>
 */
public abstract class BaseMessageTask<T extends IsMessageBean> implements Runnable{

	@Getter
	private final T messageBean;
	
	
	public BaseMessageTask(final T _messageBean){
		this.messageBean=_messageBean;
	}

	
	public abstract void execute();
	
	
	@Override
	public final void run(){
		
		final LogStream logger=LogStream.getLogger(this.getClass());
		
		try{
			logger.trace("message-task::{}::execution::begin",this.getClass().getSimpleName());
			execute();
			logger.debug("message-task::{}::execution::end",this.getClass().getSimpleName());
		}catch(Throwable e){
			logger.error("message-task::{}::execution::fail::{}",this.getClass().getSimpleName(),e.getMessage(),e);
		}
	}
}
