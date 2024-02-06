package com.swr.petweb.repository;

import com.swr.petweb.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PostRepository extends CrudRepository<Post, Long>{

    @Override
    ArrayList<Post> findAll();
}
