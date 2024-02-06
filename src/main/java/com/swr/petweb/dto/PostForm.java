package com.swr.petweb.dto;

import com.swr.petweb.entity.Post;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString

public class PostForm {

    private Long id;
    private String title;
    private String content;
    public Post toEntity() {
        return new Post(id, title, content);//DTO객체를 엔티티로 반환
    }

}
