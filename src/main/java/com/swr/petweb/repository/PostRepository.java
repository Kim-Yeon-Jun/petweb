package com.swr.petweb.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import com.swr.petweb.entity.Post;
public interface PostRepository extends CrudRepository<Post, Long>{

    @Override
    ArrayList<Post> findAll();
}
