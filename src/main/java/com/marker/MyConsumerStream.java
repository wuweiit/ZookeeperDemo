package com.marker;

import cn.wuweiit.domain.MessageBean;
import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import websocket.ChatAnnotation;

import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by Administrator on 2016/8/31.
 */
public class MyConsumerStream extends Thread
{

    private static final KafkaStreams consumerStream;

    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        //zookeeper 配置
        props.put("zookeeper.connect", "localhost:2181");

        //group 代表一个消费组
        props.put("group.id", "jd-group");
        props.put("application.id", "1");

        //zk连接超时
        props.put("zookeeper.session.timeout.ms", "4000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        //序列化类

        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //配置key的序列化类
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


        KStreamBuilder builder = new KStreamBuilder();

        KStream source =   builder.stream("topic1");


        KTable<String, Long> counts = source
                .flatMapValues(new ValueMapper<String, Iterable<String>>() {
                    @Override
                    public Iterable<String> apply(String value) {
                        return Arrays.asList(value.toLowerCase(Locale.getDefault()).split(" "));
                    }
                }).map(new KeyValueMapper<String, String, KeyValue<String, String>>() {
                    @Override
                    public KeyValue<String, String> apply(String key, String value) {
                        return new KeyValue<>(value, value);
                    }
                })
                .countByKey("Counts");

        // need to override value serde to Long type
        counts.to(Serdes.String(), Serdes.Long(), "streams-wordcount-output");


        consumerStream = new KafkaStreams(builder, props);
    }




   public void run() {




       consumerStream.start();



    }

    public static void main(String[] args) {
        new MyConsumerStream().start();
    }
}
