package com.marker;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.tools.ant.taskdefs.Sleep;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class MainTest {


    public static void main(String[] args) {
        String host = "localhost:2181";
        ZkClient zkClient4subStat = new ZkClient(host);
//        zkClient4subStat.create("/msg","", CreateMode.PERSISTENT);

        zkClient4subStat.subscribeChildChanges("/msg", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.printf(s);
                System.out.printf(JSON.toJSONString(list));
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        zkClient4subStat.create("/msg/one","1", CreateMode.PERSISTENT_SEQUENTIAL);
        zkClient4subStat.close();





        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
