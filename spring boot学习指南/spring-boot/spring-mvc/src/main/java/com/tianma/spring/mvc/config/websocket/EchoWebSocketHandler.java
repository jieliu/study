package com.tianma.spring.mvc.config.websocket;

import com.alibaba.fastjson.JSON;
import com.tianma.spring.mvc.service.websocket.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoWebSocketHandler extends TextWebSocketHandler {
	private static Logger logger = LoggerFactory.getLogger(EchoWebSocketHandler.class);
	private final EchoService echoService;

	public EchoWebSocketHandler(EchoService echoService) {
		this.echoService = echoService;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		logger.info("Opened new session in instance " + this);
		SocketSessionHandler.newInstance().addSession(session);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String echoMessage = this.echoService.getMessage(message.getPayload());
		logger.info("payLoad: {}, message: {}", echoMessage, JSON.toJSONString(message));
		
		session.sendMessage(new TextMessage(echoMessage));
		SocketSessionHandler.newInstance().addSession(session);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		session.close(CloseStatus.SERVER_ERROR);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		logger.info("Close session in instance, sessionId = " + session.getId());
		SocketSessionHandler.newInstance().removeSession(session.getId());
	}
}