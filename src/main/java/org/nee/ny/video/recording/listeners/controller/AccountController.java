package org.nee.ny.video.recording.listeners.controller;

import lombok.extern.slf4j.Slf4j;
import org.nee.ny.video.recording.domain.web.UserAccountResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

/**
 * @Author: alec
 * Description:
 * @date: 08:25 2020-12-02
 */
@RestController
@RequestMapping
@Slf4j
public class AccountController {

    /**
     * 模拟用户登录
     * 后续实现
     * */
    @PostMapping(value = "login")
    public Mono login(String name, String password) {
        UserAccountResponse userAccountResponse = UserAccountResponse.getInitUserAccountResponse();
        log.info("登录用户 {} - {} : {}", name, password, userAccountResponse);
        return Mono.just(userAccountResponse);
    }

    @GetMapping(value = "routes")
    public Mono routes() {
        return Mono.just(new ArrayList());
    }
}
