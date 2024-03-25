package com.devsu.cuentaapp.util;

import com.devsu.cuentaapp.dto.ErrorDetailsDto;
import org.springframework.web.server.ServerWebExchange;

import java.util.Calendar;

public final class ErrorUtil {

    private ErrorUtil() {
        // Empty constructor.
    }

    public static ErrorDetailsDto getErrorDetailsVO(ServerWebExchange exchange,
                                                    String errorMessage,
                                                    String errorCode) {
        return ErrorDetailsDto.builder()
                .errorDate(Calendar.getInstance().getTime().toString())
                .requestedPath(exchange.getRequest().getPath().toString())
                .errorMessage(errorMessage)
                .errorCode(errorCode)
                .build();
    }

}
