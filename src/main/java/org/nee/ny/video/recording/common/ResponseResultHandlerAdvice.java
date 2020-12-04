package org.nee.ny.video.recording.common;

import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * @Author: alec
 * Description:
 * @date: 18:03 2020-11-12
 */
@Configuration
@ConditionalOnClass(name = {"org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler"})
@Aspect
public class ResponseResultHandlerAdvice   {

    @SneakyThrows
    @Around(value = "execution(* org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler.handleResult(..)) && args(exchange, result)", argNames = "point,exchange,result")
    public Object handleResult(ProceedingJoinPoint point, ServerWebExchange exchange, HandlerResult result) {
        final Mono responseMono = ((Mono) result.getReturnValue()).map(responseValue -> responseValue instanceof ResponseResult ? responseValue : ResponseResult.ok(responseValue));
        return point.proceed(Arrays.asList(
                exchange,
                new HandlerResult(result.getHandler(), responseMono, result.getReturnTypeSource())
        ).toArray());
    }
}
