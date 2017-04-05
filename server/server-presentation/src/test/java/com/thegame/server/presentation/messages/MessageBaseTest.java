package com.thegame.server.presentation.messages;

import java.util.List;
import java.util.Map;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

/**
 * @author afarre
 * @param <T>
 */
public abstract class MessageBaseTest<T extends IsMessage> {
	
	public IsMessage instance;
	
	public abstract Class<T> getMessageClass();
	
	@Before
	public void init() throws InstantiationException, IllegalAccessException {
		System.out.println("init");
		this.instance=getMessageClass().newInstance();
		this.instance.init(new EndpointConfig() {
			@Override
			public List<Class<? extends Encoder>> getEncoders() {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}

			@Override
			public List<Class<? extends Decoder>> getDecoders() {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}

			@Override
			public Map<String, Object> getUserProperties() {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		});
	}

	@After
	public void destroy() {
		System.out.println("destroy");
		this.instance.destroy();
	}
	
	public abstract void testDecode() throws InstantiationException, IllegalAccessException, DecodeException;
	
	protected void testDecode(final String _message,final T _expected) throws InstantiationException, IllegalAccessException, DecodeException{
		T result=(T)getMessageClass().newInstance().decode(_message);
		Assert.assertEquals(_expected,result);
	}

	public abstract void testEncode() throws InstantiationException, IllegalAccessException, EncodeException;
	
	protected void testEncode(final String _expected,final T _object) throws InstantiationException, IllegalAccessException, EncodeException{
		String result=getMessageClass().newInstance().encode(_object);
		System.out.println(result);
		Assert.assertEquals(_expected.replaceFirst("\"time\":\\d*",""), result.replaceFirst("\"time\":\\d*",""));
	}
}
