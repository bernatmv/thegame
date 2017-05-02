package com.thegame.server.presentation.support;

import com.thegame.server.common.functional.LambdaUtils;
import com.thegame.server.presentation.mappers.Converter;
import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.reflection.PackageUtils;
import com.thegame.server.engine.exceptions.EngineException;
import com.thegame.server.engine.exceptions.EngineExceptionType;
import com.thegame.server.engine.messages.input.InputMessageBean;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import com.thegame.server.engine.messages.IsMessageBean;
import com.thegame.server.presentation.messages.IsMessage;
import com.thegame.server.presentation.mappers.MessageMapper;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.websocket.DecodeException;
import org.mapstruct.factory.Mappers;

/**
 * @author afarre
 */
@SuppressWarnings("UseSpecificCatch")
public class MessageFactory {
	
	private	static final LogStream logger=LogStream.getLogger(MessageFactory.class);
	private static final Map<Class<IsMessage>,Function<IsMessage,InputMessageBean>> toBeanMapping=new HashMap<>(20);
	private static final Map<Class<IsMessageBean>,Function<IsMessageBean,IsMessage>> fromBeanMapping=new HashMap<>(20);
	private static final Map<String,Function<String,IsMessage>> fromJsonMapping=new HashMap<>(20);
	private static final MessageMapper messageMapper=Mappers.getMapper(MessageMapper.class );
	
	static {
		try {
			logger.trace("message-factory::initialization::begin");
			logger.trace("message-factory::initialization::to-bean-mapping::begin");
			Stream.of(MessageMapper.class.getDeclaredMethods())
					.peek(method -> logger.finest("message-factory::initialization::to-bean-mapping::method::{}",method))
					.filter(method -> method.isAnnotationPresent(Converter.class))
					.peek(method -> logger.finest("message-factory::initialization::to-bean-mapping::method::{}::annotated",method))
					.filter(method -> IsMessage.class.isAssignableFrom(method.getParameters()[0].getType()))
					.peek(method -> logger.finest("message-factory::initialization::to-bean-mapping::method::{}::correct-param",method))
					.filter(method -> InputMessageBean.class.isAssignableFrom(method.getReturnType()))
					.peek(method -> logger.finest("message-factory::initialization::to-bean-mapping::method::{}::correct-return",method))
					.map(method -> new Object[]{
												method.getParameters()[0].getType(),
												(Function)((messageBean) -> {
															
															InputMessageBean reply=null;
													
															try {
																reply=(InputMessageBean)method.invoke(MessageFactory.messageMapper,messageBean);
															} catch (IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
																throw new RuntimeException(e);
															}

															return reply;
														})
												})
					.peek(array -> logger.finest("message-factory::initialization::to-bean-mapping::method::from::{}::convert-with::{}",array[0],array[1]))
					.forEach(array -> MessageFactory.toBeanMapping.put((Class<IsMessage>)array[0],(Function<IsMessage,InputMessageBean>)array[1]));
			logger.finest("message-factory::initialization::to-bean-mapping::{}",MessageFactory.toBeanMapping);
			logger.debug("message-factory::initialization::to-bean-mapping::end::{}",MessageFactory.toBeanMapping.size());
			logger.trace("message-factory::initialization::from-bean-mapping::begin");
			Stream.of(MessageMapper.class.getDeclaredMethods())
					.peek(method -> logger.finest("message-factory::initialization::from-bean-mapping::method::{}",method))
					.filter(method -> method.isAnnotationPresent(Converter.class))
					.peek(method -> logger.finest("message-factory::initialization::from-bean-mapping::method::{}::annotated",method))
					.filter(method -> IsMessageBean.class.isAssignableFrom(method.getParameters()[0].getType()))
					.peek(method -> logger.finest("message-factory::initialization::from-bean-mapping::method::{}::correct-param",method))
					.filter(method -> IsMessage.class.isAssignableFrom(method.getReturnType()))
					.peek(method -> logger.finest("message-factory::initialization::from-bean-mapping::method::{}::correct-return",method))
					.map(method -> new Object[]{
												method.getParameters()[0].getType(),
												(Function)((messageBean) -> {
															
															IsMessage reply=null;
													
															try {
																reply=(IsMessage)method.invoke(MessageFactory.messageMapper,messageBean);
															} catch (IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
																throw new RuntimeException(e);
															}

															return reply;
														})
												})
					.peek(array -> logger.finest("message-factory::initialization::from-bean-mapping::method::from::{}::convert-with::{}",array[0],array[1]))
					.forEach(array -> MessageFactory.fromBeanMapping.put((Class<IsMessageBean>)array[0],(Function<IsMessageBean,IsMessage>)array[1]));
			logger.finest("message-factory::initialization::from-bean-mapping::{}",MessageFactory.fromBeanMapping);
			logger.debug("message-factory::initialization::from-bean-mapping::end::{}",MessageFactory.fromBeanMapping.size());
			logger.trace("message-factory::initialization::from-json-mapping::begin");
			final List<String> explorePackages=Stream.of("com.thegame.server.presentation.messages")
														.collect(Collectors.toList());
			final List<String> resourcesFilter=Stream.of("*.class")
														.collect(Collectors.toList());
			PackageUtils.getExistentResources(explorePackages,resourcesFilter)
					.stream()
					.map(className -> className.substring(0, className.length()-6))
					.peek(clazz -> logger.finest("message-factory::initialization::from-json-mapping::class-name::{}",clazz))
					.map(className -> LambdaUtils.classForName(className))
					.peek(clazz -> logger.finest("message-factory::initialization::from-json-mapping::class::{}",clazz))
					.filter(clazz -> !clazz.equals(IsMessage.class))
					.filter(clazz -> IsMessage.class.isAssignableFrom(clazz))
					.peek(clazz -> logger.finest("message-factory::initialization::from-json-mapping::class::{}::is-assignable",clazz))
					.map(clazz -> new Object[]{
													clazz.getSimpleName(),
													(Function<String,IsMessage>)((String json) -> {
															
														IsMessage reply=null;

														try {
															reply=(IsMessage)((IsMessage)clazz.newInstance())
																		.decode(json);
														} catch (IllegalAccessException|IllegalArgumentException|InstantiationException|DecodeException e) {
															throw new RuntimeException(e);
														}

														return reply;
													})
												})
					.peek(array -> logger.finest("message-factory::initialization::from-json-mapping::method::from::{}::convert-with::{}",array[0],array[1]))
					.forEach(array -> MessageFactory.fromJsonMapping.put((String)array[0],(Function<String,IsMessage>)array[1]));
			logger.finest("message-factory::initialization::to-json-mapping::{}",MessageFactory.fromJsonMapping);
			logger.debug("message-factory::initialization::to-bean-mapping::end::{}",MessageFactory.fromJsonMapping.size());
			logger.debug("message-factory::initialization::end");
		} catch (Throwable e) {
			logger.error("message-factory::initialization::fail::{}",e.getMessage(),e);
			throw new EngineException(e,EngineExceptionType.UNABLE_TO_FIND_SUITABLE_CONSTRUCTOR);
		}
	}

