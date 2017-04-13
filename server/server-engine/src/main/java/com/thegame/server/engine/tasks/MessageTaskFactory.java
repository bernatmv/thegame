package com.thegame.server.engine.tasks;

import com.thegame.server.common.functional.LambdaUtils;
import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.reflection.PackageUtils;
import com.thegame.server.engine.exceptions.EngineException;
import com.thegame.server.engine.exceptions.EngineExceptionType;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.thegame.server.engine.messages.IsMessageBean;

/**
 * @author afarre
 */
public class MessageTaskFactory {
	
	private	static final LogStream logger=LogStream.getLogger(MessageTaskFactory.class);
	private static final Map<Class<? extends IsMessageBean>,Class<? extends BaseMessageTask<? extends IsMessageBean>>> taskMapping=new HashMap<>();
	
	static {
		try {
			logger.trace("message-task-factory::initialization::begin");
			final List<String> explorePackages=Stream.of("com.thegame.server.engine.tasks")
														.collect(Collectors.toList());
			final List<String> resourcesFilter=Stream.of("*.class")
														.collect(Collectors.toList());
			PackageUtils.getExistentResources(explorePackages,resourcesFilter)
												.stream()
												.map(className -> className.substring(0, className.length()-6))
												.peek(className -> logger.finest("message-task-factory::initialization::found::resource::{}",className))
												.map(className -> LambdaUtils.classForName(className))
												.peek(className -> logger.finest("message-task-factory::initialization::found::class::{}",className))
												.map(clazz -> new Object[]{clazz.getAnnotation(Task.class),clazz})
												.filter(array -> array[0]!=null)
												.peek(array -> logger.finest("message-task-factory::initialization::found::annotation::{}::class::{}",array[0],array[1]))
												.map(array -> new Object[]{((Task)array[0]).value(), array[1]})
												.peek(array -> logger.finest("message-task-factory::initialization::found::message-bean::{}::class::{}",array[0],array[1]))
												.forEach(array -> MessageTaskFactory.taskMapping.put((Class<? extends IsMessageBean>)array[0], 
																			(Class<? extends BaseMessageTask<? extends IsMessageBean>>)array[1]));
			logger.debug("message-task-factory::initialization::end");
		} catch (IOException e) {
			logger.error("message-task-factory::initialization::fail::{}",e.getMessage(),e);
			throw new EngineException(e,EngineExceptionType.UNABLE_TO_FIND_SUITABLE_CONSTRUCTOR);
		}
	}
	
	
	public static <MESSAGE extends IsMessageBean> BaseMessageTask<MESSAGE> getInstance(final MESSAGE _message){
		
		BaseMessageTask<MESSAGE> reply=null;
		
		try {
			logger.trace("message-task-factory::get-instance::begin");
			final Class<MESSAGE> messageClass=(Class<MESSAGE>)_message.getClass();
			reply=(BaseMessageTask<MESSAGE>) MessageTaskFactory.taskMapping
				.get(messageClass)
				.getConstructor(messageClass)
				.newInstance(_message);
			logger.debug("message-task-factory::get-instance::end");
		} catch (NoSuchMethodException|SecurityException|InstantiationException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
			throw new EngineException(e,EngineExceptionType.MESSAGE_TASK_FACTORY_INSTANT_TASK_FAILED,_message);
		}
			
		return reply;
	}
}
