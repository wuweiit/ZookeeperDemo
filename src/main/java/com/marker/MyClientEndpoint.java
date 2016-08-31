package com.marker;

import javax.websocket.*;

/**
 * Created by Administrator on 2016/8/31.
 */
@ClientEndpoint
public class MyClientEndpoint extends Endpoint{


    @Override
    public void onOpen(Session session, EndpointConfig config) {

    }


    @OnMessage
    public void incoming(String message) {
        System.out.println("====================================================");
        // Never trust the client
        System.out.println(message);

    }
}