	public static void init(){
		logger.finest("message-factory::initialized");
	}
	
	@SuppressWarnings("element-type-mismatch")
	public static IsMessage getInstance(final String _json){
		
		IsMessage reply;
		
		logger.trace("message-factory::get-instance::String::begin::{}",_json);
		int position=_json.indexOf("\"kind\":");
		if(position<0)
			throw new RuntimeException("Wrong message format");
		final StringBuilder builder=new StringBuilder(50);
		boolean exit=false;
		boolean found=false;
		position+=7;
		while(!exit){
			final char character=_json.charAt(position++);
			if(found){
				if(character=='"'){
					exit=true;
				}else{
					builder.append(character);
				}
			}else{
				if(character=='"'){
					found=true;
				}
			}
		}
		final String kind=builder.toString();
		final Function<String,IsMessage> mapper=MessageFactory.fromJsonMapping.get(kind);
		reply=mapper.apply(_json);
		logger.debug("message-factory::get-instance::String::end");
			
		return reply;
	}

	@SuppressWarnings("element-type-mismatch")
	public static InputMessageBean getInstance(final IsMessage _message){
		
		InputMessageBean reply;
		
		logger.trace("message-factory::get-instance::IsMessage::begin::{}",_message);
		final Function<IsMessage,InputMessageBean> mapper=MessageFactory.toBeanMapping.get(_message.getClass());
		reply=mapper.apply(_message);
		logger.debug("message-factory::get-instance::IsMessage::end");
			
		return reply;
	}
	
	
	@SuppressWarnings("element-type-mismatch")
	public static IsMessage getInstance(final IsMessageBean _message){
		
		IsMessage reply;
		
		logger.trace("message-factory::get-instance::IsMessageBean::begin::{}",_message);
		final Function<IsMessageBean,IsMessage> mapper=MessageFactory.fromBeanMapping.get(_message.getClass());
		reply=mapper.apply(_message);
		logger.debug("message-factory::get-instance::IsMessageBean::end");
			
		return reply;
	}
}
