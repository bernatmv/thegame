package com.thegame.commons.exceptions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

/**
 * @author e103880
 * @param <T>
 */
public interface ExceptionType<T extends TypifiedException> extends Supplier<T>{
	
	public String name();
	public String getDescription();
	public Class<T> getExceptionClass();

	@Override
	public default T get() {

		T reply;
		
		try {
			Constructor<T> constructor=getExceptionClass().getConstructor(ExceptionType.class);
			reply=constructor.newInstance(this);
		} catch (NoSuchMethodException|SecurityException|InstantiationException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
			throw new Error(TypifiedException.format("Unable to construct typified exception {} with {}({})",name(),getExceptionClass(), this),e);
		}
		
		return reply;
	}
	public default T get(final Object... _args) {

		T reply;
		
		try {
			Constructor<T> constructor=getExceptionClass().getConstructor(ExceptionType.class,Object[].class);
			reply=constructor.newInstance(this,_args);
		} catch (NoSuchMethodException|SecurityException|InstantiationException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
			throw new Error(TypifiedException.format("Unable to construct typified exception {} with {}({})",name(),getExceptionClass(), this,_args),e);
		}
		
		return reply;	
	}
	public default T get(final Throwable _cause,final Object... _args) {

		T reply;
		
		try {
			Constructor<T> constructor=getExceptionClass().getConstructor(Throwable.class,ExceptionType.class,Object[].class);
			reply=constructor.newInstance(_cause,this,_args);
		} catch (NoSuchMethodException|SecurityException|InstantiationException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
			throw new Error(TypifiedException.format("Unable to construct typified exception {} with {}({})",name(),getExceptionClass(),_cause, this,_args),e);
		}
		
		return reply;	
	}
}
