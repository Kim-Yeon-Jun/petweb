package com.swr.petweb.controller;


import lombok.extern.slf4j.Slf4j;
import com.swr.petweb.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.swr.petweb.dto.PostForm;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.swr.petweb.entity.Post;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;


@Slf4j // 로깅 기능을 위한 어노테이션 추가
@Controller
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/post/new")
    public String newPostForm() {return "post/new";}

    @PostMapping("/post/create")
    public String createPost(@RequestBody PostForm form, RedirectAttributes redirectAttributes){
        log.info(form.toString());

        // DTO -> Entity
        Post post = form.toEntity();
        log.info(post.toString());

        // DB에 저장
        Post savedPost = postRepository.save(post);
        log.info(savedPost.toString());

        // 리다이렉트할 때 포스트 ID를 전달하여 상세 페이지로 이동
//        redirectAttributes.addAttribute("id", savedPost.getId());

        // index 페이지로 리다이렉트
        return "redirect:/post";
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> show(@PathVariable Long id) {
        // id를 조회해 데이터 가져오기
        Post postEntity = postRepository.findById(id).orElse(null);

        // 조회된 데이터를 JSON 형식으로 반환
        if (postEntity != null) {
            return new ResponseEntity<>(postEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/post")
    public String index(Model model){
        //1.모든 데이터 가져오기
        ArrayList<Post> articleEntityList = postRepository.findAll();
        //2. 모델에 데이터 등록
        model.addAttribute("postList", articleEntityList);
        //3. 뷰 페이지 설정
        return "post/index";
    }

    @GetMapping("/post/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터 가져오기
        Post postEntity = postRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("post", postEntity);
        //뷰 페이지 설정하기
        return "post/edit";
    }

    @PostMapping("post/update")
    public String update(PostForm form){
        log.info(form.toString());
        //1. DTO를 엔티티로 변환
        Post postEntity = form.toEntity();
        log.info(postEntity.toString());
        //2. 엔티티를 DB에 저장
        //2-1. DB에서 기존 데이터 가져오기
        Post target = postRepository.findById(postEntity.getId()).orElse(null);
        //2-2. 기존 데이터 값을 갱신하기
        if(target!=null){
            postRepository.save(postEntity); //엔티티를 DB에 저장(갱신)
        }
        //3. 수정 결과 페이지로 리다이렉트
        return "redirect:/post/"+postEntity.getId();
    }

    @GetMapping("/post/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다.");
        //1. 삭제할 대상 불러오기
        Post target = postRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 대상 엔티티 삭제하기
        if(target!=null){
            postRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다.");
        }
        //3. 결과 페이지로 리다이렉트하기
        return "redirect:/post";
    }


}
