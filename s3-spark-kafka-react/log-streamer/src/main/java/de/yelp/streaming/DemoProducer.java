package de.yelp.streaming;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class DemoProducer implements java.io.Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoProducer.class);

    protected KafkaProducer producer;

    public DemoProducer(String kafkaBootstrapServer) {
        Properties kafkaProperties = new Properties();
        kafkaProperties.put("bootstrap.servers", kafkaBootstrapServer);
        kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer(kafkaProperties);
    }

    public void produce(String topic, String message) {
        LOGGER.info("producing message="+message+" into topic="+topic);
        producer.send(new ProducerRecord(topic, message));
    }
}
