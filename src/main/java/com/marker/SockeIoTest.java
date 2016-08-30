package com.marker;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Configuration;

/**
 * Created by Administrator on 2016/8/30.
 */
public class SockeIoTest {

    public static void main(String[] args) {

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(1337);
        config.setCloseTimeout(30);

        SocketIOServer server = new SocketIOServer(config);
//        server.addMessageListener();


    }

}
