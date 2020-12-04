package org.nee.ny.video.recording.domain.web;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: alec
 * Description:
 * @date: 08:29 2020-12-02
 */
@Setter
@Getter
@ToString
public class UserAccountResponse {

    private User user;

    private String token;

    private Date expireAt;

    private List permissions = new ArrayList();

    private List roles = new ArrayList();

    public static UserAccountResponse getInitUserAccountResponse() {
        UserAccountResponse userAccountResponse = new UserAccountResponse();
        userAccountResponse.setToken("Authorization:" + Math.random());
        userAccountResponse.setExpireAt( new Date(new Date().getTime() + 30 * 60 * 1000));
        userAccountResponse.setUser(new User("大圣"));
        return userAccountResponse;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    private static class User {
        private String name;
    }
}
