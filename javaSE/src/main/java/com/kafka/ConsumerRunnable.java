package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class ConsumerRunnable implements Runnable {
    private ConsumerRecord record;
    public ConsumerRunnable(ConsumerRecord record){
        this.record = record;
    }
    @Override
    public void run() {

        System.out.println(record.toString());
    }
}
