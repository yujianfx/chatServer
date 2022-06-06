package top.wangudiercai.chatserver.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.Session;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketSessionManager {
  ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();
  // 单例工厂
  private static WebSocketSessionManager instance = null;

  public static  WebSocketSessionManager getInstance() {
    if (instance == null) {
      synchronized (WebSocketSessionManager.class) {
        if (instance == null) {
          instance = new WebSocketSessionManager();
        }
      }
    }
    return instance;
  }

  public void addSession(String key, Session session) {
    sessions.put(key, session);
  }

  public Session getSession(String key) {
    return sessions.get(key);
  }

  public void removeSession(String key) {
    sessions.remove(key);
  }

  public Collection<Session> getAllSessions() {
    return sessions.values();
  }
}
