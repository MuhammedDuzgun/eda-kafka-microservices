package com.demo.eda_order_service.kafka;

import com.demo.eda_base_domain.model.Order;
import com.demo.eda_base_domain.model.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    private NewTopic topic;
    private KafkaTemplate<String, Order> kafkaTemplate;

    public OrderProducer(NewTopic topic, KafkaTemplate<String, Order> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceOrderEvent(OrderEvent orderEvent) {
        LOGGER.info(String.format("Producing order event: %s", orderEvent));

        //create message
        Message message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }

}
