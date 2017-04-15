package com.thegame.server.presentation.endpoints;

import com.thegame.server.common.logging.LogStream;
import com.thegame.server.engine.TheGameMessageProcessor;
import com.thegame.server.engine.messages.RegisterPlayerMessageBean;
import com.thegame.server.engine.messages.UnregisterPlayerMessageBean;
import com.thegame.server.presentation.messages.ErrorMessage;
import com.thegame.server.presentation.messages.IsMessage;
import com.thegame.server.presentation.messages.support.MessageFactory;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Stream;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * @author afarre
 */
@ServerEndpoint(value = "/thegame/channel/{player}")
public class TheGameEndpoint {

	private static final LogStream log = LogStream.getLogger(TheGameEndpoint.class);

	
	@OnOpen
	public void open(final Session _session,@PathParam("player") final String _player) {

		try {
			log.finest("thegame-endpoint::session::{}::player::{}::open::begin",_session.getId(),_player);
			_session.getUserProperties().put("user.name",_player);
			TheGameMessageProcessor.getInstance()
				.process(RegisterPlayerMessageBean.builder()
						.name(_player)
						.session(_session.getId())
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
			log.info("thegame-endpoint::session::{}::player::{}::open::end",_session.getId(),_player);
		}catch(Throwable e){
			log.error("thegame-endpoint::session::{}::player::{}::open::fail",_session.getId(),_player,e);
		}
	}
 
	@OnMessage
	public void onMessage(final Session _session,@PathParam("player") final String _player, final String _message) {

		try {
			log.finest("thegame-endpoint::session::{}::player::{}::message::begin",_session.getId(),_player);
			Stream.of(_message)
				.map(message -> message.trim())
				.peek(message -> log.finest("thegame-endpoint::session::{}::player::{}::receive::{}",_session.getId(),_player,message.length()))
				.filter(message -> message.length()>0)
				.peek(message -> log.finest("thegame-endpoint::session::{}::player::{}::receive::{}::size::{}",_session.getId(),_player,message,message.length()))
				.map(message -> MessageFactory.getInstance(message))
				.peek(isMessage -> log.finest("thegame-endpoint::session::{}::player::{}::isMessage::{}",_session.getId(),_player,isMessage))
				.map(isMessage -> MessageFactory.getInstance(isMessage))
				.peek(isMessageBean -> log.finest("thegame-endpoint::session::{}::player::{}::isMessageBean::{}",_session.getId(),_player,isMessageBean))
				.forEach(isMessageBean -> TheGameMessageProcessor.getInstance().process(isMessageBean));
			log.trace("thegame-endpoint::session::{}::player::{}::message::end",_session.getId(),_player);
		}catch(Throwable e){
			log.error("thegame-endpoint::session::{}::player::{}::message::fail",_session.getId(),_player,e);
			throw new RuntimeException(e);
		}
	}

	@OnClose
	public void onClose(final Session _session,@PathParam("player") final String _player, final CloseReason _reason) {

		try {
			log.finest("thegame-endpoint::session::{}::player::{}::close::{}-{}::begin",_session.getId(),_player,_reason.getCloseCode(),_reason.getReasonPhrase());
			TheGameMessageProcessor.getInstance()
				.process(
					UnregisterPlayerMessageBean.builder()
						.session(_session.getId())
						.name(_player)
						.build());
			log.trace("thegame-endpoint::session::{}::player::{}::close::{}-{}::end",_session.getId(),_player,_reason.getCloseCode(),_reason.getReasonPhrase());
		}catch(Throwable e){
			log.error("thegame-endpoint::session::{}::player::{}::close::{}-{}::fail",_session.getId(),_player,_reason.getCloseCode(),_reason.getReasonPhrase(),e);
			throw new RuntimeException(e);
		}
	}
	
	@OnError
	@SuppressWarnings("UseSpecificCatch")
	public void onError(final Session _session,@PathParam("player") final String _player, final Throwable _error) {
		
		try {
			log.finest("thegame-endpoint::session::{}::player::{}::error-message::begin",_session.getId(),_player);
			_session.getBasicRemote()
					.sendText(
						ErrorMessage.builder()
									.time(LocalDateTime.now())
									.code(_error.getClass().getSimpleName())
									.message(_error.getMessage())
									.build()
									.encode());
			log.warning("thegame-endpoint::session::{}::player::{}::error-message::end::{}",_session.getId(),_player,_error);
		} catch (Throwable e) {
			log.error("thegame-endpoint::session::{}::player::{}::error-message::failed",_session.getId(),_player,e);
		}
	}
}
