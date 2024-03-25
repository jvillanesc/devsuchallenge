package com.devsu.cuentaapp.thirdparty;

import com.devsu.cuentaapp.thirdparty.client.ClienteResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;


@ReactiveFeignClient(
        name = "cliente-service",
        url = "${cliente-service.urls.base-url}",
        configuration = ClienteServiceClientConfig.class
)
public interface ClienteServiceClient {
    @GetMapping(value = "${cliente-service.urls.get-cliente-url}", headers = {"Content-Type=application/json"})
    Mono<ClienteResponse> obtenerCliente(@PathVariable("identificacion") String identificacion);

}
