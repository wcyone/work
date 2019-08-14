package com.sccdrs.work.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author wcy
 * @date 2019/8/14 15:34
 * @Description: 消费者
 */
@Component
public class KafkaReceiver {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

    @KafkaListener(topics = {"wcy"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {

            Object message = kafkaMessage.get();

            log.info("----------------- record =" + record);
            log.info("------------------ message =" + message);
        }

    }
}
