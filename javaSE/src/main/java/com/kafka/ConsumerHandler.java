package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerHandler {
    private static final Logger log = LoggerFactory.getLogger(Producer.class);
    private static final String CONFIG_PATH = "src/test/kafkaConfig.properties";
    private KafkaConsumer<String,String> consumer = null;
    private static ConsumerHandler instance = new ConsumerHandler();
    private ExecutorService executorService = null;
    private ConsumerHandler() {
        initConsumer();
    }

    private void initConsumer(){
        InputStream in = ConsumerHandler.class.getClassLoader().getResourceAsStream(CONFIG_PATH);
        executorService = Executors.newFixedThreadPool(5);
        Properties properties = new Properties();
        try {
            properties.load(in);
            System.out.println(properties.toString());
            consumer = new KafkaConsumer<String, String>(properties);
            consumer.subscribe(Arrays.asList(TopicConstant.COLIN,TopicConstant.TEST));
            listen();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ConsumerHandler getInstance(){
        return instance;
    }
    public  void listen() {

        while (true) {
            Duration duration =Duration.ofMillis(1000);
            ConsumerRecords<String, String> records = consumer.poll(duration);
            records.forEach(record ->{
                executorService.execute(new ConsumerRunnable(record));
            });
        }
    }

    public static void main(String[] args) {

    }

}
