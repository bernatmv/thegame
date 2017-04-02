package com.thegame.server.presentation.endpoints;

import com.thegame.server.common.StringUtils;
import com.thegame.server.common.logging.LogStream;
import com.thegame.server.presentation.messages.ChatMessage;
import com.thegame.server.presentation.messages.ErrorMessage;
import java.io.IOException;
import javax.websocket.EncodeException;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * @author afarre
 */
@ServerEndpoint(value = "/chat/{room}"
	, encoders = {ChatMessage.class,ErrorMessage.class}
	, decoders = {ChatMessage.class,ErrorMessage.class})
public class TheGameEndpoint {

	private static final LogStream log = LogStream.getLogger(TheGameEndpoint.class);

	
	@OnOpen
	public void open(final Session _session, @PathParam("room") final String _room) {

		try {
			log.finest("thegame-endpoint::session::{}::open::{}::begin",_session.getId(),_room);
			_session.getUserProperties().put("room", _room);
			log.info("thegame-endpoint::session::{}::open::{}::open",_session.getId(),_room);
			log.trace("thegame-endpoint::session::{}::open::{}::end",_session.getId(),_room);
		}catch(Throwable e){
			log.error("thegame-endpoint::session::{}::open::{}::fail",_session.getId(),_room,e);
		}
	}
 
	@OnMessage
	public void onChatMessage(final Session _session, final ChatMessage _chatMessage) {

		try {
			log.finest("thegame-endpoint::session::{}::chat-message::begin",_session.getId());
			final String room = (String) _session.getUserProperties().get("room");
			log.trace("thegame-endpoint::session::{}::chat-message::room::{}",_session.getId(),room);
			_session.getOpenSessions().stream()
				.peek(session -> log.trace("thegame-endpoint::session::{}::chat-message::room::{}::check::{}",_session.getId(),room,session.getId()))
				.filter(session -> session.isOpen())
				.peek(session -> log.trace("thegame-endpoint::session::{}::chat-message::room::{}::check::{}::open",_session.getId(),room,session.getId()))
				.filter(session -> room.equals(session.getUserProperties().get("room")))
				.peek(session -> log.trace("thegame-endpoint::session::{}::chat-message::room::{}::deliver::{}",_session.getId(),room,session.getId()))
				.map(session ->  session.getBasicRemote())
				.forEach(basicConnection -> {
												try {
													log.finest("thegame-endpoint::session::{}::chat-message::room::{}::deliver::begin",_session.getId(),room);
													basicConnection.sendObject(_chatMessage);
													log.finest("thegame-endpoint::session::{}::chat-message::room::{}::deliver::end",_session.getId(),room);
												} catch (Throwable e) {
													log.error("thegame-endpoint::session::{}::chat-message::room::{}::deliver::failed",_session.getId(),room,e);
													throw new RuntimeException(e);
												}
											});
			log.trace("thegame-endpoint::session::{}::chat-message::end",_session.getId());
		}catch(Throwable e){
			log.error("thegame-endpoint::session::{}::chat-message::fail",_session.getId(),e);
			throw new RuntimeException(e);
		}
	}
	
	
	@OnError
	public void onError(final Session _session, final Throwable _error) {
		
		try {
			log.finest("thegame-endpoint::session::{}::error-message::begin",_session.getId());
			_session.getBasicRemote()
					.sendObject(
						ErrorMessage.builder()
									.code(_error.getClass().getSimpleName())
									.message(_error.getMessage())
									.stacktrace(StringUtils.toString(_error))
									.build());
			log.warning("thegame-endpoint::session::{}::error-message::end::{}",_session.getId(),_error);
		} catch (IOException | EncodeException e) {
			log.error("thegame-endpoint::session::{}::error-message::failed",_session.getId(),e);
		}
	}
}
