package com.thegame.commons.logging.mocks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * @author afarre
 */
public class LogHandlerMock extends Handler{

	private final Queue<LogRecord> records;
	private boolean flushed;
	private boolean closed;
	
	
	public LogHandlerMock(){
		this.records=new LinkedList<>();
		this.flushed=false;
		this.closed=false;
	}

	
	@Override
	public void publish(LogRecord _record) {
		this.records.add(_record);
	}
	@Override
	public void flush() {
		this.flushed=true;
	}
	@Override
	public void close() throws SecurityException {
		this.closed=true;
	}

	
	public Queue<LogRecord> getRecords() {
		return records;
	}
	public boolean isFlushed() {
		return flushed;
	}
	public boolean isClosed() {
		return closed;
	}
}
