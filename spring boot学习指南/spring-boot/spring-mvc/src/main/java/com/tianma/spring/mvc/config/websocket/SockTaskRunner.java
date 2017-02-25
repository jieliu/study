package com.tianma.spring.mvc.config.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

public class SockTaskRunner implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(SockTaskRunner.class);

	public void run() {
		Iterator<SocketSessionInfo> it = SocketSessionHandler.sessions.iterator();
		while(it.hasNext()) {
			SocketSessionInfo info = it.next();

			if(logger.isInfoEnabled()) {
				logger.info(info.getEndSessionTime().toString());
			}
			if(info.getEndSessionTime().before(new Date())) {
				try {
					info.getWebSocketSession().close();
					SocketSessionHandler.newInstance().removeSession(info.getSessionId());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}