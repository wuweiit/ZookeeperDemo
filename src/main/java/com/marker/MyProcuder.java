package com.marker;

import cn.wuweiit.domain.MessageBean;
import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2016/8/31.
 */
public class MyProcuder {


   static
   Producer producer;
    static {
    Map<String,Object> props = new HashMap();
    props.put("bootstrap.servers", "localhost:9092");

    //配置value的序列化类
   props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    //配置key的序列化类
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    //request.required.acks
    //0, which means that the producer never waits for an acknowledgement from the broker (the same behavior as 0.7). This option provides the lowest latency but the weakest durability guarantees (some data will be lost when a server fails).
    //1, which means that the producer gets an acknowledgement after the leader replica has received the data. This option provides better durability as the client waits until the server acknowledges the request as successful (only messages that were written to the now-dead leader but not yet replicated will be lost).
    //-1, which means that the producer gets an acknowledgement after all in-sync replicas have received the data. This option provides the best durability, we guarantee that no messages will be lost as long as at least one in sync replica remains.
    props.put("request.required.acks","-1");


      producer = new KafkaProducer ( props);
}

    void produce() {
        int messageNo = 1000;
        final int COUNT = 10000;

        while (messageNo < COUNT) {
            String key = String.valueOf(messageNo);
            String data = "hello3 kafka message " + key;

            producer.send(new ProducerRecord("chat",data));
            System.out.println(data);
            messageNo ++;
        }
    }


    public void send(String serverId,String name, String content){
        MessageBean msg = new MessageBean();
        msg.setContent(content);
        msg.setServerId(serverId);
        msg.setUsername(name);





        producer.send(new ProducerRecord("chat","123", JSON.toJSONString(msg)));
    }



    public static void main( String[] args )
    {
        new MyProcuder().produce();
    }
}
