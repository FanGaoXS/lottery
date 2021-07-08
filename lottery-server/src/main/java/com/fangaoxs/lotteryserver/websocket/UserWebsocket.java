package com.fangaoxs.lotteryserver.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/08/16:13
 * @Description: 登记用户的websocket通信（实时返回新增的用户）
 */
@Component
@Slf4j
@ServerEndpoint("/userWebsocket/{username}")
public class UserWebsocket {

    /** 当前在线客户端数量（线程安全的） */
    private static AtomicInteger onlineClientNumber = new AtomicInteger(0);

    /** 当前在线客户端集合（线程安全的）：以键值对方式存储，key是连接的编号，value是连接的对象 */
    private static Map<String ,Session> onlineClientMap = new ConcurrentHashMap<>();

    /**
     * 客户端与服务端连接成功
     * @param session
     * @param username
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username){
        onlineClientNumber.incrementAndGet();//在线人数+1
        onlineClientMap.put(session.getId(),session);//新增在session
        log.info("客户端[{}]连接，当前在线客户端数量[{}]，当前连接session编号[{}]",
                username,onlineClientNumber.get(),session.getId());
    }

    /**
     * 客户端与服务端连接关闭
     * @param session
     * @param username
     */
    @OnClose
    public void onClose(Session session,@PathParam("username") String username){
        onlineClientNumber.decrementAndGet();//在线人数-1
        onlineClientMap.remove(session.getId());//移除该session
        log.info("客户端[{}]断开，当前在线客户端数量[{}]，当前连接session编号[{}]",
                username,onlineClientNumber.get(),session.getId());
    }

    /**
     * 客户端与服务端连接异常
     * @param error
     * @param session
     * @param username
     */
    @OnError
    public void onError(Throwable error,Session session,@PathParam("username") String username) {
        error.printStackTrace();
        log.info("客户端[{}]异常，当前在线客户端数量[{}]，当前连接session编号[{}]",
                username,onlineClientNumber.get(),session.getId());
    }

    /**
     * 客户端向服务端发送消息
     * @param username
     * @throws IOException
     */
    @OnMessage
    public void onMsg(Session session,String message,@PathParam("username") String username) throws IOException {
        broadcast(message);
        log.info("来自客户端[{}]的消息：[{}]",
                username,message);
    }

    /**
     * 广播（向所有客户端发送消息）
     */
    public static void broadcast(String message){
        Set<String> sessionIdSet = onlineClientMap.keySet();
        for (String sessionId : sessionIdSet) {
            Session session = onlineClientMap.get(sessionId);
            session.getAsyncRemote().sendText(message);
        }
    }

}
