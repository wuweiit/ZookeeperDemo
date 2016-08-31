package com.marker;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class SocketTest {


    /**
     *
     * @param args
     * @throws URISyntaxException
     */
    public static void main(String[] args) throws URISyntaxException {


         WebSocketContainer container = ContainerProvider.getWebSocketContainer();


        try {
            Session session  = container.connectToServer(new MyClientEndpoint() ,
                    new URI("ws://localhost:8083/websocket/chat"));



            session.addMessageHandler(new MessageHandler.Whole<String>(){

                @Override
                public void onMessage(String message) {
                    System.out.println(message);
                }
            });

            while(true){
                session.getBasicRemote().sendText("hahahahhahaa");

//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }


        } catch (DeploymentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
