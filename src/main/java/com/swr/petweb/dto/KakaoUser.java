package com.swr.petweb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUser {
    private Long id;
    private String nickname;
    private String email;

    public KakaoUser(Long id, String nickname, String email){
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }
}
