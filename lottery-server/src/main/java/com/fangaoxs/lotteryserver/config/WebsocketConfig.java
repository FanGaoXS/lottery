package com.fangaoxs.lotteryserver.config;

import com.fangaoxs.lotteryserver.websocket.UserWebsocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2021/07/08/16:12
 * @Description:  websocket启动配置类
 */
@Configuration
/*@EnableWebSocket
@EnableScheduling*/
public class WebsocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    //3.添加定时任务,55秒是考虑5秒的延迟,从而保证60s的心跳
    @Scheduled(fixedRate=1000*50)
    private void configureTasks() throws Exception{
        //广播消息（心跳检测），socket 60秒会断连
        UserWebsocket.broadcast("heart beat");
    }

}
