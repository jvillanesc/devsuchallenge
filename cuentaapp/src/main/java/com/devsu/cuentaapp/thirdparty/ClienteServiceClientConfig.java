package com.devsu.cuentaapp.thirdparty;

import com.devsu.cuentaapp.exception.BussinesException;
import com.devsu.cuentaapp.exception.enums.BussinesErrorEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Indexed;
import reactivefeign.client.ReactiveHttpResponse;
import reactivefeign.client.statushandler.ReactiveStatusHandler;
import reactivefeign.client.statushandler.ReactiveStatusHandlers;
import java.util.function.BiFunction;

@Configuration
@ConfigurationProperties
@Indexed
@Data
@Slf4j
public class ClienteServiceClientConfig {

    @Bean
    public ReactiveStatusHandler reactiveStatusHandler() {
        ReactiveStatusHandler errorHandler =  ReactiveStatusHandlers.throwOnStatus(
                (status) -> (status == 409), errorFunction());
        return errorHandler;
    }

    private BiFunction<String, ReactiveHttpResponse, Throwable> errorFunction() {
        return (methodKey, response) -> {
            return new BussinesException(BussinesErrorEnum.CLIENTE_NO_EXISTE);
        };
    }
}