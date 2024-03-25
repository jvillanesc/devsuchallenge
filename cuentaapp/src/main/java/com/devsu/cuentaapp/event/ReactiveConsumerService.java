package com.devsu.cuentaapp.event;

import com.devsu.cuentaapp.dto.OperacionDto;
import com.devsu.cuentaapp.service.CuentaService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
public class ReactiveConsumerService implements CommandLineRunner {
    Logger log = LoggerFactory.getLogger(ReactiveConsumerService.class);

    @Autowired
    CuentaService cuentaService;

    private final ReactiveKafkaConsumerTemplate<String, OperacionDto> reactiveKafkaConsumerTemplate;

    public ReactiveConsumerService(ReactiveKafkaConsumerTemplate<String, OperacionDto> reactiveKafkaConsumerTemplate) {
        this.reactiveKafkaConsumerTemplate = reactiveKafkaConsumerTemplate;
    }

    private Flux<OperacionDto> consumeFakeConsumerDTO() {
        return reactiveKafkaConsumerTemplate
                .receiveAutoAck()
                // .delayElements(Duration.ofSeconds(2L)) // BACKPRESSURE
                .doOnNext(consumerRecord -> log.info("received key={}, value={} from topic={}, offset={}",
                        consumerRecord.key(),
                        consumerRecord.value(),
                        consumerRecord.topic(),
                        consumerRecord.offset())
                )
                .map(ConsumerRecord::value)
                .flatMap(operacionDto -> cuentaService.operarCuentasCliente(operacionDto))
                .doOnNext(operacionDto -> log.info("successfully consumed {}={}", OperacionDto.class.getSimpleName(), operacionDto))
                .doOnError(throwable -> log.error("something bad happened while consuming : {}", throwable.getMessage()));
    }

    @Override
    public void run(String... args) {
        // we have to trigger consumption
        consumeFakeConsumerDTO().subscribe();
    }
}