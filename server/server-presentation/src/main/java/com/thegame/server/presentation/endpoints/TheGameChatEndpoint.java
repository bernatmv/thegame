package com.thegame.server.presentation.endpoints;

import com.thegame.server.presentation.messages.ChatDecoderEncoder;
import com.thegame.server.presentation.messages.ChatMessage;
import java.io.IOException;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * @author afarre
 */
@ServerEndpoint(value = "/chat/{room}", encoders = ChatDecoderEncoder.class, decoders = ChatDecoderEncoder.class)
public class TheGameChatEndpoint {
	private final Logger log = Logger.getLogger(getClass().getName());
 
	@OnOpen
	public void open(final Session session, @PathParam("room") final String room) {
		log.info("session openend and bound to room: " + room);
		session.getUserProperties().put("room", room);
	}
 
	@OnMessage
	public void onMessage(final Session session, final ChatMessage chatMessage) {
		String room = (String) session.getUserProperties().get("room");
		try {
			for (Session s : session.getOpenSessions()) {
				if (s.isOpen()
						&& room.equals(s.getUserProperties().get("room"))) {
					s.getBasicRemote().sendObject(chatMessage);
				}
			}
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
			log.warning("onMessage failed");
		}
	}
}
