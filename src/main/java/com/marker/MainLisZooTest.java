package com.marker;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class MainLisZooTest extends Thread{


    @Override
    public void run() {
        String host = "localhost:2181";
        ZkClient zkClient4subStat = new ZkClient(host);

        zkClient4subStat.subscribeChildChanges("/host", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.printf(s);
                System.out.println(JSON.toJSONString(list));
            }
        });






        System.out.println("start lis");

    }

    public static void main(String[] args) {
        new MainLisZooTest().start();




        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
