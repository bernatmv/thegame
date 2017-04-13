package com.thegame.server.common.concurrent;

import java.util.concurrent.ThreadFactory;
import lombok.Getter;

/**
 * @author afarre
 */
public class CustomThreadFactory implements ThreadFactory{

	@Getter
	private int counter;
	@Getter
	private int stackSize;
	@Getter
	private ThreadGroup group;	
	
	
	public CustomThreadFactory(final String _name){
		this(_name,0,0);
	}
	public CustomThreadFactory(final String _name,final int _threadPriority){
		this(_name,_threadPriority,0);
	}
	public CustomThreadFactory(final String _name,final int _threadPriority,final int _stackSize){
		this.counter=0;
		this.stackSize=_stackSize;
		this.group=new ThreadGroup(_name);
		this.group.setDaemon(false);
		if(_threadPriority>0){
			this.group.setMaxPriority(_threadPriority);
		}
	}
	
	
	@Override
	public Thread newThread(final Runnable _runnable) {
		
		Thread reply;
		
		final ThreadGroup threadGroup=this.group;
		final String threadName=String.join("-", this.group.getName(),String.valueOf(++this.counter));
		final Runnable threadTask=_runnable;
		final int threadStackSize=this.stackSize;
		reply=new Thread(threadGroup,threadTask,threadName,threadStackSize);
		
		return reply;
	}	
}
