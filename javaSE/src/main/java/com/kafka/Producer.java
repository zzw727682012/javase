package com.kafka;

import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.Future;


public class Producer {
    private static final Logger log = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "test";
    private static final String BROKER_LIST = "127.0.0.1:9092";
    private static KafkaProducer<String, String> producer = null;

    static {
        Properties configs = initConfig();
        producer = new KafkaProducer<String, String>(configs);
    }

    private static Properties initConfig() {
        Properties props = new Properties();
        props.put("acks", "0");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("bootstrap.servers", BROKER_LIST);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    public static void main(String[] args) throws InterruptedException {

        try {
            //消息实体
            ProducerRecord<String, String> record = null;
            for (int i = 0; i < 10; i++) {
                record = new ProducerRecord<String, String>(TOPIC, 0, "123", "value" + (int) (10 * (Math.random())));
                //发送消息
                System.out.println(record.value());
                Future<RecordMetadata> future = producer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if (null != e) {
                            log.info("send error" + e.getMessage());
                        } else {
                            System.out.println(String.format("offset:%s,partition:%s", recordMetadata.offset(), recordMetadata.partition()));
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        producer.close();
    }

}
