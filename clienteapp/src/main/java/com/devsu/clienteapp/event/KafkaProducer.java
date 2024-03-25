package com.devsu.clienteapp.event;

import com.devsu.clienteapp.dto.OperacionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    public static final String TOPIC = "topico-operacion";

    private final KafkaTemplate<String, OperacionDto> kafkaTemplate;

    public void sendFlightEvent(OperacionDto operacionDto){
        String key = UUID.randomUUID().toString();
        kafkaTemplate.send(TOPIC, key , operacionDto);
        log.info("Producer produced the message {}", operacionDto);
        // write your handlers and post-processing logic, based on your use case
    }

}