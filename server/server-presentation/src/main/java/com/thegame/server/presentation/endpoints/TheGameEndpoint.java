package com.thegame.server.presentation.endpoints;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.common.utils.StringUtils;
import com.thegame.server.engine.TheGameMessageProcessor;
import com.thegame.server.engine.messages.RegisterPlayerMessageBean;
import com.thegame.server.engine.messages.UnregisterPlayerMessageBean;
import com.thegame.server.presentation.messages.ErrorMessage;
import com.thegame.server.presentation.messages.IsMessage;
import com.thegame.server.presentation.messages.MessageFactory;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
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
													bean.setTime(LocalDateTime.now());
													try {
														final String jsonMessage=bean
																		.encode(bean);
														log.debug("thegame-endpoint::session::{}::send::{}",_session.getId(),jsonMessage);
														_session
															.getBasicRemote()
															.sendText(jsonMessage);
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
			log.debug("thegame-endpoint::session::{}::receive::{}",_session.getId(),_message);
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

	@OnClose
	public void onClose(final Session _session, final CloseReason _reason) {

		try {
			log.finest("thegame-endpoint::session::{}::close::{}-{}::begin",_session.getId(),_reason.getCloseCode(),_reason.getReasonPhrase());
			TheGameMessageProcessor.getInstance()
				.process(
					UnregisterPlayerMessageBean.builder()
						.name(_session.getId())
						.build());
			log.trace("thegame-endpoint::session::{}::close::{}-{}::end",_session.getId(),_reason.getCloseCode(),_reason.getReasonPhrase());
		}catch(Throwable e){
			log.error("thegame-endpoint::session::{}::close::{}-{}::fail",_session.getId(),_reason.getCloseCode(),_reason.getReasonPhrase(),e);
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
									.time(LocalDateTime.now())
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
