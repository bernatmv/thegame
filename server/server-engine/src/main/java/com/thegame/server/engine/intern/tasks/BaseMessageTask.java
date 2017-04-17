package com.thegame.server.engine.intern.tasks;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.engine.messages.ErrorMessageBean;
import com.thegame.server.engine.messages.IsMessageBean;
import java.util.Optional;

/**
 * @author afarre
 * @param <T>
 */
public abstract class BaseMessageTask<T extends IsMessageBean> implements Runnable{

	private final Optional<T> messageBean;
	
	
	public BaseMessageTask(final T _messageBean){
		this.messageBean=Optional.ofNullable(_messageBean);
	}

	
	public abstract void execute();

	
	public Optional<T> getMessageBean() {
		return this.messageBean;
	}
	
	
	@Override
	public final void run(){
		
		final LogStream logger=LogStream.getLogger(this.getClass());
		
		try{
			logger.trace("message-task::{}::execution::begin",this.getClass().getSimpleName());
			execute();
			logger.debug("message-task::{}::execution::end",this.getClass().getSimpleName());
		}catch(Throwable e){
			logger.debug("message-task::{}::execution::fail",this.getClass().getSimpleName(),e);
			ErrorMessageBean.builder().exception(e).build();
		}
	}
}
