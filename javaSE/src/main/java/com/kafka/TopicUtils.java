package com.kafka;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;

import java.util.Arrays;
import java.util.Properties;

public class TopicUtils {
    public static void main(String[] args) {
        Arrays.asList(TopicConstant.COLIN,TopicConstant.TEST).forEach(topic ->{
            System.out.println(topic);
        });
    }
    public static void createTopic(String topic){
        ZkUtils zkUtils = ZkUtils.apply("127.0.0.1:2181",3000,3000, JaasUtils.isZkSecurityEnabled());
        AdminUtils.createTopic(zkUtils,"test",1,1,new Properties(),new RackAwareMode.Enforced$());
        zkUtils.close();
    }
}
