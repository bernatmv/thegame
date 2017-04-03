package com.thegame.server.common.functional;

import java.util.Enumeration;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 * @author afarre
 */
public class StreamableEnumeration<T> extends Spliterators.AbstractSpliterator<T> {

	final Enumeration<T> enumeration;

	public StreamableEnumeration(final Enumeration<T> _enumeration) {
		super(Long.MAX_VALUE, Spliterator.ORDERED);
		this.enumeration=_enumeration;
	}

	public Stream<T> stream(){
		return StreamSupport.stream(this,false);
	}
	
	@Override
	public boolean tryAdvance(Consumer<? super T> _action) {
		
		boolean reply=false;
		
		if (this.enumeration.hasMoreElements()) {
			_action.accept(this.enumeration.nextElement());
			reply=true;
		}
		
		return reply;
	}

	@Override
	public void forEachRemaining(Consumer<? super T> _action) {
		while (this.enumeration.hasMoreElements()) {
			_action.accept(this.enumeration.nextElement());
		}
	}
}
