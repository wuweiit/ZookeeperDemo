package com.marker;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.DataUpdater;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.tools.ant.taskdefs.Sleep;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class MainTest {


    public static void main(String[] args) {
        String host = "localhost:2181";
        ZkClient zkClient4subStat = new ZkClient(host);
        String hostIp = "localhost:8083";
//        zkClient4subStat.create("/host","",CreateMode.PERSISTENT);
        zkClient4subStat.create("/host/" + hostIp,"",CreateMode.EPHEMERAL);

        zkClient4subStat.updateDataSerialized("/host/" + hostIp, new DataUpdater<Object>() {
            @Override
            public Object update(Object currentData) {


                return "online";
            }
        });
        long userId = 11;
//        zkClient4subStat.create("/host/client","",CreateMode.PERSISTENT);
        zkClient4subStat.create("/host/client/"+userId, hostIp, CreateMode.EPHEMERAL);



        List<String> child = zkClient4subStat.getChildren("/host/client");

//        zkClient4subStat.readData()


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        zkClient4subStat.close();
    }
}
