package com.marker;

import cn.wuweiit.domain.MessageBean;
import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.TopicPartition;
import websocket.ChatAnnotation;

import java.util.*;

/**
 * Created by Administrator on 2016/8/31.
 */
public class MyConsumer extends Thread
{

    private static final KafkaConsumer consumer;

    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        //zookeeper 配置
        props.put("zookeeper.connect", "localhost:2181");

        //group 代表一个消费组
        props.put("group.id", "jd-group2");

        //zk连接超时
        props.put("zookeeper.session.timeout.ms", "4000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        //序列化类

        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //配置key的序列化类
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer(props);
    }

   public void run() {
        consumer.subscribe(Arrays.asList("chat"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1);
            for (ConsumerRecord<String, String> record : records) {

                MessageBean msg = JSON.parseObject(record.value(), MessageBean.class);

                System.out.println("consumer:"+msg.getUsername());
                ChatAnnotation.sendMsg(msg);



            }
        }

    }

    public static void main(String[] args) {
        new MyConsumer().start();
    }
}
