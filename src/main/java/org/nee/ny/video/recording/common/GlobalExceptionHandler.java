package org.nee.ny.video.recording.common;

import lombok.extern.slf4j.Slf4j;
import org.nee.ny.video.recording.exception.NotFoundDeviceException;
import org.nee.ny.video.recording.exception.ParamsErrorException;
import org.nee.ny.video.recording.exception.ResponseErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

/**
 * @Author: alec
 * Description:
 * @date: 14:20 2020-11-18
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundDeviceException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public Mono<ResponseResult> handleNotFoundDeviceException(NotFoundDeviceException exception) {
        return Mono.just(ResponseResult.error(exception.getCode(),
                exception.getMessage()));
    }

    @ExceptionHandler(ParamsErrorException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public Mono<ResponseResult> handleParamsErrorException(ParamsErrorException exception) {
        return Mono.just(ResponseResult.error(exception.getCode(),
                exception.getMessage()));
    }

    @ExceptionHandler(ResponseErrorException.class)
    @ResponseStatus(code = HttpStatus.OK)
    public Mono<ResponseResult> handleResponseErrorException(ResponseErrorException exception) {
        return Mono.just(ResponseResult.error(exception.getCode(),
                exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ResponseResult> handleCustomException(Exception exception) {
        log.error("error ", exception);
        return Mono.just(ResponseResult.error(ResponseCodeCommon.SERVER_ERROR,
                exception.getMessage()));
    }
}
