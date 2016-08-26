package com.marker;

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
        zkClient4subStat.create("/wuwei","123", CreateMode.PERSISTENT);
        zkClient4subStat.close();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
