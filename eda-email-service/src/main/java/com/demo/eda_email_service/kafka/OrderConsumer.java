package com.demo.eda_email_service.kafka;

import com.demo.eda_base_domain.model.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumeOrderEvent(OrderEvent orderEvent) {
        LOGGER.info(String.format("Order event received: %s", orderEvent.toString()));

        //send email to customer
    }

}
