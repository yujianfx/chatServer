package top.wangudiercai.chatserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.WebSocketConfigurationSupport;
import top.wangudiercai.chatserver.common.WebSocketSessionManager;

import javax.servlet.ServletContext;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


@Slf4j
@ServerEndpoint(value = "/msgWebsocket")
@Controller
public class MsgWebSocketController {
  private WebSocketSessionManager webSocketSessionManager = WebSocketSessionManager.getInstance();

  @OnOpen
  public void onOpen(Session session) {
    session.setMaxIdleTimeout(30 * 60 * 1000);
    webSocketSessionManager.addSession(session.getId(), session);
  }

  @OnClose
  public void onClose(Session session) {
    webSocketSessionManager.removeSession(session.getId());
    log.info("close session Id{}", session.getId());
    log.info("session count:{}", webSocketSessionManager.getAllSessions().size());
  }

  @OnMessage
  public void onMessage(String message, Session session) {
    webSocketSessionManager
        .getAllSessions()
        .forEach(
            (s -> {
              if (s != null) {
                try {
                  s.getBasicRemote().sendText(message);
                } catch (IOException e) {
                  e.printStackTrace();
                }
              }
            }));
  }

  @OnError
  public void onError(Session session, Throwable error) {
    try {
      session.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    error.printStackTrace();
  }
}
