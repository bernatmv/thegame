package com.thegame.server.presentation.endpoints;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.utils.StringUtils;
import com.thegame.server.engine.TheGameMessageProcessor;
import com.thegame.server.engine.messages.RegisterPlayerMessageBean;
import com.thegame.server.presentation.messages.ErrorMessage;
import com.thegame.server.presentation.messages.IsMessage;
import com.thegame.server.presentation.messages.MessageFactory;
import java.io.IOException;
import javax.websocket.EncodeException;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author afarre
 */
@ServerEndpoint(value = "/thegame/channel")
public class TheGameEndpoint {

	private static final LogStream log = LogStream.getLogger(TheGameEndpoint.class);

	
	@OnOpen
	public void open(final Session _session) {

		try {
			log.finest("thegame-endpoint::session::{}::open::begin",_session.getId());
			TheGameMessageProcessor.getInstance()
				.process(RegisterPlayerMessageBean.builder()
						.name(_session.getId())
						.channel(messageBean -> {
													final IsMessage bean=MessageFactory.getInstance(messageBean);
													try {
														_session
															.getBasicRemote()
															.sendText(bean
																		.encode(bean));
													} catch (IOException|EncodeException e) {
														throw new RuntimeException(e);
													}
												})
						.build());			
			log.info("thegame-endpoint::session::{}::open::open",_session.getId());
			log.trace("thegame-endpoint::session::{}::open::end",_session.getId());
		}catch(Throwable e){
			log.error("thegame-endpoint::session::{}::open::fail",_session.getId(),e);
		}
	}
 
	@OnMessage
	public void onMessage(final Session _session, final String _message) {

		try {
			log.finest("thegame-endpoint::session::{}::message::begin",_session.getId());
			TheGameMessageProcessor.getInstance()
				.process(
					MessageFactory.getInstance(
						MessageFactory.getInstance(_message)));
			log.trace("thegame-endpoint::session::{}::message::end",_session.getId());
		}catch(Throwable e){
			log.error("thegame-endpoint::session::{}::message::fail",_session.getId(),e);
			throw new RuntimeException(e);
		}
	}
		
	@OnError
	@SuppressWarnings("UseSpecificCatch")
	public void onError(final Session _session, final Throwable _error) {
		
		try {
			log.finest("thegame-endpoint::session::{}::error-message::begin",_session.getId());
			_session.getBasicRemote()
					.sendText(
						ErrorMessage.builder()
									.code(_error.getClass().getSimpleName())
									.message(_error.getMessage())
									.stacktrace(StringUtils.toString(_error))
									.build()
									.encode());
			log.warning("thegame-endpoint::session::{}::error-message::end::{}",_session.getId(),_error);
		} catch (Throwable e) {
			log.error("thegame-endpoint::session::{}::error-message::failed",_session.getId(),e);
		}
	}
}
